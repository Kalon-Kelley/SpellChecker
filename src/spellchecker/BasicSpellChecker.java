package spellchecker;

import java.io.*;
import java.util.*;
import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicSpellChecker implements SpellChecker {

	// creating two new strins to store the document as a string and as words
	private String docString;
	private String[] document;

	// creating a new BasicDictionary and an integer to store the index of the last
	// spellchecked word
	private BasicDictionary dictionary = new BasicDictionary();
	private int prevIndex = 0;

	@Override
	// calls the importFile method on the BasicDictionary with a filename
	public void importDictionary(String filename) throws Exception {
		dictionary.importFile(filename);
	}


	@Override
	// calls the load method on the BasicDictionary with a filename
	public void loadDictionary(String filename) throws Exception {
		dictionary.load(filename);
	}


	@Override
	// calls the save method on the BasicDictionary with a filename
	public void saveDictionary(String filename) throws Exception {
		dictionary.save(filename);
	}


	@Override
	// loads a document to be spellchecked modifys the class variables
	public void loadDocument(String filename) throws Exception {
		String document = readFile(filename);
		this.docString = document;
		String[] words = document.split("\\W+");
		this.document = words;
	}


	@Override
	// saves a document to a text file
	public void saveDocument(String filename) throws Exception {
		FileWriter fileWriter = new FileWriter(filename, false);
		fileWriter.write(this.docString);
		fileWriter.close();
	}


	@Override
	// returns the text document as a string
	public String getText() {
		return this.docString;
	}


	@Override
	// spellchecks the document can continue from the previously misspelled word or
	// start from the beginning
	public String[] spellCheck(boolean continueFromPrevious) {
		String[] error = new String[4];
		if (!continueFromPrevious) {
			for (String word : this.document) {
				if (!dictionary.spellCheck(word)) {
					error = generateError(word);
					this.prevIndex = Arrays.asList(this.document).indexOf(word);
					return error;
				}
			}
		} else {
			for (String word : this.document) {
				if (Arrays.asList(this.document).indexOf(word) > this.prevIndex && !dictionary.spellCheck(word)) {
					error = generateError(word);
					this.prevIndex = Arrays.asList(this.document).indexOf(word);
					return error;
				}
			}
		}
		return null;
	}


	// generates the error to return when a misspelled word is found
	public String[] generateError(String word) {
		String[] error = new String[4];
		String[] words = new String[2];
		if (dictionary.find(word) != null) {
			words = dictionary.find(word);
		}
		int index = docString.indexOf(word);
		error[0] = word;
		error[1] = Integer.toString(index);
		if (words[0] == null) {
			error[2] = "";
		} else {
			error[2] = words[0];
		}
		if (words[1] == null) {
			error[3] = "";
		} else {
			error[3] = words[1];
		}
		return error;
	}


	@Override
	// calls the add method on the BasicDictionary passing through a word
	public void addWordToDictionary(String word) {
		dictionary.add(word);
	}


	@Override
	// replaces the text in the document from a given start index to end index with
	// a specific string
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		StringBuilder strBuilder = new StringBuilder(docString);
		strBuilder.replace(startIndex, endIndex, replacementText);
		this.docString = strBuilder.toString();
	}

}
