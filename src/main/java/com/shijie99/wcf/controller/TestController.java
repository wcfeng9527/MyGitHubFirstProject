package com.shijie99.wcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shijie99.wcf.model.StudentEntity;
import com.shijie99.wcf.service.StudentService;

@Controller
public class TestController {
	
	@Autowired
	private  StudentService studentService;
	@RequestMapping(value = "index.do")
	public void indexPage() {
		StudentEntity entity = studentService.getStudent("10000013");
		System.out.println("name：" + entity.getStudentName());
	}
}
