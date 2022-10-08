package spellchecker;


public class BinaryTreeNode {

	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public String value;


	BinaryTreeNode(String value) {
		this.value = value;
	}


	BinaryTreeNode(BinaryTreeNode node) {
		left = node.left;
		right = node.right;
		value = node.value;
	}
}
