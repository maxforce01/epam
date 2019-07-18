package ua.nure.gunko.practice5;

public class Part6 extends Thread{
	private static final Object M = new Object();
	public void run() {
		try {
		
			interrupted();

			synchronized (M) {
				M.wait();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Part6 t = new Part6();
		t.start();
		t.interrupt();
		t.join(400);
		synchronized (M) {
			M.notifyAll();
			System.out.println(t.getState());
		}
		t = new Part6();
		t.start();
		t.interrupt();
		t.join(1);
		System.out.println(t.getState());
		synchronized (M) {
			M.notifyAll();
		}
		t.join();
		System.out.println(t.getState());
	}
}
