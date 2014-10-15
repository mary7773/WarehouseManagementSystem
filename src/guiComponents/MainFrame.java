package guiComponents;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.MenuBar;

import javax.swing.JFrame;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("unused")
public class MainFrame {

	JFrame frmWarehouseSystem;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;
//	private boolean role = false;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame();
//					window.frmWarehouseSystem.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Created by Marieta Karastoykova
	 * 
	 * 
	 */
	public MainFrame() {
//		initialize(role);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Boolean role) {
		frmWarehouseSystem = new JFrame();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		if (role) {
			frmWarehouseSystem
					.setTitle("Warehouse system - You are ADMINISTRATOR");
		} else {
			frmWarehouseSystem.setTitle("Warehouse system - You are EMPLOYEE");
		}

//		frmWarehouseSystem.setTitle("Warehouse system");
		frmWarehouseSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainFrame.class.getResource("/images/warehouse.png")));
		frmWarehouseSystem.setBounds(100, 100, 726, 448);
		frmWarehouseSystem.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmWarehouseSystem.setVisible(true);
		frmWarehouseSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmWarehouseSystem.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);

		JMenu mnOperations = new JMenu("Operations");
		menuBar.add(mnOperations);

		JMenuItem mntmSales = new JMenuItem("Sales");
//		mntmSales.setEnabled(role);
		mnOperations.add(mntmSales);

		JMenuItem mntmProducts = new JMenuItem("Products");
		mntmProducts.setEnabled(role);
		mnOperations.add(mntmProducts);

		JMenuItem mntmCategories = new JMenuItem("Categories");
		mntmCategories.setEnabled(role);
		mnOperations.add(mntmCategories);

		JMenuItem mntmBrands = new JMenuItem("Brands");
		mntmBrands.setEnabled(role);
		mnOperations.add(mntmBrands);

		JMenuItem mntmSearch = new JMenuItem("Search and Edit product");
		mntmSearch.setEnabled(role);
		mnOperations.add(mntmSearch);

		JToolBar toolBar = new JToolBar();
		frmWarehouseSystem.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Exit32.png")));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		toolBar.add(btnNewButton);

		JButton btnProducts = new JButton("");
		btnProducts.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Goods32.png")));
		toolBar.add(btnProducts);
		btnProducts.setEnabled(role);
		btnProducts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeAll();
				AddEditProduct product = new AddEditProduct();
//				mainPanel = product;
				tabbedPane.addTab("Products", product);
				product.setVisible(true);
				
			}
		});

		JButton btnInvoice = new JButton("");
		btnInvoice.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Invoice32.png")));
		toolBar.add(btnInvoice);
		btnInvoice.setEnabled(role);
		btnInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeAll();
				InvoiceView invoice = new InvoiceView();
				tabbedPane.addTab("Invoice", invoice);
				invoice.setVisible(true);
			}
		});

		JButton btnCashBook = new JButton("");
		btnCashBook.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/CashBook32.png")));
		toolBar.add(btnCashBook);

		JButton btnSales = new JButton("");
		btnSales.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/PaymentType32.png")));
		toolBar.add(btnSales);

		JButton btnDelevery = new JButton("");
		btnDelevery.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Purchase32.png")));
		toolBar.add(btnDelevery);

		JButton btnUser = new JButton("");
		btnUser.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Partner32.png")));
		toolBar.add(btnUser);
		btnUser.setEnabled(role);
		btnUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateUsers s = new CreateUsers();
				JDialog d = s.initialize();
				d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				d.pack();
				d.setVisible(true);

			}
		});

		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.setIcon(new ImageIcon(MainFrame.class
				.getResource("/Icons/Question32.png")));
		toolBar.add(btnNewButton_8);

		
		frmWarehouseSystem.getContentPane()
				.add(tabbedPane, BorderLayout.CENTER);

		mainPanel = new JPanel();
//		tabbedPane.addTab("", null, mainPanel, null);
		
		mntmBrands.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				AddEditBrand brand = new AddEditBrand();

				brand.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				brand.setVisible(true);
				

			
			}
		});
		
		mntmCategories.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditCategory category = new AddEditCategory();
				category.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				category.setVisible(true);
				
			}
		});
		
		mntmProducts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				AddEditProduct product = new AddEditProduct();
//				tabbedPane.addTab("Products", product);
//				product.setVisible(true);
				OperationsWithProducts pr = new OperationsWithProducts();
				pr.pack();
				pr.setVisible(true);
				
			}
		});
		
		mntmSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchProduct search = new SearchProduct();
				JDialog d = search.initialize();
				d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				d.pack();
				d.setVisible(true);
			}
		});
		
		frmWarehouseSystem.add(tabbedPane);
	}

}
