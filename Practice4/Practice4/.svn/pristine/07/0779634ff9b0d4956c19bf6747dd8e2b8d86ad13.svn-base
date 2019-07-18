package ua.nure.gunko.practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	

	private String fileName;
	
	Part1(String fileName) {
		setFileName(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private static String change(String arr) {
		char[] chars = arr.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : chars) {
			if (Character.isLowerCase(ch)) {
				sb.append(Character.toUpperCase(ch));
			} else {
				sb.append(Character.toLowerCase(ch));
			}
		}
		return sb.toString();
	}

	public void convertToUpperCase() {

		StringBuilder file = new StringBuilder(Util.readFile(getFileName()));
		Pattern p = Pattern.compile("[À-ßà-ÿA-Za-z¨¸¯¿²³ªº¥´'\\w]{4,}");
		Matcher m = p.matcher(file);
		while(m.find()) {
			file.replace(m.start(), m.end(),change(m.group()));
		}
		System.out.println(file);

	}

	public static void main(String[] args) {
		new Part1("part1.txt").convertToUpperCase();
	}
}
