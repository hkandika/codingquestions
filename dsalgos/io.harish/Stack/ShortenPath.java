package Stack;

import java.util.*;
import java.util.stream.*;


/**
 * 
 * 
 * Given a unix path absolute or relative, find its shortest form
 * 
 * Input: /sam/../dam/../dam/../sam//lol/./caz
 * 
 * Output: /sam/lol/caz
 * 
 * @author harish
 *
 */
public class ShortenPath {

	public static void main(String[] args) {
		System.out.println(shortenPath("/sam/../dam/../dam/../sam//lol/./caz")); //  /sam/lol/caz
		System.out.println(shortenPath("sam/../dam/../dam/../sam//lol/./caz")); //    sam/lol/caz
		System.out.println(shortenPath("../../../sam")); //                ../../../sam
		System.out.println(shortenPath("/root/../test/../../sam")); //     /sam
		System.out.println(shortenPath("/root/../test/.///////sam")); //   /test/sam
		System.out.println(shortenPath("sam/dam/./././cam/./ram"));//      sam/dam/cam/ram

	}
	public static String shortenPath(String path) {
		boolean isAbsPath = path.startsWith("/");
		String [] tokens = path.split("/");
		List<String> tokenList = Arrays.asList(tokens);
		List<String>  filteredTokens = 
			tokenList.stream().filter(token -> validToken(token)).collect(Collectors.toList());
		
		List<String> stack = new ArrayList<>();
		if(isAbsPath) stack.add("");
		
		for(int i = 0; i < filteredTokens.size(); i++) {
			int stackSize = stack.size();
			if(filteredTokens.get(i).equals("..")) {
				if (stack.size() == 0 || stack.get(stackSize - 1).equals("..")) {
					stack.add(filteredTokens.get(i));
				} else if (!stack.get(stackSize - 1).equals("")) {
					stack.remove(stackSize - 1);
				}
			} else {
				stack.add(filteredTokens.get(i));
			}
		}
		if (stack.size() == 1 && stack.get(stack.size() - 1).equals("")) return "/";
		return String.join("/", stack);
	}
		
	public static boolean validToken(String token) {
		return token.length() > 0 && !token.equals(".");
	}
}
