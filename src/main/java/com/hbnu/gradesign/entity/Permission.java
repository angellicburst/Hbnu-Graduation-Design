package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 权限表
 * permission
 * @author Fynce
 * @date 2019/03/19
 */
@Getter
@Setter
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 权限名
     * permission
     */
    private String permission;

}