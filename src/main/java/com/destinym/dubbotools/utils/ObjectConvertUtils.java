package com.destinym.dubbotools.utils;


import com.destinym.dubbotools.dal.model.Strategy;
import com.destinym.dubbotools.vo.StrategyVo;

/**
 * @author mengliang
 * @date 2019/8/14
 */
public class ObjectConvertUtils {


    public static StrategyVo strategy2StrategyVo(Strategy strategy) {
        if (strategy == null) {
            return null;
        }
        StrategyVo strategyVo = new StrategyVo();
        strategyVo.setId(strategy.getId());
        strategyVo.setName(strategy.getName());
        strategyVo.setDescription(strategy.getDescription());
        strategyVo.setOwner(strategy.getOwner());
        strategyVo.setType(strategy.getType());
        strategyVo.setSyncStatus(strategy.getSyncStatus());
        strategyVo.setUpdateDate(strategy.getUpdateDate());
        return strategyVo;
    }
}
