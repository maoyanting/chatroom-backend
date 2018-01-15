package com.sandao.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sandao.entity.SimpleUser;
import com.sandao.entity.UserGroupMapping;
import com.sandao.util.JsonDemo;
import com.sandao.entity.ChatGroup;
import com.sandao.entity.User;
import com.sandao.service.impl.ChatServiceImpl;
import com.sandao.service.impl.UserServiceImpl;
import com.sandao.util.RespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by maoyanting on 2017/11/23.
 *
 * @author sandao
 */
@Controller
public class ChatGroupController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ChatGroupController.class);

    private UserServiceImpl userService;
    private ChatServiceImpl chatService;
    JsonDemo jsonDemo;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setChatService(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    /**
     * 新建群聊
     * @param chatGroup 获取群名和群主id
     * @param response
     */
    @RequestMapping(value = "/createChatGroup")
    public void createNewChatGroup(@RequestBody ChatGroup chatGroup,
                                   HttpServletResponse response) {
        try {
            int userId = chatGroup.getOwnerId();
            String chatGroupName = chatGroup.getChatGroupName();
            chatService.createChatGroup(chatGroupName, userId);
            ChatGroup newChatGroup = chatService.getChatGroupByChatGroupName(chatGroupName);
            jsonDemo = new JsonDemo(newChatGroup, 1, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonDemo = new JsonDemo(null, 0, "创建群聊失败");
        }
        logger.info("---------create chat group----------");
        logger.info(JSON.toJSONString(jsonDemo));
        RespUtils.writeJson(response,jsonDemo);
    }

    /**
     * 获取群列表
     * @param user
     * @param response
     */
    @RequestMapping(value = "/chatGroupList", method = RequestMethod.POST)
    public void getChatGroupList(@RequestBody SimpleUser user,HttpServletRequest request,
                                 HttpServletResponse response) {
        User userIn = getSessionUser(request);
        int userId = userIn.getUserId();
        List<ChatGroup> myChatGroups = userService.getUserChatGroup(userId);
        if (myChatGroups.size() == 0) {
            jsonDemo = new JsonDemo(null, 0, "没有群");
        } else {
            jsonDemo = new JsonDemo(myChatGroups, 1, "获取群列表成功");
        }
        logger.info("------------get chat group list-------------");
        logger.info(JSON.toJSONString(jsonDemo));
        RespUtils.writeJson(response,jsonDemo);
    }

    /**
     * 查询群资料：群名，群主
     *
     * @param chatGroupId
     * @return
     */
    @RequestMapping(value = "/chatGroupInformation")
    public JSONObject getChatGroupInformation(@RequestParam(value = "chatGroupId") Integer chatGroupId) {
        ChatGroup chatGroup = chatService.getChatGroupByChatGroupId(chatGroupId);
        JSONObject chatGroupInformation = new JSONObject();
        if (chatGroup == null) {
            jsonDemo = new JsonDemo(null, 0, "获取失败");
        } else {
            jsonDemo = new JsonDemo(chatGroup, 1, "ok");
        }
        return chatGroupInformation;
    }

    /**
     * 获取群用户
     *
     * @param chatGroupId
     * @return
     */
    @RequestMapping(value = "/chatGroupUsers")
    public String getUsers(@RequestParam(value = "chatGroupId") Integer chatGroupId) {
        try {
            List<User> users = chatService.getUsersByChatGroupId(chatGroupId);
            if (users == null) {
                jsonDemo = new JsonDemo(null, 1, "群内没有用户");
            } else {
                jsonDemo = new JsonDemo(users, 1, "ok");
            }
        } catch (Exception e) {
            jsonDemo = new JsonDemo(null, 0, "wrong");
        }
        return JSON.toJSONString(jsonDemo);
    }

    /**
     * 修改群名
     *
     * @param chatGroupName
     * @param chatGroupId
     * @return
     */
    @RequestMapping(value = "/chatGroupInformation/updateChatGroupName")
    public String updateChatGroupName(@RequestParam(value = "chatGroupName") String chatGroupName,
                                      @RequestParam(value = "chatGroupId") Integer chatGroupId) {
        chatService.editChatGroupName(chatGroupId, chatGroupName);
        try {
            ChatGroup chatGroup = chatService.getChatGroupByChatGroupId(chatGroupId);
            jsonDemo = new JsonDemo(chatGroup, 1, "群名修改成功");
        } catch (Exception e) {
            jsonDemo = new JsonDemo(null, 0, "群名修改失败");
        }
        return JSON.toJSONString(jsonDemo);
    }

    /**
     * 用户主动退群。
     * @param userGroupMapping 用户和群的关系
     * @param response
     */
    @RequestMapping(value = "/quitChatGroup")
    public void quitChatGroup(@RequestBody UserGroupMapping userGroupMapping,HttpServletResponse response) {
        try {
            int userId = userGroupMapping.getUserId();
            int chatGroupId = userGroupMapping.getChatGroupId();
            chatService.quitChatGroup(userId, chatGroupId);
            jsonDemo = new JsonDemo(null, 1, "用户主动退群成功");
            logger.info("--------------quit chat group success--------------");
        } catch (Exception e) {
            logger.error("--------------quit chat group wrong--------------");
            jsonDemo = new JsonDemo(null, 0, "用户主动退群失败");
        }
        RespUtils.writeJson(response,jsonDemo);
    }
    @RequestMapping(value = "/joinChatGroup")
    public void joinChatGroup(@RequestBody UserGroupMapping userGroupMapping,HttpServletRequest request,HttpServletResponse response) {
        try {
            User user = getSessionUser(request);
            int userId = user.getUserId();
            int chatGroupId = userGroupMapping.getChatGroupId();
            if(chatService.userIsInGroup(userId,chatGroupId)){
                jsonDemo = new JsonDemo(null, 2, "用户已经是这个群的成员");
                logger.info("--------------join chat group wrong--------------");
            }else {
                chatService.addUserToGroup(userId,chatGroupId);
                jsonDemo = new JsonDemo(null, 1, "用户加入群成功");
                logger.info("--------------join chat group success--------------");
            }
        } catch (Exception e) {
            logger.error("--------------join chat group wrong--------------");
            jsonDemo = new JsonDemo(null, 0, "用户加入群失败");
        }
        RespUtils.writeJson(response,jsonDemo);
    }
}
