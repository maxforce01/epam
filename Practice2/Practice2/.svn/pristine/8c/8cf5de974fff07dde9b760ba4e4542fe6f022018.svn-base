package ua.nure.gunko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

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
	public void push(Object element) {
		Object[] collectionNew = new Object[this.size() + 1];
		for (int i = 0; i < this.size(); i++) {
			collectionNew[i] = collection[i];
		}
		collectionNew[collectionNew.length - 1] = element;
		collection = collectionNew;
	}

	@Override
	public Object pop() {
		Object el = collection[collection.length - 1];
		Object[] collectionNew = new Object[this.size() - 1];
		for (int i = 0; i < this.size() - 1; i++) {
			collectionNew[i] = collection[i];
		}
		collection = collectionNew;
		return el;
	}

	@Override
	public Object top() {
		return collection[collection.length - 1];
	}

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
		private int currentIndex = collection.length;
		private boolean deleted;

		@Override
		public boolean hasNext() {
			return currentIndex > 0;

		}

		@Override
		public Object next() {
			if (hasNext()) {
				deleted = true;
				return collection[--currentIndex];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			if (!deleted) {
				throw new IllegalStateException();
			}
			StackImpl.this.remove(currentIndex);
			deleted = false;

		}
	}

	public static void main(String[] args) {
		Stack stack = new StackImpl();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack);
		for (Object obj : stack) {
			System.out.println(obj);
		}
		Iterator<Object> stackit = stack.iterator();
		stackit.next();
		stackit.remove();
		stackit.next();
		stackit.remove();
		stackit.next();
		stackit.remove();
		stackit.next();
		stackit.remove();
		System.out.println(stack);
	}

}
