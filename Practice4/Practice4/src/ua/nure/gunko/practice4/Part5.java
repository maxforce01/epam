package ua.nure.gunko.practice4;

import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
	protected static Scanner scanner;
	protected static final String ENCODING = "Cp1251";
	protected static final String STOP = "Stop";
	protected static final String NOVALUES = "No such values";
	protected static final String FILE_NAME = "resources";
	protected static StringBuilder sb;
	protected static final int TWO = 2;
	public static void props() {
		InputStream std = System.in;
		scanner = new Scanner(std,ENCODING);
		sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			try {
				sb = new StringBuilder();
				String str = scanner.nextLine();
				if(str.equalsIgnoreCase(STOP)) {
					break;
				}
				String[] prop = str.split(" ");
				if (prop.length != TWO) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale locale = new Locale(prop[1]);
				ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
				sb.append(rb.getString(prop[0]));
				if(sb.toString().isEmpty()) {
					sb.append(NOVALUES);
				}
				System.out.println(sb);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(NOVALUES);
				continue;
			} catch (MissingResourceException e) {
				System.out.println("Invalid key");
				continue;
			}
		}
		scanner.close();
	}
	
	public static void main(String[] args) {
		props();
	}
}
