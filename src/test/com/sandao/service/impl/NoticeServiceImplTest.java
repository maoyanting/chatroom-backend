package com.sandao.service.impl;

import com.alibaba.fastjson.JSON;
import com.sandao.BaseTest;
import com.sandao.entity.FriendRequest;
import com.sandao.util.JsonDemo;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * NoticeServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 21, 2017</pre>
 */
public class NoticeServiceImplTest extends BaseTest {

    private NoticeServiceImpl noticeService;

    @Autowired
    public void setNoticeService(NoticeServiceImpl noticeService) {
        this.noticeService = noticeService;
    }



    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: setUserMappingDao(UserMappingDao userMappingDao) 搞事情
     */
    @Test
    public void testSetUserMappingDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setFriendRequestDao(FriendRequestDao friendRequestDao)
     */
    @Test
    public void testSetFriendRequestDao() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getFriendRequest(int userId)
     */
    @Test
    public void testGetFriendRequest() throws Exception {
        List<FriendRequest> a = noticeService.getFriendRequest(1006);
        JsonDemo jsonDemo = new JsonDemo(a, 1, "ok");
        System.out.println(JSON.toJSONString(jsonDemo));
    }

    /**
     * Method: addFriendRequest(int initiatorId, int acceptorId)
     */
    @Test
    public void testAddFriendRequest() throws Exception {
        System.out.println("------------------addFriendRequest---------------");
        noticeService.addFriendRequest(1004, 1007);
    }

    /**
     * Method: editFriendRequest(int friendRequestId, int request)
     */
    @Test
    public void testEditFriendRequest() throws Exception {
//        FriendRequest friendRequest = new FriendRequest();
//        friendRequest.setFriendRequestId(4);
//        friendRequest.setConditionNumber(1);
//        noticeService.editFriendRequest(friendRequest);
    }


} 
