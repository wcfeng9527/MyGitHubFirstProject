package com.shijie99.wcf.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;

import com.shijie99.wcf.model.StudentEntity;

public class SpringTest {
	@Test  
	public void test() throws Exception {  
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		ListenableFutureTask<StudentEntity> task = new ListenableFutureTask<StudentEntity>(new Callable() {  
	        @Override  
	        public Object call() throws Exception {  
	            Thread.sleep(1 * 1000L);  
	            System.out.println("=======task execute");  
	            return new StudentEntity();  
	        }  
	    });  
	  
	    task.addCallback(new ListenableFutureCallback<StudentEntity>() {  
	        @Override  
	        public void onSuccess(StudentEntity result) {  
	        	System.out.println(result);
	            System.out.println("===success callback 1");  
	        }  
	  
	        @Override  
	        public void onFailure(Throwable t) {  
	        }  
	    });  
	  
	    task.addCallback(new ListenableFutureCallback<StudentEntity>() {  
	        @Override  
	        public void onSuccess(StudentEntity result) {  
	            System.out.println("===success callback 2");  
	        }  
	  
	        @Override  
	        public void onFailure(Throwable t) {  
	        }  
	    });  
	  
	    ExecutorService executorService = Executors.newSingleThreadExecutor();  
	    executorService.submit(task);  
	    task.get();
//	    StudentEntity result = task.get();  
//	    System.out.println(result);  
	  
	} 
	
    @Test  
    public void testFixedBackOff() {  
        long interval = 100;  
        long maxAttempts = 10;  
        BackOff backOff = new FixedBackOff(interval, maxAttempts);  
        BackOffExecution execution = backOff.start();  
      
        for(int i = 1; i <= 10; i++) {  
            //每次重试时间是100毫秒  
            System.out.println(execution.nextBackOff());  
        }  
        Assert.assertEquals(BackOffExecution.STOP, execution.nextBackOff());  
    }  
}
