package ua.nure.gunko.rent.web.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.gunko.rent.db.CarClassDao;
import ua.nure.gunko.rent.db.CarDao;
import ua.nure.gunko.rent.db.entity.Car;
import ua.nure.gunko.rent.db.entity.CarClass;
import ua.nure.gunko.rent.db.entity.Role;
import ua.nure.gunko.rent.db.entity.User;
import ua.nure.gunko.rent.web.Path;
import ua.nure.gunko.rent.web.Validator;
import ua.nure.gunko.rent.web.command.Command;

public class FilterCarCommand extends Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected String doPost(HttpServletRequest request, HttpServletResponse response) {
		String errorMessage = "error.invalid.request";
		return Path.COMMAND__ERROR_PAGE + errorMessage;
	}



	@Override
	protected String doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Role role = (Role) session.getAttribute("userRole");
		if(user == null || role == null) {
			return Path.LOGIN__PAGE;
		}
		
		if(role != Role.CLIENT) {
			String errorMessage = "error.invalid.permission";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		String searchValue = (String) request.getParameter("searchValue");
		String brand = (String) request.getParameter("brand");
		String model = (String) request.getParameter("model");
		String carClass = (String) request.getParameter("class");
		String price = (String) request.getParameter("price");
		if(carClass != null && !carClass.equals("null") && !Validator.isNumeric(carClass)){
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
			
		}
		if(!Validator.isNumeric(price)) {
			String errorMessage = "error.invalid.data";
			request.setAttribute("errorMessage", errorMessage);
			return Path.PAGE__ERROR_PAGE;
		}
		List<Car> list = null;
		String query = queryParams(brand,carClass ,model, price, searchValue);
		list = new CarDao().filterCars(query);
		List<CarClass> classes = new CarClassDao().findAllClasses();
		request.setAttribute("classes", classes);
		request.setAttribute("cars", list);
		request.setAttribute("title", "Список машин");
		return Path.COMMAND__LIST_CARS;}



	private String queryParams(String brand,String carClass,String model,String price,String value) {
		StringBuilder sb = new StringBuilder();
		if(brand!=null) {
			sb.append("brand like '%").append(value).append("%'");
		}
		if(model!=null) {
			if(sb.toString().isEmpty()) {
				sb.append("model like '%").append(value).append("%'");
			}else {
				sb.append(" or model like '%").append(value).append("%'");
			}
		}
		if(price!=null) {
			if(sb.toString().isEmpty()) {
				sb.append("price BETWEEN 0 and ").append(price);
			}else {
				sb.append(" or price BETWEEN 0 and ").append(price);
			}
		}
		if(carClass!=null) {
			if(sb.toString().isEmpty()) {
				sb.append("class_id = '").append(carClass).append("'");
			}else {
				sb.append(" or class_id = '").append(carClass).append("'");
			}
		}
		return sb.toString();
	}
	
}
