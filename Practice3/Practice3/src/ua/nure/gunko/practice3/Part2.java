package ua.nure.gunko.practice3;

public class Part2 {

	private static final String EMPTY = "";

	public static String convert(String input) {
		String[] words = input.split("\\s|-|, |'|,");
		int min = min(words);
		int max = max(words);
		StringBuilder maxEl = new StringBuilder();
		StringBuilder minEl = new StringBuilder();
		StringBuilder response = new StringBuilder();
		for (String word : words) {
			if (word.matches("[À-ßà-ÿ¨¸¯¿²³ªº¥´'a-zA-Z]+")) {
				if (word.length() == min) {
					minEl.append(word).append(System.lineSeparator());
				} else {
					if (word.length() == max) {
						maxEl.append(word).append(System.lineSeparator());
					}
				}
			}
		}

		response.append("Min: ").append(sort(minEl.toString().split("\\s"))).append(System.lineSeparator())
				.append("Max: ").append(sort(maxEl.toString().split("\\s")));
		return response.toString();
	}

	private static int min(String[] array) {
		int min = array[0].length();
		for (String el : array) {
			if (el.length() < min && !"".equals(el) && el.matches("[a-zA-Z]+")) {
				min = el.length();
			}
		}
		return min;
	}

	private static int max(String[] array) {
		int max = array[0].length();
		for (String el : array) {
			if (el.length() > max && el.matches("[a-zA-Z]+")) {
				max = el.length();
			}
		}
		return max;
	}

	public static String sort(String[] arr) {
		StringBuilder result = new StringBuilder(EMPTY);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				String str = arr[i];
				if (result.toString().contains(str)) {
					continue;
				} else {
					if (i != arr.length - 1) {
						result.append(str).append(", ");
					} else {
						result.append(str).append(EMPTY);
					}
				}
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String string = Util.readFile("part2.txt");
		System.out.println(convert(string));
	}
}
