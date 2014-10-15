/*
 * test.java
 *
 * created at Feb 22, 2014 by Todor e-mail: TodorNeykov@gmail.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package guiComponents;


import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JPasswordField;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Cities;
import entity.Countries;
import entity.Users;
import entity.UsersGroups;


public class CreateUsers
{
    private Users newUser = null;
    private Session dbSession = null;
    private Transaction tx = null;
    private JDialog frmControlPanel;
    private JTextField firstNametextField;
    private JTextField companytextField;
    private JLabel confirmPass;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel Company;
    private JTextField moltextField;
    private JTextField addressTextField;
    private JTextField adress2textField;
    private JTextField faxTextFild;
    private JLabel lblNewLabel_7;
    private JLabel cityLabel;
    private JLabel countrylabel;
    private JLabel lblNewLabel_10;
    private JLabel phoneLabel;
    private JLabel address2Label;
    private JTextField lastNametextField;
    private JPasswordField confirmpasswordField;
    private JTextField phoneTextField;
    private JLabel faxLabel;
    private JTextField userNameField;
    private JLabel emailLabel;
    private JLabel bankCodeLabel;
    private JLabel creditCardlabel;
    private JLabel taxlabel;
    private JLabel bankNameLabel;
    private JLabel bankAcclabel;
    private JTextField emailTextFild;
    private JTextField bulstatTextField;
    private JTextField bankCodeTextField;
    private JTextField creditCardtextField;
    private JTextField taxTextField;
    private JTextField bankNameTextField;
    private JTextField bankAccTextField;
    private JButton btnSave;
    private JButton btnCancel;
    private JLabel userGroupLabel;
    private JButton btnAddCountry;
    private JComboBox<String> cityComboBox;
    private JComboBox<String> userGroupcombo;
    private JComboBox<String> countryCombo;
    private JLabel label;
    private JLabel UseridLabel;
    private JTextField userIdtextField;
    private JPasswordField passwordField;
    
    private Map<String, Cities> cityMap;
    private Map<String, Integer> countriesMap;
    private Map<String, UsersGroups> userGroupMap;


    // for delete !

    public static void main(String[] ar)
    {
        CreateUsers u = new CreateUsers();

        JDialog j = u.initialize();
        j.setVisible(true);
        j.pack();

    }


    public CreateUsers()
    {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    public JDialog initialize()
    {

        frmControlPanel = new JDialog();
        frmControlPanel.setTitle("Users manager");
        frmControlPanel.setName("Users manager");
        frmControlPanel.setBounds(100, 100, 349, 357);
        frmControlPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                                                0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmControlPanel.getContentPane().setLayout(gridBagLayout);

        
        JLabel label = new JLabel();        		
        label.setIcon(new ImageIcon(LoginForm.class
				.getResource("/image/users.png")));
        GridBagConstraints gbc_UseridLabel = new GridBagConstraints();
      gbc_UseridLabel.anchor = GridBagConstraints.EAST;
      gbc_UseridLabel.insets = new Insets(10, 0, 5, 5);
      gbc_UseridLabel.gridx = 0;
      gbc_UseridLabel.gridy = 0;
      frmControlPanel.getContentPane().add(label, gbc_UseridLabel);

        
        JLabel lblNewLabel = new JLabel("User Name");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(10, 5, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 0;
        frmControlPanel.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        userNameField = new JTextField();
        GridBagConstraints gbc_userNameField = new GridBagConstraints();
        gbc_userNameField.fill = GridBagConstraints.HORIZONTAL;
        gbc_userNameField.insets = new Insets(10, 0, 5, 5);
        gbc_userNameField.gridx = 3;
        gbc_userNameField.gridy = 0;
        userNameField.addMouseListener(new ColorResetActionLiteneor(userNameField));
        frmControlPanel.getContentPane().add(userNameField, gbc_userNameField);
        userNameField.setColumns(10);
       

        JLabel passwordLabel = new JLabel("Password");
        GridBagConstraints gbc_lpasswordLabel = new GridBagConstraints();
        gbc_lpasswordLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lpasswordLabel.anchor = GridBagConstraints.EAST;
        gbc_lpasswordLabel.gridx = 0;
        gbc_lpasswordLabel.gridy = 1;
        frmControlPanel.getContentPane().add(passwordLabel, gbc_lpasswordLabel);

        passwordField = new JPasswordField();
        passwordField.addMouseListener(new ColorResetActionLiteneor(passwordField));
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordField.insets = new Insets(0, 0, 5, 5);
        gbc_passwordField.gridx = 1;
        gbc_passwordField.gridy = 1;
        frmControlPanel.getContentPane().add(passwordField, gbc_passwordField);
        passwordField.setColumns(10);

        confirmPass = new JLabel("Confirm password");

        confirmPass.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_confirmPass = new GridBagConstraints();
        gbc_confirmPass.anchor = GridBagConstraints.EAST;
        gbc_confirmPass.insets = new Insets(0, 0, 5, 5);
        gbc_confirmPass.gridx = 2;
        gbc_confirmPass.gridy = 1;
        frmControlPanel.getContentPane().add(confirmPass, gbc_confirmPass);

        confirmpasswordField = new JPasswordField();
        confirmpasswordField.addMouseListener(new ColorResetActionLiteneor(confirmpasswordField));
        GridBagConstraints gbc_confirmpasswordField = new GridBagConstraints();
        gbc_confirmpasswordField.fill = GridBagConstraints.HORIZONTAL;
        gbc_confirmpasswordField.insets = new Insets(0, 0, 5, 0);
        gbc_confirmpasswordField.gridx = 3;
        gbc_confirmpasswordField.gridy = 1;
        frmControlPanel.getContentPane().add(confirmpasswordField, gbc_confirmpasswordField);
        confirmpasswordField.setColumns(10);

        label = new JLabel("");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 0, 5, 5);
        gbc_label.gridx = 1;
        gbc_label.gridy = 2;
        frmControlPanel.getContentPane().add(label, gbc_label);

        firstNameLabel = new JLabel("First Name");
        GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
        gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_firstNameLabel.anchor = GridBagConstraints.EAST;
        gbc_firstNameLabel.gridx = 0;
        gbc_firstNameLabel.gridy = 3;
        frmControlPanel.getContentPane().add(firstNameLabel, gbc_firstNameLabel);

        firstNametextField = new JTextField();
        firstNametextField.addMouseListener(new ColorResetActionLiteneor(firstNametextField));
        GridBagConstraints gbc_firstNametextField = new GridBagConstraints();
        gbc_firstNametextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_firstNametextField.insets = new Insets(0, 0, 5, 5);
        gbc_firstNametextField.gridx = 1;
        gbc_firstNametextField.gridy = 3;
        frmControlPanel.getContentPane().add(firstNametextField, gbc_firstNametextField);
        firstNametextField.setColumns(10);

        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
        gbc_lastNameLabel.ipadx = 5;
        gbc_lastNameLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lastNameLabel.insets = new Insets(0, 5, 5, 5);
        gbc_lastNameLabel.gridx = 2;
        gbc_lastNameLabel.gridy = 3;
        frmControlPanel.getContentPane().add(lastNameLabel, gbc_lastNameLabel);

        lastNametextField = new JTextField();
        lastNametextField.addMouseListener(new ColorResetActionLiteneor(lastNametextField));
        GridBagConstraints gbc_lastNametextField = new GridBagConstraints();
        gbc_lastNametextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_lastNametextField.insets = new Insets(0, 0, 5, 0);
        gbc_lastNametextField.gridx = 3;
        gbc_lastNametextField.gridy = 3;
        frmControlPanel.getContentPane().add(lastNametextField, gbc_lastNametextField);
        lastNametextField.setColumns(10);

        Company = new JLabel("Company");
        GridBagConstraints gbc_Company = new GridBagConstraints();
        gbc_Company.insets = new Insets(0, 0, 5, 5);
        gbc_Company.anchor = GridBagConstraints.EAST;
        gbc_Company.gridx = 0;
        gbc_Company.gridy = 4;
        frmControlPanel.getContentPane().add(Company, gbc_Company);

        companytextField = new JTextField();
        companytextField.addMouseListener(new ColorResetActionLiteneor(companytextField));
        GridBagConstraints gbc_companytextField = new GridBagConstraints();
        gbc_companytextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_companytextField.insets = new Insets(0, 0, 5, 5);
        gbc_companytextField.gridx = 1;
        gbc_companytextField.gridy = 4;
        frmControlPanel.getContentPane().add(companytextField, gbc_companytextField);
        companytextField.setColumns(10);

        lblNewLabel_7 = new JLabel("MOL");
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
        gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_7.gridx = 2;
        gbc_lblNewLabel_7.gridy = 4;
        frmControlPanel.getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);

        moltextField = new JTextField();
        moltextField.addMouseListener(new ColorResetActionLiteneor(moltextField));
        GridBagConstraints gbc_moltextField = new GridBagConstraints();
        gbc_moltextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_moltextField.insets = new Insets(0, 0, 5, 0);
        gbc_moltextField.gridx = 3;
        gbc_moltextField.gridy = 4;
        frmControlPanel.getContentPane().add(moltextField, gbc_moltextField);
        moltextField.setColumns(10);

        lblNewLabel_10 = new JLabel("Address");
        GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
        gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_10.gridx = 0;
        gbc_lblNewLabel_10.gridy = 6;
        frmControlPanel.getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);

        addressTextField = new JTextField();
        addressTextField.addMouseListener(new ColorResetActionLiteneor(addressTextField));
        GridBagConstraints gbc_addressTextField = new GridBagConstraints();
        gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
        gbc_addressTextField.gridx = 1;
        gbc_addressTextField.gridy = 6;
        frmControlPanel.getContentPane().add(addressTextField, gbc_addressTextField);
        addressTextField.setColumns(10);

        address2Label = new JLabel("Address2");
        GridBagConstraints gbc_address2Label = new GridBagConstraints();
        gbc_address2Label.anchor = GridBagConstraints.EAST;
        gbc_address2Label.insets = new Insets(0, 0, 5, 5);
        gbc_address2Label.gridx = 2;
        gbc_address2Label.gridy = 6;
        frmControlPanel.getContentPane().add(address2Label, gbc_address2Label);

        adress2textField = new JTextField();
        adress2textField.addMouseListener(new ColorResetActionLiteneor(adress2textField));
        GridBagConstraints gbc_adress2textField = new GridBagConstraints();
        gbc_adress2textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_adress2textField.insets = new Insets(0, 0, 5, 0);
        gbc_adress2textField.gridx = 3;
        gbc_adress2textField.gridy = 6;
        frmControlPanel.getContentPane().add(adress2textField, gbc_adress2textField);
        adress2textField.setColumns(10);

        phoneLabel = new JLabel("Phone");
        GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
        gbc_phoneLabel.insets = new Insets(0, 0, 5, 5);
        gbc_phoneLabel.anchor = GridBagConstraints.EAST;
        gbc_phoneLabel.gridx = 0;
        gbc_phoneLabel.gridy = 7;
        frmControlPanel.getContentPane().add(phoneLabel, gbc_phoneLabel);

        phoneTextField = new JTextField();
        phoneTextField.addMouseListener(new ColorResetActionLiteneor(phoneTextField));
        GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
        gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_phoneTextField.insets = new Insets(0, 0, 5, 5);
        gbc_phoneTextField.gridx = 1;
        gbc_phoneTextField.gridy = 7;
        frmControlPanel.getContentPane().add(phoneTextField, gbc_phoneTextField);
        phoneTextField.setColumns(10);

        faxLabel = new JLabel("FAX");
        GridBagConstraints gbc_faxLabel = new GridBagConstraints();
        gbc_faxLabel.insets = new Insets(0, 0, 5, 5);
        gbc_faxLabel.anchor = GridBagConstraints.EAST;
        gbc_faxLabel.gridx = 2;
        gbc_faxLabel.gridy = 7;
        frmControlPanel.getContentPane().add(faxLabel, gbc_faxLabel);

        faxTextFild = new JTextField();
        faxTextFild.addMouseListener(new ColorResetActionLiteneor(faxTextFild));
        GridBagConstraints gbc_faxTextFild = new GridBagConstraints();
        gbc_faxTextFild.fill = GridBagConstraints.HORIZONTAL;
        gbc_faxTextFild.insets = new Insets(0, 0, 5, 0);
        gbc_faxTextFild.gridx = 3;
        gbc_faxTextFild.gridy = 7;
        frmControlPanel.getContentPane().add(faxTextFild, gbc_faxTextFild);
        faxTextFild.setColumns(10);

        emailLabel = new JLabel("E-mail");
        GridBagConstraints gbc_emailLabel = new GridBagConstraints();
        gbc_emailLabel.anchor = GridBagConstraints.EAST;
        gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
        gbc_emailLabel.gridx = 0;
        gbc_emailLabel.gridy = 8;
        frmControlPanel.getContentPane().add(emailLabel, gbc_emailLabel);

        emailTextFild = new JTextField();
        emailTextFild.addMouseListener(new ColorResetActionLiteneor(emailTextFild));
        GridBagConstraints gbc_emailTextFild = new GridBagConstraints();
        gbc_emailTextFild.insets = new Insets(0, 0, 5, 5);
        gbc_emailTextFild.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailTextFild.gridx = 1;
        gbc_emailTextFild.gridy = 8;
        frmControlPanel.getContentPane().add(emailTextFild, gbc_emailTextFild);
        emailTextFild.setColumns(10);

        taxlabel = new JLabel("Tax No");
        GridBagConstraints gbc_taxlabel = new GridBagConstraints();
        gbc_taxlabel.anchor = GridBagConstraints.EAST;
        gbc_taxlabel.insets = new Insets(0, 0, 5, 5);
        gbc_taxlabel.gridx = 2;
        gbc_taxlabel.gridy = 8;
        frmControlPanel.getContentPane().add(taxlabel, gbc_taxlabel);

        taxTextField = new JTextField();
        taxTextField.addMouseListener(new ColorResetActionLiteneor(taxTextField));
        GridBagConstraints gbc_taxTextField = new GridBagConstraints();
        gbc_taxTextField.insets = new Insets(0, 0, 5, 0);
        gbc_taxTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_taxTextField.gridx = 3;
        gbc_taxTextField.gridy = 8;
        frmControlPanel.getContentPane().add(taxTextField, gbc_taxTextField);
        taxTextField.setColumns(10);

        JLabel bulstatLabel = new JLabel("Bulstat");
        GridBagConstraints gbc_bulstatLabel = new GridBagConstraints();
        gbc_bulstatLabel.anchor = GridBagConstraints.EAST;
        gbc_bulstatLabel.insets = new Insets(0, 0, 5, 5);
        gbc_bulstatLabel.gridx = 0;
        gbc_bulstatLabel.gridy = 9;
        frmControlPanel.getContentPane().add(bulstatLabel, gbc_bulstatLabel);

        bulstatTextField = new JTextField();
        bulstatTextField.addMouseListener(new ColorResetActionLiteneor(bulstatTextField));
        GridBagConstraints gbc_bulstatTextField = new GridBagConstraints();
        gbc_bulstatTextField.insets = new Insets(0, 0, 5, 5);
        gbc_bulstatTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bulstatTextField.gridx = 1;
        gbc_bulstatTextField.gridy = 9;
        frmControlPanel.getContentPane().add(bulstatTextField, gbc_bulstatTextField);
        bulstatTextField.setColumns(10);

        bankNameLabel = new JLabel("Bank Name");
        GridBagConstraints gbc_bankNameLabel = new GridBagConstraints();
        gbc_bankNameLabel.anchor = GridBagConstraints.EAST;
        gbc_bankNameLabel.insets = new Insets(0, 0, 5, 5);
        gbc_bankNameLabel.gridx = 2;
        gbc_bankNameLabel.gridy = 9;
        frmControlPanel.getContentPane().add(bankNameLabel, gbc_bankNameLabel);

        bankNameTextField = new JTextField();
        bankNameTextField.addMouseListener(new ColorResetActionLiteneor(bankNameTextField));
        GridBagConstraints gbc_bankNameTextField = new GridBagConstraints();
        gbc_bankNameTextField.insets = new Insets(0, 0, 5, 0);
        gbc_bankNameTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bankNameTextField.gridx = 3;
        gbc_bankNameTextField.gridy = 9;
        frmControlPanel.getContentPane().add(bankNameTextField, gbc_bankNameTextField);
        bankNameTextField.setColumns(10);

        bankCodeLabel = new JLabel("Bank Code");
        GridBagConstraints gbc_bankCodeLabel = new GridBagConstraints();
        gbc_bankCodeLabel.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        gbc_bankCodeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_bankCodeLabel.gridx = 0;
        gbc_bankCodeLabel.gridy = 10;
        frmControlPanel.getContentPane().add(bankCodeLabel, gbc_bankCodeLabel);

        bankCodeTextField = new JTextField();
        bankCodeTextField.addMouseListener(new ColorResetActionLiteneor(bankCodeTextField));
        GridBagConstraints gbc_bankCodeTextField = new GridBagConstraints();
        gbc_bankCodeTextField.insets = new Insets(0, 0, 5, 5);
        gbc_bankCodeTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bankCodeTextField.gridx = 1;
        gbc_bankCodeTextField.gridy = 10;
        frmControlPanel.getContentPane().add(bankCodeTextField, gbc_bankCodeTextField);
        bankCodeTextField.setColumns(10);

        bankAcclabel = new JLabel("Bank Acct");
        GridBagConstraints gbc_bankAcclabel = new GridBagConstraints();
        gbc_bankAcclabel.anchor = GridBagConstraints.EAST;
        gbc_bankAcclabel.insets = new Insets(0, 0, 5, 5);
        gbc_bankAcclabel.gridx = 2;
        gbc_bankAcclabel.gridy = 10;
        frmControlPanel.getContentPane().add(bankAcclabel, gbc_bankAcclabel);

        bankAccTextField = new JTextField();
        bankAccTextField.addMouseListener(new ColorResetActionLiteneor(bankAccTextField));
        GridBagConstraints gbc_bankAccTextField = new GridBagConstraints();
        gbc_bankAccTextField.insets = new Insets(0, 0, 5, 0);
        gbc_bankAccTextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_bankAccTextField.gridx = 3;
        gbc_bankAccTextField.gridy = 10;
        frmControlPanel.getContentPane().add(bankAccTextField, gbc_bankAccTextField);
        bankAccTextField.setColumns(10);

        creditCardlabel = new JLabel("Credit card");
        GridBagConstraints gbc_creditCardlabel = new GridBagConstraints();
        gbc_creditCardlabel.anchor = GridBagConstraints.EAST;
        gbc_creditCardlabel.insets = new Insets(0, 0, 5, 5);
        gbc_creditCardlabel.gridx = 0;
        gbc_creditCardlabel.gridy = 11;
        frmControlPanel.getContentPane().add(creditCardlabel, gbc_creditCardlabel);

        creditCardtextField = new JTextField();
        GridBagConstraints gbc_creditCardtextField = new GridBagConstraints();
        gbc_creditCardtextField.insets = new Insets(0, 0, 5, 5);
        gbc_creditCardtextField.fill = GridBagConstraints.HORIZONTAL;
        gbc_creditCardtextField.gridx = 1;
        gbc_creditCardtextField.gridy = 11;
        creditCardtextField.addMouseListener(new ColorResetActionLiteneor(creditCardtextField));
        frmControlPanel.getContentPane().add(creditCardtextField, gbc_creditCardtextField);
        creditCardtextField.setColumns(10);

        userGroupLabel = new JLabel("User Group");
        GridBagConstraints gbc_userGroupLabel = new GridBagConstraints();
        gbc_userGroupLabel.anchor = GridBagConstraints.EAST;
        gbc_userGroupLabel.insets = new Insets(0, 0, 5, 5);
        gbc_userGroupLabel.gridx = 2;
        gbc_userGroupLabel.gridy = 11;
        frmControlPanel.getContentPane().add(userGroupLabel, gbc_userGroupLabel);

        // ========================================
        Vector<String> userGrupsitems = new Vector<String>();
        final Vector<String> countriesGrupsitems = new Vector<String>();

        // Vector<String> citiesItems = new Vector<String>();

        cityMap = new HashMap<String, Cities>();
        countriesMap = new HashMap<String, Integer>();
        userGroupMap = new HashMap<String, UsersGroups>();
        try
        {

            dbSession = GetConnectionFactory.getSessionFactory().openSession();

            Query query = dbSession.createQuery("FROM UsersGroups");
            List< ? > results = query.list();
            Iterator< ? > res = results.iterator();
            while (res.hasNext())
            {
                UsersGroups gr = (UsersGroups)res.next();
                userGrupsitems.add( (String) gr.getGroupName());
                userGroupMap.put((String) gr.getGroupName(), gr);
            }
            query = dbSession.createQuery("FROM Cities");
            results = query.list();
            res = results.iterator();

            while (res.hasNext())
            {

                Cities ct = (Cities)res.next();
                cityMap.put((String) ct.getName(), ct);

            }
            query = dbSession.createQuery("FROM Countries");
            results = query.list();
            res = results.iterator();
            while (res.hasNext())
            {
                Countries c = (Countries)res.next();
                countriesGrupsitems.add((String) c.getName());
                countriesMap.put((String) c.getName(), c.getCountryId());

            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        finally
        {
            if (dbSession != null)
                dbSession.close();
        }
        countrylabel = new JLabel("Country");
        GridBagConstraints gbc_countrylabel = new GridBagConstraints();
        gbc_countrylabel.anchor = GridBagConstraints.EAST;
        gbc_countrylabel.insets = new Insets(0, 0, 5, 5);
        gbc_countrylabel.gridx = 0;
        gbc_countrylabel.gridy = 5;
        frmControlPanel.getContentPane().add(countrylabel, gbc_countrylabel);

        cityLabel = new JLabel("City");
        GridBagConstraints gbc_cityLabel = new GridBagConstraints();
        gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
        gbc_cityLabel.anchor = GridBagConstraints.EAST;
        gbc_cityLabel.gridx = 2;
        gbc_cityLabel.gridy = 5;
        frmControlPanel.getContentPane().add(cityLabel, gbc_cityLabel);

        cityComboBox = new JComboBox<String>();
        GridBagConstraints gbc_cityComboBox = new GridBagConstraints();
        gbc_cityComboBox.insets = new Insets(0, 0, 5, 0);
        gbc_cityComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_cityComboBox.gridx = 3;
        gbc_cityComboBox.gridy = 5;

        final DefaultComboBoxModel<String> countruModel = new DefaultComboBoxModel<String>(countriesGrupsitems);
        countruModel.setSelectedItem(null);
        countryCombo = new JComboBox<String>(countruModel);

        GridBagConstraints gbc_countryCombo = new GridBagConstraints();
        gbc_countryCombo.insets = new Insets(0, 0, 5, 5);
        gbc_countryCombo.fill = GridBagConstraints.HORIZONTAL;
        gbc_countryCombo.gridx = 1;
        gbc_countryCombo.gridy = 5;

        countryCombo.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                countryCombo.setBorder(UIManager.getBorder("TextField.border"));
                int index = countriesMap.get(countryCombo.getSelectedItem());
                Vector<String> citiesItems2 = new Vector<String>();

                dbSession = GetConnectionFactory.getSessionFactory().openSession();
                tx = dbSession.beginTransaction();
                Query query = dbSession.createQuery("FROM Cities WHERE CountryId="+ index +"");
                List<?> results = query.list();
                Iterator<?> res = results.iterator();

                while (res.hasNext())
                {

                    Cities ct = (Cities)res.next();
                    citiesItems2.add((String) ct.getName());

                }

                DefaultComboBoxModel<String> cityModel = new DefaultComboBoxModel<String>(citiesItems2);
                cityComboBox.setModel(cityModel);

            }
        });

        frmControlPanel.getContentPane().add(cityComboBox, gbc_cityComboBox);
        frmControlPanel.getContentPane().add(countryCombo, gbc_countryCombo);

        userGroupcombo = new JComboBox<String>(userGrupsitems);

        GridBagConstraints gbc_userGroupcombo = new GridBagConstraints();
        gbc_userGroupcombo.insets = new Insets(0, 0, 5, 0);
        gbc_userGroupcombo.fill = GridBagConstraints.HORIZONTAL;
        gbc_userGroupcombo.gridx = 3;
        gbc_userGroupcombo.gridy = 11;
        frmControlPanel.getContentPane().add(userGroupcombo, gbc_userGroupcombo);

        btnSave = new JButton("");
        btnSave.setIcon(new ImageIcon(LoginForm.class
				.getResource("/images/useradd.png")));
        GridBagConstraints gbc_btnSave = new GridBagConstraints();
        gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnSave.insets = new Insets(0, 0, 5, 5);
        gbc_btnSave.gridx = 1;
        gbc_btnSave.gridy = 13;
        frmControlPanel.getContentPane().add(btnSave, gbc_btnSave);

        btnCancel = new JButton("");
        btnCancel.setIcon(new ImageIcon(LoginForm.class
				.getResource("/images/userdelete.png")));
        GridBagConstraints gbc_btnCancel = new GridBagConstraints();
        gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancel.gridx = 2;
        gbc_btnCancel.gridy = 13;
        frmControlPanel.getContentPane().add(btnCancel, gbc_btnCancel);
        btnCancel.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                int reply = JOptionPane.showConfirmDialog(null,
                                                          "Are you sure you want to cancel?",
                                                          "Cancel?",
                                                          JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    frmControlPanel.dispose();
                }

            }
        });
        JMenuBar menuBar = new JMenuBar();
        frmControlPanel.setJMenuBar(menuBar);
        JButton btnAddUsers = new JButton("Add Users");
        btnAddUsers.setIcon(new ImageIcon(LoginForm.class
				.getResource("/image/user_group.png")));
        menuBar.add(btnAddUsers);
        JButton addCityBot = new JButton("Add City");
        addCityBot.setIcon(new ImageIcon(LoginForm.class
				.getResource("/image/town.png")));

        addCityBot.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {


                JDialog addCity = new AddCity(countriesGrupsitems, countriesMap);
                addCity.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                addCity.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                addCity.setVisible(true);

            }
        });
        menuBar.add(addCityBot);
        btnAddCountry = new JButton("Add Country");
        btnAddCountry.setIcon(new ImageIcon(LoginForm.class
				.getResource("/image/world.png")));

        btnAddCountry.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {

                JDialog addCountry = new AddCountry();
                addCountry.setTitle("Add country");
                addCountry.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                addCountry.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                addCountry.setVisible(true);

            }
        });
        menuBar.add(btnAddCountry);

        btnSave.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (checkCorrect())
                {
                    newUser = new Users();
                    // newUser.setUserId(Integer.parseInt(userIdtextField.getText()));
                    newUser.setUserName(userNameField.getText().trim());
                    newUser.setPassword(new String(passwordField.getPassword()));
                    newUser.setFirstName(firstNametextField.getText().trim());
                    newUser.setLastName(lastNametextField.getText().trim());
                    newUser.setCompany(companytextField.getText().trim());
                    newUser.setMol(moltextField.getText().trim());
                    // add city
                    Cities city = new Cities();
                    if (cityComboBox.getItemCount() != 0 && cityComboBox.getSelectedItem()!=null)
                    {
//                        city.setCityId(cityMap.get(cityComboBox.getSelectedItem()).getCityId());
                    	String c = (String)cityComboBox.getSelectedItem();                 	
                    	city = cityMap.get(c);
                        newUser.setCities(city);
                    }

                    UsersGroups usersGroups = new UsersGroups();
                    if(cityComboBox.getItemCount() != 0 && cityComboBox.getSelectedItem()!=null)
                    {
                    usersGroups.setUsersGroupId(userGroupMap.get(userGroupcombo.getSelectedItem()).getUsersGroupId());

                    newUser.setUsersGroups(usersGroups);
                    }
                    newUser.setAddress(addressTextField.getText().trim());
                    newUser.setAddress2(adress2textField.getText().trim());
                    newUser.setPhone(phoneTextField.getText().trim());
                    newUser.setFax(faxTextFild.getText().trim());
                    newUser.setEmail(emailTextFild.getText().trim());
                    newUser.setTaxNo(taxTextField.getText().trim());
                    newUser.setBulstat(bulstatTextField.getText().trim());
                    newUser.setBankAcct(bankAccTextField.getText().trim());
                    newUser.setBankCode(bankCodeTextField.getText().trim());
                    if (!("".equals(creditCardtextField.getText().trim())))
                    {
                        newUser.setCardNumber(Integer.parseInt(creditCardtextField.getText().trim()));
                    }
                    try
                    {
                        dbSession = GetConnectionFactory.getSessionFactory().openSession();
                        tx = dbSession.beginTransaction();

                        dbSession.save(newUser);
                        okMeesage("Save successful");
                        tx.commit();


                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }
                    finally
                    {

                        dbSession.close();
                    }
                    frmControlPanel.dispose();
                }
            }
        });
        return frmControlPanel;

    }


    /**
     * Check password and confirm password field matching
     *
     * @return true if match
     */
    private Boolean passwordMachCheck()
    {
        final String pass1 = new String(passwordField.getPassword());
        final String pass2 = new String(confirmpasswordField.getPassword());
        if (("".equals(pass1)) || !(pass1.equals(pass2)))
        {
        	passwordField.setText("");
        	confirmpasswordField.setText("");

            return false;
        }
        return true;
    }


    /**
     * Check if Required fields are fill correct
     *
     * @return true if corect
     */

    public boolean checkCorrect()
    {
        //System.out.println(countryCombo.getSelectedItem());
        StringBuilder str = new StringBuilder();
        str.append("Please,");
        boolean correctWriteFild = true;
        if ("".equals(userNameField.getText().trim()))
        {
            correctWriteFild = false;
            userNameField.setBorder(BorderFactory.createLineBorder(Color.RED));
            str.append("\n - Fill \"UserName\" fild");

        }

        if (null == (countryCombo.getSelectedItem()))
        {
            correctWriteFild = false;
            countryCombo.setBorder(BorderFactory.createLineBorder(Color.RED));
            str.append("\n - Select Country");

        }
        if (null == (cityComboBox.getSelectedItem()))
        {
            correctWriteFild = false;
            cityComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
            str.append("\n - Select City");

        }
        if (!passwordMachCheck())
        {
            correctWriteFild = false;
            passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
            confirmpasswordField.setBorder(BorderFactory.createLineBorder(Color.RED));
            str.append("\n - Password & confirm password do no mach");
        }

        if (!("".equals(creditCardtextField.getText().trim())))
        {

            try
            {
                Integer.parseInt(creditCardtextField.getText().trim());

            }
            catch (NumberFormatException e)
            {
                correctWriteFild = false;
                creditCardtextField.setBorder(BorderFactory.createLineBorder(Color.RED));
                str.append("\n - Credit Card must be Number");
            }

        }
        if (correctWriteFild)
        {
            return true;

        }
        errorMessage(str.toString());
        return false;
    }


    public static void errorMessage(String message)
    {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        final JDialog dialog = optionPane.createDialog("Failure");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


    public static void okMeesage(String message)
    {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog("Success");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    /*
     * public JPanel getjpanel() { JPanel frmControlPanel = new JPanel(); //
     * frmControlPanel.setTitle("Control panel"); frmControlPanel.setName("Control panel");
     * frmControlPanel.setBounds(100, 100, 349, 357); //
     * frmControlPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); GridBagLayout gridBagLayout = new
     * GridBagLayout(); gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0}; gridBagLayout.rowHeights = new
     * int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; gridBagLayout.columnWeights = new
     * double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE}; gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0,
     * 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
     * frmControlPanel.setLayout(gridBagLayout); JLabel lblNewLabel = new JLabel("User Name");
     * lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); GridBagConstraints gbc_lblNewLabel = new
     * GridBagConstraints(); gbc_lblNewLabel.insets = new Insets(10, 5, 5, 5); gbc_lblNewLabel.anchor =
     * GridBagConstraints.EAST; gbc_lblNewLabel.gridx = 0; gbc_lblNewLabel.gridy = 0;
     * frmControlPanel.add(lblNewLabel, gbc_lblNewLabel); userNameField = new JTextField(); GridBagConstraints
     * gbc_userNameField = new GridBagConstraints(); gbc_userNameField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_userNameField.insets = new Insets(10, 0, 5, 5); gbc_userNameField.gridx = 1;
     * gbc_userNameField.gridy = 0; userNameField.addMouseListener(new
     * ColorResetActionLiteneor(userNameField)); frmControlPanel.add(userNameField, gbc_userNameField);
     * userNameField.setColumns(10); UseridLabel = new JLabel("UserID"); GridBagConstraints gbc_UseridLabel =
     * new GridBagConstraints(); gbc_UseridLabel.anchor = GridBagConstraints.EAST; gbc_UseridLabel.insets =
     * new Insets(10, 0, 5, 5); gbc_UseridLabel.gridx = 2; gbc_UseridLabel.gridy = 0;
     * frmControlPanel.add(UseridLabel, gbc_UseridLabel); userIdtextField = new JTextField();
     * userIdtextField.setEditable(false); GridBagConstraints gbc_userIdtextField = new GridBagConstraints();
     * gbc_userIdtextField.insets = new Insets(10, 0, 5, 0); gbc_userIdtextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_userIdtextField.gridx = 3; gbc_userIdtextField.gridy = 0;
     * userIdtextField.addMouseListener(new ColorResetActionLiteneor(userIdtextField));
     * frmControlPanel.add(userIdtextField, gbc_userIdtextField); userIdtextField.setColumns(10); JLabel
     * passwordLabel = new JLabel("Password"); GridBagConstraints gbc_lpasswordLabel = new
     * GridBagConstraints(); gbc_lpasswordLabel.insets = new Insets(0, 0, 5, 5); gbc_lpasswordLabel.anchor =
     * GridBagConstraints.EAST; gbc_lpasswordLabel.gridx = 0; gbc_lpasswordLabel.gridy = 1;
     * frmControlPanel.add(passwordLabel, gbc_lpasswordLabel); passwordField = new JPasswordField();
     * passwordField.addMouseListener(new ColorResetActionLiteneor(passwordField)); GridBagConstraints
     * gbc_passwordField = new GridBagConstraints(); gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_passwordField.insets = new Insets(0, 0, 5, 5); gbc_passwordField.gridx = 1; gbc_passwordField.gridy
     * = 1; frmControlPanel.add(passwordField, gbc_passwordField); passwordField.setColumns(10); confirmPass =
     * new JLabel("Confirm password"); confirmPass.setHorizontalAlignment(SwingConstants.RIGHT);
     * GridBagConstraints gbc_confirmPass = new GridBagConstraints(); gbc_confirmPass.anchor =
     * GridBagConstraints.EAST; gbc_confirmPass.insets = new Insets(0, 0, 5, 5); gbc_confirmPass.gridx = 2;
     * gbc_confirmPass.gridy = 1; frmControlPanel.add(confirmPass, gbc_confirmPass); confirmpasswordField =
     * new JPasswordField(); confirmpasswordField.addMouseListener(new
     * ColorResetActionLiteneor(confirmpasswordField)); GridBagConstraints gbc_confirmpasswordField = new
     * GridBagConstraints(); gbc_confirmpasswordField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_confirmpasswordField.insets = new Insets(0, 0, 5, 0); gbc_confirmpasswordField.gridx = 3;
     * gbc_confirmpasswordField.gridy = 1; frmControlPanel.add(confirmpasswordField,
     * gbc_confirmpasswordField); confirmpasswordField.setColumns(10); label = new JLabel("");
     * GridBagConstraints gbc_label = new GridBagConstraints(); gbc_label.insets = new Insets(0, 0, 5, 5);
     * gbc_label.gridx = 1; gbc_label.gridy = 2; frmControlPanel.add(label, gbc_label); firstNameLabel = new
     * JLabel("First Name"); GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
     * gbc_firstNameLabel.insets = new Insets(0, 0, 5, 5); gbc_firstNameLabel.anchor =
     * GridBagConstraints.EAST; gbc_firstNameLabel.gridx = 0; gbc_firstNameLabel.gridy = 3;
     * frmControlPanel.add(firstNameLabel, gbc_firstNameLabel); firstNametextField = new JTextField();
     * firstNametextField.addMouseListener(new ColorResetActionLiteneor(firstNametextField));
     * GridBagConstraints gbc_firstNametextField = new GridBagConstraints(); gbc_firstNametextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_firstNametextField.insets = new Insets(0, 0, 5, 5);
     * gbc_firstNametextField.gridx = 1; gbc_firstNametextField.gridy = 3;
     * frmControlPanel.add(firstNametextField, gbc_firstNametextField); firstNametextField.setColumns(10);
     * lastNameLabel = new JLabel("Last Name"); lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
     * GridBagConstraints gbc_lastNameLabel = new GridBagConstraints(); gbc_lastNameLabel.ipadx = 5;
     * gbc_lastNameLabel.fill = GridBagConstraints.HORIZONTAL; gbc_lastNameLabel.insets = new Insets(0, 5, 5,
     * 5); gbc_lastNameLabel.gridx = 2; gbc_lastNameLabel.gridy = 3; frmControlPanel.add(lastNameLabel,
     * gbc_lastNameLabel); lastNametextField = new JTextField(); lastNametextField.addMouseListener(new
     * ColorResetActionLiteneor(lastNametextField)); GridBagConstraints gbc_lastNametextField = new
     * GridBagConstraints(); gbc_lastNametextField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_lastNametextField.insets = new Insets(0, 0, 5, 0); gbc_lastNametextField.gridx = 3;
     * gbc_lastNametextField.gridy = 3; frmControlPanel.add(lastNametextField, gbc_lastNametextField);
     * lastNametextField.setColumns(10); Company = new JLabel("Company"); GridBagConstraints gbc_Company = new
     * GridBagConstraints(); gbc_Company.insets = new Insets(0, 0, 5, 5); gbc_Company.anchor =
     * GridBagConstraints.EAST; gbc_Company.gridx = 0; gbc_Company.gridy = 4; frmControlPanel.add(Company,
     * gbc_Company); companytextField = new JTextField(); companytextField.addMouseListener(new
     * ColorResetActionLiteneor(companytextField)); GridBagConstraints gbc_companytextField = new
     * GridBagConstraints(); gbc_companytextField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_companytextField.insets = new Insets(0, 0, 5, 5); gbc_companytextField.gridx = 1;
     * gbc_companytextField.gridy = 4; frmControlPanel.add(companytextField, gbc_companytextField);
     * companytextField.setColumns(10); lblNewLabel_7 = new JLabel("MOL"); GridBagConstraints
     * gbc_lblNewLabel_7 = new GridBagConstraints(); gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
     * gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST; gbc_lblNewLabel_7.gridx = 2;
     * gbc_lblNewLabel_7.gridy = 4; frmControlPanel.add(lblNewLabel_7, gbc_lblNewLabel_7); moltextField = new
     * JTextField(); moltextField.addMouseListener(new ColorResetActionLiteneor(moltextField));
     * GridBagConstraints gbc_moltextField = new GridBagConstraints(); gbc_moltextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_moltextField.insets = new Insets(0, 0, 5, 0); gbc_moltextField.gridx
     * = 3; gbc_moltextField.gridy = 4; frmControlPanel.add(moltextField, gbc_moltextField);
     * moltextField.setColumns(10); lblNewLabel_10 = new JLabel("Address"); GridBagConstraints
     * gbc_lblNewLabel_10 = new GridBagConstraints(); gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
     * gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST; gbc_lblNewLabel_10.gridx = 0;
     * gbc_lblNewLabel_10.gridy = 6; frmControlPanel.add(lblNewLabel_10, gbc_lblNewLabel_10); addressTextField
     * = new JTextField(); addressTextField.addMouseListener(new ColorResetActionLiteneor(addressTextField));
     * GridBagConstraints gbc_addressTextField = new GridBagConstraints(); gbc_addressTextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_addressTextField.insets = new Insets(0, 0, 5, 5);
     * gbc_addressTextField.gridx = 1; gbc_addressTextField.gridy = 6; frmControlPanel.add(addressTextField,
     * gbc_addressTextField); addressTextField.setColumns(10); address2Label = new JLabel("Address2");
     * GridBagConstraints gbc_address2Label = new GridBagConstraints(); gbc_address2Label.anchor =
     * GridBagConstraints.EAST; gbc_address2Label.insets = new Insets(0, 0, 5, 5); gbc_address2Label.gridx =
     * 2; gbc_address2Label.gridy = 6; frmControlPanel.add(address2Label, gbc_address2Label); adress2textField
     * = new JTextField(); adress2textField.addMouseListener(new ColorResetActionLiteneor(adress2textField));
     * GridBagConstraints gbc_adress2textField = new GridBagConstraints(); gbc_adress2textField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_adress2textField.insets = new Insets(0, 0, 5, 0);
     * gbc_adress2textField.gridx = 3; gbc_adress2textField.gridy = 6; frmControlPanel.add(adress2textField,
     * gbc_adress2textField); adress2textField.setColumns(10); phoneLabel = new JLabel("Phone");
     * GridBagConstraints gbc_phoneLabel = new GridBagConstraints(); gbc_phoneLabel.insets = new Insets(0, 0,
     * 5, 5); gbc_phoneLabel.anchor = GridBagConstraints.EAST; gbc_phoneLabel.gridx = 0; gbc_phoneLabel.gridy
     * = 7; frmControlPanel.add(phoneLabel, gbc_phoneLabel); phoneTextField = new JTextField();
     * phoneTextField.addMouseListener(new ColorResetActionLiteneor(phoneTextField)); GridBagConstraints
     * gbc_phoneTextField = new GridBagConstraints(); gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_phoneTextField.insets = new Insets(0, 0, 5, 5); gbc_phoneTextField.gridx = 1;
     * gbc_phoneTextField.gridy = 7; frmControlPanel.add(phoneTextField, gbc_phoneTextField);
     * phoneTextField.setColumns(10); faxLabel = new JLabel("FAX"); GridBagConstraints gbc_faxLabel = new
     * GridBagConstraints(); gbc_faxLabel.insets = new Insets(0, 0, 5, 5); gbc_faxLabel.anchor =
     * GridBagConstraints.EAST; gbc_faxLabel.gridx = 2; gbc_faxLabel.gridy = 7; frmControlPanel.add(faxLabel,
     * gbc_faxLabel); faxTextFild = new JTextField(); faxTextFild.addMouseListener(new
     * ColorResetActionLiteneor(faxTextFild)); GridBagConstraints gbc_faxTextFild = new GridBagConstraints();
     * gbc_faxTextFild.fill = GridBagConstraints.HORIZONTAL; gbc_faxTextFild.insets = new Insets(0, 0, 5, 0);
     * gbc_faxTextFild.gridx = 3; gbc_faxTextFild.gridy = 7; frmControlPanel.add(faxTextFild,
     * gbc_faxTextFild); faxTextFild.setColumns(10); emailLabel = new JLabel("E-mail"); GridBagConstraints
     * gbc_emailLabel = new GridBagConstraints(); gbc_emailLabel.anchor = GridBagConstraints.EAST;
     * gbc_emailLabel.insets = new Insets(0, 0, 5, 5); gbc_emailLabel.gridx = 0; gbc_emailLabel.gridy = 8;
     * frmControlPanel.add(emailLabel, gbc_emailLabel); emailTextFild = new JTextField();
     * emailTextFild.addMouseListener(new ColorResetActionLiteneor(emailTextFild)); GridBagConstraints
     * gbc_emailTextFild = new GridBagConstraints(); gbc_emailTextFild.insets = new Insets(0, 0, 5, 5);
     * gbc_emailTextFild.fill = GridBagConstraints.HORIZONTAL; gbc_emailTextFild.gridx = 1;
     * gbc_emailTextFild.gridy = 8; frmControlPanel.add(emailTextFild, gbc_emailTextFild);
     * emailTextFild.setColumns(10); taxlabel = new JLabel("Tax No"); GridBagConstraints gbc_taxlabel = new
     * GridBagConstraints(); gbc_taxlabel.anchor = GridBagConstraints.EAST; gbc_taxlabel.insets = new
     * Insets(0, 0, 5, 5); gbc_taxlabel.gridx = 2; gbc_taxlabel.gridy = 8; frmControlPanel.add(taxlabel,
     * gbc_taxlabel); taxTextField = new JTextField(); taxTextField.addMouseListener(new
     * ColorResetActionLiteneor(taxTextField)); GridBagConstraints gbc_taxTextField = new
     * GridBagConstraints(); gbc_taxTextField.insets = new Insets(0, 0, 5, 0); gbc_taxTextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_taxTextField.gridx = 3; gbc_taxTextField.gridy = 8;
     * frmControlPanel.add(taxTextField, gbc_taxTextField); taxTextField.setColumns(10); JLabel bulstatLabel =
     * new JLabel("Bulstat"); GridBagConstraints gbc_bulstatLabel = new GridBagConstraints();
     * gbc_bulstatLabel.anchor = GridBagConstraints.EAST; gbc_bulstatLabel.insets = new Insets(0, 0, 5, 5);
     * gbc_bulstatLabel.gridx = 0; gbc_bulstatLabel.gridy = 9; frmControlPanel.add(bulstatLabel,
     * gbc_bulstatLabel); bulstatTextField = new JTextField(); bulstatTextField.addMouseListener(new
     * ColorResetActionLiteneor(bulstatTextField)); GridBagConstraints gbc_bulstatTextField = new
     * GridBagConstraints(); gbc_bulstatTextField.insets = new Insets(0, 0, 5, 5); gbc_bulstatTextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_bulstatTextField.gridx = 1; gbc_bulstatTextField.gridy = 9;
     * frmControlPanel.add(bulstatTextField, gbc_bulstatTextField); bulstatTextField.setColumns(10);
     * bankNameLabel = new JLabel("Bank Name"); GridBagConstraints gbc_bankNameLabel = new
     * GridBagConstraints(); gbc_bankNameLabel.anchor = GridBagConstraints.EAST; gbc_bankNameLabel.insets =
     * new Insets(0, 0, 5, 5); gbc_bankNameLabel.gridx = 2; gbc_bankNameLabel.gridy = 9;
     * frmControlPanel.add(bankNameLabel, gbc_bankNameLabel); bankNameTextField = new JTextField();
     * bankNameTextField.addMouseListener(new ColorResetActionLiteneor(bankNameTextField)); GridBagConstraints
     * gbc_bankNameTextField = new GridBagConstraints(); gbc_bankNameTextField.insets = new Insets(0, 0, 5,
     * 0); gbc_bankNameTextField.fill = GridBagConstraints.HORIZONTAL; gbc_bankNameTextField.gridx = 3;
     * gbc_bankNameTextField.gridy = 9; frmControlPanel.add(bankNameTextField, gbc_bankNameTextField);
     * bankNameTextField.setColumns(10); bankCodeLabel = new JLabel("Bank Code"); GridBagConstraints
     * gbc_bankCodeLabel = new GridBagConstraints(); gbc_bankCodeLabel.anchor =
     * GridBagConstraints.BELOW_BASELINE_TRAILING; gbc_bankCodeLabel.insets = new Insets(0, 0, 5, 5);
     * gbc_bankCodeLabel.gridx = 0; gbc_bankCodeLabel.gridy = 10; frmControlPanel.add(bankCodeLabel,
     * gbc_bankCodeLabel); bankCodeTextField = new JTextField(); bankCodeTextField.addMouseListener(new
     * ColorResetActionLiteneor(bankCodeTextField)); GridBagConstraints gbc_bankCodeTextField = new
     * GridBagConstraints(); gbc_bankCodeTextField.insets = new Insets(0, 0, 5, 5); gbc_bankCodeTextField.fill
     * = GridBagConstraints.HORIZONTAL; gbc_bankCodeTextField.gridx = 1; gbc_bankCodeTextField.gridy = 10;
     * frmControlPanel.add(bankCodeTextField, gbc_bankCodeTextField); bankCodeTextField.setColumns(10);
     * bankAcclabel = new JLabel("Bank Acct"); GridBagConstraints gbc_bankAcclabel = new GridBagConstraints();
     * gbc_bankAcclabel.anchor = GridBagConstraints.EAST; gbc_bankAcclabel.insets = new Insets(0, 0, 5, 5);
     * gbc_bankAcclabel.gridx = 2; gbc_bankAcclabel.gridy = 10; frmControlPanel.add(bankAcclabel,
     * gbc_bankAcclabel); bankAccTextField = new JTextField(); bankAccTextField.addMouseListener(new
     * ColorResetActionLiteneor(bankAccTextField)); GridBagConstraints gbc_bankAccTextField = new
     * GridBagConstraints(); gbc_bankAccTextField.insets = new Insets(0, 0, 5, 0); gbc_bankAccTextField.fill =
     * GridBagConstraints.HORIZONTAL; gbc_bankAccTextField.gridx = 3; gbc_bankAccTextField.gridy = 10;
     * frmControlPanel.add(bankAccTextField, gbc_bankAccTextField); bankAccTextField.setColumns(10);
     * creditCardlabel = new JLabel("Credit card"); GridBagConstraints gbc_creditCardlabel = new
     * GridBagConstraints(); gbc_creditCardlabel.anchor = GridBagConstraints.EAST; gbc_creditCardlabel.insets
     * = new Insets(0, 0, 5, 5); gbc_creditCardlabel.gridx = 0; gbc_creditCardlabel.gridy = 11;
     * frmControlPanel.add(creditCardlabel, gbc_creditCardlabel); creditCardtextField = new JTextField();
     * GridBagConstraints gbc_creditCardtextField = new GridBagConstraints(); gbc_creditCardtextField.insets =
     * new Insets(0, 0, 5, 5); gbc_creditCardtextField.fill = GridBagConstraints.HORIZONTAL;
     * gbc_creditCardtextField.gridx = 1; gbc_creditCardtextField.gridy = 11;
     * creditCardtextField.addMouseListener(new ColorResetActionLiteneor(creditCardtextField));
     * frmControlPanel.add(creditCardtextField, gbc_creditCardtextField); creditCardtextField.setColumns(10);
     * userGroupLabel = new JLabel("User Group"); GridBagConstraints gbc_userGroupLabel = new
     * GridBagConstraints(); gbc_userGroupLabel.anchor = GridBagConstraints.EAST; gbc_userGroupLabel.insets =
     * new Insets(0, 0, 5, 5); gbc_userGroupLabel.gridx = 2; gbc_userGroupLabel.gridy = 11;
     * frmControlPanel.add(userGroupLabel, gbc_userGroupLabel); // ========================================
     * Vector<String> userGrupsitems = new Vector<String>(); Vector<String> countriesGrupsitems = new
     * Vector<String>(); Vector<String> citiesItems = new Vector<String>(); try { cfg = new Configuration();
     * cfg.configure("hibernate.cfg.xml"); factory = cfg.buildSessionFactory(); s = factory.openSession(); tx
     * = s.beginTransaction(); Query query = s.createQuery("FROM UsersGroups"); List< ? > results =
     * query.list(); Iterator< ? > res = results.iterator(); while (res.hasNext()) { UsersGroups gr =
     * (UsersGroups)res.next(); userGrupsitems.add(gr.getGroupName()); } query = s.createQuery("FROM Cities");
     * results = query.list(); results = query.list(); res = results.iterator(); while (res.hasNext()) {
     * Cities ct = (Cities)res.next(); citiesItems.add(ct.getName()); } query =
     * s.createQuery("FROM Countries"); results = query.list(); results = query.list(); res =
     * results.iterator(); while (res.hasNext()) { Countries c = (Countries)res.next();
     * countriesGrupsitems.add(c.getName()); } } catch (Exception e1) { e1.printStackTrace(); } finally {
     * tx.commit(); s.close(); } countrylabel = new JLabel("Country"); GridBagConstraints gbc_countrylabel =
     * new GridBagConstraints(); gbc_countrylabel.anchor = GridBagConstraints.EAST; gbc_countrylabel.insets =
     * new Insets(0, 0, 5, 5); gbc_countrylabel.gridx = 0; gbc_countrylabel.gridy = 5;
     * frmControlPanel.add(countrylabel, gbc_countrylabel); countryCombo = new
     * JComboBox<String>(countriesGrupsitems); GridBagConstraints gbc_countryCombo = new GridBagConstraints();
     * gbc_countryCombo.insets = new Insets(0, 0, 5, 5); gbc_countryCombo.fill =
     * GridBagConstraints.HORIZONTAL; gbc_countryCombo.gridx = 1; gbc_countryCombo.gridy = 5;
     * frmControlPanel.add(countryCombo, gbc_countryCombo); cityLabel = new JLabel("City"); GridBagConstraints
     * gbc_cityLabel = new GridBagConstraints(); gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
     * gbc_cityLabel.anchor = GridBagConstraints.EAST; gbc_cityLabel.gridx = 2; gbc_cityLabel.gridy = 5;
     * frmControlPanel.add(cityLabel, gbc_cityLabel); cityComboBox = new JComboBox<String>(citiesItems);
     * GridBagConstraints gbc_cityComboBox = new GridBagConstraints(); gbc_cityComboBox.insets = new Insets(0,
     * 0, 5, 0); gbc_cityComboBox.fill = GridBagConstraints.HORIZONTAL; gbc_cityComboBox.gridx = 3;
     * gbc_cityComboBox.gridy = 5; frmControlPanel.add(cityComboBox, gbc_cityComboBox); userGroupcombo = new
     * JComboBox<String>(userGrupsitems); GridBagConstraints gbc_userGroupcombo = new GridBagConstraints();
     * gbc_userGroupcombo.insets = new Insets(0, 0, 5, 0); gbc_userGroupcombo.fill =
     * GridBagConstraints.HORIZONTAL; gbc_userGroupcombo.gridx = 3; gbc_userGroupcombo.gridy = 11;
     * frmControlPanel.add(userGroupcombo, gbc_userGroupcombo); btnSave = new JButton("Save");
     * GridBagConstraints gbc_btnSave = new GridBagConstraints(); gbc_btnSave.fill =
     * GridBagConstraints.HORIZONTAL; gbc_btnSave.insets = new Insets(0, 0, 5, 5); gbc_btnSave.gridx = 1;
     * gbc_btnSave.gridy = 13; frmControlPanel.add(btnSave, gbc_btnSave); btnCancel = new JButton("Cancel");
     * GridBagConstraints gbc_btnCancel = new GridBagConstraints(); gbc_btnCancel.fill =
     * GridBagConstraints.HORIZONTAL; gbc_btnCancel.insets = new Insets(0, 0, 5, 5); gbc_btnCancel.gridx = 2;
     * gbc_btnCancel.gridy = 13; frmControlPanel.add(btnCancel, gbc_btnCancel); JMenuBar menuBar = new
     * JMenuBar(); // frmControlPanel.setJMenuBar(menuBar); JButton btnAddUsers = new JButton("Add Users");
     * menuBar.add(btnAddUsers); JButton addCityBot = new JButton("Add City"); menuBar.add(addCityBot);
     * btnAddCountry = new JButton("Add Country"); menuBar.add(btnAddCountry); // frame.pack(); //
     * frame.setVisible(true); // setDefaultCloseOperation(z) btnSave.addActionListener(new ActionListener() {
     * @Override public void actionPerformed(ActionEvent e) { if (checkCorrect()) { newUser = new Users(); //
     * newUser.setUserId(Integer.parseInt(userIdtextField.getText()));
     * newUser.setUserName(userNameField.getText().trim()); newUser.setPassword(new
     * String(passwordField.getPassword())); newUser.setFirstName(firstNametextField.getText().trim());
     * newUser.setLastName(lastNametextField.getText().trim());
     * newUser.setCompany(companytextField.getText().trim()); newUser.setMol(moltextField.getText().trim());
     * newUser.setAddress(addressTextField.getText().trim());
     * newUser.setAddress2(adress2textField.getText().trim());
     * newUser.setPhone(phoneTextField.getText().trim()); newUser.setFax(faxTextFild.getText().trim());
     * newUser.setEmail(emailTextFild.getText().trim()); newUser.setTaxNo(taxTextField.getText().trim());
     * newUser.setBulstat(bulstatTextField.getText().trim());
     * newUser.setBankAcct(bankAccTextField.getText().trim());
     * newUser.setBankCode(bankCodeTextField.getText().trim()); if
     * (!("".equals(creditCardtextField.getText().trim()))) {
     * newUser.setCardNumber(Integer.parseInt(creditCardtextField.getText().trim())); } // UsersGroups group =
     * new UsersGroups(); // String usersGroupID = null; // newUser.setUsersGroups(usersGroupID); try { cfg =
     * new Configuration(); cfg.configure("hibernate.cfg.xml"); factory = cfg.buildSessionFactory(); s =
     * factory.openSession(); tx = s.beginTransaction(); s.save(newUser); okMeesage("Save successful"); }
     * catch (Exception e1) { e1.printStackTrace(); } finally { tx.commit(); s.close(); } //
     * frmControlPanel.dispose(); } } }); return frmControlPanel; }
     */
}
