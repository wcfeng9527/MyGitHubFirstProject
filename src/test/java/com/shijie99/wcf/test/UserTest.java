package com.shijie99.wcf.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shijie99.wcf.model.User;
import com.shijie99.wcf.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations={"classpath:conf/spring.xml", "classpath:conf/spring-mybatis.xml"}) // 加载配置
public class UserTest {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		User user = new User();
		user.setNickname("你好");
		user.setState(2);
		System.out.println(userService.insertUser(user));
	}
}
