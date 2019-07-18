package ua.nure.gunko.practice8.db.entity;

public class User {

	private String login;
	private long id;
	
	
	public User(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public String toString() {
		return getLogin();
	}

	public static User createUser(String login) {
		User user = new User(login);
		return user;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
