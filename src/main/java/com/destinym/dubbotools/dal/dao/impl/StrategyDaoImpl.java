package com.destinym.dubbotools.dal.dao.impl;

import com.destinym.dubbotools.dal.dao.StrategyDao;
import com.destinym.dubbotools.dal.model.Strategy;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import com.destinym.dubbotools.dal.mapper.StrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */

@Component
public class StrategyDaoImpl extends BaseDaoImpl<Strategy> implements StrategyDao {
    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    protected Base2Mapper<Strategy> getMapper() {
        return strategyMapper;
    }

    @Override
    public List<Strategy> listAll() {
        return strategyMapper.listAll();
    }

    @Override
    public Strategy getByName(String name) {
        return strategyMapper.getByName(name);
    }


}
