package com.hbnu.gradesign.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 菜单表
 * menu
 * @author Fynce
 * @date 2019/03/28
 */
@Getter
@Setter
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * id
     */
    private Integer id;

    /**
     * 菜单名称
     * menu_name
     */
    private String menuName;

    /**
     * 地址
     * url
     */
    private String url;

    /**
     * 菜单等级
     * level
     */
    private Integer level;

    /**
     * 图片名称
     * img
     */
    private String img;

    /**
     * 父节点ID
     * parent_id
     */
    private Integer parentId;

    /**
     * 角色ID
     * role_id
     */
    private Integer roleId;

}