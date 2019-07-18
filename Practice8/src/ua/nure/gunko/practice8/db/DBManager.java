package ua.nure.gunko.practice8.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.gunko.practice8.db.entity.Team;
import ua.nure.gunko.practice8.db.entity.User;

public class DBManager {
	private PreparedStatement prst;
	private Statement st;
	private Connection conn;
	ResultSet rs;

	public DBManager() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/rental?" + "user=root&password=243342");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void insertUser(User user) throws SQLException {
		String sql = "insert into users (login) values ('" + user.getLogin() + "')";
		prst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		prst.executeUpdate();
		rs = prst.getGeneratedKeys();
		rs.next();
		user.setId(rs.getInt(1));
	}

	public List<User> findAllUsers() throws SQLException {
		List<User> list = new ArrayList<>();
		st = conn.createStatement();
		rs = st.executeQuery("select * from users");
		while (rs.next()) {
			User user = new User(rs.getString("login"));
			user.setId(rs.getLong("id"));
			list.add(user);
		}
		return list;
	}

	public void insertTeam(Team team) throws SQLException {
		String sql = "insert into teams (name) values ('" + team.getName() + "')";
		prst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		prst.executeUpdate();
		rs = prst.getGeneratedKeys();
		rs.next();
		team.setId(rs.getInt(1));
	}

	public List<Team> findAllTeams() throws SQLException {
		List<Team> list = new ArrayList<>();
		st = conn.createStatement();
		rs = st.executeQuery("select * from teams");
		while (rs.next()) {
			Team team = new Team(rs.getString("name"));
			team.setId(rs.getLong("id"));
			list.add(team);
		}
		return list;
	}

	public User getUser(String login) throws SQLException {
		st = conn.createStatement();
		rs = st.executeQuery("select * from users where login = '" + login + "'");
		rs.next();
		User user = new User(rs.getString("login"));
		user.setId(rs.getLong("id"));
		return user;
	}

	public Team getTeam(String name) throws SQLException {
		st = conn.createStatement();
		rs = st.executeQuery("select * from teams where name = '" + name + "'");
		rs.next();
		Team team = new Team(rs.getString("name"));
		team.setId(rs.getLong("id"));
		return team;
	}

	public void setTeamsForUser(User user, Team... teams) throws SQLException {
		String sql;
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < teams.length; i++) {
			sql = "insert into users_teams (user_id,team_id) values (" + user.getId() + "," + teams[i].getId() + ")";
			try {
				prst = conn.prepareStatement(sql);
				prst.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error -> rollback");
				conn.rollback();
			}
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Team> getUserTeams(User user) throws SQLException {
		List<Team> list = new ArrayList<>();
		Team team;
		String sql = "Select team_id from users_teams where user_id = " + user.getId() + "";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		ResultSet rss = null;
		while (rs.next()) {
			sql = "select name from teams where id = " + rs.getLong("team_id") + ";";
			st = conn.createStatement();
			rss = st.executeQuery(sql);
			rss.next();
			team = new Team(rss.getString("name"));
			list.add(team);
		}
		return list;
	}
}
