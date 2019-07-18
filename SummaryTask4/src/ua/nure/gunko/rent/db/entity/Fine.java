package ua.nure.gunko.rent.db.entity;

public class Fine {
	private long id;
	private long user_id;
	private int fine;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long l) {
		this.user_id = l;
	}

	@Override
	public String toString() {
		return "Fine [id=" + id + ", user_id=" + user_id + ", fine=" + fine + "]";
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}
}
