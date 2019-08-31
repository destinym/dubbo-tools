package com.destinym.dubbotools.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author mengliang
 * @date 2019/8/15
 */
@Data
public class StrategyVo {
    private Long id;
    private String name;
    private String description;
    private String owner;
    private String syncStatus;
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
}
