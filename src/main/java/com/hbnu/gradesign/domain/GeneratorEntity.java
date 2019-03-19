package com.hbnu.gradesign.domain;

import java.io.Serializable;

/**
 * 逆向工程实体类
 * 根据数据库表字段注释生成实体类时，自动添加注释
 */
public class GeneratorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private int delFlag;

	private String remarks;

	private Long createDate;

	private Long updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
}
