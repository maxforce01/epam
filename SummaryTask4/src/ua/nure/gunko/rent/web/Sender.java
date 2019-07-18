package ua.nure.gunko.rent.web;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import ua.nure.gunko.rent.db.entity.User;

public class Sender {

	private static final Logger LOG = Logger.getLogger(Sender.class);
	private static final Session SESSION = init();
	public static final String MSG_DENIED_RENTAL_RU ="Заказ был отклонён, машина занята!";
	public static final String MSG_DENIED_RENTAL_EN ="Order was denied, car in rental!";
	public static final String MSG_DENIED_EN ="Order was denied by manager!";
	public static final String MSG_DENIED_RU ="Заказ был отклонён менеджером!";
	public static final String MSG_ACCEPTED_EN ="Order was accepted, you can pick up car!";
	public static final String MSG_ACCEPTED_RU ="Заказ был подтверждён, вы можете забрать машину!";
	public static final String MSG_BORROW_EN = "Reminder!\n You owe money ";
	public static final String MSG_BORROW_RU = "Напоминание!\n Вы должны денег " ;

	private static Session init() {
		Session session = null;
		try {
			Context initialContext = new InitialContext();
			session = (Session) initialContext.lookup("java:comp/env/mail/Session");
		} catch (Exception ex) {
			LOG.error("mail session lookup error", ex);
		}
		return session;
	}

	public static void sendMessage(User user, String text) {
		try {
			Message msg = new MimeMessage(SESSION);
			msg.setFrom(new InternetAddress("sendertest6@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setText(text);
			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (AddressException e) {
			LOG.error(e);
		} catch (MessagingException e) {
			LOG.error(e);
		}
	}

	
}
