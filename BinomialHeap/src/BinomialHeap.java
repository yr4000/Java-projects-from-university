/**
 * BinomialHeap
 *
 * An implementation of binomial heap over non-negative integers. Based on
 * exercise from previous semester.
 */
public class BinomialHeap
{
	private static final int MAX_TREES = 1;
	public HeapNode[] trees;
	private boolean isEmpty;
	private HeapNode min = null;
	private int size = 0;
	public int numberOfLinks = 0;

	public BinomialHeap()
	{
		this.trees = new HeapNode[MAX_TREES]; // TODO changed from 1024 to
												// MAX_TREES
		this.isEmpty = true;
	}

	public BinomialHeap(HeapNode[] nodes, int size)
	{
		this.trees = nodes;
		this.isEmpty = false;
		this.size = size;
	}

	/**
	 * public boolean empty()
	 *
	 * precondition: none
	 * 
	 * The method returns true if and only if the heap is empty.
	 * 
	 * complexity: O(1)
	 * 
	 */
	public boolean empty()
	{
		return isEmpty;
	}

	/**
	 * public void insert(int value)
	 *
	 * Insert value into the heap
	 *
	 * complexity: O(logn) including the linking and updateMin methods.
	 */
	public void insert(int value)
	{
		size++; // update the size counter of the heap

		if (this.isEmpty) // update the boolean value of isEmpty
			isEmpty = false;

		if (this.trees[trees.length - 1] != null) // TODO added condition
			doubling();

		HeapNode newNode = new HeapNode(value, "", null, null);

		// updates the pointer of the minimum node
		if (min == null || min.getKey() >= newNode.getKey())
			min = newNode;

		if (trees[0] == null)
		{
			trees[0] = newNode;
			return;
		}

		linking(newNode, 0);
		updateMin();
	}

	/**
	 * private void linking(HeapNode node, int degree)
	 * 
	 * links another node to BinomialHeaps child, assuming they have the same
	 * degree. if needed, keep on the linking with recursion
	 *
	 * complexity: O(logn), for there are at most logn trees in BinomialHeap.
	 */
	private void linking(HeapNode node, int degree)
	{
		this.numberOfLinks++;

		if (trees[degree] == null)
		{
			trees[degree] = node;
			return;
		}

		HeapNode currentNode = trees[degree];

		// checks which node has the bigger key, then adds
		// it the the other ones children. then keep on
		// the linking to the next degree by recursion.
		if (currentNode.getKey() > node.getKey())
		{
			node.addChildToNode(currentNode);
			trees[degree] = null;
			linking(node, degree + 1);
		}
		else
		{
			currentNode.addChildToNode(node);
			trees[degree] = null;
			linking(currentNode, degree + 1);
		}

	}

	/**
	 * public void deleteMin()
	 *
	 * Delete the minimum value
	 *
	 * complexity: O(logn)
	 */
	public void deleteMin()
	{
		if (this.min == null)
			return;
		this.size--; // updates the size counter.
		if (this.size == 0)
			this.isEmpty = true;

		int degree = this.min.maxChild + 1;
		HeapNode[] nodes = this.min.children;

		// decrease the size counter of the binomial heap by the number of the
		// minimum tree nodes.
		this.size -= ((int) Math.pow(2, degree) - 1);

		// creates a new BinomialHeap from the children of the minimum
		BinomialHeap bin2 = new BinomialHeap(nodes, (int) Math.pow(2, degree) - 1);

		this.trees[degree] = null; // empty the cell in which the minimum was

		meld(bin2);

		updateMin();

	}

	/**
	 * private void updateMin()
	 * 
	 * updates the pointer min to point to the node that has the minimum key.
	 * 
	 * complexity: O(logn)
	 */
	private void updateMin()
	{
		int maxTree = (int) log2(this.size); // find the tree with the highest
												// rank in the heap
		HeapNode minimum = this.trees[0];

		for (int i = maxTree; i >= 1; i--) // runs between the roots of the
											// trees
		{
			if (trees[i] == null)
				continue;
			if (minimum == null || trees[i].getKey() <= minimum.getKey())
				minimum = trees[i];
		}

		this.min = minimum;
	}

	/**
	 * public int findMin()
	 *
	 * Return the minimum value
	 *
	 * complexity: O(1)
	 */
	public int findMin() // TODO its not clear why we written the method this
							// way...
	{
		if (min != null)
			return min.getKey();
		return Integer.MAX_VALUE;
	}

