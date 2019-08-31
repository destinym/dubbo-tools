package com.destinym.dubbotools.dal.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyProject extends BaseModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.id
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.git_branch
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private String gitBranch;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.project_id
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Long projectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.strategy_id
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Long strategyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.create_date
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column strategy_project.update_date
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Date updateDate;
}