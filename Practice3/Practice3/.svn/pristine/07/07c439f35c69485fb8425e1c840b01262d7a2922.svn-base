package ua.nure.gunko.practice3;

public class Part3 {
	public static String convert(String input) {
		String[] lines = input.split(System.lineSeparator());
		StringBuilder result = new StringBuilder();
		StringBuilder response = new StringBuilder();
		for (String line : lines) {
			String[] words = line.split("\\s");
			for (String word : words) {
				char[] arr = word.toCharArray();
				if (arr.length >= 3 && word.matches("[À-ßà-ÿ¨¸¯¿²³ªº¥´a-zA-Z,?.-]+")) {
					if (word.contains("-")) {
						change(arr, word.indexOf('-') + 1, false);
					} else {
						if (word.contains(".")) {
							change(arr, word.indexOf('.') + 1, true);
						} else {
							change(arr);
						}
					}
				}
				result.append(arr).append(" ");
			}
			response.append(new String(result).trim() + System.lineSeparator());
			result = new StringBuilder();
		}
		return response.toString();
	}

	private static void change(char[] arr) {
		if (Character.isLowerCase(arr[0])) {
			arr[0] = Character.toUpperCase(arr[0]);
		} else {
			arr[0] = Character.toLowerCase(arr[0]);
		}
	}

	private static void change(char[] arr, int index, boolean flag) {

		if (flag) {
			if (index < arr.length) {
				if (Character.isLowerCase(arr[index])) {
					arr[index] = Character.toUpperCase(arr[index]);
				} else {
					arr[index] = Character.toLowerCase(arr[index]);
				}
			} else {
				change(arr);
			}
		} else {
			change(arr);
			change(arr, index, true);
		}
	}


	public static void main(String[] args) {
		String string = Util.readFile("part3.txt");
		System.out.println(convert(string));

	}
}
