import java.io.IOException;
import java.security.Key;
import java.util.Properties;
import java.util.Vector;

import javax.mail.*;

import javax.mail.internet.*;
//import TestMail;


public class MailClient {

	private static class Person
	{
		public String username;
		public String password;
		
		Person()
		{
			username = new String("");
			password = new String("");
		}
		
		Person(String user, String pass)
		{
			username = new String(user);
			password = new String(pass);
		}
		
		Person(String user)
		{
			username = new String(user);
			password = new String("");
		}
	}
	
	private static class MailContent
	{
		public Vector<String> recveivers;
		public Vector<String> ccs;
		public Vector<String> bccs;
		public String host;
		public String title;
		public String content;
		public Vector<String> fileList;
		
		
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
	
	public static void main(String[] args)
	{
		Person sender = new Person("ipmanxjackiechan@gmail.com","123a4567bcd");
		Person receiver = new Person("tugianghaidang125@gmail.com");
		MailContent mailContent = new MailContent();
		mailContent.setTitle("F*ck Cong!");
		mailContent.setContent("F*ck you nln");
		mailContent.setHost("smtp.gmail.com");
		//mailContent.recveiver.
		//MailClient.sendMail(sender,receiver,mailContent);
		MailClient.checkMail(sender, "smtp.gmail.com");
		
		
		//MyCrypto cryptoHandler = new MyCrypto();
		//cryptoHandler.digestMessage("HelloWorld", "SHA-1");
		//String mode = "AES";
		//int keySize = 256;
		//String key = cryptoHandler.createSymmetricKey(keySize, mode);
		//cryptoHandler.importSymmetricKey(key, mode);
		//cryptoHandler.encryptMessage(MyCrypto.stringToKey(key), "Hi, I'm Duy ^^");
	}
	
	public static void fetchMail(Person acc, String host)
	{
		
	}
	
	public static void checkMail(Person acc, String host)
	{

		try 
		{
			Properties props = new Properties();
			  props.put("mail.pop3.host", host);
			  props.put("mail.pop3.port", "995");
			  props.put("mail.pop3.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props);
			Store store = session.getStore("pop3s");
			
			store.connect(host,acc.username,acc.password);
			
			//Lấy thư mục inbox từ account
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			
			// Duyệt thư mục xử lý mail
			Message message[] = folder.getMessages();
			for(int i=0; i < message.length; i++)
			{
				System.out.println("Mail #" + (i+1));
				//message[i].ge
				System.out.println("From: " + message[i].getFrom()[0]);
				System.out.println("Subject: " + message[i].getSubject());
				//System.out.println("Content:\n" + message[i].getContent());
				//message[i].get
			}
			
			folder.close(false);
			store.close();
		} 
		catch (MessagingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendMail(Person sender, Person receiver, MailContent mailContent) 
	{
		String username = sender.username;
		String password = sender.password;
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);

		// Sử dụng TLS
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.host", mailContent.getHost());
		props.put("mail.smtp.port", "587");

		// Tao session
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiver.username));
			message.setSubject("Testing Subject");
			message.setText(mailContent.getContent());

			Transport transport = session.getTransport("smtp");
			transport.connect(mailContent.getHost(), username, password);
			transport.send(message);
			transport.close();
			
			//Transport.close();

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}