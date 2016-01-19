package util;

public class StringHelper {
	public static String toDisplayCase(String s) {
	    final String ACTIONABLE_DELIMITERS = " '-/"; 

	    StringBuilder sb = new StringBuilder();
	    boolean capitalizedNext = true;

	    for (char c : s.toCharArray()) {
	        c = (capitalizedNext)
	                ? Character.toUpperCase(c)
	                : Character.toLowerCase(c);
	        sb.append(c);
	        capitalizedNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
	    }
	    return sb.toString();
	}
}
