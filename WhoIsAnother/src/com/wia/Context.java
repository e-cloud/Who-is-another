/**
 * 
 */
package com.wia;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DataFetcher;

/**
 * @author Saint Scott
 * 
 */
public class Context {

	private Author currentAuthor;

	private volatile static Context uniqueInstance;

	private Context() {
		currentAuthor = DataFetcher.run("myi_i");
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
	public void resetCurrentAuthor(Author currentAuthor) {
		this.currentAuthor = currentAuthor;
	}

}