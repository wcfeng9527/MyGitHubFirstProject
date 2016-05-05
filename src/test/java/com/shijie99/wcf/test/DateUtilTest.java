package com.shijie99.wcf.test;

import org.junit.Test;

import com.shijie99.wcf.util.DateUtil;

public class DateUtilTest {
	
	@Test
	public void test(){
		System.out.println(DateUtil.getFirstDayOfWeek());
		System.out.println(DateUtil.getLastDayOfWeek());
	}
}
