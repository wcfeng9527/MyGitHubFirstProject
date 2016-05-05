package com.shijie99.wcf.test;

import org.junit.Test;

import com.shijie99.wcf.util.QMAllLogQueueUtil;

public class QueueTest {
	
	// 没有具体的演示含义；
   
	@Test
	public void test(){
		String s = "测试";
		int i=0;
		int j=0;
		while(j<i+100){
			j++;
			QMAllLogQueueUtil.offerLog(s+j);
		}
		testThread1 thread1 = new testThread1();
		thread1.start();
		testThread2 thread2 = new testThread2();
		thread2.start();
		try {
			Thread.sleep(5*1000);
			System.out.println("睡5秒");
			i = 100;
			while(j<i+100){
				j++;
				QMAllLogQueueUtil.offerLog(s+j);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		 // 只是为了保证ParentNotifyThread不会退出
	    synchronized (QueueTest.class) {
	        try {
	        	QueueTest.class.wait();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	private class testThread1 extends Thread{
		@Override
		public void run() {
			while(true){
				System.out.println("testThread1 开始...");
				try{
					String s = QMAllLogQueueUtil.pollLog();
					while(s!=null){
						System.out.println(s);
						s = QMAllLogQueueUtil.pollLog();
					}
					System.out.println("睡5秒");
					sleep(5*1000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	private class testThread2 extends Thread{
		@Override
		public void run() {
			while(true){
				System.out.println("testThread2 开始...");
				try{
					String s = QMAllLogQueueUtil.takeLog();
					while(s!=null){
						System.out.println(s);
						s = QMAllLogQueueUtil.takeLog();
					}
					System.out.println("睡5秒");
					sleep(5*1000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
