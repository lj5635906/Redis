package com.redis.connection;

import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * @author : Roger
 * @date : 2015-10-13
 * @desc : socket连接redis服务(通过OutputStream,InputStream)
 */
public class RedisFileInputStream extends FilterInputStream {

	protected RedisFileInputStream(InputStream in) {
		super(in);
 	}

	 
}
