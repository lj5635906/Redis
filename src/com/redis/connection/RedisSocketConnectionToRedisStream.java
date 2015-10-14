package com.redis.connection;

import java.net.InetSocketAddress;
import java.net.Socket;
import redis.clients.util.RedisInputStream;
import redis.clients.util.RedisOutputStream;
import redis.clients.util.SafeEncoder;

/**
 * @author : Roger
 * @date : 2015-10-13
 * @desc : socket连接redis服务(通过RedisOutputStream,RedisInputStream)
 */
public class RedisSocketConnectionToRedisStream {
	public static final byte DOLLAR_BYTE = '$';
	public static final byte ASTERISK_BYTE = '*';

	public static void main(String[] mains) {

		String host = "192.168.20.217";
		int port = 6379;
		int timeout = 2000;
		try {
			
			Socket socket = new Socket();
			socket.setReuseAddress(true);
			socket.setKeepAlive(true); // Will monitor the TCP connection is
			socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to
			socket.setSoLinger(true, 0); // Control calls close () method,

			socket.connect(new InetSocketAddress(host, port), timeout);
			socket.setSoTimeout(timeout);
			RedisOutputStream outputStream = new RedisOutputStream(
					socket.getOutputStream());
			RedisInputStream inputStream = new RedisInputStream(
					socket.getInputStream());
			
			String action = "GET";
			String[] key = { "test" };

			byte[][] args = new byte[key.length][];
			for (int i = 0; i < key.length; i++) {
				args[i] = SafeEncoder.encode(key[i]);
			}
			
			byte[] command = SafeEncoder.encode(action);
			// 命令

			outputStream.write(ASTERISK_BYTE);
			outputStream.writeIntCrLf(args.length + 1);
			outputStream.write(DOLLAR_BYTE);
			outputStream.writeIntCrLf(command.length);
			outputStream.write(command);
			outputStream.writeCrLf();
			
			for (final byte[] arg : args) {
				outputStream.write(DOLLAR_BYTE);
				outputStream.writeIntCrLf(arg.length);
				outputStream.write(arg);
				outputStream.writeCrLf();
			}

			outputStream.flush();

			inputStream.readByte();
			// int len = inputStream.readIntCrLf();
			// System.out.println(len);
			byte[] read = inputStream.readLineBytes();
			System.out.println(read);
			// byte[] read = new byte[len];
			// int offset = 0;
			// while (offset < len) {
			// int size = inputStream.read(read, offset, (len - offset));
			// if (size == -1)
			// throw new JedisConnectionException(
			// "It seems like server has closed the connection.");
			// offset += size;
			// }
			// inputStream.readByte();
			// inputStream.readByte();
			//
			System.out.println("返回值： " + SafeEncoder.encode(read));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
