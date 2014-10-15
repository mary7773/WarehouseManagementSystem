/*
 * colorResetActionLiteneor.java
 *
 * created at Feb 23, 2014 by Todor e-mail: TodorNeykov@gmail.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package guiComponents;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * Mouse adapter action listener to reset default color of JTextFild
 * <p>
 *
 * @author Todor
 */
public class ColorResetActionLiteneor
    extends MouseAdapter
{
    private JTextField fild = null;


    /**
     * Get
     *
     * @param fild
     */
    public ColorResetActionLiteneor(JTextField fild)
    {
        this.fild = fild;
    }


    @Override
    public void mouseClicked(MouseEvent e)
    {

        fild.setBorder(UIManager.getBorder("TextField.border"));

    }

}
