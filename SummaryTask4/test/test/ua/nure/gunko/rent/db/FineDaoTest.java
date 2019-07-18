package test.ua.nure.gunko.rent.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.User;

public class FineDaoTest {
	private Fine fine;
	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setId(3);
		fine = new Fine();
		fine.setUser_id(user.getId());
		fine.setFine(1000);
		FineDao dao = new FineDao();
		dao.createFine(fine);
	}

	@After
	public void tearDown() throws Exception {
		new FineDao().deleteFine(fine);
	}

	@Test
	public void testFindFineById() {
		assertNotNull(new FineDao().findFineById(5));
	}

	@Test
	public void testCreateFine() {
		new FineDao().deleteFine(fine);
		user = new User();
		user.setId(3);
		fine = new Fine();
		fine.setUser_id(user.getId());
		fine.setFine(1000);
		FineDao dao = new FineDao();
		assertTrue(dao.createFine(fine));
	}

	@Test
	public void testUpdateFine() {
		fine.setFine(0);
		new FineDao().updateFine(fine);
	}

	@Test
	public void testDeleteFine() {
		assertTrue(new FineDao().deleteFine(fine));
	}

	@Test
	public void testAllFines() {
		assertNotNull(new FineDao().allFines());
	}

}