	/**
	 * public void meld (BinomialHeap heap2)
	 *
	 * Meld the heap with heap2
	 *
	 * complexity: O(logn) where n is the number of nodes in the bigger heap.
	 */
	public void meld(BinomialHeap heap2)
	{
		// checks who is the bigger heap
		int maxSize = Math.max(this.size, heap2.size); // TODO changes from
														// getSize() to size

		// this part doubles the size of one of the heaps, if needed.
		if (this.trees.length < heap2.trees.length)
		{
			while (this.trees.length < heap2.trees.length) // TODO added
			{
				doubling();
			}
		}
		else
		{
			while (this.trees.length > heap2.trees.length) // TODO added
			{
				heap2.doubling();
			}
		}
		// caculate the degree of the biggest tree in the heaps
		int maxI = (int) log2(maxSize);

		HeapNode carrier = null;

		for (int i = 0; i <= maxI; i++)
		{
			if (carrier == null) // carrier is empty
			{
				// both trees in place i exist
				if (this.trees[i] != null && heap2.trees[i] != null)
				{
					carrier = merge2Nodes(this.trees[i], heap2.trees[i]);
					this.trees[i] = null;
				}
				else
				// at least one of them exists.
				{
					if (this.trees[i] == null)
					{
						// inserts the tree in the i'th cell from heap2 to this
						this.trees[i] = heap2.trees[i];
						if (this.isEmpty)
							this.isEmpty = false;
					}
				}
			}
			else // carrier is not empty
			{
				if (this.trees[i] != null && heap2.trees[i] != null) // both not
																		// empty
				{
					HeapNode tempCarrier = carrier;
					carrier = merge2Nodes(this.trees[i], heap2.trees[i]);
					this.trees[i] = tempCarrier;
				}
				else if (this.trees[i] == null && heap2.trees[i] == null) // both
																			// empty
				{
					this.trees[i] = carrier;
					carrier = null;
				}
				else if (this.trees[i] != null) // the tree in degree i exists
												// in this
				{
					carrier = merge2Nodes(this.trees[i], carrier);
					this.trees[i] = null;
				}
				else
				{
					carrier = merge2Nodes(carrier, heap2.trees[i]);
				}
			}
		}
		if (carrier != null) // inserts the carrier that was left his place
			this.trees[maxI + 1] = carrier;
		this.size += heap2.size(); // updates the size counter
		updateMin();
	}

	/**
	 * private HeapNode merge2Nodes(HeapNode node1, HeapNode node2)
	 * 
	 * merges two node on the condition they are from the same level
	 * 
	 * complexity: O(1)
	 */
	private HeapNode merge2Nodes(HeapNode node1, HeapNode node2)
	{
		this.numberOfLinks++;

		if (node2.getKey() > node1.getKey())
		{
			node1.addChildToNode(node2);
			return node1;
		}
		else
		{
			node2.addChildToNode(node1);
			return node2;
		}
	}

	/**
	 * private double log2(int num)
	 * 
	 * caculates the log2 value of the number.
	 */
	private double log2(int num)
	{
		return Math.log(num) / Math.log(2);
	}

	/**
	 * public int size()
	 *
	 * Return the number of elements in the heap
	 * 
	 * complexity: O(1)
	 */
	public int size()
	{
		return this.size;
	}

	/**
	 * public int minTreeRank()
	 *
	 * Return the minimum rank of a tree in the heap.
	 * 
	 * complexity: O(logn)
	 */
	public int minTreeRank()
	{
		if (isEmpty)
			return -1;
		// runs on all the cells in this.trees
		for (int i = 0; i < trees.length; i++)
		{
			if (trees[i] != null)
				return i;
		}
		return -1;
	}

	/**
	 * public boolean[] binaryRep()
	 *
	 * Return an array containing the binary representation of the heap.
	 * 
	 * complexity: O(logn)
	 */
	public boolean[] binaryRep()
	{
		String bin = Integer.toBinaryString(size);
		int length = bin.length();
		boolean[] arr = new boolean[length];
		for (int i = length - 1; i >= 0; i--)
		{
			arr[length - 1 - i] = bin.charAt(i) == '1' ? true : false;
		}

		return arr;
	}

