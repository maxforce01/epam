package test.ua.nure.gunko.rent.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.CarClass;

public class CarDaoTest {
	private Car car;
	
	
	@Before
	public void setUp() throws Exception {
		car = new Car();
		car.setBrand("brand");
		CarClass classs = new CarClass();
		classs.setId(3);
		car.setCarClass(classs);
		car.setModel("model");
		car.setNumber("Ai018DA");
		car.setPrice(3000);
		car.setStatus(false);
		car.setVIN("vin");
		CarDao.insertCar(car);
		car.setId(new CarDao().findCarByVin(car.getVIN()).getId());
	}

	@After
	public void tearDown() throws Exception {
		CarDao.deleteCar(car);
		car = null;
	}

	@Test
	public void testFindCarById() {
		assertNotNull(new CarDao().findCarById(1));
	}

	@Test
	public void testFindAllFreeCars() {
		assertNotNull(new CarDao().findAllFreeCars());
	}

	@Test
	public void testUpdateCarStatus() {
		car.setStatus(true);
		assertTrue(CarDao.updateCarStatus(car));
	}

	@Test
	public void testInsertCar() {
		car = new Car();
		car.setBrand("brand");
		CarClass classs = new CarClass();
		classs.setId(3);
		car.setCarClass(classs);
		car.setModel("model");
		car.setNumber("Ai018D");
		car.setPrice(3000);
		car.setStatus(false);
		car.setVIN("vinn");
		assertTrue(CarDao.insertCar(car));
		car.setId(new CarDao().findCarByVin(car.getVIN()).getId());
		CarDao.deleteCar(car);
	}

	@Test
	public void testUpdateCar() {
		car.setStatus(false);
		assertTrue(CarDao.updateCar(car));
	}

	@Test
	public void testDeleteCar() {
		assertTrue(CarDao.deleteCar(car));
	}

	@Test
	public void testFindAllCars() {
		assertNotNull(new CarDao().findAllCars());
	}

}
