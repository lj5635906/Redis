package redis.clients.util;

import java.io.UnsupportedEncodingException;

import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author : Roger
 * @date : 2015-10-12
 * @desc : 字符转换(The only reason to have this is to be able to compatible with java 1.5)
 */
public class SafeEncoder {
	
	/**
	 * 将字符串数组转换为byte二维数组
	 * @param strs
	 * 		字符串数组
	 * @return byte[][](byte二维数组)
	 */
	public static byte[][] encodeMany(final String... strs) {
		byte[][] many = new byte[strs.length][];
		for (int i = 0; i < strs.length; i++) {
			many[i] = encode(strs[i]);
		}
		return many;
	}

	/**
	 * 将字符串转换为byte数组(UTF-8编码)
	 * @param str
	 * 		字符串
	 * @return byte[]
	 * 		
	 */
	public static byte[] encode(final String str) {
		try {
			if (str == null) {
				throw new JedisDataException(
						"value sent to redis cannot be null");
			}
			return str.getBytes(Protocol.CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new JedisException(e);
		}
	}

	public static String encode(final byte[] data) {
		try {
			return new String(data, Protocol.CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new JedisException(e);
		}
	}
}
