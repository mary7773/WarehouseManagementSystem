package guiComponents;



import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Products;

public class SearchProduct {
	Session s = null;
	Transaction tx = null;
	Configuration cfg = null;
	SessionFactory factory = null;

	private List<Products> database = null;
	private ProductTable model = null;
	private int selectedRow;

	private JDialog frameSearchProduct;
	private JTextField searchTextField;
	private JButton btnSearch;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnEdit;
	private JButton btnCancel;
	private ListSelectionModel cellSelectionModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchProduct window = new SearchProduct();
					window.frameSearchProduct.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JDialog initialize() {
		database = new ArrayList<Products>();
		model = new ProductTable();
		frameSearchProduct = new JDialog();
		frameSearchProduct.setTitle("Search and Edit Product");
		// frameSearchProduct.setBounds(100, 100, 485, 465);
		// frameSearchProduct.setSize(800, 900);
		frameSearchProduct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		// gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		// gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0,
		// Double.MIN_VALUE};
		// gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0,
		// Double.MIN_VALUE};
		frameSearchProduct.getContentPane().setLayout(gridBagLayout);

		createTable(model);
		table.setAutoCreateRowSorter(true);
		table.setCellSelectionEnabled(true);
		cellSelectionModel = table.getSelectionModel();
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (e.getValueIsAdjusting()) // mouse button not
														// released yet
							return;

						int row = table.getSelectedRow();

						if (row < 0) // true when clearSelection

							return;

						int col = table.getSelectedColumn();

						if (col < 0) // true when clearSelection

							return;

						database.add(model.rowData.get(row));
						for (Products p : database) {
							System.out.println(p.getName());
						}

					}
				});

		JLabel lblSearchProductBy = new JLabel("Search product by name");
		GridBagConstraints gbc_lblSearchProductBy = new GridBagConstraints();
		gbc_lblSearchProductBy.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_lblSearchProductBy.insets = new Insets(10, 10, 5, 5);
		gbc_lblSearchProductBy.gridx = 1;
		gbc_lblSearchProductBy.gridy = 0;
		frameSearchProduct.getContentPane().add(lblSearchProductBy,
				gbc_lblSearchProductBy);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SearchProduct.class
				.getResource("/images/list.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		frameSearchProduct.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		searchTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		frameSearchProduct.getContentPane().add(searchTextField, gbc_textField);
		searchTextField.setColumns(50);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Products> list = new ArrayList<Products>();
				String name = searchTextField.getText().toLowerCase();

				list = loadAndSearchInDatabase(name);

				model.updateRow(list);
				createNewTable(model);
			}

		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		frameSearchProduct.getContentPane().add(btnSearch, gbc_btnNewButton);

		btnEdit = new JButton("Edit      ");
		btnEdit.setIcon(new ImageIcon(SearchProduct.class
				.getResource("/Icons/edit_icon.png")));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.anchor = GridBagConstraints.WEST;
		gbc_btnSelect.insets = new Insets(0, 0, 0, 0);
		gbc_btnSelect.gridx = 0;
		gbc_btnSelect.gridy = 2;
		frameSearchProduct.getContentPane().add(btnEdit, gbc_btnSelect);

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				EditProduct edit = new EditProduct();

				 database = getProduct(table);
				 for (Products p : database) {
				 edit.setFields(p);
				 
				 System.out.println(p.getProductId() + " " + p.getName());
				 }
				 
				 edit.setAlwaysOnTop(true);
				 edit.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				 edit.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				 edit.setVisible(true);

				

				// frameSearchProduct.dispose();
				// frameSearchProduct.setVisible(false);

			}

		});

		btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon(SearchProduct.class
				.getResource("/Icons/edit_not.png")));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 0);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 3;
		frameSearchProduct.getContentPane().add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	              frameSearchProduct.setVisible(false);
			}
		});
		return frameSearchProduct;
	}

	private void createTable(ProductTable model) {
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		// gbc_scrollPane.insets = new Insets(0, 10, 10, 10);
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 10;
		frameSearchProduct.getContentPane().add(scrollPane, gbc_scrollPane);

		factory = GetConnectionFactory.getSessionFactory();
		s = factory.openSession();

		Query q = s.createQuery("from Products");
		model.rowData = new ArrayList<Products>(q.list());

		table = new JTable(model);
		scrollPane.setViewportView(table);

		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	}

	private void createNewTable(final ProductTable model) {
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		// gbc_scrollPane.insets = new Insets(0, 10, 10, 10);
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 10;
		frameSearchProduct.getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		

	}

	private List<Products> loadAndSearchInDatabase(String name) {

		// String name = searchTextField.getText();
		ProductTable model = new ProductTable();

		factory = GetConnectionFactory.getSessionFactory();
		s = factory.openSession();
		tx = s.beginTransaction();
		org.hibernate.Query query = s
				.createQuery("FROM Products WHERE name like ?");
		query.setParameter(0, name + "%");

		List<?> results = query.list();

		model.rowData = new ArrayList<Products>(query.list());

		table = new JTable(model);
		scrollPane.setViewportView(table);

		List<Products> pr = null;
		for (Object product : results) {

			pr = new ArrayList<Products>();
			pr.add((Products) product);
			// System.out.println(pr.get(0).getName());

		}

		return pr;

	}

	private List<Products> getProduct(final JTable tab) {

		tab.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (e.getValueIsAdjusting()) // mouse button not
														// released yet
							return;

						int row = tab.getSelectedRow();

						if (row < 0) // true when clearSelection

							return;

						int col = tab.getSelectedColumn();

						if (col < 0) // true when clearSelection

							return;

						database.add(model.rowData.get(row));

					}
				});

		return database;
	}
}
