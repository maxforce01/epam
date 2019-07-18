package ua.nure.gunko.practice6.part3;

public class Parking {
	protected int[] park;
	protected int n;

	public Parking(int n) {
		this.n = n;
		park = new int[n];
	}

	boolean arrive(int k) {
		if (k >= 0 && k <= n - 1) {
			int i = k;
			boolean result = false;
			while (i != park.length) {
				if (park[i] == 0) {
					park[i] = 1;
					result = true;
					break;
				}
				i++;
			}
			if (!result) {
				i = 0;
				while (i != park.length) {
					if (park[i] == 0) {
						park[i] = 1;
						result = true;
						break;
					}
					i++;
				}
			}
			return result;
		}else {
			throw new IllegalArgumentException();
		}
	}

	boolean depart(int k) {
		if (k >= 0 && k <= n - 1) {
			if (park[k] == 1) {
				park[k] = 0;
				return true;
			}
			
		} else {
			throw new IllegalArgumentException();
		}
		return false;
	}

	void print() {
		StringBuilder sb = new StringBuilder();
		for (int p : park) {
			sb.append(p);
		}
		System.out.println(sb);
	}

}
