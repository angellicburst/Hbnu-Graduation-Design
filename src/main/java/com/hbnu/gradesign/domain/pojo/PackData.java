package com.hbnu.gradesign.domain.pojo;

import java.util.List;
import java.util.Map;

/**
 * 包装类
 * @param <T>
 */
public class PackData<T> {

	/**
	 * 返回码
	 */
	private Integer recode;

	/**
	 * 返回信息
	 */
	private String remsg;

	/**
	 * 返回对象
	 */
	private T reobj;

	/**
	 * 返回对象集合
	 */
	private List<T> reobjs;

	/**
	 * 返回参数
	 */
	private Map<String ,String> reparams;

	public Integer getRecode() {
		return recode;
	}

	public void setRecode(Integer recode) {
		this.recode = recode;
	}

	public String getRemsg() {
		return remsg;
	}

	public void setRemsg(String remsg) {
		this.remsg = remsg;
	}

	public T getReobj() {
		return reobj;
	}

	public void setReobj(T reobj) {
		this.reobj = reobj;
	}

	public List<T> getReobjs() {
		return reobjs;
	}

	public void setReobjs(List<T> reobjs) {
		this.reobjs = reobjs;
	}

	public Map<String, String> getReparams() {
		return reparams;
	}

	public void setReparams(Map<String, String> reparams) {
		this.reparams = reparams;
	}
}
