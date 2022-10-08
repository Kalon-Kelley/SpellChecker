package spellchecker;

import java.io.*;
import java.util.*;
import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicDictionary implements Dictionary {

	// creating a new BinaryTree object and a List of Strings for holding lines
	private BinaryTree binaryTreeDict = new BinaryTree();
	private List<String> lines;

	@Override
	// imports each word from the dictionary file into the BinaryTree
	public void importFile(String filename) throws Exception {
		if (binaryTreeDict != null) {
			binaryTreeDict.clear();
		}
		lines = readFileAsLines(filename);
		ArrayList<Integer> positions = getIndexes();
		for (int position : positions) {
			String trimmedWord = lines.get(position).trim();
			this.binaryTreeDict.insert(trimmedWord);
		}
	}


	// method to call the getIndexes method on an ArrayList of integers
	public ArrayList<Integer> getIndexes() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		indexes = getIndexes(indexes, 0, this.lines.size() - 1);
		return indexes;
	}


	// method that calculates the index of each word that should be added for a
	// complete tree
	public ArrayList<Integer> getIndexes(ArrayList<Integer> positions, int start, int end) {
		if (start > end) {
			return null;
		}
		int half = 0;
		if (start == 0 && end == this.lines.size() - 1) {
			int nodes = (int) (Math.floor((Math.log(this.lines.size())) / (Math.log(2))));
			half = (int) (((Math.pow(2, (nodes + 1))) - 2) / (2));
		} else {
			half = (int) Math.ceil((start + end) / 2.0);
		}
		positions.add(half);
		getIndexes(positions, start, half - 1);
		getIndexes(positions, half + 1, end);
		return positions;

	}


	@Override
	// loads a pre-saved dictionary document into the BinaryTree from preorder
	public void load(String filename) throws Exception {
		if (binaryTreeDict != null) {
			binaryTreeDict.clear();
		}
		List<String> lines = readFileAsLines(filename);
		for (String word : lines) {
			String trimmedWord = word.trim();
			this.binaryTreeDict.insert(trimmedWord);
		}
	}


	@Override
	// saves the BinaryTree dictionary to a file in preorder
	public void save(String filename) throws Exception {
		List<String> preorder = this.binaryTreeDict.getPreorder();
		FileWriter fileWriter = new FileWriter(filename, false);
		for (String word : preorder) {
			fileWriter.write(word + '\n');
		}
		fileWriter.close();
	}


	@Override
	// calls the find method on the BinaryTree for a set word
	public String[] find(String word) {
		String[] words = this.binaryTreeDict.find(word);
		return words;
	}


	@Override
	// adds a given word to the BinaryTree
	public void add(String word) {
		this.binaryTreeDict.insert(word);
	}


	@Override
	// returns the root node of the BinaryTree
	public BinaryTreeNode getRoot() {
		return this.binaryTreeDict.dict;
	}


	@Override
	// gets the count of nodes in the BinaryTree
	public int getCount() {
		return this.binaryTreeDict.count;
	}


	// calls the spellcheck method on the BinaryTree passing through a word
	public boolean spellCheck(String word) {
		return binaryTreeDict.spellCheck(word);
	}

}
