package ua.nure.gunko.practice6.part3;

public class Part3 {
	public static void main(String[] args) {
		Parking parking = new Parking(3);
		parking.print();
		parking.arrive(1);
		parking.arrive(2);
		parking.arrive(1);
		parking.arrive(1);
		parking.print();
	}
}
