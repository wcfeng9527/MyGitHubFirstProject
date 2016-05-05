package com.shijie99.wcf.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class NettyTest {

	static {
		BasicConfigurator.configure();
	}

	public static void main(String[] args) {
		// 这就是主要的服务启动器
		ServerBootstrap serverBootstrap = new ServerBootstrap();

		// =======================下面我们设置线程池
		// BOSS线程池
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		// WORK线程池：这样的申明方式，主要是为了向读者说明Netty的线程组是怎样工作的
		ThreadFactory threadFactory = new DefaultThreadFactory(
				"work thread pool");
		// CPU个数
		int processorsNumber = Runtime.getRuntime().availableProcessors();
		EventLoopGroup workLoopGroup = new NioEventLoopGroup(
				processorsNumber * 2, threadFactory,
				SelectorProvider.provider());
		// 顶顶Netty的boss线程和work线程
		serverBootstrap.group(bossLoopGroup, workLoopGroup);

		// 如果是以下的申明方式，说明BOSS线程和WORK线程共享一个线程池
		// （实际上一般的情况环境下，这种共享线程池的方式已经够了）
		// serverBootstrap.group(workLoogGroup);

		// ========================下面我们设置我们服务的通道类型
		// 只能是实现了ServerChannel接口的“服务器”通道类
		serverBootstrap.channel(NioServerSocketChannel.class);
		// 当然也可以这样创建（那个SelectorProvider是不是感觉很熟悉？）
		// serverBootstrap.channelFactory(new
		// ChannelFactory<NioServerSocketChannel>() {
		// @Override
		// public NioServerSocketChannel newChannel() {
		// return new NioServerSocketChannel(SelectorProvider.provider());
		// }
		// });

		serverBootstrap
				.childHandler(new ChannelInitializer<NioSocketChannel>() {

					@Override
					protected void initChannel(NioSocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new ByteArrayEncoder());
						ch.pipeline().addLast(new TCPServerHandler());
						ch.pipeline().addLast(new ByteArrayDecoder());
					}

				});

		// ========================设置netty服务器绑定的ip和端口
		serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 83));
		// 还可以监控多个端口
		// serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 84));
	}
}


class TCPServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * 日志
	 */
	private static Logger LOGGER = Logger.getLogger(TCPServerHandler.class);
	/**
	 * 每一个channel，都有独立的handler、ChannelHandlerContext、ChannelPipeline、Attribute
	 * 所以不需要担心多个channel中的这些对象相互影响。<br>
	 * 这里我们使用content这个key，记录这个handler中已经接收到的客户端信息。
	 */
	private static AttributeKey<StringBuffer> content = AttributeKey
			.valueOf("content");

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.channelRegistered(ctx)");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.channelUnregistered(ctx)");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.channelInactive(ctx)");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		TCPServerHandler.LOGGER.info("channelRead(ChannelHandlerContext ctx, Object msg)");
		/*
		 * 我们使用IDE工具模拟长连接中的数据缓慢提交。 由read方法负责接收数据，但只是进行数据累加，不进行任何处理
		 */
		ByteBuf byteBuf = (ByteBuf) msg;
		try {
			StringBuffer contextBuffer = new StringBuffer();

			while (byteBuf.isReadable()) {
				contextBuffer.append((char) byteBuf.readByte());
			}

			StringBuffer content = ctx.attr(TCPServerHandler.content).get();
			if (content == null) {
				content = new StringBuffer();
				ctx.attr(TCPServerHandler.content).set(content);
			}
			
			content.append(contextBuffer);
			TCPServerHandler.LOGGER.info(URLDecoder.decode(content.toString(),"utf-8"));
			ctx.writeAndFlush(URLEncoder.encode("返回数据", "UTF-8").getBytes());
			ctx.close();
		} catch (Exception e) {
			throw e;
		} finally {
			byteBuf.release();
		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		TCPServerHandler.LOGGER.info("super.channelWritabilityChanged(ctx)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelInboundHandlerAdapter#channelWritabilityChanged
	 * (io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.channelWritabilityChanged(ctx)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty
	 * .channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		TCPServerHandler.LOGGER.info("super.exceptionCaught(ctx, cause)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.
	 * ChannelHandlerContext)
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.handlerAdded(ctx)");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.netty.channel.ChannelHandlerAdapter#handlerRemoved(io.netty.channel
	 * .ChannelHandlerContext)
	 */
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		TCPServerHandler.LOGGER.info("super.handlerRemoved(ctx)");
	}
}
