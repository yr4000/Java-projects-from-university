package il.ac.tau.cs.sw1.ex8.filesSim;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;

public class FileIndex {
	private static final String ERROR = "[ERROR] ";
	private HashMap<String,HashMapHistogram<String>> mapMap = new HashMap<String,HashMapHistogram<String>>();

	/**
	 * Given a path to a folder, reads all the files in it and indexes them
	 */
	public void index(String folderPath) {
		// first, clear the previous contents of the index
		clearPreviousIndex();
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				String path = file.getAbsolutePath();
				System.out.println("Indexing " + path);
				try {
					// add to the index if read is successful
					addFileToIndex(file);
				} catch (IOException e) {
					System.out.println(ERROR + "failed to read from "
							+ path);
				}
			}
		}
	}

	/**
	 * Adds the input file to the index
	 */
	public void addFileToIndex(File file) throws IOException {
		List<String> tokens = FileUtils.readAllTokens(file);
		String path = file.getAbsolutePath();
		if (tokens.isEmpty()) {
			System.out.println(ERROR + "ignoring empty file " + path);
			return;
		}
		HashMapHistogram<String> mapi = new HashMapHistogram<String>();
		mapi.addAll(tokens);
		mapMap.put(file.getName(),mapi);
	}

	/**
	 * Called at the beginning of index() in order to clear the fields from
	 * previously indexed files. After calling it the index contains no files.
	 */
	public void clearPreviousIndex() {
		mapMap.clear();
	}

	/**
	 * Given indexed input files, compute their cosine similarity based on their
	 * indexed tokens
	 */
	public double getCosineSimilarity(File file1, File file2) {
		if (!verifyFile(file1) || !verifyFile(file2)) {
			return Double.NaN;
		}
		int numerator = 0;
		Iterator<String> it1 = mapMap.get(file1.getName()).iterator();
		while(it1.hasNext()){
			String word = it1.next();
			if(wordSimilarityCheck(word, file2)){
				numerator+=(mapMap.get(file1.getName()).getCountForItem(word)*
						mapMap.get(file2.getName()).getCountForItem(word));
			}
		}
		double denominator1 = denominatorCaculator(file1);
		double denominator2 = denominatorCaculator(file2);
		
		return numerator/(denominator1*denominator2);

	}

	/**
	 * Given indexed input files, return the number of the common token in both files
	 */
	public int getCommonTokensNum(File file1, File file2){
		if (!verifyFile(file1) || !verifyFile(file2)) {
			return 0;
		}
		int counter = 0;
		Iterator<String> it1 = mapMap.get(file1.getName()).iterator();
		while(it1.hasNext()){
			String word = it1.next();
			if(wordSimilarityCheck(word, file2)) counter ++;
		}
		return counter;
	}

	
	/**
	 * returns true iff the input file is currently indexed. Otherwise, prints
	 * an error message.
	 */
	public boolean verifyFile(File file) {
		if(mapMap.containsKey(file.getName())) return true;
		System.out.println(ERROR+"the file "+file.getName()+" was not found in the index");
		return false;
	}

	/**
	 * @return the number of files currently indexed.
	 */
	public int getNumIndexedFiles() {
		return mapMap.size();
	}
	
	/**
	 * checks if the word appears in file2. returns true if it does, false else.
	 */
	private boolean wordSimilarityCheck(String word, File file2){
		if(mapMap.get(file2.getName()).getMap().containsKey(word)) return true;
		return false;
	}
	
	/**
	 * calculates the parts of the denominator of every file
	 * returns -1 if file is not found
	 */
	private double denominatorCaculator(File file){
		if(!verifyFile(file))
			return -1;
		int sum = 0;
		Iterator<String> it = mapMap.get(file.getName()).iterator();
		while(it.hasNext()){
			sum += Math.pow(mapMap.get((file.getName())).getCountForItem(it.next()),2);
		}
		return Math.pow(sum, 0.5);
	}

}
