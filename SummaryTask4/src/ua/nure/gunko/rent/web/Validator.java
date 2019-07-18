package ua.nure.gunko.rent.web;

/**
 * @author maxforce01
 *
 */
public class Validator {
	
	
	/**
	 * @author maxforce01
	 *	pattern for email validation
	 */
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * @author maxforce01
	 *	@return boolean
	 *	register form validation
	 */
	public static boolean registerFormValidate(String login, String password, String password_confirm, String email) {
		boolean result = true;
		if (login == null || login.isEmpty()) {
			return false;
		}
		if (email == null || email.isEmpty() || !email.contains("@") || !email.matches(EMAIL_PATTERN)) {
			return false;
		}
		if (password == null || password.isEmpty() || password.length() < 8) {
			return false;
		}
		if (!password_confirm.equals(password)) {
			return false;
		}
		return result;
	}

	/**
	 * @author maxforce01
	 *	@return boolean
	 *	account settings form validation
	 */
	public static boolean settingsFormValidate(String login, String email, String locale) {
		boolean result = true;
		if (login == null || login.isEmpty()) {
			return false;
		}
		if (email == null || email.isEmpty() || !email.contains("@") || !email.matches(EMAIL_PATTERN)) {
			return false;
		}
		if (locale == null || locale.isEmpty() || locale.length() > 2 || locale.length() < 2) {
			return false;
		}
		return result;
	}

	/**
	 * @author maxforce01
	 * check string on number
	 */
	public static boolean isNumeric(String str) {
		try {
			if(str == null) {
				return false;
			}
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
