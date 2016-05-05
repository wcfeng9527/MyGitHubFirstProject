package com.shijie99.wcf.test;

public class ThreadTest {
	public static void main(String[] args) {
		test();
	}

	private static final Object ojb = new Object();

	public static void test() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ojb) {
					System.out.println("做了一些事:1");
					try {
						ojb.wait();
						System.out.println("做了一些事:2");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				synchronized (ojb) {
//					System.out.println("做了一些事:3");
//					try {
////						ojb.notify();
////						System.out.println("做了一些事:4");
//						ojb.wait();
//						System.out.println("做了一些事:5");
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			}
//		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				synchronized (ojb) {
//					System.out.println("做了一些事:6");
//					ojb.notifyAll();
//					System.out.println("做了一些事:8");
//				}
//			}
//		}).start();
	}
}
