package com.destinym.dubbotools.dal.dao.impl;

import com.destinym.dubbotools.dal.dao.StrategyProjectDao;
import com.destinym.dubbotools.dal.model.StrategyProject;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import com.destinym.dubbotools.dal.mapper.StrategyProjectMapper;
import com.destinym.dubbotools.vo.StrategyProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */

@Component
public class StrategyProjectDaoImpl extends BaseDaoImpl<StrategyProject> implements StrategyProjectDao {
    @Autowired
    private StrategyProjectMapper strategyProjectMapper;

    @Override
    protected Base2Mapper<StrategyProject> getMapper() {
        return strategyProjectMapper;
    }

    @Override
    public List<StrategyProject> listAll() {
        return strategyProjectMapper.listAll();
    }

    @Override
    public StrategyProject getByProjectIdAndStrategyId(Long projectId, Long strategyId) {
        return strategyProjectMapper.getByProjectIdAndStrategyId(projectId, strategyId);
    }


    @Override
    public List<StrategyProjectVo> listByStrategyId(Long strategyId) {
        return strategyProjectMapper.listByStrategyId(strategyId);
    }


}
