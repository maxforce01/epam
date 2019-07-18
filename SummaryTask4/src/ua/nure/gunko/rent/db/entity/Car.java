package ua.nure.gunko.rent.db.entity;

public class Car {
	private long id;
	private String Brand;
	private String Model;
	private String VIN;
	private String Number;
	private int Price;
	private boolean Status;
	private CarClass CarClass;

	public Car() {
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", Brand=" + Brand + ", Model=" + Model + ", VIN=" + VIN + ", Number=" + Number
				+ ", Price=" + Price + ", Status=" + Status + ", CarClass=" + CarClass + "]";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public CarClass getCarClass() {
		return CarClass;
	}

	public void setCarClass(CarClass carClass) {
		CarClass = carClass;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	
}
