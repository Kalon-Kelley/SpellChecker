package spellchecker;

import static sbcc.Core.*;
import java.util.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BinaryTree {

	// creating a BinaryTreeNode for storage and a integer count to keep track of
	// items in the tree
	public BinaryTreeNode dict;
	public int count = 0;

	// inserts a given word to the correct node of the tree
	public void insert(String aWord) {
		String word = aWord.toLowerCase();
		if (count == 0) {
			this.dict = new BinaryTreeNode(word);
			this.count++;
		} else {
			if (findSpot(word)) {
				this.count++;
			}
		}
	}


	// finds an empty location in the BinaryTreeNode for the word to be inserted
	public boolean findSpot(String aWord) {
		String word = aWord.toLowerCase();
		ArrayList<Integer> steps = new ArrayList<Integer>();
		BinaryTreeNode cursorNode = this.dict;
		while ((cursorNode.left != null && word.compareTo(cursorNode.value) < 0)
				|| (cursorNode.right != null && word.compareTo(cursorNode.value) > 0)) {
			if (word.compareTo(cursorNode.value) < 0) {
				steps.add(0);
				cursorNode = cursorNode.left;
			} else {
				steps.add(1);
				cursorNode = cursorNode.right;
			}
		}
		BinaryTreeNode tempNode = new BinaryTreeNode(word);
		if (word.compareTo(cursorNode.value) < 0) {
			cursorNode.left = tempNode;
		} else if (word.compareTo(cursorNode.value) > 0) {
			cursorNode.right = tempNode;
		} else {
			return false;
		}
		return true;
	}


	// method to call the getPreorder method on an ArrayList of integers
	public List<String> getPreorder() {
		ArrayList<String> preorder = new ArrayList<String>();
		getPreorder(this.dict, preorder);
		return preorder;
	}


	// recursive function to get the preorder of the nodes in the BinaryTreeNode
	public void getPreorder(BinaryTreeNode node, ArrayList<String> preorder) {
		if (node != null) {
			preorder.add(node.value);
			getPreorder(node.left, preorder);
			getPreorder(node.right, preorder);
		}
	}


	// clears the BinaryTreeNode
	public void clear() {
		this.dict = null;
		this.count = 0;
	}


	// finds a specific word in the BinaryTreeNode and if not found it returns the
	// preceding and succeeding words
	public String[] find(String aWord) {
		String word = aWord.toLowerCase();
		String[] words = new String[2];
		BinaryTreeNode cursor = this.dict;
		while (!word.equals(cursor.value)) {
			if (word.compareTo(cursor.value) < 0) {
				words[1] = cursor.value;
				if (cursor.left != null) {
					cursor = cursor.left;
				} else {
					break;
				}
			} else {
				words[0] = cursor.value;
				if (cursor.right != null) {
					cursor = cursor.right;
				} else {
					break;
				}
			}
		}
		if (word.equals(cursor.value)) {
			return null;
		} else {
			return words;
		}
	}


	// spellchecks a given word if the word returns true if the word is found
	public boolean spellCheck(String aWord) {
		String word = aWord.toLowerCase();
		BinaryTreeNode cursor = this.dict;
		while (!word.equals(cursor.value)) {
			if (word.compareTo(cursor.value) < 0) {
				if (cursor.left != null) {
					cursor = cursor.left;
				} else {
					break;
				}
			} else {
				if (cursor.right != null) {
					cursor = cursor.right;
				} else {
					break;
				}
			}
		}
		if (word.equals(cursor.value)) {
			return true;
		} else {
			return false;
		}
	}
}
