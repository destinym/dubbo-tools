package com.destinym.dubbotools.dal.mapper;

import com.destinym.dubbotools.dal.model.StrategyProjectApp;
import com.destinym.dubbotools.vo.StrategyProjectAppVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyProjectAppMapper extends Base2Mapper<StrategyProjectApp> {
    /**
     * @param id
     * @return
     */
    List<StrategyProjectAppVo> listByStrategyProjectId(@Param("id") Long id);

    /**
     *
     * @param strategyProjectId
     * @param appId
     * @return
     */
    StrategyProjectApp getByStrategyProjectIdAndAppId(@Param("strategyProjectId") Long strategyProjectId, @Param("appId") Long appId);
}
