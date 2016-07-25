import java.util.Arrays;
import java.util.Random;

public class BinomialTester
{
	public static void main(String[] args)
	{
		BinomialHeap bi = new BinomialHeap();

		for (int i = 0; i < 999; i++)
		{
			bi.insert(i);
		}
		System.out.println(Arrays.toString(bi.binaryRep()));
	}

	// BinomialHeap heap = new BinomialHeap();
	// addRandoms(heap, 10);
	//
	// System.out.println(Arrays.toString((heap.binaryRep())));
	//
	// heap.trees[1].setKey(-4);
	//
	// System.out.println(heap.isValid());

	// for (int i = 0; i < 5; i++)
	// {
	// System.out.println(heap.findMin());
	// heap.deleteMin();
	// }

	// System.out.println("dgdf");

	public static void addRandoms(BinomialHeap heap, int num)
	{
		Random rand = new Random();
		for (int i = 0; i < num; i++)
		{
			int val = rand.nextInt(num);
			System.out.println(val);
			heap.insert(val);
		}
	}

}
