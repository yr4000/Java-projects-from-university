package il.ac.tau.cs.sw1.ex8.filesSim;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;

public class HistogramsFactory {
	public static IHistogram<String> getHistogram(){
		return new HashMapHistogram<>();
	}
}
