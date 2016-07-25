package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Comparator;
import java.util.Map;

public class HashMapHistogramComparator<T> implements Comparator<T>{
	private Map<T,Integer> map;
	
	HashMapHistogramComparator(Map<T,Integer> map){
		this.map=map;
	}
	@Override
	public int compare(T arg0, T arg1) {
		if(map.get(arg0)<map.get(arg1)) return -1;
		else if(map.get(arg0)>map.get(arg1)) return 1;
		return 0;
	}
	
}
