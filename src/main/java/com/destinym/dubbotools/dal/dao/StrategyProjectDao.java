package com.destinym.dubbotools.dal.dao;


import com.destinym.dubbotools.dal.model.StrategyProject;
import com.destinym.dubbotools.vo.StrategyProjectVo;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyProjectDao extends BaseDao<StrategyProject>{
    /**
     * @return 返回所有策略
     */
    List<StrategyProject> listAll();


    StrategyProject getByProjectIdAndStrategyId(Long projectId, Long strategyId);


    List<StrategyProjectVo>  listByStrategyId(Long strategyId);
}
