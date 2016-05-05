package com.shijie99.wcf.model;

import java.util.Date;

public class TeacherEntity {
	private String teacherID;
	private String teacherName;
	private String teacherSex;
	private Date teacherBirthday;
	private Date workDate;
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherSex() {
		return teacherSex;
	}
	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}
	public Date getTeacherBirthday() {
		return teacherBirthday;
	}
	public void setTeacherBirthday(Date teacherBirthday) {
		this.teacherBirthday = teacherBirthday;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	@Override
	public String toString() {
		return "TeacherEntity [teacherID=" + teacherID + ", teacherName="
				+ teacherName + ", teacherSex=" + teacherSex
				+ ", teacherBirthday=" + teacherBirthday + ", workDate="
				+ workDate + "]";
	}
}
