package ua.nure.gunko.rent.web.command.manager;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class OrderReturnViewCommand extends Command{

	/**
	 * Show order returns's page.
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String error = "error.invalid.request";
		return Path.COMMAND__ERROR_PAGE+error;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if (user == null || role == null) {
			return Path.LOGIN__PAGE;
		}

		if (role != Role.MANAGER) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		String order_id = (String) request.getParameter("order_id");
		if(order_id == null || order_id.isEmpty() ||!Validator.isNumeric(order_id) ) {
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		int id = Integer.parseInt(order_id);
		Order order = new OrderDao().findOrdersById(id);
		if(order == null) {
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		User orderUser = new UserDao().findUser(order.getUser_id());
		Car car = new CarDao().findCarById((int) order.getCar_id());
		request.setAttribute("order", order);
		request.setAttribute("orderUser", orderUser);
		request.setAttribute("car", car);
		
		return Path.COMMAND__ORDER_RETURN_VIEW;
	}
}
