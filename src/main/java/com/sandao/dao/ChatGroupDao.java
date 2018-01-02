package com.sandao.dao;

import com.sandao.entity.ChatGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/20.
 */
@Repository
public interface ChatGroupDao extends BaseDao<Integer, ChatGroup> {

    /**
     * 根据群名获取群
     * @param chatGroupName
     * @return
     */
    ChatGroup getChatGroupByGroupName(String chatGroupName);
}
