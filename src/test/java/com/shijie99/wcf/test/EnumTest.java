package com.shijie99.wcf.test;

public class EnumTest {
	public static void main(String[] args) {
		Feature f = Feature.AllowArbitraryCommas;
		Feature s = f;
		System.out.println(s);
	}
}
