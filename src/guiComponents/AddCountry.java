/*
 * AddCountry.java
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Countries;


public class AddCountry
    extends JDialog
{

    /** field <code>serialVersionUID</code> */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField cNametextField;


    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        try
        {
            AddCountry dialog = new AddCountry();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Create the dialog.
     */
    public AddCountry()
    {
        setBounds(100, 100, 303, 162);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblCountryName = new JLabel("Country Name");
            GridBagConstraints gbc_lblCountryName = new GridBagConstraints();
            gbc_lblCountryName.insets = new Insets(0, 0, 5, 5);
            gbc_lblCountryName.anchor = GridBagConstraints.EAST;
            gbc_lblCountryName.gridx = 0;
            gbc_lblCountryName.gridy = 0;
            contentPanel.add(lblCountryName, gbc_lblCountryName);
        }
        {
            cNametextField = new JTextField();
            GridBagConstraints gbc_textField = new GridBagConstraints();
            gbc_textField.insets = new Insets(0, 0, 5, 0);
            gbc_textField.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField.gridx = 1;
            gbc_textField.gridy = 0;
            contentPanel.add(cNametextField, gbc_textField);
            cNametextField.addMouseListener(new ColorResetActionLiteneor(cNametextField));
            cNametextField.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setIcon(new ImageIcon(AddCountry.class.getResource("/images/addi_20.png")));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener()
                {

                    @Override
                    public void actionPerformed(ActionEvent arg0)
                    {
                        Countries newCountry = new Countries();
                        newCountry.setName(cNametextField.getText().trim());
                        Session s = GetConnectionFactory.getSessionFactory().openSession();
                        Transaction tx = null;
                        if (checkCorrect())
                        {
                            try
                            {
                                tx = s.beginTransaction();

                                s.persist(newCountry);
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
                cancelButton.setIcon(new ImageIcon(AddCountry.class.getResource("/images/cancel_20.png")));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }


    public boolean checkCorrect()
    {
        StringBuilder str = new StringBuilder();
        Boolean corect = true;
        if ("".equalsIgnoreCase(cNametextField.getText().trim()))
        {
            cNametextField.setBorder(BorderFactory.createLineBorder(Color.RED));
            corect = false;
            str.append("\n - Country name can not be empty");
        }

        if (corect)
        {
            return true;
        }
        CreateUsers.errorMessage(str.toString());
        return false;
    }
}
