package com.redis.cluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
/**
 * @author : Roger
 * @date : 2015-10-26
 * @desc : 	Redis集群连接
 */
public class ClusterPool {
	
	public static void main(String[] mains){
		
		
		Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
		clusterNodes.add(new HostAndPort("192.168.20.110", 7000));		
		clusterNodes.add(new HostAndPort("192.168.20.110", 7001));		
		clusterNodes.add(new HostAndPort("192.168.20.110", 7002));		
		clusterNodes.add(new HostAndPort("192.168.20.110", 7003));		
		clusterNodes.add(new HostAndPort("192.168.20.110", 7004));		
		clusterNodes.add(new HostAndPort("192.168.20.110", 7005));		
		
		JedisCluster cluster = new JedisCluster(clusterNodes);
		
//		cluster.set("foo", "jedis test");
//        String value = cluster.get("foo");
//        System.out.println("foo = " + value);
        // get cluster nodes
//        System.out.println("------- cluster nodes --------");
//        Map<String, JedisPool> nodes = cluster.getClusterNodes();
//        Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet().iterator();
//        while (iterNodes.hasNext()) {
//            Map.Entry<String, JedisPool> entry = iterNodes.next();
//            Jedis jedis = entry.getValue().getResource();
//            System.out.println("============");
//            System.out.println(entry.getKey());
//        }
////		cluster.auth(password);
////		cluster.set("", "testCluster");
//		System.out.println(cluster.flushAll());
		long start = System.currentTimeMillis();
		for (int j = 0; j < 2000000; j++) {
			System.out.println("测试"+j+" : "+cluster.set("test"+j, "test"+j));
		}
		long end = System.currentTimeMillis();
		long temp = end - start;
		System.out.println("时间："+(temp));
		System.out.println(2000000/temp);
//		for (int i = 0; i < 6; i++) {
//			Jedis jedis = new Jedis("192.168.20.110", 7000+i,15000);
//			System.out.println(i+"    "+jedis.ping());
//		}
//		
		
		
	}

 
}
