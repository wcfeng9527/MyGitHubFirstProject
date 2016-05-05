package com.shijie99.wcf.test;

import java.util.Iterator;
import java.util.Map;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;


public class memcachedtest {
	public static void main(String[] args) {
		MemCachedClient mcc = new MemCachedClient("priceadd");
		SockIOPool pool = SockIOPool.getInstance("priceadd");
		String[] services = {"192.168.5.237:11211"};
		Integer[] weights = {3};
        // 设置服务器信息
        pool.setServers(services);
        pool.setWeights(weights );

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn( 5 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaxIdle( 1000 * 60 * 60 * 6 );

        // 设置主线程的睡眠时间
        pool.setMaintSleep( 30 );

        // 设置TCP的参数，连接超时等
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setSocketConnectTO( 0 );
        pool.setAliveCheck( true );
        // 初始化连接池
        pool.initialize();  
		String key = "BLICKLIST_IPCCGROUP";
        if(mcc.keyExists(key)){
        	@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String,Object>)mcc.get(key);
        	Iterator<String> it = map.keySet().iterator();
        	while(it.hasNext()){
        		String keys = it.next();
        		System.out.println(keys+":"+map.get(keys));
        	}
    	}
	}
}
