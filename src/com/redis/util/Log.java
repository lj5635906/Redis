package com.redis.util;

/**
 * @author : Roger
 * @date : 2015-10-12
 * @desc : 日志打印登记
 */
public class Log {

	/**
	 * 指出细粒度信息事件对调试应用程序是非常有帮助的
	 * 
	 * @param str
	 */
	public static void debug(Object str) {
		System.err.println(str);
	}

	/**
	 * 消息在粗粒度级别上突出强调应用程序的运行过程
	 * 
	 * @param str
	 */
	public static void info(Object str) {
		System.err.println(str);
	}

	/**
	 * 表明会出现潜在错误的情形
	 * 
	 * @param str
	 */
	public static void warn(Object str) {
		System.err.println(str);
	}

	/**
	 * 指出虽然发生错误事件，但仍然不影响系统的继续运行
	 * 
	 * @param str
	 */
	public static void error(Object str) {
		System.err.println(str);
	}

	/**
	 * 指出每个严重的错误事件将会导致应用程序的退出
	 * 
	 * @param str
	 */
	public static void fatal(Object str) {
		System.err.println(str);
	}

	/**
	 * 最低等级的，用于打开所有日志记录。
	 * 
	 * @param str
	 */
	public static void all() {
	}

	/**
	 * 最高等级的，用于关闭所有日志记录
	 * 
	 * @param str
	 */
	public static void off() {
	}

}
