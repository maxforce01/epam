package ua.nure.gunko.rent.web.command.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class AcceptedOrdersCommand extends Command {

	/**
	 * Show orders with accepted status.
	 * 
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String error = "error.invalid.request";
		return Path.COMMAND__ERROR_PAGE + error;
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

		List<Order> orders = new OrderDao().findOrdersByStatus(Order.Status.ACCEPTED.getName());
		request.setAttribute("orders", orders);

		return Path.COMMAND__LIST_ORDERS;
	}

}
