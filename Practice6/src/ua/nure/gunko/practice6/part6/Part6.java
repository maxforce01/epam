package ua.nure.gunko.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part6 {

	private String fileName;
	private String[] input;
	private static final String[] NAMES = { "--input", "-i", "--task", "-t" };

	Part6() {
	}

	public static void main(String[] args) {
		new Part6().console(args[0], args[1], args[2], args[3]);
	}
	public static CountWithPlace create(int l,int place ) {
		return new CountWithPlace(l,place);
	}
	private boolean console(String input, String fileName, String task, String operation) {
		if (!(input.equals(NAMES[0]) || input.equals(NAMES[1]))) {
			System.err.println("Wrong operation");
			return false;
		}
		if (!(task.equals(NAMES[2]) || task.equals(NAMES[3]))) {
			System.err.println("Wrong task");
			return false;
		}

		this.fileName = fileName;
		initialize();

		switch (operation) {
		case "frequency":
			getResultFrequecy();
			break;
		case "length":
			getResultLength();
			break;
		case "duplicates":
			getResultDublicates();
			break;
		default:
			return false;
		}
		return true;
	}

	private String getInput() {
		StringBuilder sb = new StringBuilder();
		try (Scanner file = new Scanner(new File(fileName), "CP1251")) {
			while (file.hasNext()) {
				sb.append(file.next()).append(" ");
			}
		} catch (FileNotFoundException e) {
			System.err.println(String.format("File: %s not found", fileName));
		}
		return sb.toString();
	}

	public void initialize() {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(getInput());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		input = sb.toString().split(" ");
	}

	private void getResultLength() {
		/* count length and place */
		final HashMap<String, CountWithPlace> wordCounts = new HashMap<>();
		for (int place = 0; place < input.length; place++) {
			String w = input[place];
			int c = place;
			wordCounts.computeIfAbsent(w, k -> create(w.length(),c));
		}
		TreeMap<String, CountWithPlace> sortedWords = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				CountWithPlace countWithPlaceA = wordCounts.get(a);
				CountWithPlace countWithPlaceB = wordCounts.get(b);
				int length = countWithPlaceB.lenth - countWithPlaceA.lenth;
				if (length == 0) {
					return countWithPlaceA.place - countWithPlaceB.place;
				} else {
					return length;
				}
			}
		});
		sortedWords.putAll(wordCounts);

		int i = 0;
		for (Entry<String, CountWithPlace> s : sortedWords.entrySet()) {
			if (i == 3) {
				break;
			}
			i++;
			System.out.println(s.getKey() + " ==> " + sortedWords.get(s.getKey()).getLenth());
		}
	}

	private void getResultFrequecy() {
		/* count words and place */
		final HashMap<String, WithPlace> wordCounts = new HashMap<>();
		for (int place = 0; place < input.length; place++) {
			String w = input[place];
			WithPlace countWithPlace = wordCounts.get(w);
			if (countWithPlace == null) {
				wordCounts.put(w, new WithPlace(place));
			} else {
				countWithPlace.setCount(countWithPlace.getCount() + 1);
			}
		}
		/* sort words by lenth and place */
		TreeMap<String, WithPlace> sortedWords = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				WithPlace countWithPlaceA = wordCounts.get(a);
				WithPlace countWithPlaceB = wordCounts.get(b);
				int count = countWithPlaceB.count - countWithPlaceA.count;
				if (count == 0) {
					return countWithPlaceA.place - countWithPlaceB.place;
				} else {
					return count;
				}
			}
		});
		sortedWords.putAll(wordCounts);

		TreeSet<String> firstStrings = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		int i = 0;
		for (Entry<String, WithPlace> s : sortedWords.entrySet()) {
			if (i == 3) {
				break;
			}
			i++;
			firstStrings.add(s.getKey());
		}
		for (String s : firstStrings) {
			System.out.println(s + " ==> " + sortedWords.get(s).getCount());
		}
	}

	private void getResultDublicates() {
		/* count words and place */
		final Map<String, Integer> wordCounts = new LinkedHashMap<>();
		for (int place = 0; place < input.length; place++) {
			String w = input[place];
			Integer countWithPlace = wordCounts.get(w);
			if (countWithPlace == null) {
				wordCounts.put(w, 1);
			} else {
				int c = countWithPlace += 1;
				wordCounts.put(w, c);
			}
		}
		int i = 0;
		for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
			if (i == 3) {
				break;
			}
			if (wordCount.getValue() > 1) {
				i++;
				System.out.println(
						new StringBuilder(wordCount.getKey()).reverse().toString().toUpperCase(Locale.getDefault()));
			}
		}
		
		
		
	}

	static class CountWithPlace {
		private final int lenth;
		private final int place;

		public CountWithPlace(int lenth, int place) {
			this.lenth = lenth;
			this.place = place;
		}

		public int getLenth() {
			return lenth;
		}

	}

	static class WithPlace {
		private int count = 1;
		private final int place;

		public WithPlace(int place) {
			this.place = place;
		}

		public int getCount() {
			return count;
		}

		public WithPlace setCount(int count) {
			this.count = count;
			return this;
		}

	}
}