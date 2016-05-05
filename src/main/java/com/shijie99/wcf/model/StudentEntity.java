package com.shijie99.wcf.model;

import java.io.Serializable;
import java.util.Date;

public class StudentEntity implements Serializable{

	private static final long serialVersionUID = 6816783875968444857L;
	private ClassEntity classEntity;  
    private Date studentBirthday;  
    private String studentID;  
    private String studentName;  
    private String studentSex;
	public ClassEntity getClassEntity() {
		return classEntity;
	}
	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	@Override
	public String toString() {
		return "StudentEntity [classEntity=" + classEntity
				+ ", studentBirthday=" + studentBirthday + ", studentID="
				+ studentID + ", studentName=" + studentName + ", studentSex="
				+ studentSex + "]";
	}  
	
}
