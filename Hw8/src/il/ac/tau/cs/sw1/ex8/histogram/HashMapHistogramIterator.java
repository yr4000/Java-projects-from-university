package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
//import java.util.Comparator;

public class HashMapHistogramIterator<T> implements Iterator<T>{
	private ArrayList<T> list = new ArrayList<T>();
	//private int max=0; // max is the index of the key in the list with the maximum value in the map.
	private HashMapHistogramComparator<T> compare;  
	
	
	public HashMapHistogramIterator(Map<T,Integer> map){
		this.compare = new HashMapHistogramComparator<T>(map);
		this.list.addAll(map.keySet());
		Collections.sort(this.list, this.compare);
		//updateMax();
		}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean hasNext() {
		if(!list.isEmpty()) return true;
		return false;
	}

	@Override
	public T next() {
		T next = list.get(list.size()-1);
		list.remove(list.size()-1);
		//updateMax();
		return next;
		
		
				
			}
	/*
	private void updateMax(){
		for(T key: this.list){
			if (map.get(key)> map.get(list.get(max))) max=this.list.indexOf(key);
		}
	}
	*/
}
