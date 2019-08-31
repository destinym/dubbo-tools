package com.destinym.dubbotools.dal.dao.impl;


import com.destinym.dubbotools.dal.dao.BaseDao;
import com.destinym.dubbotools.dal.model.BaseModel;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kongleijia
 */
public abstract class BaseDaoImpl<T extends BaseModel> implements BaseDao<T> {

    /**
     * 返回mapper
     *
     * @return mapper
     */
    protected abstract Base2Mapper<T> getMapper();

    @Override
    public Long addWithNull(T entity) {
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        getMapper().insertSelective(entity);
        return entity.getId();
    }

    @Override
    public Long add(T entity) {
        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        getMapper().insertSelective(entity);
        return entity.getId();
    }

    @Override
    public void modify(T entity) {
        entity.setUpdateDate(new Date());
        getMapper().updateByPrimaryKeySelective(entity);
    }

    @Override
    public void modifyWithNull(T entity) {
        entity.setUpdateDate(new Date());
        getMapper().updateByPrimaryKey(entity);
    }

    @Override
    public int delete(Long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        return getMapper().deleteBatch(ids);
    }

    @Override
    public T get(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> list(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            return getMapper().getByIds(ids);
        }
        return new ArrayList<>();
    }
}
