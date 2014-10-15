package guiComponents;

import entity.Brands;
import entity.Categories;
import entity.Products;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EditProduct extends JDialog {

	static Session s = null;
	static Transaction tx = null;
	Configuration cfg = null;
	static SessionFactory factory = null;

	private static String name;
	private static int categoryId;
	private static int brandId;
	private static double price;
	private static double quantity;
	private static int id;

	private JFrame frmEditProduct;
	private static JTextField textFieldName;
	private static JTextField textFieldQuantity;
	private static JTextField textFieldPrice;
	private static JComboBox<String> brandCombo;
	private static JComboBox<String> categoryCombo;
	static DefaultComboBoxModel<String> catComboModel;
	static DefaultComboBoxModel<String> brandComboModel;

	static Map<String, Categories> catMap;
	static Map<String, Brands> brandMap;

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditProduct window = new EditProduct();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Created by Marieta Karastoykova
	 */
	public EditProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frmEditProduct = new JFrame();
		setTitle("Edit Product");
		setSize(250, 250);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				EditProduct.class.getResource("/Icons/Edit24.png")));
		// setBounds(100, 100, 215, 228);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.anchor = GridBagConstraints.WEST;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 2;
		add(lblQuantity, gbc_lblQuantity);

		textFieldQuantity = new JTextField();
		GridBagConstraints gbc_textFieldQuantity = new GridBagConstraints();
		gbc_textFieldQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQuantity.anchor = GridBagConstraints.WEST;
		gbc_textFieldQuantity.gridx = 1;
		gbc_textFieldQuantity.gridy = 2;
		add(textFieldQuantity, gbc_textFieldQuantity);
		textFieldQuantity.setColumns(10);

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 3;
		add(lblPrice, gbc_lblPrice);

		textFieldPrice = new JTextField();
		GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
		gbc_textFieldPrice.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPrice.anchor = GridBagConstraints.WEST;
		gbc_textFieldPrice.gridx = 1;
		gbc_textFieldPrice.gridy = 3;
		add(textFieldPrice, gbc_textFieldPrice);
		textFieldPrice.setColumns(10);

		JLabel lblCategory = new JLabel("Category");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.EAST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 0;
		gbc_lblCategory.gridy = 4;
		add(lblCategory, gbc_lblCategory);

		loadCombo();

		categoryCombo = new JComboBox<String>(catComboModel);
		categoryCombo.setMaximumRowCount(15);
		GridBagConstraints gbc_comboBoxCategory = new GridBagConstraints();
		gbc_comboBoxCategory.anchor = GridBagConstraints.WEST;
		gbc_comboBoxCategory.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxCategory.gridx = 1;
		gbc_comboBoxCategory.gridy = 4;
		add(categoryCombo, gbc_comboBoxCategory);

		JLabel lblBrand = new JLabel("Brand");
		GridBagConstraints gbc_lblBrand = new GridBagConstraints();
		gbc_lblBrand.anchor = GridBagConstraints.EAST;
		gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrand.gridx = 0;
		gbc_lblBrand.gridy = 5;
		add(lblBrand, gbc_lblBrand);

		brandCombo = new JComboBox<String>(brandComboModel);
		brandCombo.setMaximumRowCount(15);
		GridBagConstraints gbc_comboBoxBrand = new GridBagConstraints();
		gbc_comboBoxBrand.anchor = GridBagConstraints.WEST;
		gbc_comboBoxBrand.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxBrand.gridx = 1;
		gbc_comboBoxBrand.gridy = 5;
		add(brandCombo, gbc_comboBoxBrand);

		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 6;
		add(btnSave, gbc_btnSave);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateProduct();
				EditProduct.this.dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 6;
		add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
				EditProduct.this.dispose();
			}
		});
	}

	public static void loadCombo() {

		Vector<String> categotyItems = new Vector<String>();
		Vector<String> brandItems = new Vector<String>();

		catMap = new HashMap<String, Categories>();
		brandMap = new HashMap<String, Brands>();

		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			Query query = s.createQuery("FROM Brands");
			List<?> results = query.list();
			Iterator<?> res = results.iterator();
			while (res.hasNext()) {
				Brands br = (Brands) res.next();
				brandItems.add((String) br.getName());
				brandMap.put((String) br.getName(), br);

			}
			query = s.createQuery("FROM Categories");
			results = query.list();
			res = results.iterator();

			while (res.hasNext()) {
				Categories ct = (Categories) res.next();
				categotyItems.add((String) ct.getName());
				catMap.put((String) ct.getName(), ct);

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

		catComboModel = new DefaultComboBoxModel<String>(categotyItems);
		catComboModel.setSelectedItem(null);

		brandComboModel = new DefaultComboBoxModel<String>(brandItems);
		brandComboModel.setSelectedItem(null);
	}

	public void setFields(Products p) {

		id = p.getProductId();
		name = (String) p.getName();
		price = p.getPrice();
		quantity = p.getQuantity();

		textFieldName.setText(name);
		textFieldPrice.setText(price + "");
		textFieldQuantity.setText(quantity + "");

	}

	public static void getValue() {

		name = textFieldName.getText();
		price = Double.parseDouble(textFieldPrice.getText());
		quantity = Double.parseDouble(textFieldQuantity.getText());

	}

	public static void updateProduct() {

		Query query;
		try {
			factory = GetConnectionFactory.getSessionFactory();
			s = factory.openSession();
			
			getValue();
			Categories cat = catMap.get(categoryCombo.getSelectedItem());
			Brands br = brandMap.get(brandCombo.getSelectedItem());	
			Products p = new Products();
			p.setProductId(id);
			p.setName(name);
			p.setBrands(br);
			p.setCategories(cat);
			p.setPrice(price);
			p.setQuantity(quantity);
			
			tx = s.beginTransaction();
			s.update(p);
			tx.commit();
			
			System.out.println(name + id);
		
			JOptionPane.showMessageDialog(null,
					"The product is successfully updated!!!");
		} catch (HibernateException e) {
			JOptionPane.showMessageDialog(null,
					"Error!!! The product did not update!");

		} finally {
			s.close();
		}

	}

}
