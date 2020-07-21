package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Complexity: 
 * 
 * @author harish
 *
 */
public class UnderscorifyString {

	public static void main(String[] args) {
		System.out.println(underscorifySubstring("ttttttttttb", "ttt"));
		System.out.println(underscorifySubstring("ttttt", "ttt"));
		System.out.println(underscorifySubstring("hellodad this is harishdad my dadadadad is pretty daddad", "dad"));
	}
	public static String underscorifySubstring(String str, String substring) {
		List<List<Integer>> locations = getLocations(str, substring);
		if (locations.isEmpty()) return str;
		List<List<Integer>> flattenLocations = getFlattenedLocations(locations);
		return constructFinalStringWithUnderscore(str, flattenLocations);
	}
	private static List<List<Integer>> getLocations(String str, String substring) {
		List<List<Integer>> locations = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			int index = str.indexOf(substring, i);
			if (index == -1) {
				break;
			}
			List<Integer> array = new ArrayList<>();
			array.add(index);
			array.add(index + substring.length());
			locations.add(array); //start and end index to insert _
			if (index > i + 1) {
				i = index + 1; //between i and index, no more substring, so jump index + 1 for next search
			}
		}
		return locations;
	}
	private static List<List<Integer>> getFlattenedLocations(List<List<Integer>> locations) {
		List<List<Integer>> flattenLocations = new ArrayList<>();
		//correct overlapping indicies
		//example1: sitting next to next transform  [[0, 4], [4, 8]] => [[0,8]]
		//example2: overlapping transform [[0,4], [2,6]] => [[0, 6]
		for (int i = 0; i < locations.size(); i++) {
			if (i == 0) {
				flattenLocations.add(locations.get(i));
				continue;
			}
			List<Integer> prevArray = flattenLocations.get(flattenLocations.size() - 1);
			if (prevArray.get(1) >= locations.get(i).get(0)) {
				prevArray.set(1, locations.get(i).get(1));
			} else {
				flattenLocations.add(locations.get(i));
			}
		}
		return flattenLocations;
	}
	//construct underscorifying string
	private static String constructFinalStringWithUnderscore(String str, List<List<Integer>> flattenLocations) {
		int listCount = 0;
		StringBuilder builder = new StringBuilder();
		List<Integer> underScoreList = flattenLocations.get(listCount);
		for (int i = 0; i < str.length(); i++) {
			if (!underScoreList.isEmpty()) {
				if (i == underScoreList.get(0)) {
					builder.append("_").append(str.charAt(i));
				} else if (i == underScoreList.get(1)) {
					builder.append("_").append(str.charAt(i));
					listCount++;
					if (listCount < flattenLocations.size()) {
						underScoreList = flattenLocations.get(listCount);
					} else {
						underScoreList = new ArrayList<>();
					}
				} else if (i == str.length() - 1 && i + 1 == underScoreList.get(1)) { 
					builder.append(str.charAt(i)).append("_");
				} else {
					builder.append(str.charAt(i));
				}				
			} else {
				builder.append(str.charAt(i));
			}
		}
		//[[0, 4], [14, 22], [33, 43]]
		return builder.toString();
	}
}


	

