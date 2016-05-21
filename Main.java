package mailClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.filechooser.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;


public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtInbox;
	private JTable table_1;
	private static String username, password;
	private static boolean isRun = false;

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
//						Thread.sleep(100);
//					}
//					
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
		setBounds(100, 100, 823, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setToolTipText("Soạn Mail");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "So\u1EA1n Mail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(398, 296, 409, 375);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(125, 11, 21, 14);
		panel_1.add(lblTo);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCc = new JLabel("CC");
		lblCc.setBounds(125, 36, 21, 14);
		panel_1.add(lblCc);
		lblCc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblBcc = new JLabel("BCC");
		lblBcc.setBounds(119, 61, 27, 14);
		panel_1.add(lblBcc);
		lblBcc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setBounds(156, 8, 243, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(156, 33, 243, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(156, 58, 243, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 92, 389, 227);
		panel_1.add(textArea_1);
		
		JButton button_1 = new JButton("Send");
		button_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		button_1.setBounds(26, 22, 89, 53);
		panel_1.add(button_1);
		
		JButton btnSign = new JButton("Sign");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSign.setBounds(252, 330, 74, 20);
		panel_1.add(btnSign);
		
		JButton btnEncryptAes = new JButton("Encrypt AES");
		btnEncryptAes.setBounds(20, 330, 101, 20);
		panel_1.add(btnEncryptAes);
		
		JButton btnEncryptRsa = new JButton("Mixed Encrypt");
		btnEncryptRsa.setBounds(136, 330, 101, 20);
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
		
		table_1 = new JTable();
		table_1.setBounds(10, 36, 358, 168);
		panel_2.add(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"From", "Subject"
			}
		));
		table_1.setEnabled(false);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		
		JButton btnXa = new JButton("Local");
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnXa.setBounds(70, 240, 69, 23);
		panel_2.add(btnXa);
		
		JButton btnServer = new JButton("Server");
		btnServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnServer.setBounds(150, 240, 69, 23);
		panel_2.add(btnServer);
		
		JButton btnBoth = new JButton("Both");
		btnBoth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBoth.setBounds(229, 240, 69, 23);
		panel_2.add(btnBoth);
		
		JLabel lblXa = new JLabel("Xóa");
		lblXa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXa.setBounds(173, 215, 24, 14);
		panel_2.add(lblXa);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u0110\u0103ng nh\u1EADp", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 577, 360, 105);
		contentPane.add(panel);
		panel.setName("Đăng nhập");
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setBounds(84, 14, 69, 14);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(163, 11, 187, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(84, 39, 69, 14);
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
		btnLogin.setBounds(134, 70, 78, 24);
		panel.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 39, 187, 20);
		panel.add(passwordField);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Key Contacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(398, 11, 409, 274);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDanhBLu = new JLabel("Danh bạ lưu key");
		lblDanhBLu.setBounds(135, 11, 101, 14);
		panel_4.add(lblDanhBLu);
		lblDanhBLu.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		table = new JTable();
		table.setBounds(10, 30, 389, 184);
		panel_4.add(table);
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
		btnExport.setBounds(39, 225, 111, 38);
		panel_4.add(btnExport);
		
		JButton btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImport.setBounds(160, 225, 101, 38);
		panel_4.add(btnImport);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBounds(280, 225, 101, 38);
		panel_4.add(btnSave);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Mail Content", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 296, 378, 274);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNiDungMail = new JLabel("Nội dung mail");
		lblNiDungMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNiDungMail.setBounds(148, 11, 83, 14);
		panel_3.add(lblNiDungMail);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 36, 358, 193);
		panel_3.add(textArea);
		
		JButton btnDecryptAes = new JButton("Decrypt AES");
		btnDecryptAes.setBounds(10, 240, 104, 23);
		panel_3.add(btnDecryptAes);
		
		JButton btnMixedDecrypt = new JButton("Mixed Decrypt");
		btnMixedDecrypt.setBounds(140, 240, 106, 23);
		panel_3.add(btnMixedDecrypt);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.setBounds(272, 240, 89, 23);
		panel_3.add(btnVerify);
	}
}
