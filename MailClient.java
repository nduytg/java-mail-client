import java.security.Key;
import java.util.Properties;

import javax.mail.*;

import javax.mail.internet.*;
//import TestMail;


public class MailClient {

	public static void main(String[] args)
	{
		//MailClient.abc();
		MyCrypto cryptoHandler = new MyCrypto();
		//cryptoHandler.digestMessage("HelloWorld", "SHA-1");
		String mode = "AES";
		int keySize = 256;
		String key = cryptoHandler.createSymmetricKey(keySize, mode);
		cryptoHandler.importSymmetricKey(key, mode);
		cryptoHandler.encryptMessage(MyCrypto.stringToKey(key), "Hi, I'm Duy ^^");
	}
	
	public static void abc() {

		final String username = "ipmansupern@gmail.com";
		final String password = "12345678!!";
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("nduytg@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n Hello to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}