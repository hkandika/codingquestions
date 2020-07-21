package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Complexity: O(n) time | O(n) space
 * 
 * @author harish
 *
 */
public class LongestStringWithoutDups {

	public static void main(String[] args) {
		
		/**
		 * Example Test cases
		 *  I: a
			O: a
			
			I: abc
			O: abc
			
			I: abcb
			O: abc
			
			I: abcdeabcdefc
			O: abcdef
			
			I: abccdeaabbcddef
			O: cdea
			
			I: abacacacaaabacaaaeaaafa
			O: bac
			
			I: abcdabcef
			O: dabcef
			
			I: abcbde
			O: cbde
		 */

		System.out.println(longestSubstringWithoutDuplication("a"));
		System.out.println(longestSubstringWithoutDuplication("abc"));
		System.out.println(longestSubstringWithoutDuplication("abcb"));
		System.out.println(longestSubstringWithoutDuplication("abcdeabcdefc"));
		System.out.println(longestSubstringWithoutDuplication("abccdeaabbcddef"));
		System.out.println(longestSubstringWithoutDuplication("abacacacaaabacaaaeaaafa"));
		System.out.println(longestSubstringWithoutDuplication("abcdabcef"));
		System.out.println(longestSubstringWithoutDuplication("abcbde"));
	}
	public static String longestSubstringWithoutDuplication(String str) {

		int [] indices = new int [] {0, 1};
		Map<Character, Integer> map = new HashMap<>();
		//pointer which points start index of current non-duplicate word
		int startIdx = 0;
		//iterate each char in the string
		for (int i = 0; i < str.length(); i++) {
			//If current character is already seen earlier, update the startIdx
			//Example: [aabcdefa]
			//say startIdx is at 3 i.e, pointing to b
			//and assume current found non-duplicate word so far is [bcdef] and when i comes to last index
			//i.e., a already seen at index 0 and 1, map will have overidden value as 1 for a
			//we cannot update startIdx to 1, because our current longest non-dup word is starting from
			//index 3 so all last seen characters before index 3 are useless to consider if encountered again 
			//in the current construction of longest word. So a is accountable in the current longest word [bcdefa]
			if(map.containsKey(str.charAt(i))) {
				startIdx = Math.max(startIdx, map.get(str.charAt(i)) + 1);
			}
			//keep updating start and end indices of longest non dup word
			if (i + 1 - startIdx > indices[1] - indices[0]) {
				indices[0] = startIdx;
				indices[1] = i + 1;
			}
			map.put(str.charAt(i), i);
		}
		
		return str.substring(indices[0], indices[1]);
}

}
