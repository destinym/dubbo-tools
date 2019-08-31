package com.destinym.dubbotools.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author destinym
 * @date 2019/8/25.
 */

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class BaseModel implements Serializable {
    private Long id;
    private Date createDate;
    private Date updateDate;
}
