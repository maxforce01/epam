package ua.nure.gunko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
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
	public void enqueue(Object element) {
		Object[] collectionNew = new Object[this.size() + 1];
		for (int i = 0; i < this.size(); i++) {
			collectionNew[i] = collection[i];
		}
		collectionNew[collectionNew.length - 1] = element;
		collection = collectionNew;
	}

	@Override
	public Object dequeue() {
		Object el = collection[0];
		Object[] collectionNew = new Object[this.size() - 1];
		for (int i = 1; i < this.size(); i++) {
			collectionNew[i - 1] = collection[i];
		}
		collection = collectionNew;
		return el;
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

	@Override
	public Object top() {
		return collection[0];
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
			QueueImpl.this.remove(currentIndex);
			currentIndex -= 1;
			deleted = false;
		}
	}

	public static void main(String[] args) {
		Queue queue = new QueueImpl();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);

		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();

		System.out.println(queue);
	}
}
