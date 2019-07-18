package ua.nure.gunko.rent.db;

public final class Fields {
	public static final String ENTITY__ID = "id";

	public static final String USER__LOGIN = "login";
	public static final String USER__PASSWORD = "password";
	public static final String USER__EMAIL = "email";
	public static final String USER__ROLE_ID = "role_id";
	public static final String USER__LOCALE = "locale";
	public static final String USER__STATUS = "status";

	public static final String CLASS = "class";
	
	public static final String CAR_BRAND = "brand";
	public static final String CAR_MODEL = "model";
	public static final String CAR_CLASS_ID = "class_id";
	public static final String CAR_PRICE = "price";
	public static final String CAR_VIN = "vin";
	public static final String CAR_NUMBER = "number";
	public static final String CAR_STATUS = "status";
	
	public static final String ORDER_USER_ID = "user_id";
	public static final String ORDER_CAR_ID = "car_id";
	public static final String ORDER_USER_SESSION = "session";
	public static final String ORDER_USER_NUMBER = "number";
	public static final String ORDER_DATE_START = "date_start";
	public static final String ORDER_DATE_END = "date_end";
	public static final String ORDER_PAYMENT = "payment";
	public static final String ORDER_STATUS = "status";
	public static final String ORDER_CAUSE = "cause";
	
	public static final String FINE_USER_ID = "user_id";
	public static final String FINE_PAYMENT = "fine";
	
	public static final String DEFAULT_LOCALE = "ru";
	public static final int USER_ROLE = 3;
	
}
