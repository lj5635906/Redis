package com.redis.messagequeue.producer;

import com.redis.simple.RedisPubAndSub;

/**
 * @author : Roger
 * @date : 2015-10-20
 * @desc : 使用redis做消息队列，此处为生产者
 */
public class Producer {
	
	// 消息标题
	public final static String topic = "redis_queue_1";
	
	/**
	 *   消息发布，通过redis消息发布来实现，为广播式
	 * @param topic
	 * 		消息标题，即redis订阅频道
	 * @param message
	 * 		发布内容
	 */
	public void sendMessage(String topic,String message){
		RedisPubAndSub.publish(topic, message);
	}
	
	public static void main(String[] mains){
		Producer producer = new Producer();
		for (int i = 0; i < 1000; i++) {
//			System.out.println("发送....."+i);
			producer.sendMessage(topic, "this is test!!"+i);
		}
	}
	
	
}
