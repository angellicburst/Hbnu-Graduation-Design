package com.hbnu.gradesign.entity.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 包装类
 * @param
 */
@Getter
@Setter
public class PackData<T> {

	/**
	 * 返回码
	 */
	private Integer code;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 返回数据条数
	 */
	private Integer count;

	/**
	 * 返回对象
	 */
	private T obj;

	/**
	 * 返回对象集合
	 */
	private List<T> objs;

	/**
	 * 返回参数
	 */
	private Map<String, String> params;

}
