package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户表
 * user
 * @author Fynce
 * @date 2019/03/19
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 用户名
     * username
     */
    private String username;

    /**
     * 密码
     * password
     */
    private String password;

    /**
     * 盐值
     * salt
     */
    private String salt;

    /**
     * 状态
            0--启用
            1--停用
     * status
     */
    private Integer status;

}