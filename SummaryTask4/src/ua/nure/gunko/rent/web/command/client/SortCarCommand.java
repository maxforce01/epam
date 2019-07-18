package ua.nure.gunko.rent.web.command.client;

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
import ua.nure.gunko.rent.web.command.Command;

public class SortCarCommand extends Command {

	/**
	 * Sort all cars
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

		if (role != Role.CLIENT) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		List<Car> list = null;
		String sort = (String) request.getParameter("sort");
		if (sort == null || (!sort.equals("brand") && !sort.equals("model") && !sort.equals("price"))) {
			list = new CarDao().findAllFreeCars();
		} else {
			StringBuilder sr = new StringBuilder("order by ");
			sr.append(sort);
			list = new CarDao().sortCars(sr.toString());
		}
		List<CarClass> classes = new CarClassDao().findAllClasses();
		request.setAttribute("classes", classes);
		request.setAttribute("cars", list);
		request.setAttribute("title", "Список машин");
		return Path.COMMAND__LIST_CARS;
	}

}
