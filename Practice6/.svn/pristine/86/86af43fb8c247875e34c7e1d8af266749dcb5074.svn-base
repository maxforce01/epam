package ua.nure.gunko.practice6.part4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	private int[] arr;
	private boolean reverse;
	private int start;
	private int end;

	Range(int start, int end, boolean reverse) {
		setStart(start);
		setEnd(end);
		setReverse(reverse);
		arr = new int[getEnd() - getStart() + 1];
		fillArray();
	}

	Range(int start, int end) {
		setStart(start);
		setEnd(end);
		setReverse(false);
		arr = new int[getEnd() - getStart() + 1];
		fillArray();
	}

	public int[] getArr() {
		return Arrays.copyOf(arr, arr.length);
	}

	public boolean isReverse() {
		return reverse;
	}

	public final void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public final int getStart() {
		return start;
	}

	public final void setStart(int start) {
		this.start = start;
	}

	public final int getEnd() {
		return end;
	}

	public final void setEnd(int end) {
		this.end = end;
	}

	public final void fillArray() {
		int local = getStart();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = local;
			local++;
		}
	}

	public void output() {
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	private class RangeIterator implements Iterator<Integer> {

		private int pointer;

		RangeIterator() {
			if (reverse) {
				setPointer(arr.length);
			} else {
				setPointer(-1);
			}
		}

		public int getPointer() {
			return pointer;
		}

		public final void setPointer(int pointer) {
			this.pointer = pointer;
		}

		@Override
		public boolean hasNext() {
			if (reverse && getPointer() - 1 >= 0) {
					return true;
			} else {
				if (getPointer() + 1 < arr.length) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Integer next() {
			if (hasNext()) {
				if (reverse) {
					setPointer(getPointer() - 1);
					return arr[pointer];
				} else {
					setPointer(getPointer() + 1);
					return arr[pointer];
				}
			}else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}