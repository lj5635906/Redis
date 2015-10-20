package com.redis.messagequeue.queue;

import com.redis.listener.PubSubListener;
import redis.clients.jedis.Jedis;

public abstract class MessageQueueMain implements Runnable{
	
	private Jedis jedis;
	public final static String topic = "redis_queue_1";
 
	public void init() {
		System.out.println("开始初始化消费者................");
		jedis = new Jedis("192.168.20.222", 6379);
		jedis.subscribe(new PubSubListener(this), topic);
	}
	
	public abstract void getMessage(String message);


	@Override
	public void run() {
		init();
	}
	
	public void startUp(){
		new Thread(this).start();
	}
	
	public void shoudown(){
		if(null != jedis){
			jedis.close();
		}
	}
	
}
