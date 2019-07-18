package ua.nure.gunko.rent.db.entity;

public class User {
	private String login;
	private String email;
	private String password;
	private long id;
	private int roleId;
	private String locale;
	private boolean status;
	
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", email=" + email + ", password=" + password + ", id=" + id + ", roleId="
				+ roleId + ", locale=" + locale + ", status=" + status + "]";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int role) {
		this.roleId = role;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
