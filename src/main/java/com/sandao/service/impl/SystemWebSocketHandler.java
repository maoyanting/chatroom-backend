package com.sandao.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sandao.util.CommonConstant;
import com.sandao.dao.UserDao;
import com.sandao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

import static com.sandao.util.CommonConstant.*;

/**
 * Created by maoyanting on 2017/11/28.
 *
 * @author sandao
 *         注解到底用哪个？
 */
@Service
public class SystemWebSocketHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    private UserDao userDao;
    private ChatServiceImpl chatService;
    private NoticeServiceImpl noticeService;

    @Autowired
    public void setNoticeService(NoticeServiceImpl noticeService) {
        this.noticeService = noticeService;
    }
    @Autowired
    public void setChatService(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 放置用户id和用户session
     */
    private static Map<Integer, Set<WebSocketSession>> webSocketSessionMap = new HashMap<>();

    /**
     * A前端进入chat页面，var webSocket，调用.onOpen
     * 后端WebSocket连接建立后，将用户ID和WebSocketSession对象的映射保存到MAP
     * 两者建立TCP链接
     *
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("-----------webSocket is connect----------");
        int userId = (Integer) webSocketSession.getAttributes().get(WEBSOCKET_USERID);
        if (webSocketSessionMap.containsKey(userId)) {
            webSocketSessionMap.get(userId).add(webSocketSession);
        } else {
            Set<WebSocketSession> addUserSet = new HashSet<>();
            addUserSet.add(webSocketSession);
            webSocketSessionMap.put(userId, addUserSet);
        }
        System.out.println("用户id为：" + userId + "的webSocket链接成功");
    }

    /**
     * 接收到WebSocket消息后的处理方法
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String messageJson = webSocketMessage.getPayload().toString();
        JSONObject jsonObject = JSON.parseObject(messageJson);

        int messageType = (int) jsonObject.get("messageType");

        if (messageType == MESSAGE_TYPE_PERSONAL) {
            /* 私聊信息 */
            int userToId = (int) jsonObject.get("userToId");
            int userFromId = (int) jsonObject.get("userFromId");
            String message = (String) jsonObject.get("message");
            logger.info("personal chat");
            logger.info(userFromId + "send to" + userToId + ":" + message);
            sendMessageToUser(userToId, (TextMessage) webSocketMessage);
        } else if (messageType == MESSAGE_TYPE_FRIEND_REQUEST){
            /* 发送的好友请求 */
            logger.info("friend request");
            /* 获取传过来的好友请求信息 */
            int initiatorId = (int)jsonObject.get("initiatorId");
            int acceptorId = (int)jsonObject.get("acceptorId");
            /* 新建并入库一个好友请求 */
            noticeService.addFriendRequest(initiatorId,acceptorId);
            /* 把好友请求信息发给接收方 */
            sendMessageToUser(acceptorId, (TextMessage) webSocketMessage);
        }else if(messageType == MESSAGE_TYPE_FRIEND_REQUEST_REPLY){
            /* 回复的好友请求 */
            logger.info("friend request reply");
            logger.info(messageJson);
            int initiatorId = (int)jsonObject.get("initiatorId");
            int acceptorId = (int)jsonObject.get("acceptorId");
            int conditionNumber = (int)jsonObject.get("conditionNumber");
            /* 判断 */
            if (conditionNumber == FRIEND_REQUEST_REPLY_AGREE){
                /* 同意，建立好友关系，发送此好友请求回好友请求的发送方 */
                noticeService.agreeFriendRequest(initiatorId,acceptorId);
                sendMessageToUser(initiatorId, (TextMessage) webSocketMessage);
            }else {
                sendMessageToUser(initiatorId, (TextMessage) webSocketMessage);
            }
        }else if(messageType == MESSAGE_TYPE_CHAT_GROUP){
            logger.info("group chat");
            /* 群聊信息 */
            int chatGroupId = (int) jsonObject.get("chatGroupId");
            int userFromId = (int) jsonObject.get("userFromId");
            String message = (String) jsonObject.get("message");
            logger.info(userFromId + "send to" + chatGroupId + ":" + message);
            sendMessageToGroup(chatGroupId, (TextMessage) webSocketMessage);
        }
    }

    /**
     * WebSocket传输发生错误时的处理方法
     *
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.info("something wrong");
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        for (int userId : webSocketSessionMap.keySet()) {
            if (webSocketSessionMap.get(userId).equals(webSocketSession)) {
                webSocketSessionMap.remove(userId, webSocketSession);
            }
        }
    }

    /**
     * WebSocket连接关闭后的回调方法:从MAP中删除对应WebSocketSession
     *
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        //移除当前用户终端登录的websocket信息,如果该用户的所有终端都下线了，则删除该用户的记录
        for (int userId : webSocketSessionMap.keySet()) {
            //获取终端
            Set<WebSocketSession> webSocketSessionSet = webSocketSessionMap.get(userId);
            //和当前终端session对比
            for (WebSocketSession webSocketSessionOnline : webSocketSessionSet) {
                if (webSocketSessionOnline == webSocketSession) {
                    webSocketSessionSet.remove(webSocketSession);
                }
            }
            //没有终端存在了
            if (webSocketSessionSet.isEmpty()) {
                webSocketSessionMap.remove(userId);
            }
        }
        logger.info("WebSocket is closed");
    }

    /**
     * 是否处理WebSocket分段消息，暂时不管
     *
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发信息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(int userId, TextMessage message) {
        Set<WebSocketSession> users = webSocketSessionMap.get(userId);
        if (users != null) {
            logger.info("user is online");
            try {
                for (WebSocketSession user : users) {
                    if (user != null && user.isOpen()) {
                        // 找到B在后端对应的webSocketSession，发送消息，B前端接收
                        // B前端socket.onMessage被调用，用于显示消息。
                        user.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("user is not online");
        }
    }

    /**
     * 群聊发送
     * @param chatGroupId
     * @param message
     */
    public void sendMessageToGroup(int chatGroupId, TextMessage message) {
        //获取所有的群用户
        try {
            List<User> users = chatService.getUsersByChatGroupId(chatGroupId);
            for (User user : users) {
                //遍历群用户
                int userId = user.getUserId();
                //获取对应用户的session
                Set<WebSocketSession> usersSession = webSocketSessionMap.get(userId);
                for (WebSocketSession userSession : usersSession) {
                    //session存在
                    if (userSession != null && userSession.isOpen()) {
                        // 发送消息，B前端接收
                        // B前端socket.onMessage被调用，用于显示消息。
                        userSession.sendMessage(message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
