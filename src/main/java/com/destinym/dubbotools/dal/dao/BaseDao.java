package com.destinym.dubbotools.dal.dao;

import java.util.List;

public interface BaseDao<T> {

    /**
     * 新增 (指定为null)
     *
     * @param entity 实体
     * @return 实体id
     */
    Long addWithNull(T entity);

    /**
     * 新增 (包含null)
     *
     * @param entity 实体
     * @return 实体id
     */
    Long add(T entity);

    /**
     * 修改 (非null属性)
     *
     * @param entity 实体
     */
    void modify(T entity);

    /**
     * 修改 (和对象一致,包含null)
     *
     * @param entity 实体
     */
    void modifyWithNull(T entity);

    /**
     * 删除
     *
     * @param id 实体id
     * @return 删除个数
     */
    int delete(Long id);

    /**
     * 批量删除
     *
     * @param ids 实体id列表
     * @return 删除个数
     */
    int delete(List<Long> ids);

    /**
     * 通过id获取
     *
     * @param id id
     * @return 实体
     */
    T get(Long id);

    /**
     * 通过id集合获取
     *
     * @param ids id列表
     * @return 删除个数
     */
    List<T> list(List<Long> ids);

}
