package ua.nure.gunko.rent.db.entity;

import java.sql.Date;

public class Order {
	private long id;
	private long user_id;
	private long car_id;
	private String session;
	private String number;
	private Date dateStart;
	private Date dateEnd;
	private int payment;
	private String status;
	private String cause;
	
	public Order() {
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user_id=" + user_id + ", car_id=" + car_id + ", session=" + session + ", number="
				+ number + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + ", payment=" + payment + ", status="
				+ status + ", cause=" + cause + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getCar_id() {
		return car_id;
	}

	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public static enum Status {
		ACCEPTED, DENIED, UNCHECKED , RETURNED;
		public String getName() {
			return name().toLowerCase();
		}
		
	}
}
