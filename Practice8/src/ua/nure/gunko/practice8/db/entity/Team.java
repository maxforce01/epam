package ua.nure.gunko.practice8.db.entity;

public class Team {
	private String name;
	private long id;

	public Team(String login) {
		this.name = login;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return getName();
	}

	public static Team createTeam(String login) {
		Team team = new Team(login);
		return team;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
