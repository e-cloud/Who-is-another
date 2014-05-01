/**
 * 
 */
package com.wia;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;

import com.wia.model.analysis.GeneralInfo;
import com.wia.model.data.Author;

/**
 * @author Saint Scott
 * 
 */
public class Context {

	private Author currentAuthor;

	private Application coordinator;

	private volatile static Context uniqueInstance;

	private final Map<String, Object> contextObjects;

	private Context() {
		contextObjects = new HashMap<String, Object>();
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

	public void setAuthor(Author author) {
		currentAuthor = author;
		GeneralInfo generalInfo = GeneralInfo.getInstance();
		if (generalInfo.getRefAuthorID() != null
				&& !generalInfo.getRefAuthorID().equals(
						currentAuthor.getAuthorID())) {
			generalInfo.reset();
		}
	}

	/**
	 * @return the currentAuthor
	 */
	public Author getCurrentAuthor() {
		return currentAuthor;
	}

	/**
	 * @return the coordinator
	 */
	public Application getCoordinator() {
		return coordinator;
	}

	/**
	 * @param coordinator
	 *            the coordinator to set
	 */
	public void setCoordinator(Application coordinator) {
		this.coordinator = coordinator;
	}

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
