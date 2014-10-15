package guiComponents;

import entity.Brands;
import entity.Categories;
import entity.Products;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;

import java.awt.Insets;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class OperationsWithProducts extends JDialog {

	Session s = null;
	Transaction tx = null;
	Configuration cfg = null;
	SessionFactory factory = null;

	private Map<String, Categories> catMap = null;
	private Map<String, Brands> brandMap = null;
	Vector<String> categotyItems;
	Vector<String> brandItems;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldName;
	private JTextField textFieldQty;
	private JTextField textFieldPrice;
	private JComboBox<String> comboBoxBrand;
	private JComboBox<String> comboBoxCategory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OperationsWithProducts dialog = new OperationsWithProducts();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OperationsWithProducts() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				OperationsWithProducts.class.getResource("/Icons/Goods16.png")));
		setTitle("Products");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		loadDataInCombo();

		

		
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(OperationsWithProducts.class
					.getResource("/Icons/Goods32.png")));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 0;
			contentPanel.add(label, gbc_label);
		}
		{
			JLabel lblNewLabel = new JLabel("Add products");
			lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Name");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			textFieldName = new JTextField();
			textFieldName.setText("");
			textFieldName.addMouseListener(new ColorResetActionLiteneor(
					textFieldName));
			GridBagConstraints gbc_textFieldName = new GridBagConstraints();
			gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldName.anchor = GridBagConstraints.WEST;
			gbc_textFieldName.gridx = 2;
			gbc_textFieldName.gridy = 1;
			contentPanel.add(textFieldName, gbc_textFieldName);
			textFieldName.setColumns(20);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity");
			GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
			gbc_lblQuantity.anchor = GridBagConstraints.EAST;
			gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
			gbc_lblQuantity.gridx = 1;
			gbc_lblQuantity.gridy = 2;
			contentPanel.add(lblQuantity, gbc_lblQuantity);
		}
		{
			textFieldQty = new JTextField();
			textFieldQty
			.addMouseListener(new ColorResetActionLiteneor(textFieldQty));
			GridBagConstraints gbc_textFieldQty = new GridBagConstraints();
			gbc_textFieldQty.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldQty.anchor = GridBagConstraints.WEST;
			gbc_textFieldQty.gridx = 2;
			gbc_textFieldQty.gridy = 2;
			contentPanel.add(textFieldQty, gbc_textFieldQty);
			textFieldQty.setColumns(20);
		}
		{
			JLabel lblPrice = new JLabel("Price");
			GridBagConstraints gbc_lblPrice = new GridBagConstraints();
			gbc_lblPrice.anchor = GridBagConstraints.EAST;
			gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblPrice.gridx = 1;
			gbc_lblPrice.gridy = 3;
			contentPanel.add(lblPrice, gbc_lblPrice);
		}
		{
			textFieldPrice = new JTextField();
			textFieldPrice.addMouseListener(new ColorResetActionLiteneor(
					textFieldPrice));
			textFieldPrice.setColumns(20);
			GridBagConstraints gbc_textFieldPrice = new GridBagConstraints();
			gbc_textFieldPrice.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPrice.anchor = GridBagConstraints.WEST;
			gbc_textFieldPrice.gridx = 2;
			gbc_textFieldPrice.gridy = 3;
			contentPanel.add(textFieldPrice, gbc_textFieldPrice);
		}
		{
			JLabel lblBrand = new JLabel("Brand");
			GridBagConstraints gbc_lblBrand = new GridBagConstraints();
			gbc_lblBrand.anchor = GridBagConstraints.EAST;
			gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
			gbc_lblBrand.gridx = 1;
			gbc_lblBrand.gridy = 4;
			contentPanel.add(lblBrand, gbc_lblBrand);
		}
		{
			 comboBoxBrand = new JComboBox<String>();
			 comboBoxBrand.setBackground(Color.ORANGE);
			 comboBoxBrand.setMaximumRowCount(20);
			 GridBagConstraints gbc_comboBoxBrand = new GridBagConstraints();
			 gbc_comboBoxBrand.fill = GridBagConstraints.HORIZONTAL;
			 gbc_comboBoxBrand.insets = new Insets(0, 0, 5, 0);
			 gbc_comboBoxBrand.gridx = 2;
			 gbc_comboBoxBrand.gridy = 4;
			 contentPanel.add(comboBoxBrand, gbc_comboBoxBrand);
		}
		{
			JLabel lblCategory = new JLabel("Category");
			GridBagConstraints gbc_lblCategory = new GridBagConstraints();
			gbc_lblCategory.anchor = GridBagConstraints.EAST;
			gbc_lblCategory.insets = new Insets(0, 0, 0, 5);
			gbc_lblCategory.gridx = 1;
			gbc_lblCategory.gridy = 5;
			contentPanel.add(lblCategory, gbc_lblCategory);
		}
		{
			 comboBoxCategory = new JComboBox<String>();
			 comboBoxCategory.setBackground(Color.ORANGE);
			 GridBagConstraints gbc_comboBoxCategory = new
			 GridBagConstraints();
			 gbc_comboBoxCategory.fill = GridBagConstraints.HORIZONTAL;
			 gbc_comboBoxCategory.gridx = 2;
			 gbc_comboBoxCategory.gridy = 5;
			 contentPanel.add(comboBoxCategory, gbc_comboBoxCategory);
		}
		
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

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						saveProductInDB();
						
						 textFieldName.setText("");
						 textFieldPrice.setText("");
						 textFieldQty.setText("");
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						OperationsWithProducts.this.dispose();
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}

	}

	private void loadDataInCombo() {
		categotyItems = new Vector<String>();
		brandItems = new Vector<String>();

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
				brandItems.add(br.getName());
				brandMap.put(br.getName(), br);

			}
			query = s.createQuery("FROM Categories");
			results = query.list();
			res = results.iterator();

			while (res.hasNext()) {
				Categories ct = (Categories) res.next();
				categotyItems.add(ct.getName());
				catMap.put(ct.getName(), ct);

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

	}

	private void saveProductInDB() {

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
	

	private boolean checkCorrectInfo() {

		if (!textFieldPrice.getText().isEmpty()) {
			
			try {

				Double.parseDouble(textFieldPrice.getText());

			} catch (NumberFormatException ex) {

				JOptionPane
						.showMessageDialog(null,
								"Field price does not have correct data format!");
				textFieldPrice.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;

			}
			
		}
		if (!textFieldQty.getText().isEmpty()) {
			
			try {

				Double.parseDouble(textFieldQty.getText());

			} catch (NumberFormatException ex) {

				JOptionPane
						.showMessageDialog(null,
								"Field quantity does not have correct data format!");
				textFieldQty.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;

			}

		}
		
		try {

			if (textFieldName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter the name of the product!");
				textFieldName.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;
			}
			if (textFieldPrice.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"You did not enter any price of the product!");
				textFieldPrice.setBorder(BorderFactory
						.createLineBorder(Color.RED));
				return false;
			}

			if (textFieldQty.getText().isEmpty()) {
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
		return true;
	}
}
