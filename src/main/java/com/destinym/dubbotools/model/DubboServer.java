package com.destinym.dubbotools.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by mengliang on 2019/5/12.
 */
@Data
public class DubboServer {
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String name;
    private boolean syncStatus;
}
