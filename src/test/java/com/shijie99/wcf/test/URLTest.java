package com.shijie99.wcf.test;

import org.junit.Test;

import com.shijie99.wcf.util.Util;

public class URLTest {
	@Test
	public void test(){
		System.out.println(Util.transfer("", "http://localhost:8080/OneQueryAgent/airSearch.web?r=400"));
	}
}
