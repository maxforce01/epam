package ua.nure.gunko.rent.web.command.manager;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class OrderReturnCommand extends Command {

	/**
	 * Command returns car and change order's status to returned.
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

		if (role != Role.MANAGER) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		String order_id = (String) request.getParameter("order_id");
		String dam = (String) request.getParameter("damage");
		if (order_id == null || order_id.isEmpty() || dam == null || dam.isEmpty() || !Validator.isNumeric(dam)
				|| !Validator.isNumeric(order_id)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}

		int id = Integer.parseInt(order_id);
		Order order = new OrderDao().findOrdersById(id);
		User orderUser = new UserDao().findUser(order.getUser_id());
		Car car = new CarDao().findCarById((int) order.getCar_id());
		if (order == null || car == null || orderUser == null) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		int damage = Integer.parseInt(dam);
		if (damage < 0 || damage > 100) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		int borrow = 0;
		borrow += (car.getPrice() * 0.2) * damage;
		Date dateEnd = order.getDateEnd();
		Date dateNow = new Date(System.currentTimeMillis());
		int days = daysBetween(dateNow, dateEnd);
		if (days > 0) {
			borrow += car.getPrice() * days;
		}

		if (borrow > 0) {
			Fine fineUser = new FineDao().findFineById(orderUser.getId());
			if (fineUser == null) {
				fineUser = new Fine();
				fineUser.setFine(borrow);
				fineUser.setUser_id(orderUser.getId());
				new FineDao().createFine(fineUser);
			} else {
				fineUser.setFine(fineUser.getFine() + borrow);
				if (!new FineDao().updateFine(fineUser)) {
					String errorMessage = "error.somthing.was.wrong";
					return Path.COMMAND__ERROR_PAGE+errorMessage;
				}
			}
		}
		car.setStatus(false);
		order.setStatus(Order.Status.RETURNED.getName());
		if (!CarDao.updateCarStatus(car) || !OrderDao.updateOrderStatus(order)) {
			String errorMessage = "error.somthing.was.wrong";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL+"success.return.order";
	}



	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String error = "error.invalid.request";
		request.setAttribute("errorMessage", error);
		return Path.PAGE__ERROR_PAGE;
	}



	public static int daysBetween(Date d1, Date d2) {
		LocalDate startDate = LocalDate.parse(d2.toString());
		LocalDate endDate = LocalDate.parse(d1.toString());
		Period period = Period.between(startDate, endDate);
		return period.getDays();
	}
}
