package ua.nure.gunko.practice7.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.gunko.practice7.entity.Plane;
import ua.nure.gunko.practice7.entity.Planes;

public final class Sorter {
		private Sorter() {
			throw new IllegalStateException("Utility class");
		}
	
	public static Comparator<Plane> byModel = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return o1.getModel().compareTo(o2.getModel());
		}
	};

	public static Comparator<Plane> byOrigin = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return o1.getOrigin().compareTo(o2.getOrigin());
		}
	};
	public static Comparator<Plane> byType = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return o1.getChars().getType().compareTo(o2.getChars().getType());
		}
	};

	public static  void sortByModel(Planes planes) {
		Collections.sort(planes.plane, byModel);
	}

	public static  void sortByOrigin(Planes planes) {
		Collections.sort(planes.plane, byOrigin);
	}

	public static  void sortByType(Planes planes) {
		Collections.sort(planes.plane, byType);
	}
}
