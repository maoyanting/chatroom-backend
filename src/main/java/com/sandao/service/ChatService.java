package com.sandao.service;

import com.sandao.entity.ChatGroup;
import com.sandao.entity.User;
import org.springframework.web.socket.TextMessage;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 */
public interface ChatService {
    /**
     * 新建群聊吧
     * @param chatGroupName
     * @param userId
     */
    void createChatGroup(String chatGroupName, int userId);
    /**
     * 注销群聊（群内没有User了）
     * @param chatGroup
     */
    void deleteChatGroup(ChatGroup chatGroup);

    /**
     * 根据id获取群资料
     * @param chatGroupId
     * @return
     */
    ChatGroup getChatGroupByChatGroupId(int chatGroupId);

    /**
     * 获取群的所有用户
     * @param chatGroupId
     * @return
     */
    List<User> getUsersByChatGroupId(int chatGroupId);

    /**
     * 退出群聊
     * @param userId
     * @param chatGroupId
     */
    void quitChatGroup(int userId,int chatGroupId);

    /**
     * 添加新用户进群
     * @param userId
     * @param chatGroupId
     */
    void addUserToGroup(int userId,int chatGroupId) ;
    /**
     * 判断用户是否已经在群里
     * @param userId
     * @param chatGroupId
     */
    boolean userIsInGroup(int userId,int chatGroupId);
    /**
     * 根据群名获取群
     * @param chatGroupName
     * @return
     */
    ChatGroup getChatGroupByChatGroupName(String chatGroupName);

    int getChatGroupIdByChatGroupName(String chatGroupName);

    /**
     * 修改群名
     * @param chatGroupId
     * @param newName
     */
    void editChatGroupName(int chatGroupId,String newName);

    /**
     * 修改群主（群主退出群或者群主为游客且session结束，群主顺延到下一个用户）
     * @param chatGroupId
     * @param newOwnerId
     */
    void editOwner(int chatGroupId,int newOwnerId);
    /**
     * 获取所有群
     * @return
     */
    List<ChatGroup> getAllChatGroup();



}
