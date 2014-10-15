package guiComponents;

import entity.Brands;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@SuppressWarnings({ "serial", "unused" })
public class AddEditBrand extends JDialog {
	private JTextField textFieldBrand;
	private Session s = null;
	private Transaction tx = null;
	private JComboBox<String> comboBox = null;
	private Vector<String> brandItems = null;
	private Map<String, Brands> brandMap = null;

	private Vector<String> brandItemsNew = null;
	private Map<String, Brands> brandMapNew = null;
	private JTextField tfBrand ;
	
	private String selectedBrand = "";
	/**
	 * Created by Marieta Karastoykova
	 */
	public AddEditBrand() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setTitle("Brands");
		setSize(500, 300);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddEditBrand.class
				.getResource("/Icons/medal.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);

		JLabel lblOperationsWithBrand = new JLabel("OPERATIONS WITH BRANDS");
		lblOperationsWithBrand.setFont(new Font("Traditional Arabic", Font.BOLD
				| Font.ITALIC, 18));
		GridBagConstraints gbc_lblOperationsWithBrand = new GridBagConstraints();
		gbc_lblOperationsWithBrand.gridwidth = 4;
		gbc_lblOperationsWithBrand.anchor = GridBagConstraints.WEST;
		gbc_lblOperationsWithBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblOperationsWithBrand.gridx = 2;
		gbc_lblOperationsWithBrand.gridy = 1;
		add(lblOperationsWithBrand, gbc_lblOperationsWithBrand);

		JLabel lblBrandName = new JLabel("Brand name");
		GridBagConstraints gbc_lblBrandName = new GridBagConstraints();
		gbc_lblBrandName.anchor = GridBagConstraints.EAST;
		gbc_lblBrandName.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrandName.gridx = 1;
		gbc_lblBrandName.gridy = 2;
		add(lblBrandName, gbc_lblBrandName);

		textFieldBrand = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textFieldBrand, gbc_textField);
		textFieldBrand.setColumns(20);

		

		loadBrandsFromDB();
		
	

		JButton btnAddNewBrand = new JButton("Add");
		btnAddNewBrand.setIcon(new ImageIcon(AddEditBrand.class
				.getResource("/Icons/document_add.png")));
		GridBagConstraints gbc_btnAddNewBrand = new GridBagConstraints();
		gbc_btnAddNewBrand.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddNewBrand.gridx = 5;
		gbc_btnAddNewBrand.gridy = 2;
		add(btnAddNewBrand, gbc_btnAddNewBrand);

		btnAddNewBrand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveBrand();				
				newLoadBrandsFromDB();
				

			}
		});

		JLabel lblChooseBrand = new JLabel("Choose brand");
		GridBagConstraints gbc_lblChooseBrand = new GridBagConstraints();
		gbc_lblChooseBrand.anchor = GridBagConstraints.EAST;
		gbc_lblChooseBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseBrand.gridx = 1;
		gbc_lblChooseBrand.gridy = 4;
		add(lblChooseBrand, gbc_lblChooseBrand);

		JButton btnEditBrand = new JButton("Edit");
		btnEditBrand.setIcon(new ImageIcon(AddEditBrand.class
				.getResource("/Icons/edit.png")));
		GridBagConstraints gbc_btnDeleteBrand = new GridBagConstraints();
		gbc_btnDeleteBrand.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteBrand.gridx = 5;
		gbc_btnDeleteBrand.gridy = 4;
		add(btnEditBrand, gbc_btnDeleteBrand);

		btnEditBrand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				final JDialog editBrand = new JDialog();
				editBrand.setLayout(new FlowLayout());
				editBrand.setSize(300, 150);
				editBrand.setTitle("Edit brand");
				editBrand.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				editBrand
						.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				JLabel newBrand = new JLabel("*Enter the new Brand: ");
				editBrand.add(newBrand, FlowLayout.LEFT);
				tfBrand = new JTextField(20);
				editBrand.add(tfBrand, FlowLayout.LEFT);
				editBrand.add(tfBrand);
				selectedBrand = (String)comboBox.getSelectedItem();
				tfBrand.setText(selectedBrand);
//				final String oldBrand = (String)comboBox.getSelectedItem();
				JButton ok = new JButton("Ok");
				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0){
						
						String br = tfBrand.getText();
						Brands brand = brandMap.get(selectedBrand);
						if (br.isEmpty()) {
							
							JOptionPane.showMessageDialog(null,
									"You did not enter a brand name!", "Error",
									JOptionPane.ERROR_MESSAGE);
							tfBrand.setBorder(BorderFactory.createLineBorder(Color.RED));
						}else{
							if(brand == null){
								JOptionPane.showMessageDialog(null,
										"You already updated this brand!", "Error",
										JOptionPane.ERROR_MESSAGE);
							}else{
								
								brand.setName(br);
								System.out.println(brand.getBrandId() + brand.getName());							
								updateBrand(brand);								
								
							}	
							newLoadBrandsFromDB();	
							editBrand.setVisible(false);		
							
												
						}
						
								
					}
				});
				editBrand.add(ok);
				editBrand.setVisible(true);

				
			}
			
			
		});

	}

	private void saveBrand() {

		Brands brand = new Brands();
		String br = textFieldBrand.getText().trim();
		if (br.equals("")) {

			JOptionPane.showMessageDialog(null,
					"You did not enter a brand name!", "Error",
					JOptionPane.ERROR_MESSAGE);
			textFieldBrand.setBorder(BorderFactory.createLineBorder(Color.RED));

		} else {
			brand.setName(br);
			textFieldBrand.setText("");
			try {

				s = GetConnectionFactory.getSessionFactory().openSession();
				tx = s.beginTransaction();
				s.save(brand);
				JOptionPane.showMessageDialog(null,
						"The brand is added successfully", "Information",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				tx.commit();
				s.close();
			}

		}

	}

	private void loadBrandsFromDB() {
		brandItems = new Vector<String>();
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
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}
		
		brandItems.sort(null);
		DefaultComboBoxModel<String> brandComboModel = new DefaultComboBoxModel<String>(
				brandItems);
		// brandComboModel.setSelectedItem(brandItems.elementAt(0));
		comboBox = new JComboBox<String>();
		comboBox.setModel(brandComboModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);

	}
	
	private void newLoadBrandsFromDB() {
		brandItemsNew = new Vector<String>();
		brandMapNew = new HashMap<String, Brands>();

		try {
			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			Query query = s.createQuery("FROM Brands");
			List<?> results = query.list();
			Iterator<?> res = results.iterator();
			while (res.hasNext()) {
				Brands br = (Brands) res.next();
				brandItemsNew.add((String) br.getName());
				brandMapNew.put((String) br.getName(), br);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}
		
		brandItemsNew.sort(null);
		DefaultComboBoxModel<String> brandComboModel = new DefaultComboBoxModel<String>(
				brandItemsNew);
		// brandComboModel.setSelectedItem(brandItems.elementAt(0));
//		comboBox = new JComboBox<String>(brandComboModel);
		comboBox.setModel(brandComboModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		add(comboBox, gbc_comboBox);

	}

	private void updateBrand(Brands brand) {


		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			s.update(brand);
			JOptionPane.showMessageDialog(null,
					"The brand is updated successfully", "Information",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

	}
}
