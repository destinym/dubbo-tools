package com.destinym.dubbotools.dal.dao;


import com.destinym.dubbotools.dal.model.StrategyProjectApp;
import com.destinym.dubbotools.vo.StrategyProjectAppVo;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyProjectAppDao extends BaseDao<StrategyProjectApp> {
    /**
     * @param id
     * @return
     */
    List<StrategyProjectAppVo> listByStrategyProjectId(Long id);

    /**
     * @param strategyId
     * @param appId
     * @return
     */
    StrategyProjectApp getByStrategyProjectIdAndAppId(Long strategyId, Long appId);

}
