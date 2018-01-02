package com.sandao.dao;

import com.sandao.entity.ChatGroup;
import com.sandao.entity.User;
import com.sandao.entity.UserGroupMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 */
@Repository
public interface UserGroupMappingDao extends BaseDao<Integer,UserGroupMapping>{

    /**
     * 根据用户id获取所有存在的关系
     * @param userId
     * @return
     */
    List<UserGroupMapping> getAllMappingByUserId(int userId);

    /**
     * 根据群id获取所有存在的关系,按照早时间来排序，asc
     * @param chatGroupId
     * @return
     */
    List<UserGroupMapping> getAllMappingByChatGroupId(int chatGroupId);

    /**
     * 获取特定关系
     * @param userId
     * @param chatGroupId
     * @return
     */
    UserGroupMapping getUserGroupMappingById(@Param("userId")int userId, @Param("chatGroupId")int chatGroupId);
}
