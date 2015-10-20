package com.redis.messagequeue.consumer;

import com.redis.messagequeue.queue.MessageQueueMain;

/**
 * @author : Roger
 * @date : 2015-10-20
 * @desc : 使用redis做消息队列，此处为消费者
 */
public class Consumer extends MessageQueueMain{
		
	@Override
	public void getMessage(String message) {
		System.out.println("MQ消息: "+ message);
	}
	
	
}
