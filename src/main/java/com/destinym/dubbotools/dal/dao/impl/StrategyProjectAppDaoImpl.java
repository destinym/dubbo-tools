package com.destinym.dubbotools.dal.dao.impl;

import com.destinym.dubbotools.dal.dao.StrategyProjectAppDao;
import com.destinym.dubbotools.dal.model.StrategyProjectApp;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import com.destinym.dubbotools.dal.mapper.StrategyProjectAppMapper;
import com.destinym.dubbotools.vo.StrategyProjectAppVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */

@Component
public class StrategyProjectAppDaoImpl extends BaseDaoImpl<StrategyProjectApp> implements StrategyProjectAppDao {
    @Autowired
    private StrategyProjectAppMapper strategyProjectAppMapper;

    @Override
    protected Base2Mapper<StrategyProjectApp> getMapper() {
        return strategyProjectAppMapper;
    }


    @Override
    public List<StrategyProjectAppVo> listByStrategyProjectId(Long id) {
        return strategyProjectAppMapper.listByStrategyProjectId(id);
    }


    @Override
    public StrategyProjectApp getByStrategyProjectIdAndAppId(Long strategyId, Long appId) {
        return strategyProjectAppMapper.getByStrategyProjectIdAndAppId(strategyId, appId);
    }

}
