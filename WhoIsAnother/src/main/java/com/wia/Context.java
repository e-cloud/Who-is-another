/**
 * 
 */
package com.wia;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author Saint Scott
 * 
 */
public class Context {

	private volatile static Context uniqueInstance;

	private Context() {

	}

	public static Context getInstance() {
		if (uniqueInstance == null) {
			synchronized (Context.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Context();
				}
			}
		}
		return uniqueInstance;
	}

	/*
	 * configuration part
	 */

	private PropertiesConfiguration config = null;

	@SuppressWarnings("unused")
	private void loadConfig() throws ConfigurationException {
		config = new PropertiesConfiguration("application.properties");
		config.setAutoSave(true);
	}

	@SuppressWarnings("unused")
	private void saveConfig() throws ConfigurationException {
		config.save();
	}

	public Object getConfigProperty(String key) {
		return config.getProperty(key);
	}

	public void setConfigProperty(String key, Object value) {
		config.setProperty(key, value);
	}

	/*
	 * context object part
	 */

	private final Map<String, Object> contextObjects = new HashMap<String, Object>();

	public Map<String, Object> getContextObjects() {
		return contextObjects;
	}

	public Object getContextObject(String key) {
		return contextObjects.get(key);
	}

	public boolean containsKey(String key) {
		return contextObjects.containsKey(key);
	}

	public Object removeContextObject(String key) {
		return contextObjects.remove(key);
	}

	public void addContextObject(String key, Object value) {
		contextObjects.put(key, value);
	}

	public void clearContextObjects() {
		contextObjects.clear();
	}

}
