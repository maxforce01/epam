package ua.nure.gunko.rent.web.command.client;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class FinePaymentCommand extends Command {

	/**
	 * Pay borrow by client.
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
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
	
		Fine fine = (Fine) session.getAttribute("fine");
		String pay = (String) request.getParameter("payment");
		if (fine == null || pay == null || !Validator.isNumeric(pay))  {
			String errorMessage = "error.invalid.request";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		if(new FineDao().findFineById(fine.getUser_id()) == null) {
			String errorMessage = "error.bad.fine";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		int payment = Integer.parseInt(pay);
		int result = fine.getFine() - payment;
		if (result > 0) {
			fine.setFine(result);
			new FineDao().updateFine(fine);
			session.removeAttribute("fine");
			session.setAttribute("fine", fine);
		} else {
			new FineDao().deleteFine(fine);
			session.removeAttribute("fine");
		}
		return Path.ACCOUNT_URL;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}

	
}
