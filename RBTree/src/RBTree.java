/**
 *
 * RBTree
 *
 * An implementation of a Red Black Tree with non-negative, distinct integer
 * keys and values
 *
 * omriyossefy 304978000 Omry Yossefy
 * 
 * yairhadas 305638926 Yair Hadas
 *
 */

public class RBTree
{
	private static final int ERROR_ALLREADY_IN = -1;
	int size;
	RBNode min;
	RBNode max;
	RBNode root;
	RBNode dummy;

	public RBTree()
	{
		this.size = 0;
		this.root = null;
		this.min = this.root;
		this.max = this.root;
		this.dummy = new RBNode(Integer.MAX_VALUE, "");
		this.dummy.isRed = false;
	}

	/**
	 * public class RBNode
	 */
	public static class RBNode
	{
		int key;
		String val;
		boolean isRed;
		RBNode rChild;
		RBNode lChild;
		RBNode parent;

		public RBNode(int key, String val)
		{
			this.key = key;
			this.val = val;
			this.isRed = true;
			this.rChild = null;
			this.lChild = null;
			this.parent = null;
		}

		public boolean isRed()
		{
			return isRed;
		}

		public RBNode getLeft()
		{
			return lChild;
		}

		public RBNode getRight()
		{
			return rChild;
		}

		public int getKey()
		{
			return key;
		}

		public String getValue()
		{
			return this.val;
		}

	}// here RBNode is done.

	/**
	 * public RBNode getRoot()
	 * 
	 * complexity is O(1).
	 * 
	 * returns the root of the red black tree
	 * 
	 */
	public RBNode getRoot()
	{
		return this.root;
	}

	/**
	 * public boolean empty()
	 *
	 * complexity: O(1)
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty()
	{
		return (this.size == 0);
	}

	/**
	 * public String search(int k)
	 *
	 * complexity: O(logn) because the tree is always balanced and the hight is
	 * at most O(logn)
	 *
	 * returns the value of an item with key k if it exists in the tree
	 * otherwise, returns null
	 */
	public String search(int k)
	{
		RBNode crntNode = this.root;
		while (true)
		{
			if (crntNode == null)
				return null;
			if (crntNode.key == k)
				return crntNode.val;
			else if (crntNode.key > k)
				crntNode = crntNode.lChild;
			else if (crntNode.key < k)
				crntNode = crntNode.rChild;
		}
	}

	/**
	 * public int insert(int k, String v)
	 *
	 * complexit: O(logn) inculidng all the inner functions.
	 *
	 * inserts an item with key k and value v to the red black tree. the tree
	 * must remain valid (keep its invariants). returns the number of color
	 * switches, or 0 if no color switches were necessary. returns -1 if an item
	 * with key k already exists in the tree.
	 */
	public int insert(int k, String v) // take care when tree is empty.
	{

		RBNode newNode = new RBNode(k, v);
		if (this.root == null)
		{
			this.root = newNode;
			this.min = newNode;
			this.max = newNode;
			newNode.isRed = false;
			this.root.parent = dummy;
			this.size++;
			return 1;
		}

		if (k < this.min.key)
			this.min = newNode;
		if (k > this.max.key)
			this.max = newNode;

		RBNode parent = findTreePosition(k);
		if (parent.key == k)
			return ERROR_ALLREADY_IN;

		this.size++;

		newNode.parent = parent;
		newNode.lChild = null;
		newNode.rChild = null;

		if (newNode.key < parent.key)
			parent.lChild = newNode;
		else
			parent.rChild = newNode;

		return RBInsertFixup(newNode);
	}

