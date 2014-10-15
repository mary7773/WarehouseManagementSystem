package guiComponents;

import entity.Categories;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@SuppressWarnings("serial")
public class AddEditCategory extends JDialog {

	private Session s = null;
	private Transaction tx = null;

	// private JFrame frmCategory;
	private JTextField tfCategory;
	private JComboBox<String> comboCategory;
	private DefaultComboBoxModel<String> catModel;
	private Vector<String> category;
	private Map<String, Categories> mapCat;

	private Vector<String> categoryNew;
	private Map<String, Categories> mapCatNew;
	private String oldCat = "";
	private JTextField tfCategory1;

	/**
	 * Created by Marieta Karastoykova.
	 */

	public AddEditCategory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		setTitle("Category");
		setSize(500, 200);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AddEditCategory.class
				.getResource("/Icons/category.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		add(label, gbc_label);

		JLabel lblCategoryOperations = new JLabel("Category operations");
		lblCategoryOperations.setFont(new Font("TmsStrokA", Font.BOLD
				| Font.ITALIC, 18));
		GridBagConstraints gbc_lblCategoryOperations = new GridBagConstraints();
		gbc_lblCategoryOperations.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryOperations.gridx = 2;
		gbc_lblCategoryOperations.gridy = 2;
		add(lblCategoryOperations, gbc_lblCategoryOperations);

		JLabel lblCategoryName = new JLabel("Category name");
		GridBagConstraints gbc_lblCategoryName = new GridBagConstraints();
		gbc_lblCategoryName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoryName.anchor = GridBagConstraints.EAST;
		gbc_lblCategoryName.gridx = 1;
		gbc_lblCategoryName.gridy = 4;
		add(lblCategoryName, gbc_lblCategoryName);

		tfCategory = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 4;
		add(tfCategory, gbc_textField);
		tfCategory.setColumns(20);

		uploadCategory();

		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(AddEditCategory.class
				.getResource("/Icons/document_add.png")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 4;
		add(btnAdd, gbc_btnNewButton);

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addCategory();
				newUploadCategory();
				categoryNew.sort(null);
				catModel = new DefaultComboBoxModel<String>(categoryNew);
				comboCategory.setModel(catModel);
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.gridx = 2;
				gbc_comboBox.gridy = 5;
				add(comboCategory, gbc_comboBox);

				tfCategory.setText("");

			}

		});

		JLabel lblChooseCategory = new JLabel("Choose category");
		GridBagConstraints gbc_lblChooseCategory = new GridBagConstraints();
		gbc_lblChooseCategory.anchor = GridBagConstraints.EAST;
		gbc_lblChooseCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseCategory.gridx = 1;
		gbc_lblChooseCategory.gridy = 5;
		add(lblChooseCategory, gbc_lblChooseCategory);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setIcon(new ImageIcon(AddEditCategory.class
				.getResource("/Icons/edit.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 5;
		add(btnEdit, gbc_btnNewButton_1);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				editCategory();

			}

		});
	}

	private void editCategory() {
		final JDialog editCategory = new JDialog();
		editCategory.setLayout(new FlowLayout());
		editCategory.setSize(300, 150);
		editCategory.setTitle("Edit category");
		editCategory.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		editCategory.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		JLabel newCat = new JLabel("*Enter the new Category: ");
		editCategory.add(newCat, FlowLayout.LEFT);
		tfCategory1 = new JTextField(20);
		editCategory.add(tfCategory1);
//		editCategory.add(tfCategory1);
		oldCat = (String)comboCategory.getSelectedItem();
		tfCategory1.setText(oldCat);

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Categories cat = mapCat.get(oldCat);
				String ct = tfCategory1.getText();
				
				if (ct.isEmpty() || ct == null) {
					JOptionPane
							.showMessageDialog(
									null,
									"The field is empty! Please, add the new category!",
									"Error", JOptionPane.ERROR_MESSAGE);
					tfCategory1.setBorder(BorderFactory
							.createLineBorder(Color.RED));

				} else {
					if(cat == null){
						JOptionPane
						.showMessageDialog(
								null,
								"The category was aready updated!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}else{
					cat.setName(ct);
					updateCategory(cat);
					}
					newUploadCategory();

					categoryNew.sort(null);
					catModel = new DefaultComboBoxModel<String>(categoryNew);
					comboCategory.setModel(catModel);

					GridBagConstraints gbc_comboBox = new GridBagConstraints();
					gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
					gbc_comboBox.insets = new Insets(0, 0, 5, 5);
					gbc_comboBox.gridx = 2;
					gbc_comboBox.gridy = 5;
					add(comboCategory, gbc_comboBox);
					
					editCategory.setVisible(false);
				}

			
			}

		});
		editCategory.add(ok);
		editCategory.setVisible(true);

	}

	private void updateCategory(Categories cat) {

		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			s.update(cat);
			JOptionPane.showMessageDialog(null,
					"The category is updated successfully", "Information",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

	}

	private void uploadCategory() {
		category = new Vector<String>();
		mapCat = new HashMap<String, Categories>();
		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			Query query = s.createQuery("FROM Categories");
			List<?> results = query.list();
			Iterator<?> res = results.iterator();

			while (res.hasNext()) {
				Categories ct = (Categories) res.next();
				category.add((String) ct.getName());
				mapCat.put(ct.getName(), ct);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

		category.sort(null);
		catModel = new DefaultComboBoxModel<String>(category);
		comboCategory = new JComboBox<String>(catModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		add(comboCategory, gbc_comboBox);

	}

	private void newUploadCategory() {
		categoryNew = new Vector<String>();
		mapCatNew = new HashMap<String, Categories>();
		try {

			s = GetConnectionFactory.getSessionFactory().openSession();
			tx = s.beginTransaction();

			Query query = s.createQuery("FROM Categories");
			List<?> results = query.list();
			Iterator<?> res = results.iterator();

			while (res.hasNext()) {
				Categories ct = (Categories) res.next();
				categoryNew.add((String) ct.getName());
				mapCatNew.put(ct.getName(), ct);

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			tx.commit();
			s.close();
		}

	}

	private void addCategory() {
		Categories category = new Categories();
		String cat = tfCategory.getText().trim();
		if (cat.equals("")) {

			JOptionPane.showMessageDialog(null, "You did not enter a name!",
					"Error", JOptionPane.ERROR_MESSAGE);

			tfCategory.setBorder(BorderFactory.createLineBorder(Color.RED));

		} else {
			category.setName(cat);
			try {

				s = GetConnectionFactory.getSessionFactory().openSession();
				tx = s.beginTransaction();
				s.save(category);

				JOptionPane.showMessageDialog(null,
						"The category is added successfully", "Information",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				tx.commit();
				s.close();
			}
		}
	}

	// private void removeCategory() {
	//
	// String cat = null;
	//
	// List<Categories> category = new ArrayList<Categories>();
	// try {
	//
	// cat = (String) comboCategory.getSelectedItem();
	//
	// System.out.println(cat);
	//
	// s = GetConnectionFactory.getSessionFactory().openSession();
	// tx = s.beginTransaction();
	//
	// Query query = s.createQuery("FROM Categories");
	// List<?> results = query.list();
	// Iterator<?> res = results.iterator();
	//
	// while (res.hasNext()) {
	// Categories ct = (Categories) res.next();
	// category.add(ct);
	//
	// }
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// } finally {
	// tx.commit();
	// s.close();
	// }
	// System.out.println(cat);
	//
	// if (cat == null) {
	//
	// cat = "";
	//
	// JOptionPane
	// .showMessageDialog(
	// null,
	// "You did not choose any category! Please, choose category!",
	// "Error", JOptionPane.ERROR_MESSAGE);
	// } else {
	//
	// for (Categories item : category) {
	//
	// if (cat.equals(item.getName())) {
	//
	// try {
	// s = GetConnectionFactory.getSessionFactory()
	// .openSession();
	//
	// tx = s.beginTransaction();
	// s.delete(item);
	// comboCategory.setModel(catModel);
	//
	// JOptionPane.showMessageDialog(null,
	// "The category is deleted successfully",
	// "Information", JOptionPane.INFORMATION_MESSAGE);
	//
	// } catch (Exception e1) {
	// e1.printStackTrace();
	// } finally {
	// tx.commit();
	// s.close();
	// }
	//
	// System.out.println(item.getName());
	// }
	//
	// }
	// }

	// }
}
