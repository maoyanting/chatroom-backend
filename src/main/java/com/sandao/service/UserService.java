package com.sandao.service;

import com.sandao.entity.ChatGroup;
import com.sandao.entity.SimpleUser;
import com.sandao.entity.User;
import com.sandao.entity.UserMapping;

import java.util.List;

/**
 * @author sandoa
 * Created by maoyanting on 2017/11/16.
 */
public interface UserService {
    /**
     *
     * @param userName
     * @param nickname
     * @param password
     */
    User register(String userName,String password,int userType);

    /**
     * 游客登录
     * @param sessionId
     * @return
     */
    User visitorRegister(String sessionId);
    /**
     * 更新用户资料
     * @param user
     */
    void update(User user);

    /**
     * 修改密码
     * @param userName
     * @param password
     */
    void editPassword(String userName,String password);

    /**
     * 根据userId加载User对象
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据用户名获取User，获取个人信息等
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 根据用户名获取SimpleUser，用于用户登录
     * @param userName
     * @return
     */
    SimpleUser getSimpleUserByUserName(String userName);


    /**
     * 根据用户id获取群列表（游客有）
     * @param userId
     * @return
     */
    List<ChatGroup> getUserChatGroup(int userId);

    /**
     * 根据用户id获取所有好友
     * @param userId
     * @return
     */
    List<User> getUserFriends(int userId);


    /**
     * 删除好友
     * @param friendId
     */
    void deleteFriend(int userId,int friendId);

    /**
     * 根据用户名模糊查找查找用户
     * @param userName
     * @return
     */
    List<User> getUsersByUserName(String userName);

    /**
     * 修改用户状态
     * @param user
     * @param simpleUser
     */
    void setFormalUser(User user,SimpleUser simpleUser);

    /**
     * 获取所有用户
     * @return 所有用户
     */
    List<User> getAllUsers();

    /**
     * 查询这个用户是不是我的好友
     * @param myId
     * @param friendId
     * @return
     */
    boolean isFriend(int myId,int friendId);

    /**
     * 查询我和这个用户的好友关系
     * @param myId
     * @param friendId
     * @return
     */
    UserMapping getUserMapping(int myId,int friendId);
}
