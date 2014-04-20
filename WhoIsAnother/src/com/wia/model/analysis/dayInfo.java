/**
 * 
 */
package com.wia.model.analysis;

import java.util.Iterator;
import java.util.Map;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.Problem;

/**
 * @author 123
 * 
 */
public class dayInfo extends Info {
	Map<Integer, Problem> problems;

	public dayInfo() {
		Context context = Context.getInstance();
		Author author = context.getCurrentAuthor();
		problems = author.getSolvedProblemList();
		Iterator<Problem> problemIterator = author.getSolvedProblemList()
				.values().iterator();
	}

}
