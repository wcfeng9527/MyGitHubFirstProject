package com.shijie99.wcf.test;

import java.util.concurrent.ThreadFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;

import org.apache.log4j.BasicConfigurator;

public class NettyTest {

	static{
		BasicConfigurator.configure();
	}
	
	public static void main(String[] args) {
		//这就是主要的服务启动器
		ServerBootstrap serverBootstrap =new ServerBootstrap();
		
		//=======================下面我们设置线程池
        //BOSS线程池
		EventLoopGroup boosLoopGroup = new NioEventLoopGroup(1);
		 //WORK线程池：这样的申明方式，主要是为了向读者说明Netty的线程组是怎样工作的
		ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
		//CPU个数
		int processorsNumber = Runtime.getRuntime().availableProcessors();
		System.out.println(processorsNumber);
	}
}
