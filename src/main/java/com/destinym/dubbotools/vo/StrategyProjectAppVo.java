package com.destinym.dubbotools.vo;

import lombok.Data;

/**
 * @author mengliang
 * @date 2019/8/13
 */
@Data
public class StrategyProjectAppVo {
    private Long id;
    private String ip;
    private Long appId;
    private Long strategyProjectId;
    private String appAppId;
    private String path;
    private String packageName;
    private Integer port;
}
