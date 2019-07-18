package ua.nure.gunko.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Part5 {
	private static final int COUNT = 10;
	private static final int NINE = 9;
	private static final int COLOMNS = 20;
	private static RandomAccessFile rf;
	private static final String ENCODING = "cp1251";

	public static void readFile(File file) {
		System.out.println(Util.readFile(file.getName()));
	}

	public synchronized void write(int number) throws IOException{
		rf.seek(number * (COLOMNS + System.lineSeparator().length()));
		for (int i = 0; i < COLOMNS; i++) {
			rf.write(Integer.toString(number).getBytes(ENCODING));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (number != NINE) {
			rf.write(System.lineSeparator().getBytes(ENCODING));
		}
	}

	public static void main(String[] args) throws IOException{
		File f = new File("part5.txt");
		Files.deleteIfExists(f.toPath());
		synchronized (Part5.class) {
			rf = new RandomAccessFile(f, "rw");
		}
		Part5 p = new Part5();
		for (int i = 0; i < COUNT; i++) {
			int num = i;
			Thread thread = new Thread() {
				public void run() {
					try {
						p.write(num);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		rf.close();
		System.out.println(Util.readFile(f.getName()));
	}
}
