package com.destinym.dubbotools.dal.dao.impl;

import com.destinym.dubbotools.dal.dao.ProjectDao;
import com.destinym.dubbotools.dal.model.Project;
import com.destinym.dubbotools.dal.mapper.Base2Mapper;
import com.destinym.dubbotools.dal.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mengliang
 * @date 2019/8/13
 */

@Component
public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    protected Base2Mapper<Project> getMapper() {
        return projectMapper;
    }


    @Override
    public List<Project> listAll() {
        return projectMapper.listAll();
    }

    @Override
    public Project getByGitUrl(String gitUrl) {
        return projectMapper.getByGitUrl(gitUrl);
    }
}
