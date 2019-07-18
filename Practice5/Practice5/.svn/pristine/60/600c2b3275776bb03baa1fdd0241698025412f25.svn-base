
package ua.nure.gunko.practice5;

public class Part1 {

	public static void move() {
		long endTime = System.currentTimeMillis() + 1000;
		while (System.currentTimeMillis() < endTime) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(333);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	static class Extnd extends Thread {
		public void run() {
			long endTime = System.currentTimeMillis() + 1000;
			while (System.currentTimeMillis() < endTime) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(333);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	static class Impl implements Runnable {

		@Override
		public void run() {
			long endTime = System.currentTimeMillis() + 1000;
			while (System.currentTimeMillis() < endTime) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(333);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Extnd ex = new Extnd();
		ex.start();
		ex.join();
		Thread th = new Thread(new Impl());
		th.start();
		th.join();
		Thread th2 = new Thread(Part1::move);
		th2.start();
		th2.join();
	}
}
