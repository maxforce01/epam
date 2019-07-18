
package ua.nure.gunko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
	private Node first;
	private int size;
	private Node last;

	void linkFirst(Object e) {
		final Node f = first;
		final Node newNode = new Node(null, e, f);
		first = newNode;
		if (f == null) {
			last = newNode;
		} else {
			f.prev = newNode;
		}
		size++;
	}

	void linkLast(Object e) {
		final Node l = last;
		final Node newNode = new Node(l, e, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
		size++;
	}

	@Override
	public void clear() {
		for (Node x = first; x != null; x = x.next) {
			x.item = null;
			x.next = null;
			x.prev = null;
		}
		first = last = null;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		Object[] arr = new Object[size];
		int i = 0;
		for (Node x = first; x != null; x = x.next) {
			arr[i++] = x.item;
		}
		return arr;
	}

	public String toString() {
		Object[] arr = this.toArray();
		if (arr.length == 0) {
			return "[]";
		}
		StringBuilder result = new StringBuilder();
		result.append("[");
		for (int j = 0; j < arr.length; j++) {
			result.append(String.valueOf(arr[j])).append(", ");
		}
		result.replace(result.length() - 2, result.length(), "]");
		return result.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	@Override
	public void addFirst(Object element) {
		this.linkFirst(element);
	}

	@Override
	public void addLast(Object element) {
		this.linkLast(element);
	}

	@Override
	public void removeFirst() {
		if (first != null) {
			first.item = null;
			Node el = first.next;
			first.next = null;
			first = el;
			if (el != null) {
				el.prev = null;
			}
			size--;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void removeLast(){
		if (last != null) {
			last.item = null;
			Node el = last.prev;
			last.prev = null;
			if (el != null) {
				last = el;
				el.next = null;
			}else {
				throw new IllegalArgumentException();
			}
			size--;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public Object getFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.item;
	}

	@Override
	public Object getLast() {
		if (last == null) {
			throw new NoSuchElementException();
		}
		return last.item;
	}

	@Override
	public Object search(Object element) {
		for (Node i = first; i != null; i = i.next) {
			if (i.item.equals(element)) {
				return i.item;
			}
		}
		return null;
	}

	@Override
	public boolean remove(Object element) {
		Object obj = this.search(element);
		if (obj == null) {
			throw new NoSuchElementException();
		}
		for (Node i = first; i != null; i = i.next) {
			if (i.item.equals(element)) {
				if (i == first) {
					this.removeFirst();
				} else {
					if (i == last) {
						this.removeLast();
					} else {
						i.prev.next = i.next;
						i.next.prev = i.prev;
						i.next = null;
						i.prev = null;
						this.size--;
					}
				}
				return true;
			}
		}
		return false;
	}

	static class Node {
		private Object item;
		private Node next;
		private Node prev;

		Node(Node prev, Object element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	private class IteratorImpl implements Iterator<Object> {
		private int currentIndex = -1;
		private boolean deleted;

		@Override
		public boolean hasNext() {
			return currentIndex < toArray().length - 1;
		}

		@Override
		public Object next() {
			if (hasNext()) {
				deleted = true;
				return toArray()[++currentIndex];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			if (!deleted) {
				throw new IllegalStateException();
			}
			ListImpl.this.remove(toArray()[currentIndex]);
			currentIndex -= 1;
			deleted = false;

		}
	}
	public static void main(String[] args) {
		List list = new ListImpl();
		System.out.println(list);
		list.addLast("A");
		System.out.println(list);
		list.addLast("B");
		System.out.println(list);
		list.addLast("C");
		System.out.println(list);
		System.out.println(list.size());
		list.clear();
		System.out.println(list);
		System.out.println(list.size());
		list.addLast("A");
		list.addLast("B");
		list.addLast("C");
		System.out.println(list);
		System.out.println(list.size());
	}

}
