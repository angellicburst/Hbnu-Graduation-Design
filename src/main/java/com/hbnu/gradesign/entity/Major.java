package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 专业表
 * major
 * @author Fynce
 * @date 2019/04/08
 */
@Getter
@Setter
public class Major implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 专业
     * major
     */
    private String major;

}