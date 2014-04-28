/**
 * 
 */
package com.wia.model.local;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.wia.model.data.*;
import com.wia.util.FileUtil;

import net.sf.json.*;
/**
 * @author sean
 *
 */
public class ToType {
	public void Type() throws IOException{
		File inputFile = new File("E:\\GitHub\\Who-is-another\\default catalog.json");
		String str = FileUtil.readFile(inputFile);
		JSONArray cataArray = JSONArray.fromObject(str);
		
		Map<Integer, String> catalogMap = new HashMap<Integer, String>();
		for (int i = 0; i < cataArray.size(); i++){
			
			JSONObject cataObject = cataArray.getJSONObject(i);
			String typename = cataObject.getString("typename");
			JSONArray pidArray = cataObject.getJSONArray("pid");
			
			for(int j = 0 ; j < pidArray.size(); j++){
				int pidNum = Integer.parseInt(pidArray.getString(j)); 
				catalogMap.put(pidNum, typename);			
			}
			
			TypeCatalog myCata = TypeCatalog.getInstance();
			myCata.setCatalog(catalogMap);
		}
	}
}
