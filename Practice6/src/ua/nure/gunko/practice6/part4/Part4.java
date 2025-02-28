package ua.nure.gunko.practice6.part4;


public class Part4 {
	private static boolean local;
	private static final int START = -1;
	private static final int END = 5;

	public static boolean isLocal() {
		return local;
	}

	public static void setLocal(boolean local) {
		Part4.local = local;
	}

	public static void main(String[] args) {
		Range range = new Range(START, END, isLocal());
		range.output();
	}
}
