package com.destinym.dubbotools.dal.dao.impl;

import com.destinym.dubbotools.dal.dao.AppDao;
import com.destinym.dubbotools.dal.model.App;
import com.destinym.dubbotools.dal.mapper.AppMapper;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */

@Component
public class AppDaoImpl extends BaseDaoImpl<App> implements AppDao {
    @Autowired
    private AppMapper appMapper;

    @Override
    protected Base2Mapper<App> getMapper() {
        return appMapper;
    }

    @Override
    public List<App> listAll() {
        return appMapper.listAll();
    }


    @Override
    public List<App> listByProjectId(Long projectId) {
        return appMapper.listByProjectId(projectId);
    }

    @Override
    public App getByAppIdAndProjectId(String appId, Long projectId) {
        return appMapper.getByAppIdAndProjectId(appId, projectId);
    }


}
