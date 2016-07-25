import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RBTreeTester
{
	public static void main(String[] args)
	{
		int topI = 10000 * 10;
		RBTree check = new RBTree();

		int insertNum = checkInsert(true, check, topI);
		int deletionNum = checkDelete(false, check, topI);

		System.out.println("inserNum= " + insertNum);
		System.out.println("deletionNum= " + deletionNum);
		System.out.println("averageForInsert= " + ((float) insertNum) / topI);
		System.out.println("averageForDeletion= " + ((float) deletionNum) / topI);

	}

	public static int checkInsert(boolean random, RBTree check, int topI)
	{
		// prepares the set and tree
		Set<Integer> set = new HashSet<Integer>();
		int sum = 0;
		for (int i = 0; i < topI; i++)
		{
			set.add(i);
		}

		if (random)
		{
			for (int i = 0; i < topI; i++)// inserts items randomly.
			{
				Random rd = new Random();
				int num = rd.nextInt(topI);
				while (!set.contains(num))
					num = rd.nextInt(topI);

				sum += check.insert(num, "boobs-" + num);
				set.remove(num);
				// check.keysToArray();
			}
		}
		else
		{
			for (int i = 0; i < topI; i++)
			{
				sum += check.insert(i, "boobs-" + i);
			}
		}
		return sum;
	}

	public static int checkDelete(boolean random, RBTree check, int topI)
	{
		Set<Integer> set = new HashSet<Integer>();
		int sum = 0;
		if (random)
		{
			for (int i = 0; i < (topI / 1); i++)// delete items randomly.
			{
				Random rd = new Random();
				int num = rd.nextInt(topI);
				while (set.contains(num))
				{
					num = rd.nextInt(topI);
				}
				set.add(num);
				sum += check.delete(num);
			}
		}
		else
		{
			for (int i = 0; i < (topI / 1); i++)
			{
				sum += check.delete(i);
			}
		}
		return sum;
	}
}
