package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.dal.dao.ProjectDao;
import com.destinym.dubbotools.dal.dao.StrategyProjectDao;
import com.destinym.dubbotools.dal.model.Project;
import com.destinym.dubbotools.dal.model.StrategyProject;
import com.destinym.dubbotools.vo.Result;
import com.destinym.dubbotools.vo.StrategyProjectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mengliang
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class StrategyProjectController {
    @Autowired
    private StrategyProjectDao strategyProjectDao;
    @Autowired
    private ProjectDao projectDao;


    @GetMapping(path = "/strategyProjects")
    public Result<List> list() {
        return Result.success(strategyProjectDao.listAll());
    }

    @GetMapping(path = "/strategyProject/{id}")
    public Result get(@PathVariable Long id) {
        return Result.success(strategyProjectDao.get(id));
    }

    @GetMapping(path = "/strategyProjects/strategyId/{strategyId}")
    public Result listByStrategyId(@PathVariable Long strategyId) {
        return Result.success(strategyProjectDao.listByStrategyId(strategyId));
    }

    @GetMapping(path = "/strategyProjects/unBindProjects/strategyId/{strategyId}")
    public Result listUnBindProjectsByStrategyId(@PathVariable Long strategyId) {
        List<Project> projects = projectDao.listAll();
        List<StrategyProjectVo> strategyProjectVos = strategyProjectDao.listByStrategyId(strategyId);
        List<String> projectNames = strategyProjectVos.stream().map(item -> item.getProjectName()).collect(Collectors.toList());
        List<Project> unBindProjects = projects.stream().filter(project -> !projectNames.contains(project.getName())).collect(Collectors.toList());
        return Result.success(unBindProjects);
    }

    @PostMapping(path = "/strategyProject")
    public Result add(@RequestBody StrategyProject strategyProject) {
        StrategyProject dbStrategyProject = strategyProjectDao.getByProjectIdAndStrategyId(
                strategyProject.getProjectId(), strategyProject.getStrategyId());
        if (dbStrategyProject == null) {
            strategyProjectDao.add(strategyProject);
            return Result.success("ok");
        } else {
            return Result.error("error", "该策略已经绑定过该工程");
        }
    }

    @PutMapping(path = "/strategyProject")
    public Result modify(@RequestBody StrategyProject strategyProject) {
        StrategyProject dbStrategyProject = strategyProjectDao.get(strategyProject.getId());
        if (dbStrategyProject == null) {
            return Result.error("error", "策略不存在");
        } else {
            strategyProjectDao.modify(strategyProject);
            return Result.success("ok");

        }
    }

    @DeleteMapping(path = "/strategyProject/{id}")
    public Result delete(@PathVariable Long id) {
        StrategyProject dbStrategyProject = strategyProjectDao.get(id);
        if (dbStrategyProject == null) {
            return Result.error("error", "策略不存在");
        } else {
            strategyProjectDao.delete(id);
            return Result.success("ok");

        }
    }


}