	/**
	 * public void arrayToHeap()
	 *
	 * Insert the array to the heap. Delete previous elemnts in the heap.
	 * 
	 * complexity: O(n) where n is the number of items in the array
	 */
	public void arrayToHeap(int[] array)
	{
		resetBinomialHeap();
		for (int i : array)
		{
			insert(i);
		}
	}

	/**
	 * public boolean isValid()
	 *
	 * Returns true if and only if the heap is valid.
	 * 
	 * complexity: O(n) where n is the number of nodes in the heap
	 */
	public boolean isValid()
	{
		for (int i = 0; i < trees.length; i++)
		{
			if (trees[i] == null)
				continue;
			// checks if the tree has the correct degree
			if (this.trees[i].maxChild + 1 != i)
				return false;
			// checks if the tree keeps the heap rule
			if (!checkHeapRule(this.trees[i]))
				return false;
		}
		return true;
	}

	/**
	 * private boolean checkHeapRule(HeapNode node)
	 * 
	 * checks by recursion the heap keeps on the heap rule
	 * 
	 * complexity: O(n)
	 */
	private boolean checkHeapRule(HeapNode node)
	{
		if (node.maxChild == -1)
			return true;
		for (int i = 0; i <= node.maxChild; i++)
		{
			if (node.children[i] == null)// TODO added this condition to check
											// if all the children exists
				return false;
			if (node.key > node.children[i].key)
				return false;
			if (!checkHeapRule(node.children[i]))
				return false;
		}
		return true;
	}

	/**
	 * private void resetBinomialHeap()
	 * 
	 * rests the BinomialHeap
	 * 
	 * complexity: O(1)
	 */
	private void resetBinomialHeap()
	{
		this.trees = new HeapNode[MAX_TREES];
		this.isEmpty = true;
		this.min = null;
		this.size = 0;
	}

	/**
	 * private void doubling()
	 * 
	 * doubles the array of the trees
	 * 
	 * complexity: O(logn)
	 */
	private void doubling()
	{// TODO check
		int newLength = this.trees.length * 2;
		HeapNode[] newArray = new HeapNode[newLength];
		for (int i = 0; i < this.trees.length; i++)
		{
			newArray[i] = this.trees[i];
		}
		this.trees = newArray;
	}

	/**
	 * public class HeapNode
	 * 
	 * If you wish to implement classes other than BinomialHeap (for example
	 * HeapNode), do it in this file, not in another file
	 * 
	 */
	public class HeapNode
	{
		private int key;
		private String val;
		private HeapNode parent;
		private HeapNode[] children = new HeapNode[MAX_TREES];
		private int maxChild = -1;

		public HeapNode(int key, String val, HeapNode parent, HeapNode[] children)
		{
			this.key = key;
			this.val = val;
			this.parent = parent;
			if (children != null)
			{
				this.children = children;
				this.maxChild = children.length - 1;
			}
		}

		public int getKey()
		{
			return key;
		}

		public void setKey(int key)
		{
			this.key = key;
		}

		public String getVal()
		{
			return val;
		}

		public void setVal(String val)
		{
			this.val = val;
		}

		public HeapNode getParent()
		{
			return parent;
		}

		public void setParent(HeapNode parent)
		{
			this.parent = parent;
		}

		public HeapNode[] getChildren()
		{
			return children;
		}

		public void setChildren(HeapNode[] children)
		{
			this.children = children;
		}

		/**
		 * private void addChildToNode(HeapNode node)
		 * 
		 * this method adds a HeapNode to the node, Assuming the nodes degree is
		 * only 1 level bigger from the nodes child max degree
		 * 
		 * complexity: O(1)
		 */
		private void addChildToNode(HeapNode node) // TODO changed to private
		{
			if (children.length - 1 == maxChild)
				childrenDoubling();
			this.children[++maxChild] = node;
		}

		/**
		 * private void childrenDoubling()
		 * 
		 * doubles the size of the children array if it's full, and copy all the
		 * children to the new array
		 * 
		 * complexity: O(logn)
		 */
		private void childrenDoubling()
		{// TODO check
			HeapNode[] newArray = new HeapNode[this.children.length * 2];
			for (int i = 0; i < this.children.length; i++)
			{
				newArray[i] = this.children[i];
			}
			this.children = newArray;
		}

	} // Here the class HeapNode is done

}
