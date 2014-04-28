/**
 * 
 */
package com.wia;

import java.util.HashMap;
import java.util.Map;

import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class Context {

	private Author currentAuthor;
	private int dataRetrieveAddress = 0;
	public final static int LOCAL = 0;
	public final static int NETWORK = 1;

	private volatile static Context uniqueInstance;

	private Context() {
		// currentAuthor = DataPreprocessor.run("wdp515105",
		// dataRetrieveAddress);
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

	public void setDataRetrieveAddress(int address) {
		dataRetrieveAddress = address;
	}

	public void setAuthor(Author author) {
		currentAuthor = author;
	}

	/**
	 * @return the currentAuthor
	 */
	public Author getCurrentAuthor() {
		return currentAuthor;
	}

	/**
	 * @param currentAuthor
	 *            the currentAuthor to set
	 */
	public void resetCurrentAuthor(Author author) {
		this.currentAuthor = author;
	}

	private final Map<String, Object> contextObjects = new HashMap<String, Object>();

	public Map<String, Object> getContextObjects() {
		return contextObjects;
	}

	public Object getContextObject(String key) {
		return contextObjects.get(key);
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
