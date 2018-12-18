package com.yuxiao.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * 通用基础服务
 * @param <T>
 */
public class BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;

    /**
     * 主键查询
     * @param obj
     * @return
     */
    public T selectByPrimaryKey(T obj){
        return mapper.selectByPrimaryKey(obj);
    }

    /**
     * 条件查询
     * @param obj
     * @return
     */
    public List<T> select(T obj){
        return mapper.select(obj);
    }

    /**
     * 条件查询某个对象
     * @param obj
     * @return
     */
    public T selectOne(T obj){
        return mapper.selectOne(obj);
    }

    /**
     * 新增
     * @param obj
     * @return
     */
    public int insert(T obj){
        return mapper.insert(obj);

    }

    /**
     * 只保存不为空的数据
     * @param obj
     * @return
     */
    public int insertSelective(T obj){
        return mapper.insertSelective(obj);
    }


    /**
     * 通过主键更新
     * @param obj
     * @return
     */
    public int updateByPrimaryKey(T obj){
        return mapper.updateByPrimaryKey(obj);
    }


    /**
     * 通过主键更新不为空的字段信息
     * @param obj
     * @return
     */
    public int updateByPrimaryKeySelective(T obj){
        return mapper.updateByPrimaryKeySelective(obj);
    }

}
