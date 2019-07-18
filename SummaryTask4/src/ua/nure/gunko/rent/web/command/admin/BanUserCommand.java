package ua.nure.gunko.rent.web.command.admin;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class BanUserCommand extends Command{

	/**
	 *  Command ban user by id
	 *  @author maxforce01
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
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		String user_id = (String) request.getParameter("user_id");
		if(user_id == null || user_id.isEmpty() || !Validator.isNumeric(user_id)) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		User userBan = new UserDao().findUser(Long.parseLong(user_id));
		if(userBan == null) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		userBan.setStatus(true);
		
		if(!UserDao.banUser(userBan)) {
			String errorMessage = "error.something.was.wrong";
			return Path.COMMAND__ERROR_PAGE+errorMessage;
		}
		
		return Path.COMMAND__ACCEPTED_PAGE_URL+"success.ban.user";
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		request.setAttribute("errorMessage", errorMessage);
		return Path.PAGE__ERROR_PAGE;
	}


	

}
