package com.shijie99.wcf.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SelectChannelTest {
	static{
		BasicConfigurator.configure();
	}
	/**
     * 日志
     */
    private static final Log LOGGER = LogFactory.getLog(SelectChannelTest.class);
    
	public static void main(String[] args) throws IOException {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			ServerSocket serverSocket = serverChannel.socket();
			serverSocket.setReuseAddress(true);
			serverSocket.bind(new InetSocketAddress(83));
			
			
			Selector selector = Selector.open();
			
			serverChannel.register(selector, SelectionKey.OP_ACCEPT	);
			try{
				while(true){
					if(selector.select(100)==-1){
						continue;
					}
					Iterator<SelectionKey> selectionKeys=selector.selectedKeys().iterator();
					
					while(selectionKeys.hasNext()){
						SelectionKey readyKey= selectionKeys.next();
						selectionKeys.remove();
						
						SelectableChannel selectableChannel = readyKey.channel();
						if(readyKey.isValid() && readyKey.isAcceptable()){
							SelectChannelTest.LOGGER.info("==========channel 通道已经准备好了===========");
							ServerSocketChannel serverSocketChannel=(ServerSocketChannel)selectableChannel;
							SocketChannel socketChannel=serverSocketChannel.accept();
							registerSocketChannel(socketChannel,selector);
						}else if(readyKey.isValid() && readyKey.isConnectable()){
							SelectChannelTest.LOGGER.info("==========socket channel 建立连接===========");
						}else if(readyKey.isValid() && readyKey.isReadable()){
							SelectChannelTest.LOGGER.info("==========sicket channel 数据准备好了，可以去读==读取===========");
							readSocketChannel(readyKey);
						}
						
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			
	}
	
	private static void registerSocketChannel(SocketChannel socketChannel,Selector selector) throws IOException{
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ,ByteBuffer.allocate(2048));
	}
	
	private static void readSocketChannel(SelectionKey readyKey) throws Exception {
		SocketChannel clientSocketChannel = (SocketChannel)readyKey.channel();
		InetSocketAddress sourceSocketAddress  =(InetSocketAddress)clientSocketChannel.getRemoteAddress();
		Integer resourcePort = sourceSocketAddress.getPort();
		
		ByteBuffer contextBytes = (ByteBuffer)readyKey.attachment();
		int readLen = -1;
		try{
			readLen = clientSocketChannel.read(contextBytes);
		}catch (Exception e) {
			SelectChannelTest.LOGGER.error(e.getMessage());
			clientSocketChannel.close();
			return;
		}
		
		if(readLen ==-1){
			SelectChannelTest.LOGGER.info("====缓存区没有数据?====");
			return;
		}
		contextBytes.flip();
		byte[] messageBytes = contextBytes.array();
		String messageEncode = new String(messageBytes,"UTF-8");
		String message = URLDecoder.decode(messageEncode,"UTF-8");
		if(message.indexOf("over") !=-1){
			contextBytes.clear();
			SelectChannelTest.LOGGER.info("端口："+resourcePort+"客户端发来的信息=======message："+message);
			ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", "UTF-8").getBytes());
			clientSocketChannel.write(sendBuffer);
			clientSocketChannel.close();
		}else{
			SelectChannelTest.LOGGER.info("端口："+resourcePort+"客户端信息还未接受完，继续接受=======message："+message);
			contextBytes.position(readLen);
			contextBytes.limit(contextBytes.capacity());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
