package sof2formativeassessment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class T9Pad {

	/**
	 * keep the mapping between numeric key and alphabet characters. The keys of the
	 * map are integer comprised between 0..9 included, the values are set of lower
	 * case alphabet characters.
	 */
	HashMap<Integer, Set<Character>> pad;

	/**
	 * Construct an empty keypad. Numeric key and mapped characters must be added
	 * via the method addKey(Integer, String).
	 */
	public T9Pad() {
		pad = new HashMap<Integer, Set<Character>>();
	}

	/**
	 * Adds to the pad the mapping between key and every characters in the String
	 * letters.
	 * 
	 * For example, addKey(2, "abc") should add the mappings (2, 'a'), (2, 'b'), and
	 * (2, 'c') to the pad.
	 * 
	 * @param key
	 * @param letters
	 * @throws IllegalArgumentException
	 *             if any of the arguments is null, or if the key is not comprised
	 *             between 0 and 9 included.
	 */
	public void addKey(Integer key, String letters) {
		// Sanity check of all parameters
		if (letters == null || key == null || key < 0 || key > 9) {
			throw new IllegalArgumentException();
		}

		Set<Character> currentLetters = pad.get(key);
		if (currentLetters == null) {
			currentLetters = new HashSet<>();
		}
		for (int i = 0; i < letters.length(); i++) {
			currentLetters.add(letters.charAt(i));
		}
		pad.put(key, currentLetters);
	}

	@Override
	public String toString() {
		String output = "<T9Pad:\n";
		for (Integer key : pad.keySet()) {
			output += key + ":";
			for (Character letter : pad.get(key)) {
				output += letter;
			}
			output += "\n";
		}
		output += ">";
		return output;
	}

	/**
	 * Returns the set of keys used in the keypad, that is all the digits that are
	 * paired with at least one character
	 * 
	 * @return the set of keys used on the keypad
	 */
	public Set<Integer> keySet() {
		return pad.keySet();
	}

	public Integer getKeyCode(Character letter) throws IllegalArgumentException {
		for (Integer key : pad.keySet()) {
			if (pad.get(key).contains(letter)) {
				return key;
			}
		}

		throw new IllegalArgumentException("Letter not found: " + letter);
	}

	public List<Character> getPadLetters() {
		List<Character> characters = new ArrayList<>();

		for (Integer key : pad.keySet()) {
			for (Character c : pad.get(key)) {
				characters.add(c);
			}
		}

		return characters;
	}

	public boolean isTextonym(String word1, String word2) {
		StringBuilder word1_keys = new StringBuilder("");

		for (Character c : word1.toCharArray()) {
			word1_keys.append(this.getKeyCode(c));
		}

		StringBuilder word2_keys = new StringBuilder("");

		for (Character c : word2.toCharArray()) {
			word2_keys.append(this.getKeyCode(c));
		}

		if (word1_keys.toString().equals(word2_keys.toString())) {
			return true;
		}

		return false;
	}
}