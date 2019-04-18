package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 二级菜单表
 * menu
 * @author Fynce
 * @date 2019/03/27
 */
@Getter
@Setter
public class SecMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer secId;

    /**
     * 二级菜单名称
     *
     */
    private String secMenu;

    /**
     * 二级地址
     *
     */
    private String secUrl;

    /**
     * 二级菜单等级
     *
     */
    private Integer secLevel;

}