	/**
	 * public int delete(int k)
	 *
	 * complexity: O(logn) including all the inner functions.
	 *
	 * deletes an item with key k from the binary tree, if it is there; the tree
	 * must remain valid (keep its invariants). returns the number of color
	 * switches, or 0 if no color switches were needed. returns -1 if an item
	 * with key k was not found in the tree.
	 */
	public int delete(int k)
	{
		if (this.root == null)
			return -1;

		RBNode z = findTreePosition(k);
		RBNode y;
		RBNode x;

		if (z.key != k) // if k is not in the tree
			return -1;

		this.size--;

		if (z == this.min)
			this.min = successor(this.min);
		if (z == this.max)
			this.max = predecessor(this.max);

		if (z.lChild == null || z.rChild == null) // check that z has max of 1
													// child
			y = z;
		else
			// if not, we'll delete the successor of z and replace the data and
			// key after the deletion
			y = successor(z);

		if (y.lChild != null) // if the deleted node has a left child
			x = y.lChild;
		else
			x = y.rChild;

		// if the deleted node has a child - we change its father to be the
		// father of the deleted node (aka grandfather)
		if (x != null)
			x.parent = y.parent;

		// if the deleted node is the root of the tree
		if (y.parent == null || y.parent == dummy)
			this.root = x;
		// if the deleted node is a left child
		else if (y == y.parent.lChild)
			y.parent.lChild = x;
		else
			y.parent.rChild = x;

		// if the deleted node is the successor of the candidate node to be
		// deleted - we actually deleted the successor, but kept it's data
		if (y != z)
		{
			z.key = y.key;
			z.val = y.val;
			if (y == this.min)// checks the case we delete the predesseccor of
								// min
				this.min = z;
			if (y == this.max)// checks the case we delete the seccessor of max
				this.max = z;
		}

		if (!y.isRed() && this.size > 0)
			return RBDeletionFixup(x, y.parent);
		else
		{
			if (this.size == 0)
				this.root = null;
			updateRoot();
			return 0;
		}
	}

	/**
	 * public String min()
	 *
	 * complexity: O(1)
	 *
	 * Returns the value of the item with the smallest key in the tree, or null
	 * if the tree is empty
	 */
	public String min()
	{
		return (this.min != null && this.min != this.dummy) ? this.min.val : null;
	}

	/**
	 * public String max()
	 *
	 * complexity: O(1)
	 *
	 * Returns the value of the item with the largest key in the tree, or null
	 * if the tree is empty
	 */
	public String max()
	{
		return (this.max != null && this.max != dummy) ? this.max.val : null;
	}

	/**
	 * public int[] keysToArray()
	 *
	 * complexity: O(n), since we use inOrder(x).
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty
	 * array if the tree is empty.
	 */
	public int[] keysToArray()
	{
		String inOrderStr = inOrder(this.root);
		inOrderStr = inOrderStr.length() > 0 ? inOrderStr.substring(0, inOrderStr.length() - 1) : inOrderStr;

		String[] splited = inOrderStr.split(",");
		int[] arr = new int[this.size];
		for (int i = 0; i < this.size; i++)
		{
			arr[i] = Integer.parseInt(splited[i]);
		}

		return arr;
	}

	/**
	 * private String inOrder(RBNode node)
	 * 
	 * complexity: O(n), since the formula of this recursion is T(n)=2*T(n\2)+1,
	 * and according to the master method, the complexity is O(n)
	 * 
	 * returns a string of the all the keys, sorted.
	 */

	private String inOrder(RBNode node)
	{
		if (node == null)
			return "";
		String str = inOrder(node.lChild) + node.key + "," + inOrder(node.rChild);
		return str;
	}

	/**
	 * private String inOrderVals(RBNode node)
	 * 
	 * complexity: O(n)
	 * 
	 * the same as inOrder only for values, where the values are sorted
	 * according to their keys
	 */

	private String inOrderVals(RBNode node)
	{
		if (node == null)
			return "";
		String str = inOrderVals(node.lChild) + node.val + "," + inOrderVals(node.rChild);
		return str;
	}

