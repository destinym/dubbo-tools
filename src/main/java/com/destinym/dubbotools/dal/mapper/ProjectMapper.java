package com.destinym.dubbotools.dal.mapper;

import com.destinym.dubbotools.dal.model.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface ProjectMapper extends Base2Mapper<Project> {
    /**
     *
     * @return
     */
    List<Project> listAll();

    Project getByGitUrl(@Param("gitUrl") String gitUrl);
}
