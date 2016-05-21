import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.TextArea;
import javax.swing.Box;
import javax.swing.JTextArea;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 259, 109);
		panel.setName("Đăng nhập");
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setBounds(10, 14, 69, 14);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtUsername = new JTextField();
		txtUsername.setBounds(89, 11, 160, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 45, 59, 14);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setBounds(89, 75, 69, 23);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(89, 44, 160, 20);
		panel.add(passwordField);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(364, 203, 1, 1);
		contentPane.add(verticalBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(337, 380, 257, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(28, 11, 21, 14);
		panel_1.add(lblTo);
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblCc = new JLabel("CC");
		lblCc.setBounds(28, 36, 21, 14);
		panel_1.add(lblCc);
		lblCc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblBcc = new JLabel("BCC");
		lblBcc.setBounds(28, 61, 27, 14);
		panel_1.add(lblBcc);
		lblBcc.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textField = new JTextField();
		textField.setBounds(54, 11, 177, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(54, 36, 177, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(54, 61, 177, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 92, 237, 144);
		panel_1.add(textArea_1);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(79, 247, 89, 23);
		panel_1.add(btnSend);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 131, 259, 274);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblHpTh = new JLabel("Hộp thư");
		lblHpTh.setBounds(38, 11, 52, 14);
		panel_2.add(lblHpTh);
		lblHpTh.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtInbox = new JTextField();
		txtInbox.setBounds(111, 8, 86, 20);
		panel_2.add(txtInbox);
		txtInbox.setText("INBOX");
		txtInbox.setColumns(10);
		
		table_1 = new JTable();
		table_1.setBounds(10, 36, 239, 168);
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
		btnXa.setBounds(21, 240, 69, 23);
		panel_2.add(btnXa);
		
		JButton btnServer = new JButton("Server");
		btnServer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnServer.setBounds(101, 240, 69, 23);
		panel_2.add(btnServer);
		
		JButton btnBoth = new JButton("Both");
		btnBoth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBoth.setBounds(180, 240, 69, 23);
		panel_2.add(btnBoth);
		
		JLabel lblXa = new JLabel("Xóa");
		lblXa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXa.setBounds(124, 215, 24, 14);
		panel_2.add(lblXa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(281, 11, 316, 358);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNiDungMail = new JLabel("Nội dung mail");
		lblNiDungMail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNiDungMail.setBounds(125, 11, 83, 14);
		panel_3.add(lblNiDungMail);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(17, 36, 289, 311);
		panel_3.add(textArea);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 416, 327, 238);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDanhBLu = new JLabel("Danh bạ lưu key");
		lblDanhBLu.setBounds(111, 11, 101, 14);
		panel_4.add(lblDanhBLu);
		lblDanhBLu.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		table = new JTable();
		table.setBounds(10, 30, 307, 229);
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
	}
}
