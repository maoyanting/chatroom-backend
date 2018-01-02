package com.sandao.service.impl;

import com.alibaba.fastjson.JSON;
import com.sandao.BaseTest;
import com.sandao.util.JsonDemo;
import com.sandao.entity.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 30, 2017</pre>
 */

public class UserServiceImplTest extends BaseTest {

    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: visitorRegister(String sessionId)
     */
    @Test
    public void testVisitorRegister() throws Exception {
        String sessionId = "1232222444";
        User user = userService.visitorRegister(sessionId);
        System.out.println(user);
    }

    /**
     * Method: register(String userName, String nickname, String password)
     */
    @Test
    public void testRegister() throws Exception {
        String userName = "1111";
        String nickname = "三刀";
        String password = "666666";
        userService.register(userName, password, 1);
    }

    /**
     * Method: update(User user)
     */
    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setUserId(1006);
        user.setUserNickname("即刻小哥哥");
        userService.update(user);
    }

    /**
     * Method: editPassword(String userName, String password)
     */
    @Test
    public void testEditPassword() throws Exception {
        userService.editPassword("你好", "123123");
    }

    /**
     * Method: getUserById(int userId)
     */
    @Test
    public void testGetUserById() throws Exception {
        List<User> users = userService.getAllUsers();
        JsonDemo jsonDemo = new JsonDemo(users, 1, "ok");
        System.out.println(JSON.toJSONString(jsonDemo));
    }

    /**
     * Method: getUserByUserName(String userName)
     */
    @Test
    public void testGetUserByUserName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSimpleUserByUserName(String userName)
     */
    @Test
    public void testGetSimpleUserByUserName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUserChatGroup(int userId)
     */
    @Test
    public void testGetUserChatGroup() throws Exception {
        int userId = 1001;
        System.out.println(userService.getUserChatGroup(userId));
    }

    /**
     * Method: getUserFriends(int userId)
     */
    @Test
    public void testGetUserFriends() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addFriend(int initiatorId, int acceptorId)
     */
    @Test
    public void testAddFriend() throws Exception {

    }

    /**
     * Method: deleteFriend(int userId, int friendId)
     */
    @Test
    public void testDeleteFriend() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUsersByUserName(String userName)
     */
    @Test
    public void testGetUsersByUserName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setFormalUser(User user, SimpleUser simpleUser)
     */
    @Test
    public void testSetFormalUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAllUsers()
     */
    @Test
    public void testGetAllUsers() throws Exception {
//TODO: Test goes here... 
    }


} 
