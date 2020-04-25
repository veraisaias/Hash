package com.politecnicomalaga.UI;


import com.politecnicomalaga.DataTransfer.DataTransfer;

import javax.swing.*;
import java.awt.event.*;

public class ComprobarCredencial extends JDialog{
    private JPanel pnlTitle, pnlValues, pnlActions;
    private JLabel lblTitle, lblName, lblPassword;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JButton btnAccept, btnCancel;
    private JPanel pnlValidateCredential;
    private DataTransfer data;

    public ComprobarCredencial(DataTransfer data){
        this.data = data;
        txtName.setText((String) data.get("name"));
        setContentPane(pnlValidateCredential);
        setModal(true);
        getRootPane().setDefaultButton(btnAccept);

        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pnlValidateCredential.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setLocationRelativeTo(SwingUtilities.getRoot(null));
        pack();
        setVisible(true);
    }

    private void onOK() {
        data.put("password",txtPassword.getText());
        data.put("writtenName", txtName.getText());
        data.put("cerrado",false);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        data.put("cerrado",true);
        dispose();
    }
}
