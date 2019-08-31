package com.destinym.dubbotools.dal.mapper;

import com.destinym.dubbotools.dal.model.StrategyProject;
import com.destinym.dubbotools.vo.StrategyProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface StrategyProjectMapper extends Base2Mapper<StrategyProject> {
    /**
     * @return 列表
     */
    List<StrategyProject> listAll();

    StrategyProject getByProjectIdAndStrategyId(@Param("projectId") Long projectId, @Param("strategyId") Long strategyId);

    List<StrategyProjectVo> listByStrategyId(@Param("strategyId") Long strategyId);
}
