package com.sandao.service.impl;

import com.sandao.dao.ChatGroupDao;
import com.sandao.dao.UserDao;
import com.sandao.dao.UserGroupMappingDao;
import com.sandao.entity.ChatGroup;
import com.sandao.entity.User;
import com.sandao.entity.UserGroupMapping;
import com.sandao.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 *
 * @author
 */
@Service
public class ChatServiceImpl implements ChatService {

    private ChatGroupDao chatGroupDao;
    private UserGroupMappingDao userGroupMappingDao;
    private UserDao userDao;

    @Autowired
    public void setChatGroupDao(ChatGroupDao chatGroupDao) {
        this.chatGroupDao = chatGroupDao;
    }

    @Autowired
    public void setUserGroupMappingDao(UserGroupMappingDao userGroupMappingDao) {
        this.userGroupMappingDao = userGroupMappingDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 新建群聊，设置群主，群名
     * 群主和群建立关系
     * @param chatGroupName
     * @param userId
     */
    @Override
    public void createChatGroup(String chatGroupName, int userId) {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setChatGroupName(chatGroupName);
        chatGroup.setCreateDate(new Date());
        chatGroup.setOwnerId(userId);
        chatGroup.setGroupType(1);
        chatGroupDao.insertSelective(chatGroup);

        int chatGroupId = getChatGroupByChatGroupName(chatGroupName).getChatGroupId();
        addUserToGroup(userId,chatGroupId);
    }

    @Override
    public void deleteChatGroup(ChatGroup chatGroup) {
        chatGroup.setGroupType(0);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }

    @Override
    public List<User> getUsersByChatGroupId(int chatGroupId) {
        List<UserGroupMapping> userGroupMappings = userGroupMappingDao.getAllMappingByChatGroupId(chatGroupId);
        LinkedList<User> users = new LinkedList<>();
        userGroupMappings.forEach(userGroupMapping -> {
            if (userGroupMapping.getMappingStat() == 1) {
                users.add(userDao.selectByPrimaryKey(userGroupMapping.getUserId()));
            }
        });
        return users;
    }

    @Override
    public ChatGroup getChatGroupByChatGroupId(int chatGroupId) {
        return chatGroupDao.selectByPrimaryKey(chatGroupId);
    }

    @Override
    public void quitChatGroup(int userId, int chatGroupId) {
        UserGroupMapping userGroupMapping = userGroupMappingDao.getUserGroupMappingById(userId, chatGroupId);
        userGroupMapping.setMappingStat(0);
        userGroupMappingDao.updateByPrimaryKeySelective(userGroupMapping);
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        if (userId == chatGroup.getOwnerId()) {
            List<User> users = getUsersByChatGroupId(chatGroupId);
            //最后一个用户，删除群
            if (users.get(1) == null) {
                deleteChatGroup(chatGroup);
            } else {
                //还有别的用户，群主顺延
                int newOwnerId = users.get(0).getUserId();
                editOwner(chatGroupId, newOwnerId);
            }
        }
    }

    @Override
    public void sendMessageToUser(int userId, TextMessage message) {
//        SystemWebSocketHandler handler = new SystemWebSocketHandler();
//        handler.sendMessageToUser(userId, message);
    }

    @Override
    public int sendMessageToGroup(int chatGroupId, TextMessage message) {
        List<User> users = getUsersByChatGroupId(chatGroupId);
        try {
            for (User user : users) {
                sendMessageToUser(user.getUserId(), message);
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void addUserToGroup(int userId, int chatGroupId) {
        UserGroupMapping userGroupMapping = new UserGroupMapping();
        userGroupMapping.setChatGroupId(chatGroupId);
        userGroupMapping.setCreateDate(new Date());
        userGroupMapping.setMappingStat(1);
        userGroupMapping.setUserId(userId);
        userGroupMappingDao.insertSelective(userGroupMapping);
    }

    @Override
    public ChatGroup getChatGroupByChatGroupName(String chatGroupName) {
        return chatGroupDao.getChatGroupByGroupName(chatGroupName);
    }

    @Override
    public int getChatGroupIdByChatGroupName(String chatGroupName) {
        return getChatGroupByChatGroupName(chatGroupName).getChatGroupId();
    }

    @Override
    public void editChatGroupName(int chatGroupId, String newName) {
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        chatGroup.setChatGroupName(newName);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }

    @Override
    public void editOwner(int chatGroupId, int newOwnerId) {
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        chatGroup.setOwnerId(newOwnerId);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }

    @Override
    public List<ChatGroup> getAllChatGroup() {
        return chatGroupDao.getAll();
    }
}
