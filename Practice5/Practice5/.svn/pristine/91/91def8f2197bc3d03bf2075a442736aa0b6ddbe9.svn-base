package ua.nure.gunko.practice5;

import java.io.IOException;

public class Spam {
	private Thread[] threads;
	private String[] messages = new String[] { "@@@@", "bbbbbb" };
	private int[] times = new int[] { 333, 222 };

	public Spam() {
		threads = new Thread[messages.length];
	}

	public void start(){
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Worker(messages[i], times[i]);
			threads[i].start();
		}

	}

	public void stop(){
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
		}
	}

	private static class Worker extends Thread {
		private String messages;
		private int intervals;

		public Worker(String string, int i) {
			this.messages = string;
			this.intervals = i;
		}

		public void run() {
			while (true) {
				System.out.println(messages);
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(intervals);
					this.join(intervals);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Spam s1 = new Spam();
		s1.start();
		Thread th = new Thread() {
			public void run() {
				byte[] buffer = new byte[10];
				int count;
				try {
					do {
						while ((count = System.in.read(buffer)) == -1) {
							if (count == -1) {
								continue;
							}
						}
					} while (!System.lineSeparator().equals(new String(buffer, 0, count, "cp1251")));
				} catch (IOException ex) {
					System.err.println("Spam IOexception in method main");
				}
				s1.stop();
			}
		};
		th.start();
		th.join();
	}
}
