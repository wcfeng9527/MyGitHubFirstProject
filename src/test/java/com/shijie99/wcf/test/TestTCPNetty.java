package com.shijie99.wcf.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;

public class TestTCPNetty {
	public static void main(String[] args) {
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		
		ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
		//获取cpu个数
		int processorsNumber = Runtime.getRuntime().availableProcessors();
		
		EventLoopGroup workLoopGroup = new NioEventLoopGroup(processorsNumber*2,threadFactory,SelectorProvider.provider());
		
		serverBootstrap.group(bossLoopGroup, workLoopGroup);
		
		serverBootstrap.channel(NioServerSocketChannel.class);
		
		serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
			@Override
			protected void initChannel(NioSocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ByteArrayEncoder());
			}
		});
		
		
	}
}
