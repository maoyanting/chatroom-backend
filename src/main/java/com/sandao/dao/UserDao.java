package com.sandao.dao;

import com.sandao.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/21.
 * @author sandao
 */
@Repository
public interface UserDao extends BaseDao<Integer,User>{
    /**
     * 根据用户名获取用户
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    /**
     * 模糊查找
     * @param userName
     * @return
     */
    List<User> getUsersByUserName(String userName);
}
