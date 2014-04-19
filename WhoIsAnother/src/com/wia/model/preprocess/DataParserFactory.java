/**
 * 
 */
package com.wia.model.preprocess;

/**
 * @author Saint Scott
 * 
 */
public class DataParserFactory {

	public static DataParser createGeneralAuthorInfoFetcher() {
		return new GeneralAuthorInfoParser();
	}

	public static DataParser createSubmitLogsFetcher() {
		return new SubmitLogsParser();
	}
}
