package com.sandao.dao;

import com.sandao.entity.FriendRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 * @author sandao
 */
@Repository
public interface FriendRequestDao extends BaseDao<Integer,FriendRequest> {
    /**
     * 根据发起者id获取邀请
     * @param initiatorId
     * @return
     */
    List<FriendRequest> getFriendRequestByInitiatorId(int initiatorId);

    /**
     * 根据接受者id获取邀请
     * @param acceptorId
     * @return
     */
    List<FriendRequest> getFriendRequestByAcceptorId(int acceptorId);

    /**
     * 根据两者id获取邀请
     * @param initiatorId
     * @param acceptorId
     * @return
     */
    FriendRequest getFriendRequestByBothId(@Param("initiatorId")int initiatorId, @Param("acceptorId")int acceptorId);
}
