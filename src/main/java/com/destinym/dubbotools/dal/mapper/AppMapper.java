package com.destinym.dubbotools.dal.mapper;

import com.destinym.dubbotools.dal.model.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface AppMapper extends Base2Mapper<App> {
    /**
     * @return 列表
     */
    List<App> listAll();

    App getByAppIdAndProjectId(@Param("appId") String appId, @Param("projectId") Long projectId);

    List<App> listByProjectId(@Param("projectId") Long projectId);
}
