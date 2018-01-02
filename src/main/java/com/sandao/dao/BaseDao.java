package com.sandao.dao;

import java.util.List;

/**
 * Created by maoyanting on 2017/11/20.
 */
public interface BaseDao<PK, T> {

    T selectByPrimaryKey(PK id);

    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> getAll();
}
