package com.sandao.dao;

import com.sandao.entity.UserMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 */
@Repository
public interface UserMappingDao extends BaseDao<Integer,UserMapping> {

    /**
     * 根据用户id获取所有好友
     * @param userId
     * @return
     */
    List<UserMapping> getAllFriendsMapping(int userId);

    /**
     * 获取特定好友关系
     * @param userAId
     * @param userBId
     * @return
     */
    UserMapping getFriendMappingById(@Param("userAId") int userAId, @Param("userBId") int userBId);
}
