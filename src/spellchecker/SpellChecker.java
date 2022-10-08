package spellchecker;

public interface SpellChecker {

	/**
	 * Replaces the current dictionary with words imported from the specified text file. Words in the file are in
	 * lexicographical (ASCII) order, one word per line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to import.
	 * @throws Exception
	 */
	public void importDictionary(String filename) throws Exception;


	/**
	 * Loads the specified file from a previously saved dictionary tree file. The file format is text, with one word per
	 * line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to load from.
	 * @throws Exception
	 */
	public void loadDictionary(String filename) throws Exception;


	/**
	 * Saves the dictionary tree to the specified file in preorder. The file format is text, with one word per line.
	 * 
	 * @param filename
	 *            Name (possibly including a path) of the file to save to. If the file exists, it is overwritten.
	 * @throws Exception
	 */
	public void saveDictionary(String filename) throws Exception;


	/**
	 * Loads the specified text document.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void loadDocument(String filename) throws Exception;


	/**
	 * Saves the specified text document.
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void saveDocument(String filename) throws Exception;


	/**
	 * 
	 * @return Returns the text in the document.
	 */
	public String getText();


	/**
	 * Starts/continues a spell check of the text. Use the regular expression below to match words (it's not great, but
	 * it's simple and works OK for basic text).
	 * 
	 * <pre>
	 * \b[\w']+\b
	 * </pre>
	 * 
	 * The method returns when the first unknown word is located or when the end of the document is reached (whichever
	 * comes first). The index of the character after the unknown word is retained for use as the starting index for
	 * subsequent calls to spellCheck where continueFromPrevious is true.
	 * 
	 * @param continueFromPrevious
	 *            If false, a new spell check is started from the first character of the document. If true, the spell
	 *            check continues from it's current location.
	 * @return If no unknown word is found this method returns null. Otherwise, it returns a String array organized as
	 *         follows:
	 * 
	 *         <pre>
	 *         [0] = Unknown word 
	 *         [1] = Index of start of unknown word.  The index is the position within the entire document.
	 *         [2] = Preceeding word in the dictionary .  If the unknown word is before all words in the dictionary, this element should be "".
	 *         [3] = Succeeding word in the dictionary.  If the unknown word is after all words in the dictionary, this element should be "".
	 *              e.g. 
	 *         [0] = "spelm"
	 *         [1] = "224"
	 *         [2] = "spells" 
	 *         [3] = "spelt"
	 *         </pre>
	 */
	public String[] spellCheck(boolean continueFromPrevious);


	/**
	 * Adds the given word to the dictionary's tree
	 * 
	 * @param word
	 */
	public void addWordToDictionary(String word);


	/**
	 * Replaces text in the document from startIndex to just before endIndex with the given replacementText.
	 * 
	 * NOTE: Be sure to update your spell checker index by adding the difference between the length of the replacement
	 * text and the length of the text that was replaced.
	 * 
	 * @param startIndex
	 *            Index of the first character to replace.
	 * @param endIndex
	 *            Index of the character <b>after</b> the last character to replace.
	 * @param replacementText
	 */
	public void replaceText(int startIndex, int endIndex, String replacementText);

}
