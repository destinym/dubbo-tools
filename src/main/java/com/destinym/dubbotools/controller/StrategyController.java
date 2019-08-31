package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.dal.dao.StrategyDao;
import com.destinym.dubbotools.dal.model.Strategy;
import com.destinym.dubbotools.service.StrategyService;
import com.destinym.dubbotools.utils.ObjectConvertUtils;
import com.destinym.dubbotools.vo.Result;
import com.destinym.dubbotools.vo.StrategyVo;
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
public class StrategyController {
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private StrategyDao strategyDao;


    @GetMapping(path = "/strategies")
    public Result<List> list() {
        List<StrategyVo> strategyVos = strategyDao.listAll()
                .stream().map(item -> ObjectConvertUtils.strategy2StrategyVo(item))
                .collect(Collectors.toList());

        return Result.success(strategyVos);
    }

    @GetMapping(path = "/strategy/{strategyId}")
    public Result getByStrategyId(@PathVariable Long strategyId) {
        return Result.success(strategyDao.get(strategyId));
    }


    @PostMapping(path = "/strategy")
    public Result add(@RequestBody Strategy strategy) {
        Strategy dbStrategy = strategyDao.getByName(strategy.getName());
        if (dbStrategy == null) {
            strategyDao.add(strategy);
            return Result.success("ok");
        } else {
            return Result.error("error", "策略名字重复");
        }
    }

    @PutMapping(path = "/strategy")
    public Result modify(@RequestBody Strategy strategy) {
        Strategy dbStrategy = strategyDao.get(strategy.getId());
        if (dbStrategy == null) {
            return Result.error("error", "策略不存在");
        } else {
            Strategy strategyByName = strategyDao.getByName(strategy.getName());
            if (strategyByName != null && !strategyByName.getId().equals(strategy.getId())) {
                return Result.error("error", "git地址重复");
            }
            strategyDao.modify(strategy);
            return Result.success("ok");

        }
    }

    @DeleteMapping(path = "/strategy/{id}")
    public Result delete(@PathVariable Long id) {
        Strategy dbStrategy = strategyDao.get(id);
        if (dbStrategy == null) {
            return Result.error("error", "策略不存在");
        } else {
            strategyDao.delete(id);
            return Result.success("ok");

        }
    }


    @GetMapping(path = "/strategy/sync/{id}")
    public Result sync(@PathVariable Long id) {
        strategyService.sync(id);
        return Result.success("ok");
    }
}
