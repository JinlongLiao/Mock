package com.liaojl.log.logtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class InitProperties {
	public static String TARGE_IP;
	public static Integer TARGE_PORT;
	public static Date TIME_START;
	public static Date TIME_END;
	public static String LOG_TYPE;
	public static Long LOG_DELAY;
	public static Integer LOG_COUNT;
	public static String LOG_CHARSET;
	public static Properties properties = new Properties();
	static {
		try {
			properties.load(new FileInputStream(System.getProperty("Config")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TARGE_IP = properties.getProperty("targe_ip");
		TARGE_PORT = Integer.parseInt(properties.getProperty("targe_port"));
		TIME_START = java.sql.Date.valueOf(properties.getProperty("time_start"));
		TIME_END = java.sql.Date.valueOf(properties.getProperty("time_end"));
		LOG_TYPE = properties.getProperty("log_type");
		LOG_DELAY = Long.parseLong(properties.getProperty("log_delay"));
		LOG_COUNT = Integer.parseInt(properties.getProperty("log_count"));
		LOG_CHARSET = (properties.getProperty("log_charset"));
	}
}