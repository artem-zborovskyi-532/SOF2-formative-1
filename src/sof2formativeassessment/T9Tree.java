package sof2formativeassessment;

import java.util.HashSet;
import java.util.Set;

public class T9Tree {

	/**
	 * the set of word associated with this node in the T9Tree.
	 */
	Set<String> words;

	/**
	 * The array containing all the children of that node. The index of the
	 * children in the array correspond to the digit of a character on a numeric
	 * pad. Therefore the size of the array is 10. A null value in the array at
	 * index [i] means that the node does not have a child (branch) for that
	 * particular index.
	 */
	T9Tree[] children;

	/**
	 * create an empty node with no children.
	 */
	public T9Tree() {
		children = new T9Tree[10];
		words = new HashSet<>();
	}

	/**
	 * return true if the node has a child at position digit, false otherwise.
	 * The parameter digit represents a digit from the numeric pad or a t9 code.
	 * 
	 * @param digit
	 *            the digit of the numeric pad, or t9 code
	 * @return true if the node has a child at position digit, false otherwise.
	 * @throws IndexOutOfBoundsException
	 *             if the digit is greater than 9.
	 */
	private boolean hasChild(int digit) {
		return children[digit] != null;
	}

	/**
	 * Add a words to the dictionary of the tree. t9code is used to find the
	 * node where word should be stored. If the word already exist in the tree,
	 * it will not be added (no duplicate in the dictionary).
	 * 
	 * @param t9code
	 *            the t9code representing word
	 * @param word
	 *            the word mapped to the t9code
	 * @throws IllegalArgumentException
	 *             if t9code contains characters that are not digits, i.e not in
	 *             0..9.
	 */
	public void add(String t9code, String word) {
		if (t9code.isEmpty()) {
			if (!words.contains(word)) {
				words.add(word);
			}
		} else {
			switch (t9code.charAt(0)) {
			case '0':
				if (!hasChild(0)) {
					children[0] = new T9Tree();
				}
				children[0].add(t9code.substring(1), word);
				break;
			case '1':
				if (!hasChild(1)) {
					children[1] = new T9Tree();
				}
				children[1].add(t9code.substring(1), word);
				break;
			case '2':
				if (!hasChild(2)) {
					children[2] = new T9Tree();
				}
				children[2].add(t9code.substring(1), word);
				break;
			case '3':
				if (!hasChild(3)) {
					children[3] = new T9Tree();
				}
				children[3].add(t9code.substring(1), word);
				break;
			case '4':
				if (!hasChild(4)) {
					children[4] = new T9Tree();
				}
				children[4].add(t9code.substring(1), word);
				break;
			case '5':
				if (!hasChild(5)) {
					children[5] = new T9Tree();
				}
				children[5].add(t9code.substring(1), word);
				break;
			case '6':
				if (!hasChild(6)) {
					children[6] = new T9Tree();
				}
				children[6].add(t9code.substring(1), word);
				break;
			case '7':
				if (!hasChild(7)) {
					children[7] = new T9Tree();
				}
				children[7].add(t9code.substring(1), word);
				break;
			case '8':
				if (!hasChild(8)) {
					children[8] = new T9Tree();
				}
				children[8].add(t9code.substring(1), word);
				break;
			case '9':
				if (!hasChild(9)) {
					children[9] = new T9Tree();
				}
				children[9].add(t9code.substring(1), word);
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	public Set<String> getAllWords() {
		Set<String> all_words = new HashSet<>();

		all_words.addAll(this.words);

		for (int i = 0; i < 10; i++) {
			if (this.hasChild(i)) {
				all_words.addAll(this.children[i].getAllWords());
			}
		}
		
		return all_words;
	}

	public Set<String> getAllWords(String t9code) {
		if (t9code.isEmpty()) {
			return getAllWords();
		}

		if (!t9code.matches("[0-9]+")) {
			throw new IllegalArgumentException("The t9code contains keys that are not part of the numeric pad.");
		}

		int c = Character.getNumericValue(t9code.charAt(0));

		if (this.hasChild(c)) {
			return this.children[c].getAllWords(t9code.substring(1));
		}

		return new HashSet<>();
	}
}