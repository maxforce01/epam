package ua.nure.gunko.practice6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordContainer {

	private static ArrayList<Word> list = new ArrayList<>();

	WordContainer(ArrayList<Word> list) {

	}

	public static void main(String[] args) throws IOException {
		 list = new ArrayList<>();
		String s = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while ((s = in.readLine()) != null) {
			String[] wrds = s.split("\\s");
			for (int i = 0; i < wrds.length; i++) {
				if ("stop".equals(wrds[i])) {
					break;
				} else {
					addToContainer(wrds[i]);
				}
			}
		}
		in.close();
		System.out.println(new WordContainer(WordContainer.list));
	}

	private static void addToContainer(String wrd) {
		Word w = new Word(wrd, 1);
		if (list.isEmpty()) {
			list.add(w);
			return;
		}
		int k = 0;
		boolean flag = false;
		for (int j = 0; j < list.size(); j++) {
			if (wrd.equals(list.get(j).getContent())) {
				flag = true;
				k = list.indexOf(list.get(j));
				break;
			}
		}

		if (flag) {
			list.get(k).setFrequency();
		} else {
			list.add(w);
		}
	}

	@Override
	public String toString() {
		Collections.sort(list, new Comparator<Word>() {

			public int compare(Word o1, Word o2) {
				return o2.compareTo(o1);
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < list.size(); j++) {
			sb.append(list.get(j).getContent() + " : " + list.get(j).getFrequency() + System.lineSeparator());
		}
		return sb.toString();
	}
}