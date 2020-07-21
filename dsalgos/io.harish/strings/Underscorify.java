package strings;

import java.util.ArrayList;
import java.util.List;

public class Underscorify {

	public static void main(String[] args) {
		
		//System.out.println(underscorifySubstring("ttttttttttttttbtttttctatawtatttttastvb", "ttt"));

		//System.out.println(underscorifySubstring("ttttttttttttttb", "ttt"));
		System.out.println(underscorifySubstring("ttttttttb", "ttt"));
	}
	public static String underscorifySubstring(String str, String substring) {
		
		List<List<Integer>> locations = new ArrayList<>();
		List<List<Integer>> correctedOverlapLocations = new ArrayList<>();
		
		for (int i = 0; i < str.length(); i++) {
			int index = str.indexOf(substring, i);
				if (index == -1) {
					break;
				}
			List<Integer> array = new ArrayList<>();
			array.add(index);
			array.add(index + substring.length());
			System.out.println(locations);
			locations.add(array); //start and end index to insert _
			//i = index + 1; //between i and index, no more substring, so jump index + 1 for next search
		}
		if (locations.isEmpty()) return str;
		
		//correct overlapping indicies
		//example1: sitting next to next transform  [[0, 4], [4, 8]] => [[0,8]]
		//example2: overlapping transform [[0,4], [2,6]] => [[0, 6]
		for (int i = 0; i < locations.size(); i++) {
			List<Integer> nLocation = new ArrayList<>(locations.get(i));
			if (i == 0) {
				correctedOverlapLocations.add(nLocation);
				continue;
			}
			List<Integer> prevArray = correctedOverlapLocations.get(correctedOverlapLocations.size() - 1);
			if (prevArray.get(1) >= locations.get(i).get(0)) {
				prevArray.set(1, locations.get(i).get(1));
			} else {
				correctedOverlapLocations.add(nLocation);
			}
		}
		System.out.println(locations);
		System.out.println(correctedOverlapLocations);
		//construct underscorifying string
		int listCount = 0;
		StringBuilder builder = new StringBuilder();
		List<Integer> underScoreList = correctedOverlapLocations.get(listCount);
		for (int i = 0; i < str.length(); i++) {
			if (!underScoreList.isEmpty()) {
				if (i == underScoreList.get(0)) {
					builder.append("_").append(str.charAt(i));
				} else if (i == underScoreList.get(1)) {
					builder.append("_").append(str.charAt(i));
					listCount++;
					if (listCount < correctedOverlapLocations.size()) {
						underScoreList = correctedOverlapLocations.get(listCount);
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
		System.out.println(builder.toString());
		return builder.toString();
		
	}	

}



