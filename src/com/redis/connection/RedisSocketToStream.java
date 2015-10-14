package com.redis.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import redis.clients.util.SafeEncoder;

/**
 * @author : Roger
 * @date : 2015-10-13
 * @desc : socket连接redis服务(通过OutputStream,InputStream)
 */
public class RedisSocketToStream {

	public static final String host = "192.168.20.217";
	public static final int port = 6379;
	public static final int timeout = 2000;
	public static final byte ASTERISK = '*';
	public static final byte DOLLAR = '$';

	public static void main(String[] main) {

		Socket socket = new Socket();
		// 向服务器传输流
		RedisFileOutputStream os = null;
		// 服务器返回流
		RedisFileInputStream is = null;
		try {
			socket.setReuseAddress(true);
			socket.setKeepAlive(true); // Will monitor the TCP connection is
			socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to
			socket.setSoLinger(true, 0); // Control calls close () method,
			socket.connect(new InetSocketAddress(host, port), timeout);
			socket.setSoTimeout(timeout);
			
			os = new RedisFileOutputStream(socket.getOutputStream());
			is = new RedisFileInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		// 操作命令
		String command = "DEL";
		// 操作字符
		String[] strs = { "pwd", "password123" };
		// 转换后的操作字符
		byte[][] strByte = RedisSocketToStream.strArrayToByte(strs);
		// 转换后的操作命令
		byte[] commandBtye = RedisSocketToStream.strToByte(command);
		
		try {
			// *N
			os.write(ASTERISK);
			os.write(strByte.length+1);
			
			// \r\n
			os.write(RedisSocketToStream.crlf());
			// $N
			os.write(DOLLAR);
			os.write(command.length());
			// \r\n
			os.write(RedisSocketToStream.crlf());
			// command
			os.write(commandBtye);
			// \r\n
			os.write(RedisSocketToStream.crlf());
			
			// $N
			// \r\n
			// str
			for (final byte[] str : strByte) {
				os.write(DOLLAR);
				os.write(str.length);
				os.write(RedisSocketToStream.crlf());
				os.write(str);
				os.write(RedisSocketToStream.crlf());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 将数据传输到服务器
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			byte[] inputByte = new byte[8198];
			is.read(inputByte);
			
			System.out.println(SafeEncoder.encode(inputByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[][] strArrayToByte(String[] str) {
		byte[][] args = new byte[str.length][];
		for (int i = 0; i < str.length; i++) {
			args[i] = SafeEncoder.encode(str[i]);
		}
		return args;
	}
	
	public static byte[] strToByte(String str){
		return SafeEncoder.encode(str);
	}

	public static byte[] crlf() {
		return SafeEncoder.encode("\r\n");
	}

 
}
