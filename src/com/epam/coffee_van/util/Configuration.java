package com.epam.coffee_van.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
	private final static Logger log = LogManager.getLogger("Configuration.class");

	static private ResourceBundle resBundle;

	static {
		resBundle = ResourceBundle.getBundle("log4j");
	}

	public static String getKey(String key) {
		try {
			String value = resBundle.getString(key);
			return value;
		} catch (MissingResourceException e) {
			log.error("Missing log4j.properties file");
			return null;
		}
	}
}
