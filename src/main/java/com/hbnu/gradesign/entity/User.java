package com.hbnu.gradesign.entity;

/**
 * 用户表
 * user
 * @author Fynce
 * @date 2019/03/04
 */
public class User {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", status=" + status +
				'}';
	}
}