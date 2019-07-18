
package ua.nure.gunko.practice5;

import java.io.ByteArrayInputStream;

import java.io.InputStream;

public class Part2 {

	private static String encoding = "cp1251";

	public static String getEncoding() {
		return encoding;
	}

	public static void setEncoding(String encoding) {
		Part2.encoding = encoding;
	}

	public static void main(String[] args) throws InterruptedException {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(System.lineSeparator().getBytes());
		long l = byteIn.skip(System.lineSeparator().length());
		l = l+l;
		InputStream stdIn = System.in;
		System.setIn(byteIn);
		Thread th = new Thread() {
			public void run() {
				try {
					Spam.main(args);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		th.start();
		Thread.sleep(2000);
		byteIn.reset();
		th.join();
		System.setIn(stdIn);
	}
}
