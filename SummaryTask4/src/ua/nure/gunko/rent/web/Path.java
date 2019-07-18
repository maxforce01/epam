package ua.nure.gunko.rent.web;

public class Path {
	
	// Manager commands
	public static final String COMMAND__LIST_ORDERS = "/WEB-INF/jsp/manager/ordersList.jsp";
	public static final String COMMAND__ORDER_INFO = "/WEB-INF/jsp/manager/orderInfo.jsp";
	public static final String COMMAND__ORDER_INFO_URL = "http://localhost:8080/SummaryTask4/controller?command=OrderInfo";
	public static final String COMMAND__ORDER_RETURN_VIEW = "/WEB-INF/jsp/manager/orderReturnView.jsp";
	public static final String COMMAND__ORDER_RETURN_VIEW_URL = "http://localhost:8080/SummaryTask4/controller?command=OrderReturnView";
	// Client commands
	public static final String COMMAND__LIST_CARS = "/WEB-INF/jsp/client/list_cars.jsp";
	public static final String COMMAND__VIEW_ORDER = "/WEB-INF/jsp/client/viewOrder.jsp";
	public static final String COMMAND__PAYMENT_FORM = "/WEB-INF/jsp/client/paymentOrder.jsp";
	public static final String COMMAND__PAYMENT_URL = "http://localhost:8080/SummaryTask4/controller?command=Payment";
	public static final String COMMAND__CREATE_ORDER_URL = "http://localhost:8080/SummaryTask4/controller?command=CreateOrder";
	
	
	// Administrator commands
	public static final String COMMAND__LIST_CARS_ADMIN = "/WEB-INF/jsp/admin/list_cars.jsp";
	public static final String COMMAND__LIST_CARS_ADMIN_URL = "http://localhost:8080/SummaryTask4/controller?command=CarListAdmin";
	public static final String PAGE_CAR_FORM = "/WEB-INF/jsp/admin/CarForm.jsp";
	public static final String PAGE_CAR_CREATE_VIEW_URL = "http://localhost:8080/SummaryTask4/controller?command=CarCreateView";
	public static final String PAGE_CAR_UPDATE_VIEW_URL = "http://localhost:8080/SummaryTask4/controller?command=CarUpdateView";
	public static final String PAGE_CAR_UPDATE_URL = "http://localhost:8080/SummaryTask4/controller?command=CarUpdate";
	public static final String PAGE_CAR_CREATE_URL = "http://localhost:8080/SummaryTask4/controller?command=CarUpdate";
	public static final String PAGE_CAR_DELETE_URL = "http://localhost:8080/SummaryTask4/controller?command=CarDelete";
	public static final String COMMAND_LIST_USERS = "/WEB-INF/jsp/admin/UserList.jsp";
	public static final String COMMAND_BAN_USER_URL = "http://localhost:8080/SummaryTask4/controller?command=BanCommand";
	public static final String COMMAND_UNBAN_USER_URL = "http://localhost:8080/SummaryTask4/controller?command=UnBanCommand";
	public static final String COMMAND_UPDATE_TO_MANAGER_USER_URL = "http://localhost:8080/SummaryTask4/controller?command=UpdateManagerCommand";
	
		
	// common commands
	public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/common/error-page.jsp";
	public static final String COMMAND__ERROR_PAGE = "http://localhost:8080/SummaryTask4/controller?command=error&message=";
	public static final String LOGIN__PAGE = "/WEB-INF/jsp/index.jsp";
	public static final String LOGIN__URL = "http://localhost:8080/SummaryTask4/";
	public static final String REGISTER__PAGE = "/WEB-INF/jsp/register.jsp";
	public static final String REGISTER__PAGE_URL = "http://localhost:8080/SummaryTask4/?command=registerPage";
	public static final String ACCOUNT_PAGE = "/WEB-INF/jsp/common/account.jsp";
	public static final String ACCOUNT_URL = "http://localhost:8080/SummaryTask4/controller";
	public static final String ACCOUNT_SETTINGS_PAGE = "/WEB-INF/jsp/common/accountSettings.jsp";
	public static final String ACCOUNT_SETTINGS_URL = "http://localhost:8080/SummaryTask4/controller?command=viewSettings";
	public static final String COMMAND__ACCEPTED_PAGE = "/WEB-INF/jsp/common/acceptedOrder.jsp";
	public static final String COMMAND__ACCEPTED_PAGE_URL = "http://localhost:8080/SummaryTask4/controller?command=accept&success=";
	
	
	//fonts
	public static final String ARIAL =  "/WEB-INF/style/fonts/ArialBlack.ttf";
	public static final String ARIALL =  "/home/maxforce01/Документы/Java/Epam/SummaryTask4/ArialBlack.ttf";
}
