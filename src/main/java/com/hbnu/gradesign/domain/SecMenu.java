package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 二级菜单表
 * menu
 * @author Fynce
 * @date 2019/03/27
 */
public class SecMenu implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public String getSecMenu() {
        return secMenu;
    }

    public void setSecMenu(String secMenu) {
        this.secMenu = secMenu;
    }

    public String getSecUrl() {
        return secUrl;
    }

    public void setSecUrl(String secUrl) {
        this.secUrl = secUrl;
    }

    public Integer getSecLevel() {
        return secLevel;
    }

    public void setSecLevel(Integer secLevel) {
        this.secLevel = secLevel;
    }

    @Override
    public String toString() {
        return "SecMenu{" +
                "secId=" + secId +
                ", secMenu='" + secMenu + '\'' +
                ", secUrl='" + secUrl + '\'' +
                ", secLevel=" + secLevel +
                '}';
    }
}