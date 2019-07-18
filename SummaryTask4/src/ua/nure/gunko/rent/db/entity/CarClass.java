package ua.nure.gunko.rent.db.entity;

public class CarClass {
	private int id;
	
	private String CarClass;
	
	public CarClass() {
	}

	@Override
	public String toString() {
		return "Class [id=" + id + ", Class=" + CarClass + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarClass() {
		return CarClass;
	}

	public void setCarClass(String carClass) {
		CarClass = carClass;
	}

	
}
