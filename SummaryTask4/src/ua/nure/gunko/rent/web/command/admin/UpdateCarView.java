package ua.nure.gunko.rent.web.command.admin;

import java.util.List;

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

public class UpdateCarView extends Command {

	/**
	 * Show form for car's update
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		return Path.COMMAND__ERROR_PAGE + errorMessage;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if (user == null || role == null) {
			return Path.LOGIN__PAGE;
		}

		if (role != Role.ADMIN) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}

		String car_id = (String) request.getParameter("car_id");
		if (car_id == null || car_id.isEmpty() || !Validator.isNumeric(car_id)) {
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}

		Car car = new CarDao().findCarById(Integer.parseInt(car_id));
		if (car == null) {
			String errorMessage = "error.bad.car";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}

		request.setAttribute("car", car);

		List<CarClass> list = new CarClassDao().findAllClasses();
		request.setAttribute("classes", list);

		return Path.PAGE_CAR_FORM;
	}

}
