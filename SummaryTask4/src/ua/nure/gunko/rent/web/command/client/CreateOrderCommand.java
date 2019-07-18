package ua.nure.gunko.rent.web.command.client;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class CreateOrderCommand extends Command {

	/**
	 * @author maxforce01
	 * Create new order
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
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		String flag = (String) session.getAttribute("flag");
		if(flag != null) {
			String errorMessage = "error.redirect";
			return Path.COMMAND__ERROR_PAGE+errorMessage;		}
		Order order = (Order) session.getAttribute("order");
		session.removeAttribute("order");
		String pay = (String) request.getParameter("payment");

		if(pay == null || order == null || !Validator.isNumeric(pay)) {
			String errorMessage = "error.invalid.request";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		int payment = Integer.parseInt(pay);
		
		if (payment < order.getPayment() / 2 || payment > order.getPayment()) {
			String errorMessage = "error.invalid.not.money";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		Fine fine = new FineDao().findFineById(order.getUser_id());
		if(fine != null) {
			String errorMessage = "error.fine";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		if ((order.getPayment() - payment) > 0) {
			fine = new Fine();
			fine.setUser_id(order.getUser_id());
			fine.setFine(order.getPayment() - payment);
			new FineDao().createFine(fine);
			new OrderDao().createOrder(order);
			session.setAttribute("fine", fine);
		} else {
			new OrderDao().createOrder(order);
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL+"success.add.order.user";
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}

	
	
}
