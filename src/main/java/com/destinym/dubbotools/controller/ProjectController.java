package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.dal.dao.ProjectDao;
import com.destinym.dubbotools.dal.model.Project;
import com.destinym.dubbotools.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mengliang
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectDao projectDao;


    @GetMapping(path = "/projects")
    public Result<List> list() {
        return Result.success(projectDao.listAll());
    }


    @GetMapping(path = "/project/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(projectDao.get(id));
    }

    @PostMapping(path = "/project")
    public Result add(@RequestBody Project project) {
        if (StringUtils.isEmpty(project.getName()) || StringUtils.isEmpty(project.getGitUrl())){
            return Result.error("error", "参数错误");
        }
        Project dbProject = projectDao.getByGitUrl(project.getGitUrl());
        if (dbProject == null) {
            projectDao.add(project);
            return Result.success("ok");
        } else {
            return Result.error("error", "git地址重复");
        }
    }

    @DeleteMapping(path = "/project/{id}")
    public Result delete(@PathVariable Long id) {
        Project dbProject= projectDao.get(id);
        if (dbProject == null) {
            return Result.error("error", "项目不存在");
        } else {
            projectDao.delete(id);
            return Result.success("ok");

        }
    }

    @PutMapping(path = "/project")
    public Result modify(@RequestBody Project project) {
        Project dbProject = projectDao.get(project.getId());
        if (dbProject == null) {
            return Result.error("error", "策略不存在");
        } else {
            Project gitProject = projectDao.getByGitUrl(project.getGitUrl());
            if (gitProject != null && !gitProject.getId().equals(project.getId())){
                return Result.error("error", "git地址重复");
            }
            projectDao.modify(project);
            return Result.success("ok");

        }
    }
}
