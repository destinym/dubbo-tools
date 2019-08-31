package com.destinym.dubbotools.dal.mapper;


import com.destinym.dubbotools.dal.model.Strategy;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyMapper extends Base2Mapper<Strategy> {
    /**
     * @return 列表
     */
    List<Strategy> listAll();

    Strategy getByName(String name);
}
