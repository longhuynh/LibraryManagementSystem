package util;
public class RuleSetFactory {

	public static boolean isNumeric(String value) {
		if (value.isEmpty())
			return false;
		for (int i = 0; i < value.length(); i++) {
			if (!Character.isDigit(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isExactLength(String stringCheck, int length) {
		if (stringCheck.length() == length)
			return true;
		return false;
	}

	public static boolean isAllLetters(String stringCheck) {
		if (stringCheck.isEmpty())
			return false;
		for (int i = 0; i < stringCheck.length(); i++) {

			if (!Character.isLetter(stringCheck.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// A-Z
	public static boolean isAllCapitals(String str) {
		if (str.isEmpty())
			return false;
		for (int i = 0; i < str.length(); i++) {

			if (!Character.isUpperCase(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}