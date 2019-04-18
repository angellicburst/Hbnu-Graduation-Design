package com.hbnu.gradesign.entity.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 逆向工程实体类
 * 根据数据库表字段注释生成实体类时，自动添加注释
 */
@Getter
@Setter
public class GeneratorEntity {

	private String id;

	private int delFlag;

	private String remarks;

	private Long createDate;

	private Long updateDate;

}
