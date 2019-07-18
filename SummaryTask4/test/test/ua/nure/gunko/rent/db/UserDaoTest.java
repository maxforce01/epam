package test.ua.nure.gunko.rent.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;

public class UserDaoTest {

	private User user;
	private static long userId;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setEmail("maxmax@maxfoce01.com");
		user.setLocale("en");
		user.setLogin("maximtest");
		user.setPassword("password");
		user.setRoleId(Role.CLIENT.ordinal()+1);
		user.setStatus(false);
		UserDao.createUser(user);
		
		userId = new UserDao().findUserByLogin(user.getLogin()).getId();
		user.setId(userId);
	}
	
	@After
	public void tearDown() throws Exception {
		UserDao.deleteUser(user);
		user = null;
		userId = 0;
	}

	@Test
	public void testFindUser() {
		assertNotNull(new UserDao().findUser(userId));
	}

	@Test
	public void testFindUserByLogin() {
		assertNotNull(new UserDao().findUserByLogin(user.getLogin()));
	}

	@Test
	public void testCreateUser() {
		User u = new User();
		u.setEmail("max@maxfoce01.com");
		u.setLocale("ru");
		u.setLogin("maxforce01");
		u.setPassword("password");
		u.setRoleId(Role.CLIENT.ordinal()+1);
		u.setStatus(false);
		assertTrue(UserDao.createUser(u));
		u.setId(new UserDao().findUserByLogin("maxforce01").getId());
		UserDao.deleteUser(u);
	}
	
	
	@Test
	public void testUpdateUser() {
		user.setLogin("ClientUpdated");
		assertTrue(UserDao.updateUser(user));
	}

	@Test
	public void testBanUser() {
		assertTrue(UserDao.banUser(user));
	}

	@Test
	public void testUnBanUser() {
		assertTrue(UserDao.unBanUser(user));
	}

	@Test
	public void testUpdateUserToManager() {
		assertTrue(UserDao.updateUserToManager(user));
	}

	@Test
	public void testFindAllUsers() {
		assertNotNull(new UserDao().findAllUsers());
	}
	
	
}
