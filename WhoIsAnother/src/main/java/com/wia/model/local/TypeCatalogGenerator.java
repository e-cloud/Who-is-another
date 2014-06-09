/**
 * 
 */
package com.wia.model.local;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

/**
 * @author sean
 * 
 */
public class TypeCatalogGenerator {
	public Map<Integer, String> generateCatalogMap(String pathname)
			throws IOException {
		InputStream is = getClass().getResourceAsStream(pathname);
		JSONArray cataArray = JSONArray.fromObject(IOUtils
				.toString(is, "utf-8"));

		Map<Integer, String> catalogMap = new HashMap<Integer, String>();
		for (int i = 0; i < cataArray.size(); i++) {

			JSONObject cataObject = cataArray.getJSONObject(i);
			String typename = cataObject.getString("typename");
			JSONArray pidArray = cataObject.getJSONArray("pids");

			for (int j = 0; j < pidArray.size(); j++) {
				int pidNum = Integer.parseInt(pidArray.getString(j));
				catalogMap.put(pidNum, typename);
			}
		}
		return catalogMap;
	}
}
