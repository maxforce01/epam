package ua.nure.gunko.rent.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.UserDao;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class UserListCommand extends Command{

	/**
	 * Show all users for administrator.
	 * @author maxforce01
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.permission";
		return Path.COMMAND__ERROR_PAGE+errorMessage;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if (user == null || role == null) {
			return Path.LOGIN__PAGE;
		}

		if (role != Role.ADMIN) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		
		List<User> users = new UserDao().findAllUsers();
		request.setAttribute("users", users);
		
		
		return Path.COMMAND_LIST_USERS;}

	
	
}
