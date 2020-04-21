package com.liaojl.log.udplogsend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liaojl
 *
 */
public enum LogModelType {
	SSL("SSL", "ssl"), MIDDLE("MIDDLE", "middle"), SSLM("SSL", "sslmobile");
	private LogModelType(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public static LogModelType[] ALL_VALUES;
	public static List<Map<String, List<String>>> ALL_MAP_VALUES;
	public final static String ALL = "ALL";
	static {
		ALL_VALUES = LogModelType.values();
	}
	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getTypeByName(String name) {
		List<String> types = new ArrayList<>();
		for (LogModelType item : ALL_VALUES) {
			if (item.getName().equalsIgnoreCase(ALL) || item.getName().equals(name)) {
				types.add(item.getType());
			}
		}
		return types;
	}
}