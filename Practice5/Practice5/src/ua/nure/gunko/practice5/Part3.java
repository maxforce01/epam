package ua.nure.gunko.practice5;

public class Part3 {
	private int counter;
	private int counter2;
	private int k;
	private int t;
	private int n;
	private static final String OUTPUT = "%s %s%n";

	public Part3(int n, int k, int t) {
		this.k = k;
		this.n = n;
		this.t = t;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getCounter2() {
		return counter2;
	}

	public void setCounter2(int counter2) {
		this.counter2 = counter2;
	}

	public void reset() {
		this.setCounter(0);
		this.setCounter2(0);
	}

	public void ran() throws InterruptedException {
		for (int i = 0; i < k; i++) {
			if (counter != counter2) {
				System.out.printf(OUTPUT, getCounter(), getCounter2());
			} else {
				System.out.printf(OUTPUT, getCounter() + 1, getCounter2());
			}
			setCounter(getCounter() + 1);
			Thread.sleep(t);
			setCounter2(getCounter2() + 1);
		}
	}

	public void ran2() throws InterruptedException {
		for (int i = 0; i < k; i++) {
			System.out.printf(OUTPUT, getCounter(), getCounter2());
			setCounter(getCounter() + 1);
			Thread.sleep(t);
			setCounter2(getCounter2() + 1);
		}
	}

	public void test(){
		for (int i = 0; i < n; i++) {
			Thread th = new Thread() {
				public void run() {
					try {
						ran();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			th.start();
		}
	}

	public void testSync(){
		for (int i = 0; i < n; i++) {
			Thread th = new Thread() {
				public void run() {
					try {
						synchronized (Part3.class) {
							ran2();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			th.start();
		}
	}

	public static void main(String[] args) {
		Part3 p = new Part3(3, 3, 50);
		// p.testSync();
		// p.reset();
		p.test();

	}

}