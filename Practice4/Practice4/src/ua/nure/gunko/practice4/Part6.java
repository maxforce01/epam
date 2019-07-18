package ua.nure.gunko.practice4;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

	protected static Scanner scanner;
	protected static final String[] TYPES = { "Latn", "Cyrl", "Stop" };
	protected static final String BAD = "Incorrect input";
	protected static final String NOVALUES = "No such values";
	protected static final int TWO = 2;
	public static void show(String input) {
		InputStream std = System.in;
		scanner = new Scanner(std);
		
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			Pattern latn = Pattern.compile("[a-zA-z]+");
			Pattern cyrl = Pattern.compile("[À-ßà-ÿ¨¸¯¿²³ªº¥´']+");
			Matcher matcherLatn = latn.matcher(input);
			Matcher matcherCyrl = cyrl.matcher(input);
			if (str.equalsIgnoreCase(TYPES[TWO])) {
				break;
			}
			if (getIndex(str) == -1) {
				System.out.println(str + ": " + BAD);
			} else  {
				switch (getIndex(str)) {
				case 0:
					System.out.println(str + ": "+match(matcherLatn,input));
					break;
				case 1:
					System.out.println(str + ": "+match(matcherCyrl,input));
					break;
				default:
					System.out.println(NOVALUES);
					break;
				}
			}
		}
	}
	private static String match(Matcher matcher,String input) {
		StringBuilder sb = new StringBuilder();
		while(matcher.find()) {
			sb.append(input.substring(matcher.start(), matcher.end())).append(" ");
		}
		return sb.toString();
	}
	private static int getIndex(String input) {
		int index = -1;
		for (int i = 0; i < TYPES.length; i++) {
			if (TYPES[i].equalsIgnoreCase(input)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		String file = Util.readFile("part6.txt");
		show(file);
	}
}
