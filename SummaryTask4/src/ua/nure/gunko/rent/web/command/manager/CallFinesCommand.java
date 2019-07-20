package ua.nure.gunko.rent.web.command.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.FineDao;
import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Fine;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Sender;
import ua.nure.gunko.rent.web.command.Command;

public class CallFinesCommand extends Command {

	/**
	 * Call all debts about borrow
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

		if (role != Role.MANAGER) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		List<Fine> list = new FineDao().allFines();
		UserDao dao = new UserDao();
		for (Fine f : list) {
			User fUser = dao.findUser(f.getUser_id());
			if (fUser.getLocale().equals("ru")) {
				Sender.sendMessage(fUser, Sender.MSG_BORROW_RU + f.getFine() + " UAN");
			} else {
				Sender.sendMessage(fUser, Sender.MSG_BORROW_EN + f.getFine() + " UAN");
			}
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String error = "error.invalide.request";
		request.setAttribute("errorMessage", error);
		return Path.PAGE__ERROR_PAGE;
	}

}
