package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.dal.dao.AppDao;
import com.destinym.dubbotools.dal.dao.StrategyProjectAppDao;
import com.destinym.dubbotools.dal.dao.StrategyProjectDao;
import com.destinym.dubbotools.dal.model.App;
import com.destinym.dubbotools.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mengliang
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AppController {
    @Autowired
    private AppDao appDao;
    @Autowired
    private StrategyProjectDao strategyProjectDao;
    @Autowired
    private StrategyProjectAppDao strategyProjectAppDao;


    @GetMapping(path = "/apps")
    public Result<List> list() {
        return Result.success(appDao.listAll());
    }

    @GetMapping(path = "/apps/project/{projectId}")
    public Result<List> listByProjectId(@PathVariable Long projectId) {
        return Result.success(appDao.listByProjectId(projectId));
    }

    @PostMapping(path = "/app")
    public Result add(@RequestBody App app) {
        App dbApp = appDao.getByAppIdAndProjectId(app.getAppId(),app.getProjectId());
        if (dbApp == null) {
            appDao.add(app);
            return Result.success("ok");
        } else {
            return Result.error("error", "appId重复");
        }
    }

    @PutMapping(path = "/app")
    public Result modify(@RequestBody App app) {
        App dbApp = appDao.get(app.getId());
        if (dbApp == null) {
            return Result.error("error", "app不存在");
        } else {
            appDao.modify(app);
            return Result.success("ok");

        }
    }

    @DeleteMapping(path = "/app/{id}")
    public Result delete(@PathVariable Long id) {
        App dbApp = appDao.get(id);
        if (dbApp == null) {
            return Result.error("error", "app不存在");
        } else {
            appDao.delete(id);
            return Result.success("ok");

        }
    }


}
