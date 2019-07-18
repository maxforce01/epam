package ua.nure.gunko.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	protected static final String[] TYPES = { "int", "double", "char", "String", "Stop" };
	protected static final String BADFORMAT = "Incorrect input";
	protected static final String NOVALUES = "No such values";
	protected static final String[] TYPES_REGX = { "(^|\\s)(\\d+)(\\s|$)", "\\.+\\d+|\\d+\\.\\d+|\\d+\\.+",
			"(?i)(^|(?<=\\s))[a-zà-ÿÀ-ßA-Z]($|(?=\\s))", "[À-ßà-ÿ¨¸¯¿²³ªº¥´'a-zA-Z]{2,}" };
	protected Scanner scanner;
	protected static final int TWO = 2;
	protected static final int THREE = 3;
	protected static final int FOUR = 4;
	public Part3(Scanner scanner) {
		this.scanner = scanner;
	}

	public void show(String fileString) {
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			if (str.equalsIgnoreCase(TYPES[FOUR])) {
				break;
			}
			switch (getIndex(str)) {
			case 0:
				System.out.println(value(fileString, TYPES_REGX[0]));
				break;
			case 1:
				System.out.println(value(fileString, TYPES_REGX[1]));
				break;
			case TWO:
				System.out.println(value(fileString, TYPES_REGX[TWO]));
				break;
			case THREE:
				System.out.println(value(fileString, TYPES_REGX[THREE]));
				break;
			default:
				System.out.println(BADFORMAT);
				break;
			}
		}
	}

	public static String value(String file, String regx) {
		StringBuilder sb = new StringBuilder();
		Pattern p2 = Pattern.compile(regx);
		Matcher m2 = p2.matcher(file);
		while (m2.find()) {
			sb.append(m2.group() + " ");
		}
		if (sb.toString().isEmpty()) {
			sb.append(NOVALUES);
		}
		return sb.toString().trim().replaceAll("[\\s]{2,}", " ");
	}

	private static int getIndex(String input) {
		int index = -1;
		for (int i = 0; i < TYPES.length; i++) {
			if (TYPES[i].equals(input)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		String file = Util.readFile("part3.txt");
		Scanner scanner = new Scanner(System.in);
		new Part3(scanner).show(file);
	}
}
