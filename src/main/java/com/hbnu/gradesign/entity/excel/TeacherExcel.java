package com.hbnu.gradesign.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 解析excel的teacher实体类
 * @author Fynce
 * @date 2019/04/08
 */
@Getter
@Setter
public class TeacherExcel extends BaseRowModel {

	@ExcelProperty(index = 0 , value = "ID")
	private String id;

	/**
	 * 教师姓名
	 * name
	 */
	@ExcelProperty(index = 1 , value = "姓名")
	private String name;

	/**
	 * 教师性别
	 ---0：男
	 ---1：女
	 * gender
	 */
	@ExcelProperty(index = 2 , value = "性别（0：男    1：女）")
	private Integer gender;

	/**
	 * 教师工龄
	 * year
	 */
	@ExcelProperty(index = 5 , value = "工龄")
	private Integer year;

	/**
	 * 教师电话
	 * phone
	 */
	@ExcelProperty(index = 3 , value = "电话")
	private String phone;

	/**
	 * 教师邮箱
	 * email
	 */
	@ExcelProperty(index = 4 , value = "邮箱")
	private String email;

	/**
	 * 状态
	 ---0：有效
	 ---1：失效
	 * status
	 */
	@ExcelProperty(index = 6 , value = "状态(0:启用   1：停用)")
	private Integer status;

	/**
	 * 教师院系
	 * departmentId
	 */
	@ExcelProperty(index = 7 , value = "院系（填写院系ID）")
	private Integer departmentId;

	/**
	 * 用户ID
	 * user_id
	 */
	private Integer userId;
}
