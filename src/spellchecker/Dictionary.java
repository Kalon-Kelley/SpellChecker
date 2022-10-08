package spellchecker;

public interface Dictionary {

	/**
	 * Replaces the current dictionary with words imported from the specified text
	 * file. Words in the file are in lexicographical (ASCII) order, one word per
	 * line. BE SURE TO trim() THE WORDS BEFORE ADDING THEM TO THE TREE.
	 *
	 * @param filename
	 *            Name (possibly including a path) of the file to import.
	 * @throws Exception
	 */
	public void importFile(String filename) throws Exception;

	/**
	 * Loads the specified file from a previously saved dictionary tree file. The file format is text, with one word per
	 * line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to load from.
	 * @throws Exception
	 */
	public void load(String filename) throws Exception;

	/**
	 * Saves the dictionary tree to the specified file in preorder. The file format is text, with one word per line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to save to. If the file exists, it is overwritten.
	 * @throws Exception
	 */
	public void save(String filename) throws Exception;

	/**
	 * 
	 * @param word
	 * @return If the word is <b>found</b> this method returns <b>null</b>. Otherwise, it returns a String array
	 *         organized as follows:
	 * 
	 *         <pre>
	 *         [0] = Preceeding word in the dictionary 
	 *         [1] = Succeeding word in the dictionary 
	 *         
	 *              e.g. if the unknown word was "spelm", the result might be:
	 *              
	 *         [0] = "spells" 
	 *         [1] = "spelt"
	 *         
	 *         If there is no preceeding or succeeding word in the dictionary, set the element to "".
	 * </pre>
	 */
	public String[] find(String word);

	/**
	 * Adds the given word to the dictionary's binary tree. Duplicates are ignored.
	 * 
	 * @param word
	 */
	public void add(String word);

	/**
	 * 
	 * @return Returns a reference to the root node of the binary tree.
	 */
	public BinaryTreeNode getRoot();

	/**
	 * 
	 * @return Returns the number of words in the dictionary.
	 */
	public int getCount();
}
