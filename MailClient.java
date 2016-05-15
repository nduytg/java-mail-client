package MailClient;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
		public Vector<String> cc;
		public Vector<String> bcc;
		public String host;
		public String title;
		public String content; //setText
		public Vector<String> fileList;
		public Vector<DataSource> source;
		
		MailContent()
		{
			// null het
			this.recveivers = new Vector<String>();
			this.cc = new Vector<String>();
			this.bcc = new Vector<String>();
			this.host = new String();
			this.title = new String();
			this.content = new String();
			this.fileList = new Vector<String>();
			this.source = new Vector<DataSource>();
		}
		
		public void addReceiver(String name)
		{
			this.recveivers.addElement(name);
		}
		
		public void addCC(String name)
		{
			this.cc.addElement(name);
		}
		
		public void addBCC(String name)
		{
			this.bcc.addElement(name);
		}
		
		public void addfileList(String nameFile) // Add các đường dẫn chứa file đính kèm
		{
			this.fileList.addElement(nameFile);
		}
		
		public void addAttachPart () // Phần đính kèm
		{
			if (fileList.size() > 0)
			{
				int numfile = fileList.size();
				this.source.setSize(numfile);
				for (int i = 0; i < numfile; i++)
				{
					this.source.set(i,new FileDataSource(fileList.get(i))); // set đường dẫn tới file vào, mỗi phần tử source trong DataSource là chứa một file
				}
			}
		}
		
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
		public int getSizeListFile()
		{
			return this.fileList.size();
		}
		public String getLinkFile(int i)
		{
			return this.fileList.get(i);
		}
	}

	
	public static void main(String[] args) throws IOException
	{
		Person sender = new Person("ipmanxjackiechan@gmail.com","123a4567bcd");
		//Person receiver = new Person("testmailclient126@gmail.com");
		String host = "pop.gmail.com";
		MailContent mailContent = new MailContent();
		mailContent.setTitle("F*ck Cong again!");
		mailContent.setContent("F*ck you nln");
		mailContent.setHost(host);
		mailContent.addReceiver("zzzthanhcongzzz@gmail.com");
		mailContent.addReceiver("nduytg@gmail.com");
		mailContent.addReceiver("testmailclient126@gmail.com");
		mailContent.addCC("ipmansuper@gmail.com");
		
		String storeType = "pop3";
		
		MailClient.fetchMail(sender, host, storeType);
		//MailClient.sendMail(sender, mailContent);
		//mailContent.recveiver.
		
		//MailClient.checkMail(sender, "smtp.gmail.com");
		
		
		//MyCrypto cryptoHandler = new MyCrypto();
		//cryptoHandler.digestMessage("HelloWorld", "SHA-1");
		//String mode = "AES";
		//int keySize = 256;
		//String key = cryptoHandler.createSymmetricKey(keySize, mode);
		//cryptoHandler.importSymmetricKey(key, mode);
		//cryptoHandler.encryptMessage(MyCrypto.stringToKey(key), "Hi, I'm Duy ^^");
	}
	
	public static void fetchMail(Person acc, String host, String storeType)
	{
		System.out.println("Fetching mail.....\nPlease wait....");
		try
		{
			Properties properties = new Properties();
			properties.put("mail.store.protocol", storeType);
			properties.put("mail.pop3.host", host); // để test thôi nên sửa imap
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			
			Session emailSession = Session.getDefaultInstance(properties);
	        // emailSession.setDebug(true);

	        // create the POP3 store object and connect with the pop server
	        Store store = emailSession.getStore("pop3s");

	        store.connect(host, acc.username, acc.password);

	        // Chọn đường dẫn folder lưu trữ mail
	        Folder emailFolder = store.getFolder("INBOX");
	        emailFolder.open(Folder.READ_ONLY);

	        BufferedReader reader = new BufferedReader(new InputStreamReader(
		     System.in));

	        // retrieve the messages from the folder in an array and print it
	        Message[] messages = emailFolder.getMessages();
	        System.out.println("messages.length---" + messages.length);

	        for (int i = 0; i < messages.length; i++) {
	           Message message = messages[i];
	           System.out.println("---------------------------------");
	           writePart(message);
	           String line = reader.readLine();
	           if ("YES".equals(line)) {
	              message.writeTo(System.out);
	           } else if ("QUIT".equals(line)) {
	              break;
	           }
	        }

	        // close the store and folder objects
	        emailFolder.close(false);
	        store.close();
		}
		catch (NoSuchProviderException e){
			e.printStackTrace();
		}
		catch (MessageRemovedException e){
			e.printStackTrace();
		}
		catch (IOException e){
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void checkMail(Person acc, String host)
	{
		try 
		{
			System.out.println("Checking mail.....\nPlease wait....");
			Properties props = new Properties();
			props.put("mail.pop3.host", host);
			props.put("mail.pop3.port", "995");		//POP3
			//props.put("mail.imap.host", host);	//IMAP
			//props.put("mail.imap.port", "993");
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
				System.out.println("From: " + message[i].getFrom()[0].toString());
				System.out.println("Subject: " + message[i].getSubject() + "\n");
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
	
	@SuppressWarnings("static-access")
	public static void sendMail(Person sender, MailContent mailContent) throws IOException 
	{
		final String username = sender.username;
		final String password = sender.password;
		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);

		// Sử dụng TLS
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", mailContent.getHost());
		props.put("mail.smtp.port", "587");

		// Tao session chứng thực
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(username, password);
			}
		  });

		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			
			// 3 vòng lặp cho TO, CC, BCC
			for(int i=0; i < mailContent.recveivers.size(); i++)
			{
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailContent.recveivers.get(i)));
			}
			
			//CC
			for(int i=0; i < mailContent.cc.size(); i++)
			{
				if (mailContent.cc.get(i) != null) // Em này đang chết
				{
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mailContent.cc.get(i)));
				}
			}
			
			//BCC
			for(int i=0; i < mailContent.bcc.size(); i++)
			{
				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailContent.bcc.get(i)));
			}
			//message.setRecipients(Message.RecipientType.TO,
				//InternetAddress.parse(receiver.username));
			message.setSubject(mailContent.getTitle());
			
			
			// Message Body Part
			// phần content text
			
			MimeBodyPart mainBodyPart = new MimeBodyPart();
			mainBodyPart.setText(mailContent.getContent());
			
			// Message Attach Part
			// Hỏi có đính kèm file không thì set null
			DataInput in = new DataInputStream(System.in);
			System.out.println("So luong file dinh kem");
			int numFile = Integer.parseInt(in.readLine());
			// Nhập file đính kèm nếu số lượng là 0 thì là null
			for (int i = 0; i < numFile; i++)
			{
				System.out.printf("Nhap file dinh kem %d: ", i);
				String linkattachpart = in.readLine();
				mailContent.addfileList(linkattachpart); //1 lan add la 1 file dinh kem
			}
			mailContent.addAttachPart();
			Vector<MimeBodyPart> attachBodyPart = new Vector<MimeBodyPart>();
			
			// 1 list DataSource cho 1 list file đính kèm
			numFile = mailContent.getSizeListFile();
			//attachBodyPart.setSize(numFile);
			
			// Multipart for attach file
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mainBodyPart);
			
			for (int i = 0; i < numFile; i++)
			{
				DataSource source = new FileDataSource(mailContent.getLinkFile(i));
				MimeBodyPart temp = new MimeBodyPart();
				attachBodyPart.addElement(temp);
				attachBodyPart.get(i).setDataHandler((new DataHandler(source)));
				attachBodyPart.get(i).setFileName(source.getName());
				multipart.addBodyPart(attachBodyPart.get(i));
			}
			
			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect(mailContent.getHost(), username, password);
			transport.send(message);
			
			//transport.sendMessage(arg0, arg1);
			transport.close();

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	   /*
	   * This method checks for content-type 
	   * based on which, it processes and
	   * fetches the content of the message
	   * -1: error
	   * 0: other types
	   * 1: plain text
	   * 2: multipart
	   * 3: Nested Message
	   * 4: image/jpeg
	   * 5: image/ (don't have extension)
	   * tạm thời return như trên lúc làm có thêm return tiếp
	   */
	   public static int writePart(Part p) throws Exception {
	      if (p instanceof Message)
	         //Call methos writeEnvelope
	         writeEnvelope((Message) p);

	      System.out.println("----------------------------");
	      System.out.println("CONTENT-TYPE: " + p.getContentType());

	      //check if the content is plain text
	      if (p.isMimeType("text/plain")) {
	         System.out.println("This is plain text");
	         System.out.println("---------------------------");
	         System.out.println((String) p.getContent());
	         return 1;
	      } 
	      //check if the content has attachment
	      else if (p.isMimeType("multipart/*")) {
	         System.out.println("This is a Multipart");
	         System.out.println("---------------------------");
	         Multipart mp = (Multipart) p.getContent();
	         int count = mp.getCount();
	         for (int i = 0; i < count; i++)
	            writePart(mp.getBodyPart(i));
	         return 2;
	      } 
	      //check if the content is a nested message
	      else if (p.isMimeType("message/rfc822")) {
	         System.out.println("This is a Nested Message");
	         System.out.println("---------------------------");
	         writePart((Part) p.getContent());
	         return 3;
	      } 
	      //check if the content is an inline image
	      else if (p.isMimeType("image/jpeg")) {
	         System.out.println("--------> image/jpeg");
	         Object o = p.getContent();

	         InputStream x = (InputStream) o;
	         // Construct the required byte array
	         System.out.println("x.length = " + x.available());
	         int i = 0;
	         byte[] bArray = new byte[x.available()];
	         while ((i = (int) ((InputStream) x).available()) > 0)
	         {
	            int result = (int) (((InputStream) x).read(bArray));
	            if (result == -1)
	            	break;
	         }
	         FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
	         f2.write(bArray);
	         return 4;
	      } 
	      else if (p.getContentType().contains("image/")) {
	         System.out.println("content type" + p.getContentType());
	         File f = new File("image" + new Date().getTime() + ".jpg");
	         DataOutputStream output = new DataOutputStream(
	            new BufferedOutputStream(new FileOutputStream(f)));
	            com.sun.mail.util.BASE64DecoderStream test = 
	                 (com.sun.mail.util.BASE64DecoderStream) p
	                  .getContent();
	         byte[] buffer = new byte[1024];
	         int bytesRead;
	         while ((bytesRead = test.read(buffer)) != -1) {
	            output.write(buffer, 0, bytesRead);
	         }
	         return 5;
	      } 
	      else {
	         Object o = p.getContent();
	         if (o instanceof String) {
	            System.out.println("This is a string");
	            System.out.println("---------------------------");
	            System.out.println((String) o);
	         } 
	         else if (o instanceof InputStream) {
	            System.out.println("This is just an input stream");
	            System.out.println("---------------------------");
	            InputStream is = (InputStream) o;
	            is = (InputStream) o;
	            int c;
	            while ((c = is.read()) != -1)
	               System.out.write(c);
	         } 
	         else {
	            System.out.println("This is an unknown type");
	            System.out.println("---------------------------");
	            System.out.println(o.toString());
	         }
	         return 0;
	      }
	   }
	   /*
	   * This method would print FROM,TO and SUBJECT of the message
	   */
	   public static void writeEnvelope(Message m) throws Exception {
	      System.out.println("This is the message envelope");
	      System.out.println("---------------------------");
	      Address[] a;

	      // FROM
	      if ((a = m.getFrom()) != null) {
	         for (int j = 0; j < a.length; j++)
	         System.out.println("FROM: " + a[j].toString());
	      }

	      // TO
	      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
	         for (int j = 0; j < a.length; j++)
	         System.out.println("TO: " + a[j].toString());
	      }

	      // SUBJECT
	      if (m.getSubject() != null)
	         System.out.println("SUBJECT: " + m.getSubject());

	   }
}
