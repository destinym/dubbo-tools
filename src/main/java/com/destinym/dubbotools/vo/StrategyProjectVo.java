package com.destinym.dubbotools.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author mengliang
 * @date 2019/8/15
 */
@Data
public class StrategyProjectVo {
    private Long id;
    private String projectName;
    private String strategyName;
    private Long projectId;
    private String gitBranch;
    private String gitUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
}
