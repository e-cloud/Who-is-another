/**
 * 
 */
package com.wia;

import com.wia.model.data.Author;
import com.wia.model.preprocess.DataPreprocessor;

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
		currentAuthor = DataPreprocessor.run("wdp515105", dataRetrieveAddress);
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

	public void setAuthor(String authorid) {
		currentAuthor = DataPreprocessor.run(authorid, dataRetrieveAddress);
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
