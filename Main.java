package mailClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.filechooser.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mailClient.MailClient.MailContent;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.TextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JTable table;
	private JTextField toField;
	private JTextField ccField;
	private JTextField bccField;
	private JTextField txtInbox;
	private JTable inbox;
	private static String username, password;
	private static boolean isRun = false;
	private static Message localMessage[];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Main frame = new Main();
					frame.setVisible(true);
					//frame.g
					
					//MailClient client = new MailClient();
					//client.
//					while (isRun == false)
//					{
//						Thread.sleep(1000);
//					}
					
					//String username = "", password = "";
					// Mốt dùng đừng xóa nhé
					/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Input username: ");
					username = reader.readLine();
					System.out.print("Input password: ");
					password = reader.readLine();*/
//					username = "ipmanxjackiechan@gmail.com";
//					password = "123a4567bcd";
					//frame.contentPane.
//					Person sender = new Person(username, password);
//					String host = MailClient.IMAP + ".gmail.com";
//					//System.out.println(getCurrentDirectory());
//					String foldername = MailClient.CreateFolderUser(MailClient.SeparatorUsername(sender.username));
//					MailClient.setPlaceDirectory(foldername);
//					System.out.println(foldername);
//					//DeleteMail(sender, host);
//					//DeleteLocal(sender);
//					//DeleteRemote(sender, host);
//					MailClient.fetchMail(sender, host);
				} 
				
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Mail Client - 1312084_1312134");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setToolTipText("Soạn Mail");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "So\u1EA1n Mail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(408, 296, 452, 375);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(161, 14, 21, 14);
		panel_1.add(lblTo);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCc = new JLabel("CC");
		lblCc.setBounds(161, 39, 21, 14);
		panel_1.add(lblCc);
		lblCc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblBcc = new JLabel("BCC");
		lblBcc.setBounds(155, 64, 27, 14);
		panel_1.add(lblBcc);
		lblBcc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		toField = new JTextField();
		toField.setBounds(192, 11, 214, 20);
		panel_1.add(toField);
		toField.setColumns(10);
		
		ccField = new JTextField();
		ccField.setBounds(192, 36, 214, 20);
		panel_1.add(ccField);
		ccField.setColumns(10);
		
		bccField = new JTextField();
		bccField.setBounds(192, 61, 214, 20);
		panel_1.add(bccField);
		bccField.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 89, 432, 227);
		panel_1.add(scrollPane_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_1);
		
		JButton button_1 = new JButton("Send");

		button_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		button_1.setBounds(35, 18, 89, 55);
		panel_1.add(button_1);
		
		JButton btnSign = new JButton("Sign");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSign.setBounds(321, 327, 95, 36);
		panel_1.add(btnSign);
		
		JButton btnEncryptAes = new JButton("Encrypt AES");

		btnEncryptAes.setBounds(73, 327, 109, 36);
		panel_1.add(btnEncryptAes);
		
		JButton btnEncryptRsa = new JButton("Encrypt RSA");

		btnEncryptRsa.setBounds(192, 328, 119, 36);
		panel_1.add(btnEncryptRsa);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Mail Box", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 378, 274);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblHpTh = new JLabel("Hộp thư");
		lblHpTh.setBounds(100, 8, 52, 14);
		panel_2.add(lblHpTh);
		lblHpTh.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtInbox = new JTextField();
		txtInbox.setBounds(173, 5, 86, 20);
		panel_2.add(txtInbox);
		txtInbox.setText("INBOX");
		txtInbox.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(10, 36, 358, 168);
		panel_2.add(scrollPane_3);
		
		inbox = new JTable();

		scrollPane_3.setViewportView(inbox);
		inbox.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mail ID", "From", "Subject"
			}
		));
		inbox.setColumnSelectionAllowed(true);
		inbox.setCellSelectionEnabled(true);
		
		JButton btnXa = new JButton("Local");
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnXa.setBounds(88, 240, 69, 23);
		panel_2.add(btnXa);
		
		JButton btnServer = new JButton("Server");
		btnServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnServer.setBounds(167, 240, 69, 23);
		panel_2.add(btnServer);
		
		JButton btnBoth = new JButton("Both");
		btnBoth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBoth.setBounds(246, 240, 69, 23);
		panel_2.add(btnBoth);
		
		JLabel lblXa = new JLabel("Xóa:");
		lblXa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXa.setBounds(47, 244, 24, 14);
		panel_2.add(lblXa);
		
		JButton btnRefresh = new JButton("Fetch Mail");

		btnRefresh.setBounds(68, 206, 106, 23);
		panel_2.add(btnRefresh);
		
		JButton btnCheckMail = new JButton("Check Mail");

		btnCheckMail.setBounds(202, 206, 101, 23);
		panel_2.add(btnCheckMail);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(79, 577, 248, 105);
		contentPane.add(panel);
		panel.setName("");
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setBounds(10, 14, 69, 14);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(89, 11, 150, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 42, 69, 14);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				username = new String(txtUsername.getText());
				password =  new String(passwordField.getPassword());
				if(username != null && password != null)
					isRun = true;
				else
					JOptionPane.showMessageDialog(panel, "Username and password is null....!");
			}
		});
		btnLogin.setBounds(76, 70, 78, 24);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(89, 42, 150, 20);
		panel.add(passwordField);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Key Contacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(398, 11, 462, 274);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDanhBLu = new JLabel("Danh bạ lưu key");
		lblDanhBLu.setBounds(197, 11, 101, 14);
		panel_4.add(lblDanhBLu);
		lblDanhBLu.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_4.setBounds(10, 30, 442, 102);
		panel_4.add(scrollPane_4);
		
		table = new JTable();
		scrollPane_4.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u0110\u1ECBa ch\u1EC9", "AES Key", "Public Key"
			}
		));
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExport.setBounds(20, 143, 65, 30);
		panel_4.add(btnExport);
		
		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImport.setBounds(20, 184, 65, 30);
		panel_4.add(btnImport);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBounds(20, 225, 65, 30);
		panel_4.add(btnSave);
		
		JButton btnNewButton = new JButton("Create RSA Key");

		btnNewButton.setBounds(95, 143, 109, 38);
		panel_4.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create AES Key");

		btnNewButton_1.setBounds(96, 221, 109, 38);
		panel_4.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(215, 143, 237, 120);
		panel_4.add(scrollPane);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane.setViewportView(textArea_2);
		textArea_2.setWrapStyleWord(true);
		textArea_2.setLineWrap(true);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Mail Content", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 296, 378, 274);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNiDungMail = new JLabel("Nội dung mail");
		lblNiDungMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNiDungMail.setBounds(148, 11, 83, 14);
		panel_3.add(lblNiDungMail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 36, 358, 193);
		panel_3.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnDecryptAes = new JButton("Decrypt AES");
		btnDecryptAes.setBounds(10, 240, 104, 23);
		panel_3.add(btnDecryptAes);
		
		JButton btnMixedDecrypt = new JButton("Mixed Decrypt");
		btnMixedDecrypt.setBounds(140, 240, 106, 23);
		panel_3.add(btnMixedDecrypt);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.setBounds(272, 240, 89, 23);
		panel_3.add(btnVerify);
		
		
		inbox.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) 
		    {
		    	String attachFiles = "";
		    	String messageContent = "";
		    	int i = inbox.getSelectedRow(); 
		        if (i > -1) 
		        {
		            // print first column value from selected row
		            System.out.println(inbox.getValueAt(inbox.getSelectedRow(), 0).toString());
		            System.out.println(inbox.getValueAt(inbox.getSelectedRow(), 1).toString());
		            System.out.println(inbox.getValueAt(inbox.getSelectedRow(), 2).toString());
		           
		            try
		            {
		        	  Multipart multimsg = (Multipart)localMessage[i].getContent();
		        	  //localMessage[i].getCon
		        	  int numberOfPart = multimsg.getCount();
		        	  //multimsg.get
		        	  for (int CountPart = 0; CountPart < numberOfPart; CountPart++)
		        	  {
		        		  System.out.println("Test 1");
		        		  MimeBodyPart Part = (MimeBodyPart) multimsg.getBodyPart(CountPart);
		        		  if (Part.ATTACHMENT.equalsIgnoreCase(Part.getDisposition()) || Part.getContentType().contains("IMAGE/"))	// Kiểm tra xem có phải là Attachment hay ko để tải về
		        		  {
		        			  System.out.println("Test 2");
		        			  String fileName = Part.getFileName();
		        			  String temp = "" + localMessage[i].getMessageNumber() + " " + localMessage[i].getSentDate().getTime();
		        			  File attachfolder = new File(MailClient.FolderPart + "\\" + temp);
		        			  attachfolder.mkdirs();
		        			  Part.saveFile(MailClient.FolderPart + "\\" + temp + "\\" + File.separator + fileName);
		        		  }
		        		  else
		        		  {
		        			  System.out.println("Test 3");
		        			  // Phần chứa nội dung file
		        			  messageContent = Part.getContent().toString();
		        		  }
		        		  System.out.println("Test 4");
		        	  }
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
		            
		            textArea.setText(messageContent);
		            
		        }
		    }
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				KeyPair keyPair = MyCrypto.createRSAKeyPair();
				String temp = new String();
				temp = "-----BEGIN RSA PUBLIC KEY-----\n" + MyCrypto.keyToString(keyPair.getPublic()) +
				          "\n-----END RSA PUBLIC KEY-----\n";
				
						
				temp += "-----BEGIN RSA PRIVATE KEY-----\n" + MyCrypto.keyToString(keyPair.getPrivate()) + 
						"\n-----END RSA PRIVATE KEY-----\n";
				textArea_2.setText(temp);
			}
		});
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Key key = MyCrypto.generateSecretKey("AES", 256);
				
				String temp = MyCrypto.keyToString(key);
				textArea_2.setText(temp);
			}
		});
		
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Person sender = new Person(username, password);
				String host = MailClient.IMAP + ".gmail.com";
				//System.out.println(getCurrentDirectory());
				String foldername = MailClient.CreateFolderUser(MailClient.SeparatorUsername(sender.username));
				MailClient.setPlaceDirectory(foldername);
				System.out.println(foldername);
				//DeleteMail(sender, host);
				//DeleteLocal(sender);
				//DeleteRemote(sender, host);
				MailClient.fetchMail(sender, host);
				
			}
		});
		
		btnCheckMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
					try 
					{
						String host = MailClient.IMAP + ".gmail.com";
						System.out.println("Checking mail.....\nPlease wait....");
						Properties props = new Properties();
						props.put("mail." + MailClient.IMAP + ".host", host);
						props.put("mail." + MailClient.IMAP + ".port", "993");		
						props.put("mail." + MailClient.IMAP + ".starttls.enable", "true");
						  
						Session session = Session.getDefaultInstance(props);
						Store store = session.getStore("pop3s");
						store.connect(host,username,password);
						
						//Lấy thư mục inbox từ account
						Folder folder = store.getFolder("INBOX");
						folder.open(Folder.READ_ONLY);
						
						DefaultTableModel model = (DefaultTableModel) inbox.getModel();
						
						
						// Duyệt thư mục xử lý mail
						Message message[] = folder.getMessages();
						//Luu lai trong bien local nay
						localMessage = folder.getMessages();
						for(int i=0; i < message.length; i++)
						{
							
							System.out.println("Mail #" + (i + 1));
							System.out.println("From: " + message[i].getFrom()[0]);
							System.out.println("Subject: " + message[i].getSubject() + "\n");
							model.addRow(new Object[]{i+1,
									message[i].getFrom()[0],message[i].getSubject()});
						
						}
				
						folder.close(false);
						store.close();

					} 
					catch (MessagingException e11) 
					{
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
				}
		});
		
		
		btnEncryptRsa.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String content = textArea_1.getText();
				KeyPair keyPair = MyCrypto.createRSAKeyPair();
				MyCrypto.exportRSAKeyPair(keyPair, "mailKeyPair");
				content = MyCrypto.encrypRSAMessage(keyPair.getPublic(), content);
				
				
				textArea_1.setText(content);
			}
		});
		
		btnEncryptAes.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String content = textArea_1.getText();
				Key key = MyCrypto.generateSecretKey("AES", 256);
				MyCrypto.exportSymmetricKey(key, "AES_key");
				
				content = MyCrypto.symEncryptMessage(MyCrypto.keyToString(key), MyCrypto.initVector, content);
				textArea_1.setText(content);
			}
		});
		
		
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				Person sender = new Person(username,password);
				
				MailClient.MailContent mailContent = new MailContent();

				if(toField != null)
					mailContent.recveivers = new Vector( Arrays.asList(toField.getText().toString().split(",")));
				if(ccField != null)
					mailContent.cc = new Vector( Arrays.asList(ccField.getText().toString().split(",")));
				if(bccField != null)
					mailContent.bcc = new Vector( Arrays.asList(bccField.getText().toString().split(",")));
				
				mailContent.setContent(textArea_1.getText());
				//to = Arrays.asList(.split)
				//mailContent.recveivers
				try {
					MailClient.sendMail(sender, mailContent);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		
	}
}
