
package ua.nure.gunko.practice8;

import java.sql.SQLException;
import java.util.List;

import ua.nure.gunko.practice8.db.DBManager;
import ua.nure.gunko.practice8.db.entity.Team;
import ua.nure.gunko.practice8.db.entity.User;

public class Demo {

	private static void printList(List<?> list) {
		System.out.println(list);
	}

	public static void main(String[] args) throws SQLException {

		DBManager dbManager = new DBManager();

		/*
		 * dbManager.insertUser(User.createUser("petrov"));
		 * dbManager.insertUser(User.createUser("obama"));
		 * Demo.printList(dbManager.findAllUsers());
		 * 
		 * // Part 2 dbManager.insertTeam(Team.createTeam("teamB"));
		 * dbManager.insertTeam(Team.createTeam("teamC"));
		 * printList(dbManager.findAllTeams());
		 */
		// Part 3
		User userPetrov = dbManager.getUser("petrov");
		User userIvanov = dbManager.getUser("ivanov");
		User userObama = dbManager.getUser("obama");
		System.out.println(userIvanov + " " + userObama + " " + userPetrov);
		Team teamA = dbManager.getTeam("teamA");
		Team teamB = dbManager.getTeam("teamB");
		Team teamC = dbManager.getTeam("teamC");
		System.out.println(teamA + " " + teamB + " " + teamC);
		dbManager.setTeamsForUser(userIvanov, teamA);
		dbManager.setTeamsForUser(userPetrov, teamA, teamB);
		dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

		for (User user : dbManager.findAllUsers()) {
			printList(dbManager.getUserTeams(user));
			System.out.println("~~~~~");
		}

	}
}
