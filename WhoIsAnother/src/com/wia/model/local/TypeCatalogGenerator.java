/**
 * 
 */
package com.wia.model.local;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.wia.util.FileUtil;

/**
 * @author sean
 * 
 */
public class TypeCatalogGenerator {
	public Map<Integer, String> generateCatalogMap(String pathname)
			throws IOException {
		File inputFile = new File(pathname);
		String str = FileUtil.readFile(inputFile);
		JSONArray cataArray = JSONArray.fromObject(str);

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
