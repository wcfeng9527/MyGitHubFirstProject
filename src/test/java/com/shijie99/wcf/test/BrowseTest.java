package com.shijie99.wcf.test;

import java.io.IOException;

import org.junit.Test;

public class BrowseTest {
	/**
	 * 打开指定浏览器
	 * @throws IOException   
	 * @author wangcf
	 * @date 2016-4-18 下午4:46:54
	 */
	@Test
	public void test() throws IOException{
		String url = "https://www.baidu.com/";
		ProcessBuilder builder = new ProcessBuilder(
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", url);
		builder.start();
	}
	/**
	 * 打开默认浏览器
	 * @author wangcf
	 * @date 2016-4-18 下午4:47:42
	 */
	@Test
	public void test2(){
		String url = "http://www.jb51.net/";
		java.net.URI uri = java.net.URI.create(url);
		// 获取当前系统桌面扩展
		java.awt.Desktop dp = java.awt.Desktop.getDesktop();
		// 判断系统桌面是否支持要执行的功能
		if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
			try {
				dp.browse(uri);
			} catch (IOException e) {
				e.printStackTrace();
			}// 获取系统默认浏览器打开链接
		}
	}
}
