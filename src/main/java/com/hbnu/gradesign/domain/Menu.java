package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 菜单表
 * menu
 * @author Fynce
 * @date 2019/03/28
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}