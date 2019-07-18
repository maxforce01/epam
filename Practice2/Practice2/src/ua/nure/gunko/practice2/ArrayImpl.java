package ua.nure.gunko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

	private Object[] collection = {};

	@Override
	public void clear() {
		collection = new Object[] {};

	}

	@Override
	public int size() {
		return collection.length;
	}

	public String toString() {

		if (this.size() == 0) {
			return "[]";
		}
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < size(); i++) {
			result.append(String.valueOf(collection[i])).append(", ");
		}
		result.replace(result.length() - 2, result.length(), "]");
		return result.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	@Override
	public void add(Object element) {
		Object[] collectionNew = new Object[this.size() + 1];
		for (int i = 0; i < this.size(); i++) {
			collectionNew[i] = collection[i];
		}
		collectionNew[collectionNew.length - 1] = element;
		collection = collectionNew;

	}

	@Override
	public void set(int index, Object element) {
		for (int i = 0; i < this.size(); i++) {
			if (i == index) {
				collection[i] = element;
			}
		}
	}

	@Override
	public Object get(int index) {
		for (int i = 0; i < this.size(); i++) {
			if (i == index) {
				return collection[i];
			}
		}
		return null;
	}

	@Override
	public int indexOf(Object element) {
		for (int i = 0; i < this.size(); i++) {
			if (collection[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void remove(int index) {
		for (int i = 0; i < this.size(); i++) {
			if (i == index) {
				Object[] arr = new Object[this.size() - 1];
				System.arraycopy(collection, 0, arr, 0, i);
				System.arraycopy(collection, i + 1, arr, i, arr.length - i);
				collection = arr;
			}
		}
	}

	private class IteratorImpl implements Iterator<Object> {
		private int currentIndex = -1;
		private boolean deleted;

		@Override
		public boolean hasNext() {
			return currentIndex < collection.length - 1;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				deleted = true;
				return collection[++currentIndex];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			if (!deleted) {
				throw new IllegalStateException();
			}
			ArrayImpl.this.remove(currentIndex);
			currentIndex -= 1;
			deleted = false;
		}
	}

	public static void main(String[] args) {
		System.out.println("==== Part1");
		Array list = new ArrayImpl();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		System.out.println(list.toString());
		list.remove(1);
		System.out.println(list.toString());
		list.set(3, "chanched");
		System.out.println(list.indexOf("chanched"));
		System.out.println(list.get(3));
		System.out.println(list.toString());
		Array c = new ArrayImpl();
		c.add("D");
		c.add("K");
		System.out.println("==== Part2");
		list = new ArrayImpl();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Iterator<Object> it = list.iterator();
		System.out.println(list.toString());
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		it.next();
		it.remove();
		System.out.println(list);
		try {
			it.remove();
		} catch (IllegalStateException ex) {
			System.out.println(ex.getClass());
		}

	}
}
