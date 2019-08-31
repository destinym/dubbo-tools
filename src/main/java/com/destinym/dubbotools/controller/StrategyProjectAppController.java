package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.dal.dao.StrategyProjectAppDao;
import com.destinym.dubbotools.dal.model.StrategyProjectApp;
import com.destinym.dubbotools.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mengliang
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class StrategyProjectAppController {
    @Autowired
    private StrategyProjectAppDao strategyProjectAppDao;


    @GetMapping(path = "/strategyProjectApps/strategyProjectId/{strategyProjectId}")
    public Result listByStrategyId(@PathVariable Long strategyProjectId) {
        return Result.success(strategyProjectAppDao.listByStrategyProjectId(strategyProjectId));
    }

    @PostMapping(path = "/strategyProjectApps/createOrUpdateList")
    public Result createOrUpdateList(@RequestBody List<StrategyProjectApp> strategyProjectAppList) {
        if (!CollectionUtils.isEmpty(strategyProjectAppList)) {
            strategyProjectAppList.forEach(item -> createOrUpdate(item));
        }
        return Result.success("ok");
    }

    private void createOrUpdate(StrategyProjectApp item) {
        if (item.getId() == null) {
            strategyProjectAppDao.add(item);
        } else {
            strategyProjectAppDao.modify(item);
        }
    }

}
