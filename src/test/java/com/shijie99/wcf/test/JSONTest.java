package com.shijie99.wcf.test;

import net.sf.json.JSONObject;

import org.junit.Test;

public class JSONTest {

	@Test
	public void JSONNullTest(){
//		String jsonStr ="{\"test\":null}";
//		JSONObject json = JSONObject.fromObject(jsonStr);
//		System.out.println(json.get("test").equals("null"));
		String s = "{\"routing\":null}";
		JSONObject json = JSONObject.fromObject(s);
		if(json.containsKey("routing")){
			System.out.println(json.get("routing")==null);
		}
	}
	
	
}
