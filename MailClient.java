package mailClient;

import java.io.*;
//import java.nio.file.Files;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.*;
//import TestMail;


public class MailClient {

	public static String FolderPart = "";
	public static String IMAP = "imap";
	public static Store store;
	public static Session emailSession;
	
	
	public static String getCurrentDirectory()
	{
		return System.getProperty("user.dir") + "\\";
	}
	
	public static void setPlaceDirectory (String dir)
	{
		FolderPart = dir;
	}
	
	public static String CreateFolderUser (String dir) //Địa chỉ tương đối
	{
		File Folder = new File("users");
		if (!Folder.exists())
		{
			if (Folder.mkdir())
			{
				System.out.println("Create Folder Successfull");
			}
			else
			{
				System.out.println("Fail to create new folder!");
			}
		}
		dir = getCurrentDirectory() + "users\\" + dir;
		File FolderUser = new File(dir);
		if (!FolderUser.exists())
		{
			if (FolderUser.mkdir())
			{
				System.out.println("Create Folder Successfull");
			}
			else
			{
				System.out.println("Fail to create new folder!");
			}
		}
		return dir;
	}
	
	public static String SeparatorUsername(String username) // Trả về tên folder
	{
		String FolderName = "";
		int index = username.indexOf(".com");
		FolderName = username.substring(0, index);
		return FolderName;
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
		
		@SuppressWarnings("unused")
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
		String username = "", password = "";
		// Mốt dùng đừng xóa nhé
		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input username: ");
		username = reader.readLine();
		System.out.print("Input password: ");
		password = reader.readLine();*/
		username = "ipmanxjackiechan@gmail.com";
		password = "123a4567bcd";
		Person sender = new Person(username, password);
		String host = IMAP + ".gmail.com";
		//System.out.println(getCurrentDirectory());
		String foldername = CreateFolderUser(SeparatorUsername(sender.username));
		setPlaceDirectory(foldername);
		System.out.println(foldername);
		//DeleteMail(sender, host);
		//DeleteLocal(sender);
		//DeleteRemote(sender, host);
		fetchMail(sender, host);
		/*MailContent mailContent = new MailContent();
		mailContent.setTitle("F*ck Cong again!");
		mailContent.setContent("Yêu gái nhiều nln\nNgày mai đi chơi nhé :3");
		mailContent.setHost(host);
		//mailContent.addReceiver("zzzthanhcongzzz@gmail.com");
		mailContent.addReceiver("nduytg@gmail.com");
		mailContent.addReceiver("testmailclient126@gmail.com");
		mailContent.addCC("ipmansuper@gmail.com");
		
		String storeType = IMAP;
		
		MailClient.sendMail(sender, mailContent);*/
		//mailContent.recveiver.
		
		//MailClient.checkMail(sender, "smtp.gmail.com");
		
	}
	
