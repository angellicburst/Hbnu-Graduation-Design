package com.hbnu.gradesign.bean;

import java.util.List;

public class TestData<T> {
	private Integer code;	//操作码
	private String msg;		//返回信息
	private Integer count;	//总页数

	private List<T> data;	//数据的对象集合
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
