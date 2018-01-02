package com.sandao.service.impl;

import com.sandao.util.RandomName;
import com.sandao.dao.*;
import com.sandao.entity.*;
import com.sandao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.sandao.util.CommonConstant.*;

/**
 * Created by maoyanting on 2017/11/21.
 *
 * @author sandao
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private SimpleUserDao simpleUserDao;
    private ChatGroupDao chatGroupDao;
    private UserMappingDao userMappingDao;
    private UserGroupMappingDao userGroupMappingDao;
    private FriendRequestDao friendRequestDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setSimpleUserDao(SimpleUserDao simpleUserDao) {
        this.simpleUserDao = simpleUserDao;
    }

    @Autowired
    public void setChatGroupDao(ChatGroupDao chatGroupDao) {
        this.chatGroupDao = chatGroupDao;
    }

    @Autowired
    public void setUserMappingDao(UserMappingDao userMappingDao) {
        this.userMappingDao = userMappingDao;
    }

    @Autowired
    public void setUserGroupMappingDao(UserGroupMappingDao userGroupMappingDao) {
        this.userGroupMappingDao = userGroupMappingDao;
    }

    @Autowired
    public void setFriendRequestDao(FriendRequestDao friendRequestDao) {
        this.friendRequestDao = friendRequestDao;
    }

    @Override
    public User visitorRegister(String sessionId) {
        String password = "123456";
        return register(sessionId, password, 0);
    }

    @Override
    public User register(String userName, String password, int userType) {
        User user = new User();
        RandomName randomName = new RandomName();
        String nickname = randomName.getRandomName();
        user.setUserName(userName);
        user.setUserNickname(nickname);
        user.setIntroduction("dd");
        user.setHeadshot("ss");
        user.setCreateDate(new Date());
        user.setUserType(userType);
        userDao.insertSelective(user);
        SimpleUser simpleUser = new SimpleUser();
        User a = userDao.getUserByUserName(userName);
        simpleUser.setUserId(a.getUserId());
        simpleUser.setUserName(userName);
        simpleUser.setCreateDate(a.getCreateDate());
        simpleUser.setPassword(password);
        simpleUserDao.insertSelective(simpleUser);
        return a;
    }


    @Override
    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }


    @Override
    public void editPassword(String userName, String password) {
        SimpleUser simpleUser = getSimpleUserByUserName(userName);
        simpleUser.setPassword(password);
        simpleUserDao.updateByPrimaryKeySelective(simpleUser);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public SimpleUser getSimpleUserByUserName(String userName) {
        return simpleUserDao.getSimpleUserBySimpleUserName(userName);
    }

    @Override
    public List<ChatGroup> getUserChatGroup(int userId) {
        List<UserGroupMapping> userGroupMappings = userGroupMappingDao.getAllMappingByUserId(userId);
        LinkedList<ChatGroup> chatGroups = new LinkedList<>();
        userGroupMappings.forEach(userGroupMapping -> {
            int chatGroupId = userGroupMapping.getChatGroupId();
            ChatGroup chatGroup =chatGroupDao.selectByPrimaryKey(chatGroupId);
            chatGroups.add(chatGroup);
        });
        return chatGroups;
    }

    @Override
    public List<User> getUserFriends(int userId) {
        List<UserMapping> userMappings = userMappingDao.getAllFriendsMapping(userId);
        LinkedList<User> users = new LinkedList<>();
        userMappings.forEach(userMapping -> {
            int userAId = userMapping.getUserAId();
            int userBId = userMapping.getUserBId();
            int mappingType = userMapping.getMappingType();
            if (mappingType == USER_MAPPING_IS_FRIEND){
                if (userAId == userId) {
                    users.add(userDao.selectByPrimaryKey(userBId));
                } else {
                    users.add(userDao.selectByPrimaryKey(userAId));
                }
            }
        });
        return users;
    }



    @Override
    public void deleteFriend(int userId, int friendId) {
        UserMapping userMapping = getUserMapping(userId, friendId);
        userMapping.setMappingType(USER_MAPPING_NOT_FRIEND);
        userMappingDao.updateByPrimaryKeySelective(userMapping);
    }

    @Override
    public UserMapping getUserMapping(int myId, int friendId) {
        UserMapping userMapping;
        if (myId < friendId) {
            userMapping = userMappingDao.getFriendMappingById(myId, friendId);
        } else {
            userMapping = userMappingDao.getFriendMappingById(friendId, myId);
        }
        return userMapping;
    }

    @Override
    public boolean isFriend(int myId, int friendId) {
        UserMapping userMapping = getUserMapping(myId,friendId);
        return (userMapping != null);
    }

    @Override
    public List<User> getUsersByUserName(String userName) {
        return userDao.getUsersByUserName(userName);
    }

    @Override
    public void setFormalUser(User user, SimpleUser simpleUser) {
        user.setUserType(1);
        userDao.updateByPrimaryKey(user);
        simpleUserDao.updateByPrimaryKey(simpleUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

}
