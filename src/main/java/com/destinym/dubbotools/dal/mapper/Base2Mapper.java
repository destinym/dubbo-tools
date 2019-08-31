package com.destinym.dubbotools.dal.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author destinym
 */
public interface Base2Mapper<T> extends Mapper {

    /**
     * 插入实体(不包含非null属性)
     * @param entity 实体
     * @return id
     */
    Long insertSelective(T entity);

    /**
     * 插入实体(所有属性,包含非null字段)
     * @param entity 实体
     * @return id
     */
    Long insert(T entity);

    /**
     * 删除实体
     * @param id id
     * @return 个数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 批量删除
     * @param ids id列表
     * @return 个数
     */
    int deleteBatch(List<Long> ids);

    /**
     * 选择实体
     * @param id id
     * @return 实体
     */
    T selectByPrimaryKey(Long id);

    /**
     * 获取实体列表
     * @param ids id列表
     * @return 实体列表
     */
    List<T> getByIds(List<Long> ids);

    /**
     * 根据id,更新实体(只更新非空属性)
     * @param entity 实体
     */
    void updateByPrimaryKeySelective(T entity);

    /**
     * 根据id,更新实体（更新所有属性,包含非空属性）
     * @param entity 实体
     */
    void updateByPrimaryKey(T entity);
}
