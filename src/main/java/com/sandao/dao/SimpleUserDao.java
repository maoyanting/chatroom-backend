package com.sandao.dao;

import com.sandao.entity.SimpleUser;
import org.springframework.stereotype.Repository;

/**
 * Created by maoyanting on 2017/11/21.
 */
@Repository
public interface SimpleUserDao extends BaseDao<Integer,SimpleUser> {

    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    SimpleUser getSimpleUserBySimpleUserName(String userName);
}
