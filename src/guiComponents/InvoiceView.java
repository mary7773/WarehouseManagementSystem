package guiComponents;

import entity.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import output.OutputFile;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class InvoiceView extends JPanel {
	
	private static String VAT = "20";

	Session s = null;
	Transaction tx = null;
	Configuration cfg = null;
	SessionFactory factory = null;

	private InvoiceTable model;
	private Invoice invoice;
	private List<Products> data;
	private Map<String, Users> user;
	private Map<String, Users> userClient;
	private Map<String, Products> pr;
	private int percent;

	// JFrame frmInoice;
	private JTable tableInvoice;
	private JTextField textFieldID;
	private JTextField ftTotal;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbCompany;
	// private List<Invoice> invoiceList;
	private JTextField tfQuantity;
	private JTextField tfTax;
	private JDateChooser dateChooser = null;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbCustomer = null;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbProduct = null;
	private JCheckBox chckbxVat = null;
	private JLabel lblTaxValue = null;

	/**
	 * Created by Marieta Karastoykova.
	 */
	public InvoiceView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	public void initialize() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 40, 40,
				0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
				1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0,
				0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		model = new InvoiceTable();
		data = new ArrayList<Products>();
		invoice = new Invoice();

		JLabel lblInvoice = new JLabel("INVOICE");
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblInvoice = new GridBagConstraints();
		gbc_lblInvoice.gridwidth = 6;
		gbc_lblInvoice.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblInvoice.insets = new Insets(0, 0, 5, 5);
		gbc_lblInvoice.gridx = 17;
		gbc_lblInvoice.gridy = 1;
		add(lblInvoice, gbc_lblInvoice);

		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.EAST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 2;
		gbc_lblDate.gridy = 3;
		add(lblDate, gbc_lblDate);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 10;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 3;
		add(dateChooser, gbc_dateChooser);

		JLabel lblInvoiceId = new JLabel("Invoice ID");
		GridBagConstraints gbc_lblInvoiceId = new GridBagConstraints();
		gbc_lblInvoiceId.anchor = GridBagConstraints.EAST;
		gbc_lblInvoiceId.insets = new Insets(0, 0, 5, 5);
		gbc_lblInvoiceId.gridx = 28;
		gbc_lblInvoiceId.gridy = 3;
		add(lblInvoiceId, gbc_lblInvoiceId);

		textFieldID = new JTextField();
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.gridwidth = 3;
		gbc_textFieldID.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 29;
		gbc_textFieldID.gridy = 3;
		add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(20);

		JLabel lblCompany = new JLabel("Customer");
		GridBagConstraints gbc_lblCompany = new GridBagConstraints();
		gbc_lblCompany.anchor = GridBagConstraints.EAST;
		gbc_lblCompany.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompany.gridx = 2;
		gbc_lblCompany.gridy = 4;
		add(lblCompany, gbc_lblCompany);

		cmbCustomer = new JComboBox();
		GridBagConstraints gbc_cmbCustomer = new GridBagConstraints();
		gbc_cmbCustomer.gridwidth = 10;
		gbc_cmbCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCustomer.gridx = 3;
		gbc_cmbCustomer.gridy = 4;
		add(cmbCustomer, gbc_cmbCustomer);

		JLabel lblCompany_1 = new JLabel("Company");
		GridBagConstraints gbc_lblCompany_1 = new GridBagConstraints();
		gbc_lblCompany_1.anchor = GridBagConstraints.EAST;
		gbc_lblCompany_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompany_1.gridx = 28;
		gbc_lblCompany_1.gridy = 4;
		add(lblCompany_1, gbc_lblCompany_1);

		cmbCompany = new JComboBox();
		GridBagConstraints gbc_cmbCompany = new GridBagConstraints();
		gbc_cmbCompany.gridwidth = 10;
		gbc_cmbCompany.insets = new Insets(0, 0, 5, 5);
		gbc_cmbCompany.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCompany.gridx = 29;
		gbc_cmbCompany.gridy = 4;
		add(cmbCompany, gbc_cmbCompany);

		JLabel lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.anchor = GridBagConstraints.EAST;
		gbc_lblProduct.gridx = 2;
		gbc_lblProduct.gridy = 5;
		add(lblProduct, gbc_lblProduct);

		cmbProduct = new JComboBox();
		GridBagConstraints gbc_cmbProduct = new GridBagConstraints();
		gbc_cmbProduct.gridwidth = 10;
		gbc_cmbProduct.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProduct.gridx = 3;
		gbc_cmbProduct.gridy = 5;
		add(cmbProduct, gbc_cmbProduct);

		JLabel lblQty = new JLabel("QTY");
		GridBagConstraints gbc_lblQty = new GridBagConstraints();
		gbc_lblQty.anchor = GridBagConstraints.EAST;
		gbc_lblQty.insets = new Insets(0, 0, 5, 5);
		gbc_lblQty.gridx = 28;
		gbc_lblQty.gridy = 5;
		add(lblQty, gbc_lblQty);

		tfQuantity = new JTextField();
		GridBagConstraints gbc_tfQuantity = new GridBagConstraints();
		gbc_tfQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_tfQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuantity.gridx = 29;
		gbc_tfQuantity.gridy = 5;
		add(tfQuantity, gbc_tfQuantity);
		tfQuantity.setColumns(20);

		JButton btnAdd = new JButton("Insert data into table ");
		btnAdd.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Add16.png")));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
//		gbc_btnAdd.gridwidth = 3;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 29;
		gbc_btnAdd.gridy = 6;
		add(btnAdd, gbc_btnAdd);

		loadData();

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (checkIfCorrect()) {

					Date date = new Date();
					date = dateChooser.getDate();
					int id = 0;
					int quantity = 0;

					if (date == null) {
						JOptionPane.showMessageDialog(null,
								"You did not choose date!");
						date = new Date();
						dateChooser.setDate(date);

					}
					invoice.setDateInvoice(date);

					quantity = Integer.parseInt(tfQuantity.getText());

					if (quantity < 0) {
						JOptionPane.showMessageDialog(null,
								"You entered wrong number!");
						tfQuantity.setText("");

					}
					
					id = Integer.parseInt(textFieldID.getText());
					if (id > 2000000000 || id < 0) {
						JOptionPane.showMessageDialog(null,
								"You entered wrong format!", "Error",
								JOptionPane.ERROR_MESSAGE);
						textFieldID.setText("");
					}
					invoice.setInvoiceNumber(id);
					
					String product = (String) cmbProduct.getSelectedItem();
					Products p = pr.get(product);
					p.setQuantity(quantity);
					
//					invoice.setQuantity(quantity);
					String com = (String) cmbCompany.getSelectedItem();
					String c = (String) cmbCustomer.getSelectedItem();
					Users company = user.get(com);
					Users customer = userClient.get(c);
										
					
//						invoice.setProduct(p);
//						invoice.addProduct(p);
						invoice.setCompany(company);
						invoice.setCustomer(customer);
						data.add(p);
						invoice.addProduct(p);
						insertIntoTable(data);
						tfQuantity.setText("");

				}			

			}

		});

		createTable();

		chckbxVat = new JCheckBox("VAT");
		GridBagConstraints gbc_chckbxVat = new GridBagConstraints();
		gbc_chckbxVat.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVat.gridx = 30;
		gbc_chckbxVat.gridy = 12;
		add(chckbxVat, gbc_chckbxVat);

		lblTaxValue = new JLabel("Tax value (%)");
		GridBagConstraints gbc_lblTaxValue = new GridBagConstraints();
		gbc_lblTaxValue.anchor = GridBagConstraints.EAST;
		gbc_lblTaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaxValue.gridx = 29;
		gbc_lblTaxValue.gridy = 13;
		add(lblTaxValue, gbc_lblTaxValue);

		tfTax = new JTextField();
		GridBagConstraints gbc_tfTax = new GridBagConstraints();
		gbc_tfTax.insets = new Insets(0, 0, 5, 5);
		gbc_tfTax.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTax.gridx = 30;
		gbc_tfTax.gridy = 13;
		add(tfTax, gbc_tfTax);
		tfTax.setColumns(10);

		lblTaxValue.setEnabled(false);
		tfTax.setEditable(false);
		tfTax.setText(VAT);
		chckbxVat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = chckbxVat.isSelected();

				lblTaxValue.setEnabled(isTicked);
				tfTax.setEditable(isTicked);
						

				percent = Integer.parseInt(tfTax.getText());
				if (percent > 100 || percent < 0) {

					JOptionPane.showMessageDialog(null,
							"The entered value is wrong format!");
					tfTax.setText(VAT);

				} else {
					invoice.setPercent(percent);

				}

			}
		});

		JButton btnPrint = new JButton("Print Pdf");
		btnPrint.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Print32.png")));
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrint.gridx = 8;
		gbc_btnPrint.gridy = 14;
		add(btnPrint, gbc_btnPrint);

		btnPrint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					printInvoice(invoice);
				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		});

		JButton btnCalculateTotal = new JButton("Calculate total");
		btnCalculateTotal.setIcon(new ImageIcon(InvoiceView.class
				.getResource("/Icons/Coin24.png")));
		GridBagConstraints gbc_btnCalculateTotal = new GridBagConstraints();
		gbc_btnCalculateTotal.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculateTotal.gridx = 29;
		gbc_btnCalculateTotal.gridy = 14;
		add(btnCalculateTotal, gbc_btnCalculateTotal);
		btnCalculateTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				double total = 0;
				for (Products pr : invoice.getProducts()) {
					total += pr.getPrice()*pr.getQuantity();
				}
				total = total + total*percent/100;
				invoice.setTotal(total);
				String totalString = total + "";
				ftTotal.setText(totalString);
				ftTotal.setEditable(false);

			}
		});

		ftTotal = new JTextField();
		GridBagConstraints gbc_ftTotal = new GridBagConstraints();
		gbc_ftTotal.insets = new Insets(0, 0, 5, 5);
		gbc_ftTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftTotal.gridx = 30;
		gbc_ftTotal.gridy = 14;
		add(ftTotal, gbc_ftTotal);
		ftTotal.setColumns(10);
	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		@SuppressWarnings("unused")
		InvoiceTable model = new InvoiceTable();

		Vector<String> productItems = new Vector<String>();
		pr = new HashMap<String, Products>();

		factory = GetConnectionFactory.getSessionFactory();
		s = factory.openSession();
		tx = s.beginTransaction();

		Query query = s.createQuery("FROM Products");
		List<?> results = query.list();
		Iterator<?> res = results.iterator();
		while (res.hasNext()) {
			Products prod = (Products) res.next();
			productItems.add((String) prod.getName());
			pr.put((String) prod.getName(), prod);

		}
		
		productItems.sort(null);
		DefaultComboBoxModel<String> modelProduct = new DefaultComboBoxModel<String>(
				productItems);
		cmbProduct.setModel(modelProduct);
		cmbProduct.setSelectedItem(modelProduct.getElementAt(0));

		Vector<String> userItems = new Vector<String>();
		
		userClient= new HashMap<String, Users>();
		Vector<String> clientItems = new Vector<String>();

		query = s.createQuery("FROM Users Where UserGroupID=3");
		results = query.list();
		res = results.iterator();
		while (res.hasNext()) {
			Users u = (Users) res.next();
			clientItems.add(u.getFirstName() + " " + u.getLastName());
			userClient.put(u.getFirstName() + " " + u.getLastName(), u);

		}

		clientItems.sort(null);
		DefaultComboBoxModel<String> modelUser = new DefaultComboBoxModel<String>(
				clientItems);
		cmbCustomer.setModel(modelUser);
