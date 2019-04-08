package com.hbnu.gradesign.domain.dto;

import com.hbnu.gradesign.domain.Student;

public class StudentDto extends Student {

	private String department;

	private String major;

	private String cla;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}
}
