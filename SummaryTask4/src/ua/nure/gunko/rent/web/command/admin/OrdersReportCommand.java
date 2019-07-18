package ua.nure.gunko.rent.web.command.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.DocumentException;

import ua.nure.gunko.rent.db.OrderDao;
import ua.nure.gunko.rent.db.entity.Order;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.PDFDocument;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.command.Command;

public class OrdersReportCommand extends Command {

	/**
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

		if (role != Role.ADMIN) {
			String errorMessage = "error.invalid.permission";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		String start = (String) request.getParameter("start");
		String end = (String) request.getParameter("end");
		if(start == null || start.isEmpty() || end == null || end.isEmpty()) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		Date one = Date.valueOf(start);
		Date two = Date.valueOf(end);
		int days = daysBetween(two, one);
		if(days<0) {
			String errorMessage = "error.invalid.data";
			return Path.COMMAND__ERROR_PAGE + errorMessage;
		}
		List<Order> list = new OrderDao().findOrdersByDates(one, two);
		try {
			if (user.getLocale().equals("ru")) {
				PDFDocument.createReportRU(list);
			} else {
				PDFDocument.createReportEN(list);
			}
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Path.COMMAND__ACCEPTED_PAGE_URL;
	}

	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("errorMessage", "error.invalid.request");
		return Path.PAGE__ERROR_PAGE;
	}
	
	public static int daysBetween(Date d1, Date d2) {
		LocalDate startDate = LocalDate.parse(d2.toString());
		LocalDate endDate = LocalDate.parse(d1.toString());
		Period period = Period.between(startDate, endDate);
		return period.getDays();
	}

}
