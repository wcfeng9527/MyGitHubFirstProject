package com.shijie99.wcf.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class SyncThread implements Runnable {

	/**
     * 日志
     */
    private static final Logger LOGGER = Logger.getLogger(SyncThread.class);
    private  Integer value;

    
    private static Integer NOWVALUE = 0;

    static {
        BasicConfigurator.configure();
    }
	
    public SyncThread(){
    	value = 0;
    	LOGGER.info("value:"+value);
    }
    public SyncThread(Integer num){
    	value = num;
    	LOGGER.info("value:"+value);
    }
    
    private void doSomething(Long id){
    	synchronized(NOWVALUE){
    		NOWVALUE = value;
        	LOGGER.info("id:"+id+",当前NOWVALUE值："+NOWVALUE);
    	}
    		
    }
	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
	    Long id = currentThread.getId();
	    LOGGER.info(id);
		doSomething(id);
	}
	
	public static void main(String[] args) throws Exception {
		Thread syncThread1 = new Thread(new SyncThread(10));
		Thread syncThread2 = new Thread(new SyncThread(100));
		Thread syncThread3 = new Thread(new SyncThread());

		syncThread1.start();
		syncThread2.start();
		syncThread3.start();
	}

}
