package ua.nure.gunko.practice4;

import java.security.SecureRandom;

public class Part2 {
	protected static final int[] NUMBERS = {8,9,50};
	protected static final int TWO = 2;
	public static String generate() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < NUMBERS[1]; i++) {
			if (i < NUMBERS[0]) {
				sb.append(random.nextInt(NUMBERS[TWO])).append(" ");
			} else {
				sb.append(random.nextInt(NUMBERS[TWO]));
			}
		}
		return sb.toString();
	}

	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static String sort(String numbers) {
		String[] nums = numbers.split("\\s");
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (isNumeric(nums[i])) {
				arr[i] = Integer.parseInt(nums[i]);
			}
		}
		for (int i = arr.length - 1; i >= 1; i--) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				sb.append(arr[i]).append(" ");
			} else {
				sb.append(arr[i]);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Util.writeFile(generate(), "part2.txt");
		String numbers = Util.readFile("part2.txt");
		Util.writeFile(sort(numbers), "part2_sorted.txt");
		StringBuilder sb = new StringBuilder();
		sb.append("input  ==> ").append(generate()).append(System.lineSeparator());
		sb.append("output  ==> ").append(sort(numbers));
		System.out.println(sb);
	}
}
