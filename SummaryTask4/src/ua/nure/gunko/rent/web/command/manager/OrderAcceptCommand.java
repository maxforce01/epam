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
import ua.nure.gunko.rent.web.Sender;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class OrderAcceptCommand extends Command {

	/**
	 * Command change status to accepted.
	 * 
	 * @author maxforce01
	 * 
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

		if (role != Role.MANAGER) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		String flag = (String) session.getAttribute("flag");
		if (flag != null) {
			String errorMessage = "error.redirect";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		String status = (String) request.getParameter("status");
		String order_id = (String) request.getParameter("order_id");
		String cause = (String) request.getParameter("cause");
		if (status == null || order_id == null || status.isEmpty() || order_id.isEmpty()
				|| !Validator.isNumeric(order_id)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}

		int id = Integer.parseInt(order_id);
		Order order = new OrderDao().findOrdersById(id);
		Car car = new CarDao().findCarById((int) order.getCar_id());
		User orderUser = new UserDao().findUser(order.getUser_id());
		if (order == null || car == null || orderUser == null) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}

		if (car.isStatus()) {
			order.setStatus(Order.Status.DENIED.getName());
			order.setCause("Car rental!");
			if (!OrderDao.updateOrderStatusAndCause(order)) {
				String errorMessage = "error.invalid.data";
				return Path.COMMAND__ERROR_PAGE+errorMessage;
			}
			if (orderUser.getLocale().equals("en")) {
				Sender.sendMessage(orderUser, Sender.MSG_DENIED_RENTAL_EN);
			} else {
				Sender.sendMessage(orderUser, Sender.MSG_DENIED_RENTAL_RU);
			}
			return Path.COMMAND__ACCEPTED_PAGE_URL+"success.order.denied";
		}

		if (status.toLowerCase().equals(Order.Status.ACCEPTED.getName())) {
			car.setStatus(true);
			order.setStatus(Order.Status.ACCEPTED.getName());
			if (!CarDao.updateCarStatus(car) || !OrderDao.updateOrderStatus(order)) {
				String errorMessage = "error.invalid.data";
				if (orderUser.getLocale().equals("en")) {
					Sender.sendMessage(orderUser, Sender.MSG_DENIED_EN);
				} else {
					Sender.sendMessage(orderUser, Sender.MSG_DENIED_RU);
				}
				return Path.COMMAND__ERROR_PAGE+errorMessage;
			}
		} else if (status.toLowerCase().equals(Order.Status.DENIED.getName())) {
			order.setStatus(Order.Status.DENIED.getName());
			order.setCause(cause);
			if (!OrderDao.updateOrderStatusAndCause(order)) {
				String errorMessage = "error.invalid.data";
				if (orderUser.getLocale().equals("en")) {
					Sender.sendMessage(orderUser, Sender.MSG_ACCEPTED_EN + " " + cause);
				} else {
					Sender.sendMessage(orderUser, Sender.MSG_ACCEPTED_RU + " " + cause);
				}
				return Path.COMMAND__ERROR_PAGE+errorMessage;
			}
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String error = "error.invalid.request";
		request.setAttribute("errorMessage", error);
		return Path.PAGE__ERROR_PAGE;
	}

}
