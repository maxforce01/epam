package ua.nure.gunko.rent.web.command.admin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class DeleteCarCommand extends Command {

	/**
	 * Delete car by id.
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
		String car_id = (String) request.getParameter("car_id");
		if (car_id == null || car_id.isEmpty() || !Validator.isNumeric(car_id)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		Car car = new CarDao().findCarById(Integer.parseInt(car_id));
		if (car == null) {
			String errorMessage = "error.bad.car";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		if (!CarDao.deleteCar(car)) {
			String errorMessage = "error.something.was.wrong";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		return Path.COMMAND__ACCEPTED_PAGE_URL+"success.delete.car";
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("errorMessage", "error.invalid.request");
		return Path.PAGE__ERROR_PAGE;
	}

}
