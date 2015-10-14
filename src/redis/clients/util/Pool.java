package redis.clients.util;

import java.io.Closeable;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.redis.util.Log;

import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

public abstract class Pool<T> implements Closeable {
	protected GenericObjectPool<T> internalPool;

	/**
	 * Using this constructor means you have to set and initialize the
	 * internalPool yourself.
	 */
	public Pool() {
	}

	/**
	 * 释放资源
	 */
	@Override
	public void close() {
		closeInternalPool();
	}

	/**
	 * 验证资源是否释放
	 * @return
	 */
	public boolean isClosed() {
		return this.internalPool.isClosed();
	}
	
	/**
	 *  构造函数
	 * @param poolConfig
	 * 		连接池配置信息
	 * @param factory
	 * 		池工厂
	 */
	public Pool(final GenericObjectPoolConfig poolConfig,
			PooledObjectFactory<T> factory) {
		initPool(poolConfig, factory);
	}
	
	/**
	 * 初始化连接池
	 * @param poolConfig
	 * @param factory
	 */
	public void initPool(final GenericObjectPoolConfig poolConfig,
			PooledObjectFactory<T> factory) {
 		if (this.internalPool != null) {
			try {
				// 释放pool资源
				closeInternalPool();
			} catch (Exception e) {
			}
		}
		this.internalPool = new GenericObjectPool<T>(factory, poolConfig);
	}

	public T getResource() {
		try {
			return internalPool.borrowObject();
		} catch (Exception e) {
			throw new JedisConnectionException(
					"Could not get a resource from the pool", e);
		}
	}

	public void returnResourceObject(final T resource) {
		if (resource == null) {
			return;
		}
		try {
			internalPool.returnObject(resource);
		} catch (Exception e) {
			throw new JedisException(
					"Could not return the resource to the pool", e);
		}
	}

	public void returnBrokenResource(final T resource) {
		if (resource != null) {
			returnBrokenResourceObject(resource);
		}
	}

	public void returnResource(final T resource) {
		if (resource != null) {
			returnResourceObject(resource);
		}
	}

	public void destroy() {
		closeInternalPool();
	}

	protected void returnBrokenResourceObject(final T resource) {
		try {
			internalPool.invalidateObject(resource);
		} catch (Exception e) {
			throw new JedisException(
					"Could not return the resource to the pool", e);
		}
	}
	
	/**
	 * 释放内部资源
	 */
	protected void closeInternalPool() {
		try {
			internalPool.close();
		} catch (Exception e) {
			throw new JedisException("Could not destroy the pool", e);
		}
	}
}
