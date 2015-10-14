package com.redis.connection;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.redis.simple.RedisUtil;
import com.redis.simple.base.BaseRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.SafeEncoder;

/**
 * 
 * @author : Roger
 * @date : 2015-10-12
 * @desc : redis分片
 */
public class JedisShard {

	public static void main(String[] args) {
		// JedisShardInfo shard = new JedisShardInfo("192.168.20.217", 6379);
		// shard.setPassword("password1");
		// shard.setTimeout(3000);
		// System.out.println(shard.createResource().get("test"));
		// for (int d = 0; d < 1100; d++) {
		// long start = System.currentTimeMillis();
		// for (int i = 0; i < 1000000; i++) {
		// if(System.currentTimeMillis() - start > 1000){
		// System.out.println(i);
		// break;
		// }
		// RedisUtil.get("Roger");
		// }
		// }

		// ExecutorService ex = Executors.newFixedThreadPool(20);
		//
		// for (int i = 0; i < 1000000; i++) {
		// ex.execute(new Runnable() {
		// @Override
		// public void run() {
		// RedisUtil.get("Roger");
		// }
		// });
		//
		// }
		//
		// for (int i = 0; i < 10; i++) {
		// System.out.println("总连接数"+i+"： "+BaseRedis.getNumAll());
		// System.out.println("活跃连接数"+i+"："+BaseRedis.getNumActive());
		// System.out.println("空闲连接数"+i+"： "+BaseRedis.getNumIdle());
		//
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		//

		// String host = "192.168.20.217";
		// int port = 6379;
		// int timeout = 5000;
		// Jedis jedis = new Jedis(host, port, timeout);
		// jedis.auth("password1");
		// System.out.println(jedis.get("Roger"));

		byte[] read = new byte[] { 42, 50, 13, 10, 36, 52, 13, 10, 65, 85, 84,
				72, 13, 10, 36, 57, 13, 10, 112, 97, 115, 115, 119, 111, 114,
				100, 49, 13, 10 };
		byte[] read1 = new byte[] { 50 };

//		System.out.println(SafeEncoder.encode(read1));

		byte[] read2 = new byte[]{58, 48, 13, 10};

		System.out.println(SafeEncoder.encode(read2));
 
	}

	public static byte[] intToByte(int number) {  
	    byte[] abyte = new byte[4];  
	    // "&" 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。  
	    abyte[0] = (byte) (0xff & number);  
	    // ">>"右移位，若为正数则高位补0，若为负数则高位补1  
	    abyte[1] = (byte) ((0xff00 & number) >> 8);  
	    abyte[2] = (byte) ((0xff0000 & number) >> 16);  
	    abyte[3] = (byte) ((0xff000000 & number) >> 24);  
	    return abyte;  
	}  

}
