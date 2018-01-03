package com.sandao.service.impl;

import com.sandao.BaseTest;
import com.sandao.entity.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ChatServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>一月 3, 2018</pre>
 */
public class ChatServiceImplTest extends BaseTest {
    private ChatServiceImpl chatService;
    @Autowired
    public void setChatService(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setChatGroupDao(ChatGroupDao chatGroupDao)
     */
    @Test
    public void testSetChatGroupDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setUserGroupMappingDao(UserGroupMappingDao userGroupMappingDao)
     */
    @Test
    public void testSetUserGroupMappingDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setUserDao(UserDao userDao)
     */
    @Test
    public void testSetUserDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: createChatGroup(String chatGroupName, int userId)
     */
    @Test
    public void testCreateChatGroup() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteChatGroup(ChatGroup chatGroup)
     */
    @Test
    public void testDeleteChatGroup() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUsersByChatGroupId(int chatGroupId)
     */
    @Test
    public void testGetUsersByChatGroupId() throws Exception {
        List<User> users =chatService.getUsersByChatGroupId(10);
        System.out.println(users);
    }

    /**
     * Method: getChatGroupByChatGroupId(int chatGroupId)
     */
    @Test
    public void testGetChatGroupByChatGroupId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: quitChatGroup(int userId, int chatGroupId)
     */
    @Test
    public void testQuitChatGroup() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addUserToGroup(int userId, int chatGroupId)
     */
    @Test
    public void testAddUserToGroup() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: userIsInGroup(int userId, int chatGroupId)
     */
    @Test
    public void testUserIsInGroup() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getChatGroupByChatGroupName(String chatGroupName)
     */
    @Test
    public void testGetChatGroupByChatGroupName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getChatGroupIdByChatGroupName(String chatGroupName)
     */
    @Test
    public void testGetChatGroupIdByChatGroupName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: editChatGroupName(int chatGroupId, String newName)
     */
    @Test
    public void testEditChatGroupName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: editOwner(int chatGroupId, int newOwnerId)
     */
    @Test
    public void testEditOwner() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAllChatGroup()
     */
    @Test
    public void testGetAllChatGroup() throws Exception {
//TODO: Test goes here... 
    }


} 
