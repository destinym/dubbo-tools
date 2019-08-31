package com.destinym.dubbotools.dal.dao;


import com.destinym.dubbotools.dal.model.App;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface AppDao extends BaseDao<App> {
    /**
     * @return 返回所有策略
     */
    List<App> listAll();


    List<App> listByProjectId(Long projectId);

    App getByAppIdAndProjectId(String appId, Long projectId);
}
