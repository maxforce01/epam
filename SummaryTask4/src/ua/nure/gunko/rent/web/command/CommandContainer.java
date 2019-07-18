package ua.nure.gunko.rent.web.command;

import java.util.Map;
import java.util.TreeMap;

import ua.nure.gunko.rent.web.command.admin.BanUserCommand;
import ua.nure.gunko.rent.web.command.admin.CarListAdminCommand;
import ua.nure.gunko.rent.web.command.admin.CreateCarCommand;
import ua.nure.gunko.rent.web.command.admin.CreateCarView;
import ua.nure.gunko.rent.web.command.admin.DeleteCarCommand;
import ua.nure.gunko.rent.web.command.admin.OrdersReportCommand;
import ua.nure.gunko.rent.web.command.admin.UnBanUserCommand;
import ua.nure.gunko.rent.web.command.admin.UpdateCarCommand;
import ua.nure.gunko.rent.web.command.admin.UpdateCarView;
import ua.nure.gunko.rent.web.command.admin.UpdateToManagerCommand;
import ua.nure.gunko.rent.web.command.admin.UserListCommand;
import ua.nure.gunko.rent.web.command.client.CarListCommand;
import ua.nure.gunko.rent.web.command.client.CreateOrderCommand;
import ua.nure.gunko.rent.web.command.client.FilterCarCommand;
import ua.nure.gunko.rent.web.command.client.FinePaymentCommand;
import ua.nure.gunko.rent.web.command.client.PaymentCommand;
import ua.nure.gunko.rent.web.command.client.SortCarCommand;
import ua.nure.gunko.rent.web.command.client.ViewOrderCommand;
import ua.nure.gunko.rent.web.command.common.AcceptedViewCommand;
import ua.nure.gunko.rent.web.command.common.ErrorCommand;
import ua.nure.gunko.rent.web.command.common.LoginCommand;
import ua.nure.gunko.rent.web.command.common.LoginPageCommand;
import ua.nure.gunko.rent.web.command.common.LogoutCommand;
import ua.nure.gunko.rent.web.command.common.NoCommand;
import ua.nure.gunko.rent.web.command.common.RegisterCommand;
import ua.nure.gunko.rent.web.command.common.RegisterPageCommand;
import ua.nure.gunko.rent.web.command.common.UpdateSettingsCommand;
import ua.nure.gunko.rent.web.command.common.ViewSettingsCommand;
import ua.nure.gunko.rent.web.command.manager.AcceptedOrdersCommand;
import ua.nure.gunko.rent.web.command.manager.CallFinesCommand;
import ua.nure.gunko.rent.web.command.manager.DeniedOrdersCommand;
import ua.nure.gunko.rent.web.command.manager.OrderAcceptCommand;
import ua.nure.gunko.rent.web.command.manager.OrderInfoCommand;
import ua.nure.gunko.rent.web.command.manager.OrderReturnCommand;
import ua.nure.gunko.rent.web.command.manager.OrderReturnViewCommand;
import ua.nure.gunko.rent.web.command.manager.ReturnedOrdersCommand;
import ua.nure.gunko.rent.web.command.manager.UncheckedOrdersCommand;

public class CommandContainer {
	
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("register", new RegisterCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("loginPage", new LoginPageCommand());
		commands.put("registerPage", new RegisterPageCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("accept", new AcceptedViewCommand());
		commands.put("error", new ErrorCommand());
		
		
		//Client commands
		commands.put("CarList", new CarListCommand());
		commands.put("ViewOrder", new ViewOrderCommand());
		commands.put("Payment", new PaymentCommand());
		commands.put("CreateOrder", new CreateOrderCommand());
		commands.put("FinePayment", new FinePaymentCommand());
		commands.put("SortCarList", new SortCarCommand());
		commands.put("FilterCarList", new FilterCarCommand());
		
		
		//Manager Commands
		commands.put("UncheckedOrders", new UncheckedOrdersCommand());
		commands.put("AcceptedOrders", new AcceptedOrdersCommand());
		commands.put("DeniedOrders", new DeniedOrdersCommand());
		commands.put("ReturnedOrders", new ReturnedOrdersCommand());
		commands.put("OrderInfo", new OrderInfoCommand());
		commands.put("OrderAccept", new OrderAcceptCommand());
		commands.put("OrderReturnView", new OrderReturnViewCommand());
		commands.put("OrderReturn", new OrderReturnCommand());
		commands.put("CallFines", new CallFinesCommand());
		
		
		//Administrator commands
		commands.put("CarListAdmin", new CarListAdminCommand());
		commands.put("CarCreateView", new CreateCarView());
		commands.put("CarCreate", new CreateCarCommand());
		commands.put("CarUpdateView", new UpdateCarView());
		commands.put("CarUpdate", new UpdateCarCommand());
		commands.put("CarDelete", new DeleteCarCommand());
		commands.put("UserList", new UserListCommand());
		commands.put("BanCommand", new BanUserCommand());
		commands.put("UnBanCommand", new UnBanUserCommand());
		commands.put("OrdersReport", new OrdersReportCommand());
		commands.put("UpdateManagerCommand", new UpdateToManagerCommand());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
}
