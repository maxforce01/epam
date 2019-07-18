package ua.nure.gunko.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part4 {
	private static final int N = 5;
	private static final int M = 20;
	private static int[][] arr = new int[N][M];
	protected static Scanner sc;
	private static long counter;
	private static int max2;
	private static long time2;

	public static int maximumValue(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread().getName());
			}
			if (arr[i] > max) {
				max = arr[i];
			}

		}
		return max;
	}

	public static void singleThread() {
		long time = System.currentTimeMillis();
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.err.println(Thread.currentThread().getName());
				}
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		max2 = max;
		time2 = (System.currentTimeMillis() - time);
	}

	public static int[] multiplyThreads() {
		final int[] temp = new int[N];
		for (int i = 0; i < N; i++) {

			final int count = i;
			Thread myThread = new Thread() {
				public void run() {
					long time = System.currentTimeMillis();

					temp[count] = maximumValue(arr[count]);
					time = System.currentTimeMillis() - time;
					if (counter < time) {
						counter = time;
					}
				}
			};
			myThread.start();
		}
		return temp;
	}

	public static void read() throws FileNotFoundException {
		sc = new Scanner(new File("part4.txt"));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		read();
		singleThread();
		int[] temp = multiplyThreads();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println(Thread.currentThread().getName());
		}
		Arrays.sort(temp);
		System.out.println(temp[N - 1]);
		System.out.println(counter);
		System.out.println(max2);
		System.out.println(time2);
	}
}