	/**
	 * public String[] valuesToArray()
	 *
	 * complexity: O(n)
	 *
	 * Returns an array which contains all values in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty.
	 */
	public String[] valuesToArray()
	{
		String inOrderStr = inOrderVals(this.root);
		inOrderStr = inOrderStr.length() > 0 ? inOrderStr.substring(0, inOrderStr.length() - 1) : inOrderStr;

		String[] splited = this.size > 0 ? inOrderStr.split(",") : new String[0];

		return splited;
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * complexity: O(1)
	 *
	 * precondition: none postcondition: none
	 */
	public int size()
	{
		return this.size;
	}

	/**
	 * If you wish to implement classes, other than RBTree and RBNode, do it in
	 * this file, not in another file.
	 */

	/**
	 * leftChild(RBNode x, RBNode y)
	 * 
	 * put y as the left child of x.
	 * 
	 * complexity: O(1)
	 */
	public static void leftChild(RBNode x, RBNode y)
	{
		x.lChild = y;
		if (y != null)
			y.parent = x;
	}

	/**
	 * rightChild(RBNode x, RBNode y)
	 * 
	 * put y as the right child of x
	 * 
	 * complexity: O(1)
	 */
	public void rightChild(RBNode x, RBNode y)
	{
		x.rChild = y;
		if (y != null)
			y.parent = x;
	}

	/**
	 * translate(RBNode x, RBNode y)
	 * 
	 * put y as a child of x.parent instead of x.
	 * 
	 * complexity: O(1)
	 */
	public void transplant(RBNode x, RBNode y)
	{
		if (x == x.parent.lChild)
			leftChild(x.parent, y);
		else
			rightChild(x.parent, y);
	}

	/**
	 * rotateLeft(RBNode node)
	 * 
	 * put the right child (which is y) of node as nodes father, and put node as
	 * the left child of y.
	 * 
	 * complexity: O(1)
	 */
	public void rotateLeft(RBNode node)
	{
		RBNode y = node.rChild;
		transplant(node, y);
		rightChild(node, y.lChild);
		leftChild(y, node);
	}

	/**
	 * rotateRight(RBNode node)
	 * 
	 * put the left child (which is y) of node as nodes father, and put node as
	 * the right child of y.
	 * 
	 * complexity: O(1)
	 */
	public void rotateRight(RBNode node)
	{
		RBNode y = node.lChild;
		transplant(node, y);
		leftChild(node, y.rChild);
		rightChild(y, node);
	}

	/**
	 * findTreePosition(int k)
	 * 
	 * complexity: O(logn)
	 * 
	 * returns the node with key k if it exists, or last node found before
	 * reaching to the place it should have been located in.
	 */
	public RBNode findTreePosition(int k)
	{
		return innerFindTreePosition(this.root, k);

	}

	public RBNode innerFindTreePosition(RBNode node, int k)
	{
		if (k == node.key)
			return node;
		if (k < node.key)
		{
			if (node.lChild == null)
				return node;
			return innerFindTreePosition(node.lChild, k);
		}
		else if (node.rChild == null)
			return node;
		return innerFindTreePosition(node.rChild, k);
	}

	/**
	 * RBinsertFixup(RBNode z)
	 * 
	 * complexity: O(logn)
	 * 
	 * fix up the tree to be balanced acording to the RB tree rules after
	 * inseration and returns the number of color switching during the fixup.
	 */
	public int RBInsertFixup(RBNode z)
	{
		int coloringCounter = 0;
		while (z.parent.isRed) // we keep fixing the tree until we don't have 2
								// reds in a row.
		{
			if (z.parent == z.parent.parent.lChild) // check if the father is a
													// left child
			{
				if (z.parent.parent.rChild != null && z.parent.parent.rChild.isRed) // check
																					// if
																					// uncle
																					// is
																					// red
																					// -
				// case 1
				{
					// if true, we change the uncle + the father to be black and
					// the grandfather to be red
					z.parent.parent.rChild.isRed = false;
					z.parent.isRed = false;
					z.parent.parent.isRed = true;
					z = z.parent.parent;
					coloringCounter += 3;
				}
				else
				{
					if (z == z.parent.rChild) // case 2
					{
						z = z.parent;
						rotateLeft(z);
					}
					// case 3
					z.parent.isRed = false;
					z.parent.parent.isRed = true;
					coloringCounter += 2;
					rotateRight(z.parent.parent);
				}
			}
			else
			{
				if (z.parent.parent.lChild != null && z.parent.parent.lChild.isRed) // check
																					// if
																					// uncle
																					// is
																					// red
																					// -
				// case 1
				{
					// if true, we change the uncle + the father to be black and
					// the grandfather to be red
					z.parent.parent.lChild.isRed = false;
					z.parent.isRed = false;
					z.parent.parent.isRed = true;
					z = z.parent.parent;
					coloringCounter += 3;
				}
				else
				{
					if (z == z.parent.lChild) // case 2
					{
						z = z.parent;
						rotateRight(z);
					}
					// case 3
					z.parent.isRed = false;
					z.parent.parent.isRed = true;
					coloringCounter += 2;
					rotateLeft(z.parent.parent);
				}
			}
		}
		updateRoot();
		if (this.root.isRed)
		{
			this.root.isRed = false;
			coloringCounter++;
		}
		return coloringCounter;
	}

	/**
	 * RBDeletionFixup(RBNode x, RBNose parent)
	 * 
	 * complexity: O(logn)
	 * 
	 * fix up the tree to be balanced according to the RB tree rules after
	 * deletion, and returns the number of color switching during the fixup.
	 */
	public int RBDeletionFixup(RBNode x, RBNode parent)
	{
		int coloringCounter = 0;
		while (x != this.root && (x == null || !x.isRed))
		{
			if (x == parent.lChild)// x is a left child.
			{
				RBNode w = parent.rChild; // x's brother is w
				if (w != null && w.isRed)
				{ // this is case1
					parent.isRed = true;
					w.isRed = false;
					rotateLeft(parent);
					w = parent.rChild;
					coloringCounter += 2;
				}

				if ((w.lChild == null || !w.lChild.isRed) && (w.rChild == null || !w.rChild.isRed))
				{// this is case2
					w.isRed = true;
					x = parent;
					parent = parent.parent;
					coloringCounter += 1;
				}
				else if (w.rChild == null || !w.rChild.isRed)
				{// this is case3
					w.isRed = true;
					w.lChild.isRed = false;
					rotateRight(w);
					w = parent.rChild;
					coloringCounter += 2;
				}
				else
				{// this is case4. it must be it if
					// it's not one of the other cases.
					if (parent.isRed)
						coloringCounter += 3;
					else
						coloringCounter++;
					w.rChild.isRed = false;
					w.isRed = parent.isRed;
					parent.isRed = false;
					rotateLeft(parent);
					x = this.root;
				}

			}
			else
			{
				RBNode w = parent.lChild; // x's brother is w
				if (w.isRed)
				{ // this is case1
					parent.isRed = true;
					w.isRed = false;
					rotateRight(parent);
					w = parent.lChild;
					coloringCounter += 2;
				}
				if ((w.lChild == null || !w.lChild.isRed) && (w.rChild == null || !w.rChild.isRed))
				{// this is case2
					w.isRed = true;
					x = parent;
					parent = parent.parent;
					coloringCounter += 1;
				}
				else if (w.lChild == null || !w.lChild.isRed)
				{// this is case3
					w.isRed = true;
					w.rChild.isRed = false;
					rotateLeft(w);
					w = parent.lChild;
					coloringCounter += 2;
				}
				else
				{ // this is case4. it must be it if
					// it's not one of the other cases.
					if (parent.isRed)
						coloringCounter += 3;
					else
						coloringCounter++;
					w.lChild.isRed = false;
					w.isRed = parent.isRed;
					parent.isRed = false;
					rotateRight(parent);
					x = this.root;
				}
			}
		}
		if (x != null)
			x.isRed = false;
		updateRoot();
		return coloringCounter;
	}

	/**
	 * updateRoot()
	 * 
	 * complexity: O(logn)
	 * 
	 * updates the root to be the correct root.
	 */
	public void updateRoot()
	{
		if (this.root == null)
			return;
		while (this.root.parent != null && this.root.parent != dummy)
			this.root = this.root.parent;
		this.root.parent = dummy;
	}

	/**
	 * successor(RBNode x)
	 * 
	 * complexity: O(logn)
	 * 
	 * returns the successor of x.
	 */
	public RBNode successor(RBNode x)
	{
		if (x.rChild != null)
			return min(x.rChild);
		RBNode y = x.parent;
		while (y != null && x == y.rChild)
		{
			x = y;
			y = x.parent;
		}
		return y;
	}

	/**
	 * predecessor(RBNode x)
	 * 
	 * complexity: O(logn)
	 * 
	 * returns the predecessor of x.
	 */
	public RBNode predecessor(RBNode x)
	{
		if (x.lChild != null)
			return max(x.lChild);
		RBNode y = x.parent;
		while (y != null && x == y.lChild)
		{
			x = y;
			y = x.parent;
		}
		return y;
	}

	/**
	 * min(RBNode x)
	 * 
	 * complexity: O(logn)
	 * 
	 * returns the minimum of the tree where x is his root.
	 */
	public RBNode min(RBNode x)
	{
		while (x.lChild != null)
			x = x.lChild;
		return x;
	}

	/**
	 * max(RBNode x)
	 * 
	 * complexity: O(logn)
	 * 
	 * returns the maximum of the tree where x is his root.
	 */
	public RBNode max(RBNode x)
	{
		while (x.rChild != null)
			x = x.rChild;
		return x;
	}
}
