package com.redis.simple;

import java.util.Set;

import redis.clients.jedis.Jedis;

import com.redis.simple.base.BaseRedis;

/**
* RedisSetUtil
* @author Roger
*/
public class RedisSetUtil extends BaseRedis {

	private RedisSetUtil(){
		
	}
	
     /**
     * 向Set添加一条记录，如果member已存在返回0,否则返回1
     *
     * @param String
     *            key
     * @param String
     *            member
     * @return 操作码,0或1
     * */
     public static long sadd(String key, String member) {
          Jedis jedis = getJedis();
          long s = jedis.sadd(key, member);
          returnJedis(jedis);
          return s;
     }

     public static long sadd(byte[] key, byte[] member) {
          Jedis jedis = getJedis();
          long s = jedis.sadd(key, member);
          returnJedis(jedis);
          return s;
     }

     /**
     * 获取给定key中元素个数
     *
     * @param String
     *            key
     * @return 元素个数
     * */
     public static long scard(String key) {
          Jedis sjedis = getJedis();
          long len = sjedis.scard(key);
          returnJedis(sjedis);
          return len;
     }

     /**
     * 返回从第一组和所有的给定集合之间的差异的成员
     *
     * @param String
     *            ... keys
     * @return 差异的成员集合
     * */
     public static Set<String> sdiff(String... keys) {
          Jedis jedis = getJedis();
          Set<String> set = jedis.sdiff(keys);
          returnJedis(jedis);
          return set;
     }

     /**
     * 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param String
     *            newkey 新结果集的key
     * @param String
     *            ... keys 比较的集合
     * @return 新集合中的记录数
     * **/
     public static long sdiffstore(String newkey, String... keys) {
          Jedis jedis = getJedis();
          long s = jedis.sdiffstore(newkey, keys);
          returnJedis(jedis);
          return s;
     }

     /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set
     *
     * @param String
     *            ... keys
     * @return 交集成员的集合
     * **/
     public static Set<String> sinter(String... keys) {
          Jedis jedis = getJedis();
          Set<String> set = jedis.sinter(keys);
          returnJedis(jedis);
          return set;
     }

     /**
     * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param String
     *            newkey 新结果集的key
     * @param String
     *            ... keys 比较的集合
     * @return 新集合中的记录数
     * **/
     public static long sinterstore(String newkey, String... keys) {
          Jedis jedis = getJedis();
          long s = jedis.sinterstore(newkey, keys);
          returnJedis(jedis);
          return s;
     }

     /**
     * 确定一个给定的值是否存在
     *
     * @param String
     *            key
     * @param String
     *            member 要判断的值
     * @return 存在返回1，不存在返回0
     * **/
     public static boolean sismember(String key, String member) {
          Jedis sjedis = getJedis();
          boolean s = sjedis.sismember(key, member);
          returnJedis(sjedis);
          return s;
     }
    
     /**
     * 返回集合中的所有成员
     *
     * @param String
     *            key
     * @return 成员集合
     * */
     public static Set<String> smembers(String key) {
          Jedis sjedis = getJedis();
          Set<String> set = sjedis.smembers(key);
          returnJedis(sjedis);
          return set;
     }

     public static Set<byte[]> smembers(byte[] key) {
          Jedis sjedis = getJedis();
          Set<byte[]> set = sjedis.smembers(key);
          returnJedis(sjedis);
          return set;
     }

     /**
     * 将成员从源集合移出放入目标集合 <br/>
     * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0<br/>
     * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
     *
     * @param String
     *            srckey 源集合
     * @param String
     *            dstkey 目标集合
     * @param String
     *            member 源集合中的成员
     * @return 状态码，1成功，0失败
     * */
     public static long smove(String srckey, String dstkey, String member) {
          Jedis jedis = getJedis();
          long s = jedis.smove(srckey, dstkey, member);
          returnJedis(jedis);
          return s;
     }

     /**
     * 从集合中删除成员
     *
     * @param String
     *            key
     * @return 被删除的成员
     * */
     public static String spop(String key) {
          Jedis jedis = getJedis();
          String s = jedis.spop(key);
          returnJedis(jedis);
          return s;
     }

     /**
     * 从集合中删除指定成员
     *
     * @param String
     *            key
     * @param String
     *            member 要删除的成员
     * @return 状态码，成功返回1，成员不存在返回0
     * */
     public static long srem(String key, String member) {
          Jedis jedis = getJedis();
          long s = jedis.srem(key, member);
          returnJedis(jedis);
          return s;
     }

     /**
     * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存<br/>
     *
     * @param String
     *            ... keys
     * @return 合并后的结果集合
     * @see sunionstore
     * */
     public static Set<String> sunion(String... keys) {
          Jedis jedis = getJedis();
          Set<String> set = jedis.sunion(keys);
          returnJedis(jedis);
          return set;
     }

     /**
     * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖
     *
     * @param String
     *            newkey 新集合的key
     * @param String
     *            ... keys 要合并的集合
     * **/
     public static long sunionstore(String newkey, String... keys) {
          Jedis jedis = getJedis();
          long s = jedis.sunionstore(newkey, keys);
          returnJedis(jedis);
          return s;
     }
    

}