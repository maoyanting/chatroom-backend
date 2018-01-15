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

    /**
     * 删除群聊（在群主退群的情况下）
     * @param chatGroup
     */
    @Override
    public void deleteChatGroup(ChatGroup chatGroup) {
        chatGroup.setGroupType(0);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }

    /**
     * 根据群id获取群内的用户
     * @param chatGroupId
     * @return
     */
    @Override
    public List<User> getUsersByChatGroupId(int chatGroupId) {
        List<UserGroupMapping> userGroupMappings = userGroupMappingDao.getAllMappingByChatGroupId(chatGroupId);
        LinkedList<User> users = new LinkedList<>();
        userGroupMappings.forEach(userGroupMapping -> {
            if (userGroupMapping.getMappingStat() == 1) {
                int userId = userGroupMapping.getUserId();
                users.add(userDao.selectByPrimaryKey(userId));
            }
        });
        return users;
    }

    /**
     * 根据群id获取群
     * @param chatGroupId
     * @return
     */
    @Override
    public ChatGroup getChatGroupByChatGroupId(int chatGroupId) {
        return chatGroupDao.selectByPrimaryKey(chatGroupId);
    }
    /**
     * 退出群聊
     * 先获取并修改userGroupMapping，保存
     * 再获取这个chatGroup，判断user是否是群主，是（群主顺延，无法顺延则删除群），否（无操作）
     * @param userId
     * @param chatGroupId
     */
    @Override
    public void quitChatGroup(int userId, int chatGroupId) {
        UserGroupMapping userGroupMapping = userGroupMappingDao.getUserGroupMappingById(userId, chatGroupId);
        userGroupMapping.setMappingStat(0);
        userGroupMappingDao.updateByPrimaryKeySelective(userGroupMapping);
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        if (userId == chatGroup.getOwnerId()) {
            List<User> users = getUsersByChatGroupId(chatGroupId);
            //没有剩下的用户了，删除群
            if (users.size() ==0) {
                deleteChatGroup(chatGroup);
            } else {
                //还有别的用户，群主顺延
                int newOwnerId = users.get(0).getUserId();
                editOwner(chatGroupId, newOwnerId);
            }
        }
    }
    /**
     * 把用户添加进群
     * @param userId
     * @param chatGroupId
     */
    @Override
    public void addUserToGroup(int userId, int chatGroupId) {
        UserGroupMapping userGroupMapping = new UserGroupMapping();
        userGroupMapping.setChatGroupId(chatGroupId);
        userGroupMapping.setCreateDate(new Date());
        userGroupMapping.setMappingStat(1);
        userGroupMapping.setUserId(userId);
        userGroupMappingDao.insertSelective(userGroupMapping);
    }

    /**
     * 判断用户是否在群里面
     * @param userId
     * @param chatGroupId
     */
    @Override
    public boolean userIsInGroup(int userId, int chatGroupId) {
        UserGroupMapping userGroupMapping = userGroupMappingDao.getUserGroupMappingById(userId, chatGroupId);
        if (userGroupMapping != null){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 根据群名获取群
     * @param chatGroupName
     * @return
     */
    @Override
    public ChatGroup getChatGroupByChatGroupName(String chatGroupName) {
        return chatGroupDao.getChatGroupByGroupName(chatGroupName);
    }
    /**
     * 根据群名获取群id
     * @param chatGroupName
     * @return
     */
    @Override
    public int getChatGroupIdByChatGroupName(String chatGroupName) {
        return getChatGroupByChatGroupName(chatGroupName).getChatGroupId();
    }
    /**
     * 修改群名（要求是群主）
     * @param chatGroupId
     * @param newName
     */
    @Override
    public void editChatGroupName(int chatGroupId, String newName) {
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        chatGroup.setChatGroupName(newName);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }
    /**
     * 修改群主
     * @param chatGroupId
     * @param newOwnerId
     */
    @Override
    public void editOwner(int chatGroupId, int newOwnerId) {
        ChatGroup chatGroup = chatGroupDao.selectByPrimaryKey(chatGroupId);
        chatGroup.setOwnerId(newOwnerId);
        chatGroupDao.updateByPrimaryKeySelective(chatGroup);
    }
    /**
     * 获取所有的群
     * @return
     */
    @Override
    public List<ChatGroup> getAllChatGroup() {
        return chatGroupDao.getAll();
    }
}
