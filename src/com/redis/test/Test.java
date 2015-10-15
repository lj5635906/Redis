package com.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.redis.simple.RedisUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
//		RedisUtil.get("");
		
		
		
//		for (int i = 0; i < 100; i++) {
//			System.out.println("action"+i);
//			Jedis jedis = new Jedis("192.168.20.217", 6379);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) { 
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			jedis.set("action"+i, "action"+i);
//		}
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMaxIdle(5);
		config.setMinIdle(5);
		config.setMaxWaitMillis(1000);
		// 在获取连接的时候检查有效性, 默认false
		config.setTestOnBorrow(true);
		// 在获取返回结果的时候检查有效性, 默认false
		config.setTestOnReturn(true);
		
		JedisPool pool = new JedisPool(config,"192.168.20.217", 6379);
		
		for (int i = 0; i < 11; i++) {
			System.out.print("action"+i);
			Jedis jedis = pool.getResource();
			System.out.print(jedis);
			
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			pool.returnResource(jedis);
			System.out.println();
		}
		
		
	}

}
