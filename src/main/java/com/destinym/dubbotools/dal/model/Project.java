package com.destinym.dubbotools.dal.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseModel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.id
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.git_url
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private String gitUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.name
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project.owner
     *
     * @mbg.generated Wed Aug 21 17:55:24 CST 2019
     */
    private String owner;
}