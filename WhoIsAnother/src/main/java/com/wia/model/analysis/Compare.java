/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.List;

import com.wia.model.data.Author;

/**
 * @author 123
 * 
 */
public class Compare {

	/**
	 * @param ID
	 * @return 要显示的数据的List
	 */
	public List<Integer> comparator(String ID) {
		Author author = null;// getAuthorAccordingToID(ID);
		List<Integer> who = new ArrayList<Integer>();
		who.add(author.getRank());
		who.add(author.getSubmitted());
		who.add(author.getSubmissions());
		who.add(author.getSolved());
		who.add(author.getAccepted());

		return who;
	}

}
