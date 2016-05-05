package com.shijie99.wcf.model;

import java.util.List;

public class ClassEntity {
	private String classID;
	private String className;
	private String classYear;
	private TeacherEntity teacherEntity;
	private List<StudentEntity> studentList;
	public String getClassID() {
		return classID;
	}
	public void setClassID(String classID) {
		this.classID = classID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassYear() {
		return classYear;
	}
	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}
	public TeacherEntity getTeacherEntity() {
		return teacherEntity;
	}
	public void setTeacherEntity(TeacherEntity teacherEntity) {
		this.teacherEntity = teacherEntity;
	}
	public List<StudentEntity> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<StudentEntity> studentList) {
		this.studentList = studentList;
	}
	@Override
	public String toString() {
		return "ClassEntity [classID=" + classID + ", className=" + className
				+ ", classYear=" + classYear + ", teacherEntity="
				+ teacherEntity + ", studentList=" + studentList + "]";
	}
}
