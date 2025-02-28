package ua.nure.gunko.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
	protected int n;
	protected int k;
	protected List<Integer> arrayList = new ArrayList<>();
	protected List<Integer> arrayItr = new ArrayList<>();
	protected List<Integer> linkedList = new LinkedList<>();
	protected List<Integer> linkedItr = new LinkedList<>();

	public Part2(int n, int k) {
		this.n = n;
		this.k = k;
		for (int i = 0; i < n; i++) {
			arrayList.add(i);
			arrayItr.add(i);
			linkedList.add(i);
			linkedItr.add(i);
		}
		System.out.println("ArrayList#Index: " + removeByIndex(arrayList, k) + " ms");
		System.out.println(arrayList.get(0));
		System.out.println("LinkedList#Index: " + removeByIndex(linkedList, k) + " ms");
		System.out.println(linkedList.get(0));
		System.out.println("ArrayList#Index: " + removeByIterator(arrayItr, k) + " ms");
		System.out.println(arrayItr.get(0));
		System.out.println("linkedList#Index: " + removeByIterator(linkedItr, k) + " ms");
		System.out.println(linkedItr.get(0));
	}

	public static long removeByIndex(List<Integer> list, int k) {
		long time = System.currentTimeMillis();
		int local = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.size() == 1) {
				break;
			}

			local += (k - 1);

			while (local >= list.size()) {
				local = local - list.size();
			}
			list.remove(local);
		}
		return System.currentTimeMillis() - time;
	}

	public static long removeByIterator(List<Integer> list, int k) {
		long time = System.currentTimeMillis();
		int count = 0;
		Iterator<Integer> it = list.iterator();
		while (list.size() > 1) {
			if (it.hasNext()) {
				it.next();
				count++;
				if (count == k) {
					it.remove();
					count = 0;
				}
			} else {
				it = list.iterator();
			}
		}
		return System.currentTimeMillis() - time;
	}

	public static void main(String[] args) {
		new Part2(10_000, 33);
	}
}
