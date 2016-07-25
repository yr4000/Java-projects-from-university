package il.ac.tau.cs.sw1.ex8.filesSim;

import java.io.File;
import java.io.IOException;


public class FileSimilarityTest {
	private static final String FOLDER = "testFiles";

	public static void main(String[] args) throws IOException {
		FileIndex fileIndex = new FileIndex();
		fileIndex.index(FOLDER);
		System.out.println("Indexed " + fileIndex.getNumIndexedFiles() + " files.");
		File firstFile = new File(FOLDER + File.separator + "file1.txt");
		for (int i = 2; i <= 4; i++) {
			File otherFile = new File(FOLDER + File.separator + "file" + i + ".txt");
			System.out.printf("%s: cosine similarity: %.3f, number of common words: %d%n", otherFile.getAbsolutePath(),
					fileIndex.getCosineSimilarity(firstFile, otherFile), fileIndex.getCommonTokensNum(firstFile, otherFile));
		}
		
	}
}

