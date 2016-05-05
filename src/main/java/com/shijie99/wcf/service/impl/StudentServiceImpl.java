package com.shijie99.wcf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shijie99.wcf.dao.StudentDao;
import com.shijie99.wcf.model.ClassEntity;
import com.shijie99.wcf.model.StudentEntity;
import com.shijie99.wcf.model.TeacherEntity;
import com.shijie99.wcf.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public StudentEntity getStudent(String studentID) {
		return studentDao.getStudent(studentID);
	}

	@Override
	public StudentEntity getStudentAndClass(String studentID) {
		return studentDao.getStudentAndClass(studentID);
	}

	@Override
	public List<StudentEntity> getStudentAll() {
		return studentDao.getStudentAll();
	}

	@Override
	public void insertStudent(StudentEntity entity) {
		studentDao.insertStudent(entity);
	}

	@Override
	@Transactional
	public void saveClass(ClassEntity entity) {
		studentDao.saveTeacher(entity.getTeacherEntity());
		studentDao.saveClass(entity);
	}

	@Override
	public void saveTeacher(TeacherEntity entity) {
		studentDao.saveTeacher(entity);
	}

}
