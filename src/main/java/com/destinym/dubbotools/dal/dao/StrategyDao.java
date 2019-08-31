package com.destinym.dubbotools.dal.dao;


import com.destinym.dubbotools.dal.model.Strategy;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyDao extends BaseDao<Strategy> {
    /**
     * @return 返回所有策略
     */
    List<Strategy> listAll();


    Strategy getByName(String name);
}
