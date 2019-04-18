package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 角色表
 * role
 * @author Fynce
 * @date 2019/03/19
 */
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 角色名
     * role
     */
    private String role;

}