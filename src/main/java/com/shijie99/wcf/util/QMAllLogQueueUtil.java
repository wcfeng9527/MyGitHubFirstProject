package com.shijie99.wcf.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;


/**
 * 缓存监控日志工具类
 */
public class QMAllLogQueueUtil {
	
	private static Logger logger = Logger.getLogger(QMAllLogQueueUtil.class);
	private static final BlockingQueue<String> CMLOG_QUEUE = new ArrayBlockingQueue<>(10000);

	public static void offerLog(String qmLog) {
		if (!CMLOG_QUEUE.offer(qmLog)) {
			logger.error("QUEUE_ERROR CMLog入队列失败");
		}
	}
	
	public static String takeLog() {
		try {
			return CMLOG_QUEUE.take();
		} catch (InterruptedException e) {
			logger.error("QUEUE_ERROR CMLog出队列失败.",e);
			return null;
		}
	}
	
	public static String pollLog() {
		return CMLOG_QUEUE.poll();
		
	}
}
