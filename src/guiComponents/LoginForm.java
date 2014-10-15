package guiComponents;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Users;

import java.awt.Font;
import java.util.List;

public class LoginForm {

	Session session;
	Transaction tx;
	Configuration cfg;
	SessionFactory factory;
	private JFrame frmLoginForm;
	private JTextField tfUserName;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLoginForm.setSize(400, 250);
					window.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Created by Marieta Karastoykova
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginForm = new JFrame();
		frmLoginForm.setIconImage(Toolkit.getDefaultToolkit().getImage(
				LoginForm.class.getResource("/Icons/User16.png")));
		frmLoginForm.setTitle("Warehouse system - Login");
		frmLoginForm.setBounds(100, 100, 384, 291);
		frmLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmLoginForm.getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginForm.class
				.getResource("/images/1393622690_Login.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 3;
		frmLoginForm.getContentPane().add(label, gbc_label);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		frmLoginForm.getContentPane().add(lblLogin, gbc_lblNewLabel);

		JLabel lblUserName = new JLabel("User name");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.gridx = 2;
		gbc_lblUserName.gridy = 5;
		frmLoginForm.getContentPane().add(lblUserName, gbc_lblUserName);

		tfUserName = new JTextField("Enter user name");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 5;
		frmLoginForm.getContentPane().add(tfUserName, gbc_textField_1);
		tfUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 6;
		frmLoginForm.getContentPane().add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField("Enter password");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 6;
		frmLoginForm.getContentPane().add(passwordField, gbc_passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(LoginForm.class
				.getResource("/images/1394491324_OK.png")));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 7;
		frmLoginForm.getContentPane().add(btnLogin, gbc_btnLogin);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName = tfUserName.getText();
				char[] passF = passwordField.getPassword();
				String pass = new String(passF);

				session = GetConnectionFactory.getSessionFactory()
						.openSession();
				tx = null;

				try {
					tx = session.beginTransaction();
					Query query = session
							.createQuery("from Users u where u.userName =:username");
					query.setParameter("username", userName);

					List<?> userList = query.list();
					Users user = null;
					try {
						user = (Users) userList.get(0);
						
						if (user.getPassword().equals(pass))

						{
							Boolean admin = false;
							if (1 == (user.getUsersGroups().getUsersGroupId())) {
								admin = true;
							}
							MainFrame main = new MainFrame();
							main.initialize(admin);
							frmLoginForm.dispose();

						}
					} catch (Exception e) {

						JOptionPane.showMessageDialog(null,
								"User name and/or password is not correct!",
								"Error", JOptionPane.ERROR_MESSAGE);
						tfUserName.setText("Enter user name");
						passwordField.setText("Enter password");

					}
				

				} catch (HibernateException e) {
					if (tx != null)
						tx.rollback();
					e.printStackTrace();
				} finally {
					session.close();
				}

			}

		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(LoginForm.class
				.getResource("/images/1394491235_No.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 7;
		frmLoginForm.getContentPane().add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}
}