//		cmbCustomer.setSelectedIndex(0);
		
		user = new HashMap<String, Users>();
		query = s.createQuery("FROM Users  Where UserGroupID=1");
		results = query.list();
		res = results.iterator();
		while (res.hasNext()) {
			Users u = (Users) res.next();
			userItems.add(u.getFirstName() + " " + u.getLastName());
			user.put(u.getFirstName() + " " + u.getLastName(), u);

		}
		userItems.sort(null);
		DefaultComboBoxModel<String> modelCompany = new DefaultComboBoxModel<String>(
				userItems);
		cmbCompany.setModel(modelCompany);
		 cmbCompany.setSelectedIndex(1);

	}

	private void createTable() {

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 30;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 7;
		add(scrollPane, gbc_scrollPane);

		tableInvoice = new JTable(model);
		scrollPane.setViewportView(tableInvoice);
		// tableInvoice.setModel(model);

	}

	@SuppressWarnings("static-access")
	private void printInvoice(Invoice in)
			throws IOException, Exception {

		OutputFile out = new OutputFile();

		String file = "c:/Temp/Invoice" +in.getInvoiceNumber() +".pdf";
		out.createPDF(file, in);

	}

	private void insertIntoTable(List<Products> data2) {

	
		model.updateRowInvoice(data2);
		model.fireTableDataChanged();

	}


	@SuppressWarnings("unused")
	private boolean checkIfCorrect() {

		try {
			Integer.parseInt(textFieldID.getText());
			Integer.parseInt(tfQuantity.getText());

		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(null,
							"Fields invoice id and/or quantity do not have correct data format!");
			textFieldID.setText("");
			tfQuantity.setText("");
			return false;
		}

		try {

			if (textFieldID.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter the invoice id!");
				return false;
			}
			if (tfQuantity.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter any quantity!");
				return false;
			}
		} catch (NullPointerException ex) {
			JOptionPane optionPane = new JOptionPane("Fields cannot be empty!",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}
