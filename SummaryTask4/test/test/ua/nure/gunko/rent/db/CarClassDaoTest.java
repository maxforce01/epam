package test.ua.nure.gunko.rent.db;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.gunko.rent.db.CarClassDao;

public class CarClassDaoTest {


	@Test
	public void testFindById() {
		assertNotNull(new CarClassDao().findById(1));
	}

	@Test
	public void testFindAllClasses() {
		assertNotNull(new CarClassDao().findAllClasses());
	}

}
