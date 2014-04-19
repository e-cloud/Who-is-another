/**
 * 
 */
package com.wia.model.fetcher;

/**
 * @author Saint Scott
 * 
 */
public class DataFetcherFactory {

	public static DataFetcher createGeneralAuthorInfoFetcher() {
		return new GeneralAuthorInfoFetcher();
	}

	public static DataFetcher createSubmitLogsFetcher() {
		return new SubmitLogsFetcher();
	}
}
