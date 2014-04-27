/**
 * 
 */
package com.wia.model.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wia.Context;
import com.wia.model.data.Author;
import com.wia.model.data.Problem;

import javafx.util.Pair;

/**
 * @author 123
 *
 */
public class Recommend {
	public List<Pair<String,List<Integer>>> neighborRecommend(){
		Context context = Context.getInstance();
		Author author = context.getCurrentAuthor();
		Set<Integer> authorPidList = author.getProblemMap().keySet();
		Map<Integer,Author> neighbours = author.getNeighbourMap();
		Set<Integer> temp;
		for(int i = 0;i<6;i++){
			Author neighborI = neighbours.get(i);
			Set<Integer> neighborPidList = neighborI.getProblemMap().keySet();
			//并操作
			temp.addAll(neighborPidList);			
			}
		//差操作
		temp.removeAll(authorPidList);
		//遍历该集合，放到相应的分类中
			
	}
	
	public List<Pair<Integer,Integer>> top100Commonity(){
		List<Integer> pid = new ArrayList<Integer>();
		List<Integer> count = new ArrayList<Integer>();
		List<Pair<Integer,Integer>> recommend = new ArrayList<Pair<Integer,Integer>>();
		int Pid;
		int index;
		int temp;
		getTop100List();
		//遍历该list，获取每个ID对应的author
		//获取该author的每一个题目号,对于每一个题目号，
		if(pid.contains(Pid)){		//如果题目在pid表中，那么相应下标的count表中的数据加一
			index = pid.indexOf(Pid);
			temp = count.get(index);
			temp++;
			count.set(index, temp);
		}
		else{		//否则，放入pid表，count表相应下标值加一
			pid.add(Pid);
			index = pid.indexOf(Pid);
			count.set(index, 1);
		}
		
		for(int i=0;i<pid.size();i++){
			Pair<Integer,Integer> pair = new Pair<>(pid.get(i),count.get(i));
			recommend.add(pair);
		}
		//排序
		Collections.sort(recommend,new Comparator<Pair<Integer,Integer>>(){
			public int compare(Pair<Integer,Integer> p1,Pair<Integer,Integer> p2){
				if(p1.getValue() > p2.getValue())
					return 1;
				else
					return 0;
			}
		});
		
			
	}	

}
