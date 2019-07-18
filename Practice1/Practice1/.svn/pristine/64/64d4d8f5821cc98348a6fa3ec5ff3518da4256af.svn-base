package ua.nure.gunko.practice1;

public class Part7 {
	private static final int[] NUMBERS = { 1, 2, 26, 27, 52, 53, 702, 703, 64 };
	private static final int EIGHT = 8;
	private static final int TWO = 2;

	public static int str2int(String number) {
		int digit = 0;
		for (int i = 1, j = number.length(); j > 0; i++, j--) {

			digit += (number.charAt(number.length() - i) - NUMBERS[EIGHT])
					* Math.pow(NUMBERS[TWO], number.length() - (double) j);

		}
		return digit;
	}

	public static String int2str(int number) {
		StringBuilder chars = new StringBuilder();
		StringBuilder charsMirror = new StringBuilder();
		int modul;
		int divident = number;
		while (divident != 0) {
			modul = divident % NUMBERS[TWO];
			if (modul == 0) {
				chars.append("Z");
				divident = (divident - 1) / NUMBERS[TWO];
			} else {
				chars.append((char) (modul + NUMBERS[EIGHT]));
				divident = (divident - modul) / NUMBERS[TWO];
			}
		}
		for (int i = 0; i < chars.length(); i++) {
			charsMirror.append(chars.charAt(chars.length() - i - 1));
		}
		return charsMirror.toString();

	}

	public static String rightColumn(String number) {
		StringBuilder chars = new StringBuilder();
		int num;
		num = str2int(number);
		num++;
		chars.append(int2str(num));
		return chars.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < EIGHT; i++) {
			System.out.println(
					int2str(NUMBERS[i]) + " ==> " + str2int(int2str(NUMBERS[i])) + " ==> " + int2str(NUMBERS[i]));
		}
	}

}
