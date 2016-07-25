import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class RBTreeTests
{

	@Test
	public void RBNodeIsRed_NewNode_NodeIsRed()
	{
		final int key = 1;
		final String value = "1";
		RBTree.RBNode node = new RBTree.RBNode(key, value);

		Assert.assertTrue(node.isRed());
	}

	@Test
	public void RBNodeGetKey_NewNodeWithKey17_NodeKeyIs17()
	{
		final int key = 17;
		final String value = "1";
		RBTree.RBNode node = new RBTree.RBNode(key, value);

		Assert.assertEquals(17, node.getKey());
	}

	@Test
	public void RBNodeGetValue_NewNodeWithValueABC_NodeValueIsABC()
	{
		final int key = 1;
		final String value = "ABC";
		RBTree.RBNode node = new RBTree.RBNode(key, value);

		Assert.assertEquals("ABC", node.getValue());
	}

	@Test
	public void RBNodeGetRightGetLeft_NewNode_RightAndLeftAreNull()
	{
		final int key = 1;
		final String value = "1";
		RBTree.RBNode node = new RBTree.RBNode(key, value);

		Assert.assertNull(node.getLeft());
		Assert.assertNull(node.getRight());
	}

	@Test
	public void RBTreeInsert_NewTree_RootIsNull()
	{
		RBTree tree = new RBTree();

		Assert.assertNull(tree.getRoot());
	}

	@Test
	public void RBTreeInsert_TreeHasOneNode_RootIsTheOneNode()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key, value));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(key, value, false));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasThreeNodeInsertedInKeyOrder_RootAndItsRightAndLeftAreCorrect()
	{
		final int key1 = 1;
		final String value1 = "A";
		final int key2 = 2;
		final String value2 = "B";
		final int key3 = 3;
		final String value3 = "C";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key1, value1));
		Assert.assertEquals(0, tree.insert(key2, value2));
		Assert.assertEquals(2, tree.insert(key3, value3));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(key1, value1, true));
		expected.add(new NodeData(key2, value2, false));
		expected.add(new NodeData(key3, value3, true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasThreeNodeInsertedInReverseKeyOrder_RootAndItsRightAndLeftAreCorrect()
	{
		final int key1 = 1;
		final String value1 = "A";
		final int key2 = 2;
		final String value2 = "B";
		final int key3 = 3;
		final String value3 = "C";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key3, value3));
		Assert.assertEquals(0, tree.insert(key2, value2));
		Assert.assertEquals(2, tree.insert(key1, value1));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(key1, value1, true));
		expected.add(new NodeData(key2, value2, false));
		expected.add(new NodeData(key3, value3, true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasThreeNodeInsertedInMixedKeyOrder_RootAndItsRightAndLeftAreCorrect()
	{
		final int key1 = 1;
		final String value1 = "A";
		final int key2 = 2;
		final String value2 = "B";
		final int key3 = 3;
		final String value3 = "C";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key2, value2));
		Assert.assertEquals(0, tree.insert(key1, value1));
		Assert.assertEquals(0, tree.insert(key3, value3));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(key1, value1, true));
		expected.add(new NodeData(key2, value2, false));
		expected.add(new NodeData(key3, value3, true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasManyNodes_StructureIsCorrect()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(7, "7")); //
		Assert.assertEquals(0, tree.insert(5, "5"));
		Assert.assertEquals(0, tree.insert(8, "8"));
		Assert.assertEquals(4, tree.insert(17, "17"));
		Assert.assertEquals(0, tree.insert(1, "1"));
		Assert.assertEquals(2, tree.insert(2, "2"));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(1, "1", true));
		expected.add(new NodeData(2, "2", false));
		expected.add(new NodeData(5, "5", true));
		expected.add(new NodeData(7, "7", false));
		expected.add(new NodeData(8, "8", false));
		expected.add(new NodeData(17, "17", true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasManyOtherNodes_StructureIsCorrect()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(5, "5"));
		Assert.assertEquals(0, tree.insert(13, "13"));
		Assert.assertEquals(0, tree.insert(2, "2"));
		Assert.assertEquals(4, tree.insert(9, "9"));
		Assert.assertEquals(0, tree.insert(22, "22"));
		Assert.assertEquals(0, tree.insert(1, "1"));

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(1, "1", true));
		expected.add(new NodeData(2, "2", false));
		expected.add(new NodeData(5, "5", false));
		expected.add(new NodeData(9, "9", true));
		expected.add(new NodeData(13, "13", false));
		expected.add(new NodeData(22, "22", true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeInsert_TreeHasOneNodeAndInsertingItAgain_ReturnsMinus1()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key, value));
		Assert.assertEquals(-1, tree.insert(key, value));
		Assert.assertEquals(-1, tree.insert(key, value));
	}

	@Test
	public void RBTreeInsert_TreeHasThreeNodesAndInsertThemAgain_ReturnsMinus1()
	{
		final int key1 = 1;
		final String value1 = "A";
		final int key2 = 2;
		final String value2 = "B";
		final int key3 = 3;
		final String value3 = "C";
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(key1, value1));//
		Assert.assertEquals(0, tree.insert(key2, value2));
		Assert.assertEquals(2, tree.insert(key3, value3));

		Assert.assertEquals(-1, tree.insert(key1, value1));
		Assert.assertEquals(-1, tree.insert(key2, value2));
		Assert.assertEquals(-1, tree.insert(key3, value3));
	}

	@Test
	public void RBTreeInsert_TreeHasManyNodesAndInsertThemAgain_ReturnsMinus1()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(7, "7"));
		Assert.assertEquals(0, tree.insert(5, "5"));
		Assert.assertEquals(0, tree.insert(8, "8"));
		Assert.assertEquals(4, tree.insert(17, "17"));
		Assert.assertEquals(0, tree.insert(1, "1"));
		Assert.assertEquals(2, tree.insert(2, "2"));

		Assert.assertEquals(-1, tree.insert(7, "7"));
		Assert.assertEquals(-1, tree.insert(5, "5"));
		Assert.assertEquals(-1, tree.insert(8, "8"));
		Assert.assertEquals(-1, tree.insert(17, "17"));
		Assert.assertEquals(-1, tree.insert(1, "1"));
		Assert.assertEquals(-1, tree.insert(2, "2"));
	}

	@Test
	public void RBTreeInsert_TreeHasManyOtherNodesAndInsertedAgain_ReturnsMinus1()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(1, tree.insert(5, "5"));
		Assert.assertEquals(0, tree.insert(13, "13"));
		Assert.assertEquals(0, tree.insert(2, "2"));
		Assert.assertEquals(4, tree.insert(9, "9"));
		Assert.assertEquals(0, tree.insert(22, "22"));
		Assert.assertEquals(0, tree.insert(1, "1"));

		Assert.assertEquals(-1, tree.insert(1, "5"));
		Assert.assertEquals(-1, tree.insert(22, "13"));
		Assert.assertEquals(-1, tree.insert(9, "2"));
		Assert.assertEquals(-1, tree.insert(2, "9"));
		Assert.assertEquals(-1, tree.insert(13, "22"));
		Assert.assertEquals(-1, tree.insert(5, "1"));
	}

	@Test
	public void RBTreeDelete_NewTree_ReturnsMinus1()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(-1, tree.delete(17));
	}

	@Test
	public void RBTreeDelete_TreeHasOneNodeAndDeletingIt_CorrectValueReturned()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		int colorSwitches = tree.delete(key);
		Assert.assertEquals(0, colorSwitches);
	}

	@Test
	public void RBTreeDelete_TreeHasOneNodeAndDeletingNonExistingKey_ReturnsMinus1()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		int colorSwitches = tree.delete(5);
		Assert.assertEquals(-1, colorSwitches);
	}

	@Test
	public void RBTreeDelete_TreeHasManyNodeAndDeletingNonExistingKeys_ReturnsMinus1()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertEquals(-1, tree.delete(0));
		Assert.assertEquals(-1, tree.delete(4));
		Assert.assertEquals(-1, tree.delete(25));
	}

	@Test
	public void RBTreeDelete_TreeHasManyNodeAndDeletingLeafNode_ReturnsCorrectNumberOfColorSwitches()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		int colorSwitches = tree.delete(22);
		Assert.assertEquals(0, colorSwitches);

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(1, "1", true));
		expected.add(new NodeData(2, "2", false));
		expected.add(new NodeData(5, "5", false));
		expected.add(new NodeData(9, "9", true));
		expected.add(new NodeData(13, "13", false));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeDelete_TreeHasManyNodeAndDeletingMiddleNode_ReturnsCorrectNumberOfColorSwitches()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		int colorSwitches = tree.delete(13);
		Assert.assertEquals(0, colorSwitches);// TODO delteting red lead.

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(1, "1", true));
		expected.add(new NodeData(2, "2", false));
		expected.add(new NodeData(5, "5", false));
		expected.add(new NodeData(9, "9", true));
		expected.add(new NodeData(22, "22", false));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeDelete_TreeHasManyNodeAndDeletingRootNode_ReturnsCorrectNumberOfColorSwitches()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		int colorSwitches = tree.delete(5);
		Assert.assertEquals(0, colorSwitches);

		ArrayList<NodeData> expected = new ArrayList<NodeData>();
		expected.add(new NodeData(1, "1", true));
		expected.add(new NodeData(2, "2", false));
		expected.add(new NodeData(9, "9", false));
		expected.add(new NodeData(13, "13", false));
		expected.add(new NodeData(22, "22", true));
		assertTree(expected, tree);
	}

	@Test
	public void RBTreeSearch_NewTree_ReturnsNull()
	{
		RBTree tree = new RBTree();

		Assert.assertNull(tree.search(17));
	}

	@Test
	public void RBTreeSearch_TreeHasOneNodeAndSearchingForIt_CorrectValueReturned()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		String foundValue = tree.search(key);
		Assert.assertEquals(value, foundValue);
	}

	@Test
	public void RBTreeSearch_TreeHasOneNodeAndSearchingForNonExistingKey_ReturnsNull()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		Assert.assertNull(tree.search(5));
	}

	@Test
	public void RBTreeSearch_TreeHasManyNodeAndSearchingForNonExistingKeys_ReturnsNull()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertNull(tree.search(0));
		Assert.assertNull(tree.search(4));
		Assert.assertNull(tree.search(25));
	}

	@Test
	public void RBTreeSearch_TreeHasManyNodeAndSearchingForExistingKeys_ReturnsCorrectValues()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertEquals("5", tree.search(5));
		Assert.assertEquals("13", tree.search(13));
		Assert.assertEquals("2", tree.search(2));
		Assert.assertEquals("9", tree.search(9));
		Assert.assertEquals("22", tree.search(22));
		Assert.assertEquals("1", tree.search(1));
	}

	@Test
	public void RBTreeEmpty_NewTree_ReturnsTrue()
	{
		RBTree tree = new RBTree();

		Assert.assertTrue(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasOneNode_ReturnsFalse()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		Assert.assertFalse(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasOneNodeAndTriedToDeleteNonExistingKey_ReturnsFalse()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);
		tree.delete(5);

		Assert.assertFalse(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasOneNodeAndDeletingIt_ReturnsTrue()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);
		tree.delete(key);

		Assert.assertTrue(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasManyNodes_ReturnsFalse()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertFalse(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasManyNodesAndExistingKeyDeleted_ReturnsFalse()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");
		tree.delete(13);

		Assert.assertFalse(tree.empty());
	}

	@Test
	public void RBTreeEmpty_TreeHasManyNodesAndAllKeysDeleted_ReturnsTrue()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");
		tree.delete(5);
		tree.delete(13);
		tree.delete(2);
		tree.delete(9);
		tree.delete(22);
		tree.delete(1);

		Assert.assertTrue(tree.empty());
	}

	@Test
	public void RBTreeSize_NewTree_Returns0()
	{
		RBTree tree = new RBTree();

		Assert.assertEquals(0, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasOneNode_Returns1()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		Assert.assertEquals(1, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasOneNodeAndTriedToDeleteNonExistingKey_Returns1()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);
		tree.delete(5);

		Assert.assertEquals(1, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasOneNodeAndDeletingIt_Returns0()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);
		tree.delete(key);

		Assert.assertEquals(0, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasManyNodes_ReturnsCorrectSize()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertEquals(6, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasManyNodesAndExistingKeyDeleted_ReturnsCorrectSize()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");
		tree.delete(13);

		Assert.assertEquals(5, tree.size());
	}

	@Test
	public void RBTreeSize_TreeHasManyNodesAndAllKeysDeleted_Returns0()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");
		tree.delete(5);
		tree.delete(13);
		tree.delete(2);
		tree.delete(9);
		tree.delete(22);
		tree.delete(1);

		Assert.assertEquals(0, tree.size());
	}

	@Test
	public void RBTreeMinMax_NewTree_ReturnsNull()
	{
		RBTree tree = new RBTree();

		Assert.assertNull(tree.min());
		Assert.assertNull(tree.max());
	}

	@Test
	public void RBTreeMinMax_TreeHasOneNode_ReturnsSameMinMax()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		Assert.assertEquals(value, tree.min());
		Assert.assertEquals(value, tree.max());
	}

	@Test
	public void RBTreeMinMax_TreeHasManyNodes_ReturnsCorrectMinMax()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		Assert.assertEquals("1", tree.min());
		Assert.assertEquals("22", tree.max());
	}

	public void RBTreeMinMax_TreeHasManyOtherNodes_ReturnsCorrectMinMax()
	{
		RBTree tree = new RBTree();

		tree.insert(7, "7");
		tree.insert(5, "5");
		tree.insert(8, "8");
		tree.insert(17, "17");
		tree.insert(1, "1");
		tree.insert(2, "2");

		Assert.assertEquals("1", tree.min());
		Assert.assertEquals("17", tree.max());
	}

	@Test
	public void RBTreeToArray_NewTree_ReturnsEmptyArrays()
	{
		RBTree tree = new RBTree();

		int[] keys = tree.keysToArray();
		Assert.assertEquals(0, keys.length);

		String[] values = tree.valuesToArray();
		Assert.assertEquals(0, values.length);
	}

	@Test
	public void RBTreeToArray_TreeHasOneNode_ReturnsCorrectArrays()
	{
		final int key = 17;
		final String value = "ABC";
		RBTree tree = new RBTree();

		tree.insert(key, value);

		int[] keys = tree.keysToArray();
		int[] expectedKeys =
		{ key };
		Assert.assertArrayEquals(expectedKeys, keys);

		String[] values = tree.valuesToArray();
		String[] expectedValues =
		{ value };
		Assert.assertArrayEquals(expectedValues, values);
	}

	@Test
	public void RBTreeToArray_TreeHasManyNodes_ReturnsCorrectArrays()
	{
		RBTree tree = new RBTree();

		tree.insert(5, "5");
		tree.insert(13, "13");
		tree.insert(2, "2");
		tree.insert(9, "9");
		tree.insert(22, "22");
		tree.insert(1, "1");

		int[] keys = tree.keysToArray();
		int[] expectedKeys =
		{ 1, 2, 5, 9, 13, 22 };
		Assert.assertArrayEquals(expectedKeys, keys);

		String[] values = tree.valuesToArray();
		String[] expectedValues =
		{ "1", "2", "5", "9", "13", "22" };
		Assert.assertArrayEquals(expectedValues, values);
	}

	public void RBTreeToArray_TreeHasManyOtherNodes_ReturnsCorrectArrays()
	{
		RBTree tree = new RBTree();

		tree.insert(7, "7");
		tree.insert(5, "5");
		tree.insert(8, "8");
		tree.insert(17, "17");
		tree.insert(1, "1");
		tree.insert(2, "2");

		int[] keys = tree.keysToArray();
		int[] expectedKeys =
		{ 1, 2, 5, 7, 8, 17 };
		Assert.assertArrayEquals(expectedKeys, keys);

		String[] values = tree.valuesToArray();
		String[] expectedValues =
		{ "1", "2", "5", "7", "8", "17" };
		Assert.assertArrayEquals(expectedValues, values);
	}

	private class NodeData
	{
		public final int key;
		public final String value;
		public final boolean isRed;

		public NodeData(int key, String value, boolean isRed)
		{
			this.key = key;
			this.value = value;
			this.isRed = isRed;
		}
	}

	private void assertTree(ArrayList<NodeData> inOrderExpectedValues, RBTree tree)
	{
		RBTree.RBNode root = tree.getRoot();
		traverseTree(inOrderExpectedValues, root);
		Assert.assertTrue(inOrderExpectedValues.isEmpty());
	}

	private void traverseTree(ArrayList<NodeData> data, RBTree.RBNode node)
	{
		if (node == null)
			return;
		traverseTree(data, node.getLeft());
		assertNode(data.get(0), node);
		data.remove(0);
		traverseTree(data, node.getRight());
	}

	private void assertNode(NodeData expected, RBTree.RBNode node)
	{
		Assert.assertEquals(expected.key, node.getKey());
		Assert.assertEquals(expected.value, node.getValue());
		Assert.assertEquals(expected.isRed, node.isRed());
	}
}
