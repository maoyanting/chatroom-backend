package com.sandao.service;

import com.sandao.entity.FriendRequest;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 * @author sandao
 * 消息：发送的好友请求，收到的好友请求，被踢出群的消息。
 */
public interface NoticeService {

    /**
     * 获取请求，未处理的在最上面，剩下按照时间排序
     * @param initiatorId
     * @return
     */
    List<FriendRequest> getFriendRequest(int initiatorId);


    /**
     * 发送好友请求
     * @param initiatorId
     * @param acceptorId
     */
    void addFriendRequest(int initiatorId, int acceptorId);

    /**
     * 好友请求处理，发送webSocket提醒更新通知栏
     * @param friendRequestId
     * @param request
     * @return
     */
    void agreeFriendRequest(int initiatorId,int acceptorId);
}
