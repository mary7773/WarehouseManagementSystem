package guiComponents;

import entity.Brands;
import entity.Categories;
import entity.Products;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("serial")
public class AddEditProduct extends JPanel {
	Session s = null;
	Transaction tx = null;
	Configuration cfg = null;
	SessionFactory factory = null;

	private List<Products> database = null;
	private ProductTable model = null;
	private JTextField textFieldName;
	private JTextField textFieldQty;
	private JTextField textFieldPrice;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox<String> comboBoxBrand = null;
	private JComboBox<String> comboBoxCategory = null;
	private Map<String, Categories> catMap = null;
	private Map<String, Brands> brandMap = null;

	/**
	 * Create the panel.
	 */
	public AddEditProduct() {
		database = new ArrayList<Products>();
		model = new ProductTable();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddEditProduct.class
				.getResource("/Icons/Goods32.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		add(label, gbc_label);

		createTable(model);
		JLabel lblOperationsWithProducts = new JLabel(
				"OPERATIONS WITH PRODUCTS");
		lblOperationsWithProducts.setFont(new Font("Traditional Arabic",
				Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblOperationsWithProducts = new GridBagConstraints();
		gbc_lblOperationsWithProducts.insets = new Insets(0, 0, 5, 5);
		gbc_lblOperationsWithProducts.anchor = GridBagConstraints.WEST;
		gbc_lblOperationsWithProducts.gridwidth = 2;
		gbc_lblOperationsWithProducts.gridx = 3;
		gbc_lblOperationsWithProducts.gridy = 1;
		add(lblOperationsWithProducts, gbc_lblOperationsWithProducts);

		JLabel lblNameOfThe = new JLabel("Name of the product");
		GridBagConstraints gbc_lblNameOfThe = new GridBagConstraints();
		gbc_lblNameOfThe.anchor = GridBagConstraints.EAST;
		gbc_lblNameOfThe.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameOfThe.gridx = 3;
		gbc_lblNameOfThe.gridy = 3;
		add(lblNameOfThe, gbc_lblNameOfThe);

		textFieldName = new JTextField();
		textFieldName.addMouseListener(new ColorResetActionLiteneor(
				textFieldName));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		add(textFieldName, gbc_textField);
		textFieldName.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 3;
		gbc_lblQuantity.gridy = 4;
		add(lblQuantity, gbc_lblQuantity);

		textFieldQty = new JTextField();
		textFieldQty
				.addMouseListener(new ColorResetActionLiteneor(textFieldQty));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 4;
		add(textFieldQty, gbc_textField_1);
		textFieldQty.setColumns(20);

		JLabel lblCategory = new JLabel("Category");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.anchor = GridBagConstraints.EAST;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 3;
		gbc_lblCategory.gridy = 6;
		add(lblCategory, gbc_lblCategory);

		JLabel lblBrand = new JLabel("Brand");
		GridBagConstraints gbc_lblBrand = new GridBagConstraints();
		gbc_lblBrand.anchor = GridBagConstraints.EAST;
		gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrand.gridx = 3;
		gbc_lblBrand.gridy = 7;
		add(lblBrand, gbc_lblBrand);

		loadDataInCombo();

		JLabel lblPrice = new JLabel("Price");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 3;
		gbc_lblPrice.gridy = 5;
		add(lblPrice, gbc_lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.addMouseListener(new ColorResetActionLiteneor(
				textFieldPrice));
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 4;
		gbc_textField_4.gridy = 5;
		add(textFieldPrice, gbc_textField_4);
		textFieldPrice.setColumns(10);

		JButton btnSave = new JButton("Add      ");
		btnSave.setIcon(new ImageIcon(AddEditProduct.class
				.getResource("/Icons/document_add.png")));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 0);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 8;
		add(btnSave, gbc_btnSave);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				insertIntoTable();
				createTable(model);

				textFieldName.setText("");
				textFieldPrice.setText("");
				textFieldQty.setText("");
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon(AddEditProduct.class
				.getResource("/Icons/zoom_icon.png")));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 0, 0);
		gbc_btnDelete.gridx = 6;
		gbc_btnDelete.gridy = 8;
		add(btnSearch, gbc_btnDelete);

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// removeSelectedFromTable(table);

				SearchProduct search = new SearchProduct();
				JDialog d = search.initialize();
				d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				d.pack();
				d.setVisible(true);

			}
		});

	}

	private void loadDataInCombo() {
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

		comboBoxBrand = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 4;
		gbc_comboBox_1.gridy = 7;
		add(comboBoxBrand, gbc_comboBox_1);

		comboBoxCategory = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 6;
		add(comboBoxCategory, gbc_comboBox);
		
		categotyItems.sort(null);

		DefaultComboBoxModel<String> catComboModel = new DefaultComboBoxModel<String>(
				categotyItems);
		// catComboModel.setSelectedItem(categotyItems.elementAt(0));
		comboBoxCategory.setModel(catComboModel);

		brandItems.sort(null);
		DefaultComboBoxModel<String> brandComboModel = new DefaultComboBoxModel<String>(
				brandItems);
		// brandComboModel.setSelectedItem(brandItems.elementAt(0));
		comboBoxBrand.setModel(brandComboModel);

	}

	@SuppressWarnings("unchecked")
	private void createTable(ProductTable model) {

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollPane.gridx = 5;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		try {
			factory = GetConnectionFactory.getSessionFactory();
			s = factory.openSession();

			Query q = s.createQuery("from Products");
			model.rowData = new ArrayList<Products>(q.list());

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {

			// s.close();
		}
		// model.updateRow(database);
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane.setViewportView(table);

	}

	private Products saveProductInDB() {

		final Products newProduct = new Products();

		if (checkCorrectInfo()) {
			newProduct.setName(textFieldName.getText());
			newProduct.setPrice(Double.parseDouble(textFieldPrice.getText()));
			newProduct.setQuantity(Double.parseDouble(textFieldQty.getText()));

			Categories cat = new Categories();

			if (comboBoxCategory.getItemCount() != 0
					&& comboBoxCategory.getSelectedItem() != null) {

				cat.setCategoryId(catMap
						.get(comboBoxCategory.getSelectedItem())
						.getCategoryId());
				newProduct.setCategories(cat);

			}
			// add brand
			Brands brand = new Brands();

			if (comboBoxBrand.getItemCount() != 0
					&& comboBoxBrand.getSelectedItem() != null) {

				brand.setBrandId(brandMap.get(comboBoxBrand.getSelectedItem())
						.getBrandId());
				newProduct.setBrands(brand);

			}

			try {
				s = GetConnectionFactory.getSessionFactory().openSession();

				tx = s.beginTransaction();
				s.save(newProduct);

				okMeesage("Save successful");
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				tx.commit();
				s.close();

			}

		}
		if (!checkCorrectInfo()) {
			errorMessage("Product is not saved in database!");
		}
		return newProduct;
	}

	private void okMeesage(String message) {

		JOptionPane optionPane = new JOptionPane(message,
				JOptionPane.INFORMATION_MESSAGE);
		JDialog dialog = optionPane
				.createDialog("Successfully added product in database!");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);

	}

	public void errorMessage(String message) {
		JOptionPane optionPane = new JOptionPane(message,
				JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Failure");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	@SuppressWarnings("unused")
	private boolean checkCorrectInfo() {

		try {

			if (textFieldName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter the name of the product!");
				textFieldName.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;
			} else if (textFieldPrice.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter any price of the product!");
				textFieldPrice.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;
			}

			else if (textFieldQty.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter any quantity of the product!");
				textFieldQty.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;

			}

		} catch (NullPointerException ex) {
			JOptionPane optionPane = new JOptionPane("Fields cannot be empty!",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {

			Double.parseDouble(textFieldPrice.getText());

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(null,
					"Field price does not have correct data format!");
			textFieldPrice.setBorder(BorderFactory.createLineBorder(Color.RED));

			return false;

		}

		try {

			Double.parseDouble(textFieldQty.getText());

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(null,
					"Field quantity does not have correct data format!");
			textFieldQty.setBorder(BorderFactory.createLineBorder(Color.RED));

			return false;

		}

		return true;
	}

	private void insertIntoTable() {
		database.add(saveProductInDB());
		model.updateRow(database);
		model.fireTableDataChanged();
	}

}