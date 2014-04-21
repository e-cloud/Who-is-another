/**
 * 
 */
package com.wia.model.preprocess;

/**
 * @author Saint Scott
 * 
 */
public class DataParserFactory {

	public static DataParser createGeneralAuthorInfoParser() {
		return new GeneralAuthorInfoParser();
	}

	public static DataParser createSubmitLogsParser() {
		return new SubmitLogsParser();
	}
}
