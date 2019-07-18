package ua.nure.gunko.rent.web.command.client;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class PaymentCommand extends Command {

	/**
	 * Command return payment for client.
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

		if (role != Role.CLIENT) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		String driver = (String) request.getParameter("driver");
		String dateEnd = (String) request.getParameter("date_end");
		String userSession = (String) request.getParameter("pass_session");
		String userNumber = (String) request.getParameter("pass_number");
		if (dateEnd == null || userNumber == null || userSession == null) {
			String errorMessage = "error.invalid.request";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}

		Date End = Date.valueOf(dateEnd);
		Date Start = new Date(System.currentTimeMillis());
		Car car = new CarDao().findCarById(Integer.parseInt(request.getParameter("car_id")));
		if (car == null) {
			String errorMessage = "error.bad.car";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		int days = daysBetween(End, Start);
		if (days < 0) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		int payment;
		if (driver == null || driver.isEmpty()) {
			payment = days * car.getPrice();
		} else {
			payment = days * car.getPrice() + 500;
		}
		Order order = new Order();
		order.setUser_id(user.getId());
		order.setCar_id(car.getId());
		order.setSession(userSession);
		order.setNumber(userNumber);
		order.setDateStart(Start);
		order.setDateEnd(End);
		order.setPayment(payment);
		order.setStatus(Order.Status.UNCHECKED.getName());
		session.setAttribute("order", order);

		return Path.COMMAND__PAYMENT_URL;
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
		
		Order order = (Order)session.getAttribute("order");
		if(order == null) {
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		
		return Path.COMMAND__PAYMENT_FORM;
	}

	public static int daysBetween(Date d1, Date d2) {
		LocalDate startDate = LocalDate.parse(d2.toString());
		LocalDate endDate = LocalDate.parse(d1.toString());
		Period period = Period.between(startDate, endDate);
		return period.getDays();
	}
}
