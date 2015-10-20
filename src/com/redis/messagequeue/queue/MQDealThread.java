package com.redis.messagequeue.queue;

/**
 * 消息处理线程
 * 
 * @author Rita
 * 
 */
public class MQDealThread implements Runnable {

	private String message;

	private MessageQueueMain queue;

	public MQDealThread(String message, MessageQueueMain queue) {
		this.message = message;
		this.queue = queue;
	}

	@Override
	public void run() {
 		queue.getMessage(message);
	}

}
