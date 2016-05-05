package com.shijie99.wcf.dao;

import java.util.List;

import com.shijie99.wcf.model.ClassEntity;
import com.shijie99.wcf.model.StudentEntity;
import com.shijie99.wcf.model.TeacherEntity;

public interface StudentDao {
	public StudentEntity getStudent(String studentID);  
    
    public StudentEntity getStudentAndClass(String studentID);  
      
    public List<StudentEntity> getStudentAll();  
      
    public void insertStudent(StudentEntity entity);
    
    public void saveClass(ClassEntity entity);
    
    public void saveTeacher(TeacherEntity entity);
    
}
