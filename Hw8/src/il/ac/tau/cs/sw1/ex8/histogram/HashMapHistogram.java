package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapHistogram<T> implements IHistogram<T>{

	private HashMap<T,Integer> map = new HashMap<T,Integer>();

	public HashMap<T, Integer> getMap() {
		return map;
	}

	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<>(map);  // TODO whats this?
	}

	@Override
	public void addItem(T item) {
		try {
			addItemKTimes(item,1);
		} catch (IllegalKValue e) {
			//will never get here.
		}
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValue {
		if(map.containsKey(item))
			map.put(item, map.get(item)+k);
		else
		{
			map.put(item, k);
		}
			
		
	}

	@Override
	public void addAll(Collection<T> items) {
		for(T item: items){
				this.addItem(item);
		}
		
	}

	@Override
	public int getCountForItem(T item) {
		if(map.containsKey(item))
			return map.get(item);
		return 0;
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Set<T> getItemsSet() {
		return map.keySet();
	}
	
	public int size(){
		return map.size();
	}

}