	public static void GetSessionObject(Person acc, String host)
	{
		try
		{
			Properties pros = new Properties();
			pros.put("mail.store.protocol", IMAP);
			pros.put("mail." + IMAP + ".host", host);
			pros.put("mail." + IMAP + ".port", "993");
			pros.put("mail." + IMAP + ".starttls.enable", "true");
			
			Session emailSession = Session.getDefaultInstance(pros);
			
			store = emailSession.getStore(IMAP + "s");
			store.connect(host, acc.username, acc.password);	
		}
		catch (NoSuchProviderException e){
			e.printStackTrace();
		}
		catch (MessageRemovedException e){
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void fetchMail(Person acc, String host)
	{
		System.out.println("Fetching mail.....\nPlease wait....");
		try
		{
			GetSessionObject(acc, host);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));			
		        Folder emailFolder = store.getFolder("INBOX");
		        emailFolder.open(Folder.READ_ONLY);
	
		        // retrieve the messages from the folder in an array and print it
		        Message[] messages = emailFolder.getMessages();
		        System.out.println("messages.length---" + messages.length);
	
		        for (int i = 0; i < messages.length; i++) {
		           
		           Message message = messages[i];
		           
		           File fmail = new File(FolderPart + "\\" + message.getMessageNumber() + " " +  message.getSentDate().getTime() + ".mail");
		           if (!fmail.exists())
		           {
		        	   System.out.println("File isn't existing");
				       PrintWriter in = new PrintWriter(fmail, "UTF-8");
			           in.println("---------------------------------");
			           
			           //Đọc từ danh sách file local lên tìm có mail ko nếu ko thì hãy tải (dò theo numberMessage
			           
			           int type = writePart(message, in); //Xét xem là kiểu message gì
			           if (type == 2) //Nếu multipart thì vào đây (Attachment)
			           {
			        	  String attachFiles = "", messageContent = "";
			        	  Multipart multimsg = (Multipart)message.getContent();
			        	  int numberOfPart = multimsg.getCount();
			        	  for (int CountPart = 0; CountPart < numberOfPart; CountPart++)
			        	  {
			        		  MimeBodyPart Part = (MimeBodyPart) multimsg.getBodyPart(CountPart);
			        		  if (Part.ATTACHMENT.equalsIgnoreCase(Part.getDisposition()) || Part.getContentType().contains("IMAGE/"))	// Kiểm tra xem có phải là Attachment hay ko để tải về
			        		  {
			        			  String fileName = Part.getFileName();
			        			  String temp = "" + message.getMessageNumber() + " " + message.getSentDate().getTime();
			        			  File attachfolder = new File(FolderPart + "\\" + temp);
			        			  attachfolder.mkdirs();
			        			  Part.saveFile(FolderPart + "\\" + temp + "\\" + File.separator + fileName);
			        		  }
			        		  else
			        		  {
			        			  // Phần chứa nội dung file
			        			  messageContent = Part.getContent().toString();
			        		  }
			        	  }
			        	  if (attachFiles.length() > 1)
			        	  {
			        		  attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
			        	  }
			           }
			           
			           in.close();
		           }
		           else
		           {
		        	   System.out.println("File is existing");
		           }
		           
		           // Hỏi xem có tiếp tục tải hay là thoát đi ngủ
		           String line = reader.readLine();
		           if ("YES".equals(line)) {
		              message.writeTo(System.out);
		           } else if ("QUIT".equals(line)) {
		              break;
		           }
	        	}
		        reader.close();
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
	
	public static void DeleteLocal(Person acc)
	{
		String PathfolderUser = getCurrentDirectory();
		File Folder = new File(PathfolderUser + "\\users\\" + SeparatorUsername(acc.username));
		File[] list = Folder.listFiles();
		File[] listOfFiles = new File[list.length];
		File[] listOfFolders = new File[list.length];
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int length = listOfFiles.length, numFiles = 0, k = 0;
		for (int i = 0; i < length; i++)
		{
			if (list[i].isFile())
			{
				listOfFiles[numFiles] = list[i];
				numFiles++;
			}
			else
			{
				listOfFolders[k] = list[i];
				k++;
			}
		}
		try {
			String ans = "C";
			while ("C".equals(ans) || "c".equals(ans))
			{
				System.out.print("Input index of message you want to delete: ");
				int index = Integer.parseInt(input.readLine());
				FileReader fread = new FileReader(listOfFiles[index].getPath());
				BufferedReader instr = new BufferedReader(fread);
				String line;
				while ((line = instr.readLine()) != null)
				{
					System.out.println(line);
				}
				
				System.out.print("Do you want to delete this file [y/n]: ");
				ans = input.readLine();
				if ("Y".equals(ans) || "y".equals(ans))
				{
					instr.close();
					fread.close();
					for (int i = 0; i < k; i++)
					{
						System.out.println(listOfFiles[index].getName().substring(0, listOfFiles[index].getName().indexOf('.')));
						System.out.println(listOfFolders[i].getName());
						if (listOfFolders[i].getName().equals(listOfFiles[index].getName().substring(0, listOfFiles[index].getName().indexOf('.'))))
						{
							File FolderAttachment = new File(listOfFolders[i].getPath());
							File[] listAttachment = FolderAttachment.listFiles();
							for (int numAttach = 0; numAttach < listAttachment.length; numAttach++)
							{
								listAttachment[numAttach].delete();
							}
							listOfFolders[i].delete();
							break;
						}
					}
					listOfFiles[index].delete();
				}
				
				System.out.print("Do you continue [c/n]: ");
				ans = input.readLine();
				instr.close();
				fread.close();
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void DeleteRemote(Person acc, String host)
	{
		Folder emailFolder;
		try {
			emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_WRITE);

	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	        // retrieve the messages from the folder in an array and print it
	        Message[] messages = emailFolder.getMessages();
	        //Nhận mail xong dò với number cần xóa nằm trong danh sách lưu hiện tại trên local máy
	        System.out.print("Input quantity message you want to delete: ");
	        int numDelete = Integer.parseInt(reader.readLine());
	        for (int i = 0; i < numDelete; i++)
	        {
		        System.out.print("Input id message you want to delete: ");
				int id = Integer.parseInt(reader.readLine());
				System.out.print("Do you want to delete this message [y/n]: ");
				String ans = reader.readLine();
				if ("Y".equals(ans) || "y".equals(ans))
				{
					messages[id - 1].setFlag(Flags.Flag.DELETED, true);
				}
				else if ("N".equals(ans) || "n".equals(ans))
					break;
	        }
	        
	        emailFolder.close(true);
	        store.close();
	        System.out.println("Deleted successful");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void DeleteMail(Person acc, String host)
	{
		GetSessionObject(acc, host);
		System.out.println("Lua chon phuong thuc xoa:");
		System.out.println(" 1. Xoa Local");
		System.out.println(" 2. Xoa Remote");
		System.out.println(" 3. Xoa ca hai");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int Choose = 0;
		try {
			Choose = Integer.parseInt(reader.readLine());
			switch (Choose)
			{
				case 1:
				{
					DeleteLocal(acc);
					break;
				}
				case 2:
				{
					DeleteRemote(acc, host);
					break;
				}
				case 3:
				{
					DeleteRemote(acc, host);
					DeleteLocal(acc);
					break;
				}
				default:
					{
						System.out.println("Error Syntax");
						break;
					}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkMail(Person acc, String host)
	{
		try 
		{
			System.out.println("Checking mail.....\nPlease wait....");
			Properties props = new Properties();
			props.put("mail." + IMAP + ".host", host);
			props.put("mail." + IMAP + ".port", "993");		
			props.put("mail." + IMAP + ".starttls.enable", "true");
			  
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
				System.out.println("Mail #" + (i + 1));
				System.out.println("From: " + message[i].getFrom()[0].toString());
				System.out.println("Subject: " + message[i].getSubject() + "\n");
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
				if (mailContent.cc.get(i) != null)
				{
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mailContent.cc.get(i)));
				}
			}
			
			//BCC
			for(int i=0; i < mailContent.bcc.size(); i++)
			{
				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailContent.bcc.get(i)));
			}
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
	   public static int writePart(Part p, PrintWriter in) throws Exception {
	      if (p instanceof Message)
	         //Call methos writeEnvelope
	         writeEnvelope((Message) p, in);
	      
	      System.out.println("----------------------------");
	      System.out.println("CONTENT-TYPE: " + p.getContentType());
	      if (p.getContentType().contains("name="))
	      {
	    	  in.println("Files Attachment: ");
	    	  String FileNameAttach = p.getContentType().substring(p.getContentType().indexOf("name=") + 5, p.getContentType().length());
	    	  in.println (FileNameAttach);
	      }
	      
	      //check if the content is plain text
	      if (p.isMimeType("text/plain")) {
	         in.println("This is plain text");
	         in.println("---------------------------");
	         in.println((String) p.getContent());
	         return 1;
	      } 
	      //check if the content has attachment
	      else if (p.isMimeType("multipart/*")) {
	    	 System.out.println("This is a Multipart");
	    	 System.out.println("---------------------------");
	         Multipart mp = (Multipart) p.getContent();
	         int count = mp.getCount();
	         for (int i = 0; i < count; i++)
	            writePart(mp.getBodyPart(i), in);
	         return 2;
	      } 
	      //check if the content is a nested message
	      else if (p.isMimeType("message/rfc822")) {
	    	 System.out.println("This is a Nested Message");
	         System.out.println("---------------------------");
	         writePart((Part) p.getContent(), in);
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
	   public static void writeEnvelope(Message m, PrintWriter in) throws Exception {
	      System.out.println("This is the message envelope");
	      System.out.println("---------------------------");
	      Address[] a;

	      // FROM
	      if ((a = m.getFrom()) != null) {
	         for (int j = 0; j < a.length; j++)
	         in.println("FROM: " + a[j].toString().substring(a[j].toString().indexOf('<') + 1, a[j].toString().length() - 1));
	      }

	      // TO
	      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
	         for (int j = 0; j < a.length; j++)
	         in.println("TO: " + a[j].toString());
	      }

	      // SUBJECT
	      if (m.getSubject() != null)
	         in.println("SUBJECT: " + m.getSubject() + "\n\n");
	      
	      // Sent day
	      if (m.getSentDate() != null)
	    	 in.println ("Sent day: " + m.getSentDate() + "\n\n");
	   }
}
