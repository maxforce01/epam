package ua.nure.gunko.rent.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarClassDao;
import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.CarClass;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class CreateCarCommand extends Command {

	/**
	 * Command create new car.
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if (user == null || role == null) {
			return Path.LOGIN__URL;
		}

		if (role != Role.ADMIN) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		String model = (String) request.getParameter("model");
		String brand = (String) request.getParameter("brand");
		String class_id = (String) request.getParameter("class");
		String vin = (String) request.getParameter("vin");
		String number = (String) request.getParameter("text");
		String price = (String) request.getParameter("price");
		if (model == null || brand == null || class_id == null || vin == null || number == null || price == null
				|| model.isEmpty() || brand.isEmpty() || class_id.isEmpty() || vin.isEmpty() || number.isEmpty()
				|| price.isEmpty() || !Validator.isNumeric(class_id) || !Validator.isNumeric(price)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		CarClass carClass = new CarClassDao().findById(Integer.parseInt(class_id));
		Car car = new Car();
		car.setBrand(brand);
		car.setModel(model);
		car.setNumber(number);
		car.setPrice(Integer.parseInt(price));
		car.setStatus(false);
		car.setVIN(vin);
		car.setCarClass(carClass);
		if (!CarDao.insertCar(car)) {
			String errorMessage = "error.something.was.wrong";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL+"success.create.car";
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}
}
