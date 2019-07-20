package ua.nure.gunko.rent.web.Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.ActionType;
import ua.nure.gunko.rent.web.command.Command;
import ua.nure.gunko.rent.web.command.CommandContainer;

/**
 * Servlet implementation class Controller
 */
@WebServlet(name = "Controller", urlPatterns = { "/" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN_COMMAND = "loginPage";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response, ActionType.GET);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response, ActionType.POST);
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request, HttpServletResponse response, ActionType type)
			throws IOException, ServletException {
		String commandName = request.getParameter("command");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null && commandName == null) {
			dispatcher(request, response, LOGIN_COMMAND,type);
		} else {
			dispatcher(request, response, commandName,type);
		}
	}

	private void dispatcher(HttpServletRequest request, HttpServletResponse response, String commandName, ActionType type)
			throws IOException, ServletException {
		Command command = CommandContainer.get(commandName);
		String forward = command.execute(request, response,type);
		System.out.println(forward);
		if (forward != null) {
			if(type == ActionType.GET) {
			RequestDispatcher disp = request.getRequestDispatcher(forward);
			disp.forward(request, response);
			}else if(type == ActionType.POST) {
				response.sendRedirect(forward);
			}
		}
	}

}
