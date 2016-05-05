package com.shijie99.wcf.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shijie99.wcf.model.ClassEntity;
import com.shijie99.wcf.model.StudentEntity;
import com.shijie99.wcf.model.TeacherEntity;
import com.shijie99.wcf.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/spring.xml","classpath:conf/spring-mybatis.xml"})
public class StudentTest {
	@Autowired
	private StudentService studentService;
	
	@Test
	public void test(){
		StudentEntity student = new StudentEntity();
		student.setStudentName("lucy");
		Calendar c = Calendar.getInstance();
		c.set(1988, 02, 01);
		student.setStudentBirthday(c.getTime());
		student.setStudentID("1255");
		student.setStudentSex("woman");
		studentService.insertStudent(student);
		System.out.println(studentService.getStudentAll());
	}
	
	@Test
	public void test2(){
		TeacherEntity teacher = new TeacherEntity();
		teacher.setTeacherID("t11116");
		teacher.setTeacherName("teacher6");
		teacher.setTeacherSex("man");
		teacher.setTeacherBirthday(new Date());
		teacher.setWorkDate(new Date());
		ClassEntity cla = new ClassEntity();
		cla.setClassID("c11116");
		cla.setClassName("class6");
		cla.setClassYear("6");
		cla.setTeacherEntity(teacher);
		studentService.saveClass(cla);
	}
	
	@Test
	public void test3(){
		
	}
}
