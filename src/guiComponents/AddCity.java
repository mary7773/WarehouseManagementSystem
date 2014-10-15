/*
 * AddCity.java
 *
 * created at Mar 14, 2014 by Todor e-mail: TodorNeykov@gmail.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package guiComponents;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Cities;
import entity.Countries;

import javax.swing.ImageIcon;

import java.awt.Rectangle;


public class AddCity
    extends JDialog
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField cityNametextField;
    private JTextField posteCodetextField_1;
    private JComboBox<String> countryComboBox;
    private Transaction tx = null;


    /**
     * Create the dialog.
     */
    public AddCity(Vector<String> vec, Map<String, Integer> countryMap)
    {
        setTitle("Create City");
        setBounds(100, 100, 250, 218);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblCityName = new JLabel("City Name");
            GridBagConstraints gbc_lblCityName = new GridBagConstraints();
            gbc_lblCityName.insets = new Insets(0, 0, 5, 5);
            gbc_lblCityName.anchor = GridBagConstraints.EAST;
            gbc_lblCityName.gridx = 0;
            gbc_lblCityName.gridy = 0;
            contentPanel.add(lblCityName, gbc_lblCityName);
        }
        {
            cityNametextField = new JTextField();
            cityNametextField.setBounds(new Rectangle(10, 0, 0, 0));
            GridBagConstraints gbc_cityNametextField = new GridBagConstraints();
            gbc_cityNametextField.insets = new Insets(0, 0, 5, 0);
            gbc_cityNametextField.fill = GridBagConstraints.HORIZONTAL;
            gbc_cityNametextField.gridx = 1;
            gbc_cityNametextField.gridy = 0;
            cityNametextField.addMouseListener(new ColorResetActionLiteneor(cityNametextField));
            contentPanel.add(cityNametextField, gbc_cityNametextField);
            cityNametextField.setColumns(10);
        }
        {
            JLabel lblPosteCode = new JLabel("Poste code");
            GridBagConstraints gbc_lblPosteCode = new GridBagConstraints();
            gbc_lblPosteCode.anchor = GridBagConstraints.EAST;
            gbc_lblPosteCode.insets = new Insets(0, 0, 5, 5);
            gbc_lblPosteCode.gridx = 0;
            gbc_lblPosteCode.gridy = 1;
            contentPanel.add(lblPosteCode, gbc_lblPosteCode);
        }
        {
            posteCodetextField_1 = new JTextField();
            GridBagConstraints gbc_posteCodetextField_1 = new GridBagConstraints();
            gbc_posteCodetextField_1.insets = new Insets(0, 0, 5, 0);
            gbc_posteCodetextField_1.fill = GridBagConstraints.HORIZONTAL;
            gbc_posteCodetextField_1.gridx = 1;
            gbc_posteCodetextField_1.gridy = 1;
            contentPanel.add(posteCodetextField_1, gbc_posteCodetextField_1);
            posteCodetextField_1.setColumns(10);
        }
        {
            JLabel lblCountry = new JLabel("Country");
            GridBagConstraints gbc_lblCountry = new GridBagConstraints();
            gbc_lblCountry.anchor = GridBagConstraints.EAST;
            gbc_lblCountry.insets = new Insets(0, 0, 0, 5);
            gbc_lblCountry.gridx = 0;
            gbc_lblCountry.gridy = 2;
            contentPanel.add(lblCountry, gbc_lblCountry);
        }
        {
            DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(vec);

            countryComboBox = new JComboBox<String>(comboModel);
            countryComboBox.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                    countryComboBox.setBorder(UIManager.getBorder("TextField.border"));
                }
            });
            GridBagConstraints gbc_countryComboBox = new GridBagConstraints();
            gbc_countryComboBox.fill = GridBagConstraints.HORIZONTAL;
            gbc_countryComboBox.gridx = 1;
            gbc_countryComboBox.gridy = 2;
            contentPanel.add(countryComboBox, gbc_countryComboBox);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setIcon(new ImageIcon(AddCity.class.getResource("/images/addi_20.png")));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                final Map<String, Integer> countryMap2 = countryMap;
                okButton.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        Cities newCity = new Cities();
                        newCity.setName(cityNametextField.getText().trim());
                        Countries c = new Countries();
                        if (checkCorrect())
                        {
//                            c.setCountryId(countryMap2.get(countryComboBox.getSelectedItem()));
                        	String con = (String) countryComboBox.getSelectedItem();
                        	c.setName(con);
                        	c.setCountryId(countryMap2.get(con));
                        	System.out.println(c.getCountryId());
                            newCity.setCountries(c);
                            Session s = GetConnectionFactory.getSessionFactory().openSession();
                            try
                            {
                                tx = s.beginTransaction();

                                s.persist(newCity);
                                tx.commit();
                            }
                            catch (Exception e)
                            {

                            }
                            finally
                            {
                                if (s != null)
                                {
                                    s.close();
                                }
                            }
                            dispose();

                        }
                    }
                });

            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setIcon(new ImageIcon(AddCity.class.getResource("/images/cancel_20.png")));
                cancelButton.addActionListener(new ActionListener()
                {

                    public void actionPerformed(ActionEvent arg0)
                    {
                        int reply = JOptionPane.showConfirmDialog(null,
                                                                  "Are you sure you want to cancel?",
                                                                  "Cancel?",
                                                                  JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION)
                        {
                            dispose();
                        }

                    }
                });
                buttonPane.add(cancelButton);

            }
        }
    }


    public boolean checkCorrect()
    {
        StringBuilder str = new StringBuilder();
        Boolean corect = true;
        if ("".equalsIgnoreCase(cityNametextField.getText().trim()))
        {
            cityNametextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            corect = false;
            str.append("\n - City name can not be empty");
        }
        if (countryComboBox.getItemCount() == 0 || countryComboBox.getSelectedItem()==null)
        {
            countryComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
            corect = false;
            str.append("\n - Select Country");
        }
        if (corect)
        {
            return true;
        }
        CreateUsers.errorMessage(str.toString());
        return false;
    }
}
