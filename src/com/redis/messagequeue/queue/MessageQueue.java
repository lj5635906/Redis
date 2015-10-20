package com.redis.messagequeue.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public abstract class MessageQueue extends JedisPubSub implements Runnable{
	
	/**
	 * 订阅的消息主题
	 */
	protected String topic;
	
	protected String host;
	
	protected int port;
	
	protected Jedis jedis;
	
	@Override
	public void run(){
		initQueue();
	}
	
	/**
	 * 初始化订阅频道
	 */
	public void initQueue(){
		jedis = new Jedis(host, port);
		jedis.subscribe(this, topic);
 	}
	
	/**
	 * 取得订阅的消息
	 */
	public void onMessage(String channel, String message) {
		getMessage(message);
	}
	
	/**
	 * 子类需要实现该抽象方法来处理MQ读取的数据
	 * 
	 * @param message
	 */
	public abstract void getMessage(String message);

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
