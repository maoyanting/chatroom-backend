package com.sandao.service.impl;

import com.sandao.dao.FriendRequestDao;
import com.sandao.dao.UserMappingDao;
import com.sandao.entity.FriendRequest;
import com.sandao.entity.UserMapping;
import com.sandao.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by maoyanting on 2017/12/4.
 *
 * @author
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private UserMappingDao userMappingDao;
    private FriendRequestDao friendRequestDao;

    @Autowired
    public void setUserMappingDao(UserMappingDao userMappingDao) {
        this.userMappingDao = userMappingDao;
    }

    @Autowired
    public void setFriendRequestDao(FriendRequestDao friendRequestDao) {
        this.friendRequestDao = friendRequestDao;
    }


    /**
     * 获取请求，未处理的好友请求在最上面，然后是按照时间排序的好友请求和发送的好友请求（已经回复的）
     *
     * @param userId
     * @return
     */
    @Override
    public List<FriendRequest> getFriendRequest(int userId) {
        //发送的好友请求
        List<FriendRequest> a = friendRequestDao.getFriendRequestByInitiatorId(userId);
        //收到的好友请求
        List<FriendRequest> b = friendRequestDao.getFriendRequestByAcceptorId(userId);
        a.addAll(b);
        return a;
    }

    /**
     * 添加好友请求
     *
     * @param initiatorId
     * @param acceptorId
     */
    @Override
    public void addFriendRequest(int initiatorId, int acceptorId) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setInitiatorId(initiatorId);
        friendRequest.setAcceptorId(acceptorId);
        friendRequest.setCreateDate(new Date());
        friendRequestDao.insertSelective(friendRequest);
    }

    /**
     * 好友请求回复了，建立好友关系
     *
     * @param initiatorId
     * @param acceptorId
     * @param conditionNumber
     */
    @Override
    public void agreeFriendRequest(int initiatorId, int acceptorId) {
        /* 获取friendRequest */
//        FriendRequest friendRequest = friendRequestDao.getFriendRequestByBothId(initiatorId, acceptorId);
//        friendRequest.setConditionNumber(1);
        //添加好友关系
        UserMapping userMapping = new UserMapping();
        if (initiatorId < acceptorId) {
            userMapping.setUserAId(initiatorId);
            userMapping.setUserBId(acceptorId);
        } else {
            userMapping.setUserAId(acceptorId);
            userMapping.setUserBId(initiatorId);
        }
        userMapping.setCreateDate(new Date());
        userMappingDao.insertSelective(userMapping);
    }
}
