package ua.nure.gunko.practice5;

import java.security.SecureRandom;

public class Matrix {
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final int N = 5;
	private static final int M = 20;
	private static int[][] arr = new int[N][M];
	public static String fillTheArrayWithNumbers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sb.append(RANDOM.nextInt(999)).append(" ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Util.writeFile(fillTheArrayWithNumbers(), "part4.txt");
	}
}
