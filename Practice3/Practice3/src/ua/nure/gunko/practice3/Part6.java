package ua.nure.gunko.practice3;

import java.util.Arrays;

public class Part6 {

	public static String convert(String input) {
		String[] lines = input.split(System.lineSeparator());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			String[] line = lines[i].split("\\s");
			for (int j = 0; j < line.length; j++) {
				if (j == line.length - 1) {
					sb.append(line[j]).append("|").append(System.lineSeparator());
				} else {
					sb.append(line[j]).append(System.lineSeparator());
				}
			}
		}
		StringBuilder dublicate = new StringBuilder(sort(sb.toString().split(System.lineSeparator())));
		String[] dubArray = dublicate.toString().split("\\s|\\|");
		StringBuilder result = new StringBuilder();
		String[] words = sb.toString().split(System.lineSeparator());
		for (int i = 0; i < words.length; i++) {
			if (!words[i].contains("|")) {
				if (Arrays.asList(dubArray).contains(words[i])) {
					result.append("_").append(words[i]).append(" ");
				} else {
					result.append(words[i]).append(" ");
				}
			} else {
				if (Arrays.asList(dubArray).contains(words[i].substring(0, words[i].length() - 1))) {
					result.append("_").append(words[i].substring(0, words[i].length() - 1))
							.append(System.lineSeparator());
				} else {
					result.append(words[i].substring(0, words[i].length() - 1)).append(System.lineSeparator());
				}
			}
		}
		return result.toString().trim();

	}

	private static String sort(String[] arr) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				StringBuilder str = new StringBuilder(arr[i]);
				if (result.toString().contains(str)) {
					continue;
				}
				for (int j = i + 1; j < arr.length; j++) {
					if (str.toString().contains(arr[j])) {
						result.append(arr[j]).append(" ");
					}
				}
			}
		}
		return dublicate(result.toString().split("\\s"));
	}

	public static String dublicate(String[] arr) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				String str = arr[i];
				if (result.toString().contains(str)) {
					continue;
				} else {
					if (i != arr.length - 1) {
						result.append(str).append(" ");
					} else {
						result.append(str).append("");
					}
				}
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String string = Util.readFile("part6.txt");
		System.out.println(convert(string));
	}
}
