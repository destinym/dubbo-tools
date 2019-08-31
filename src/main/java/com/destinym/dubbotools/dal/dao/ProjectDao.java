package com.destinym.dubbotools.dal.dao;

import com.destinym.dubbotools.dal.model.Project;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */
public interface ProjectDao extends BaseDao<Project>{
    /**
     *
     * @return
     */
    List<Project> listAll();

    Project getByGitUrl(String gitUrl);
}
