package com.hbnu.gradesign.entity.pojo;

import java.util.List;
import java.util.Map;

/**
 * 包装类
 * @param
 */
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public List<T> getObjs() {
		return objs;
	}

	public void setObjs(List<T> objs) {
		this.objs = objs;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
