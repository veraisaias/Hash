package com.politecnicomalaga.UI;

import com.politecnicomalaga.DataTransfer.DataTransfer;

import javax.swing.*;
import java.awt.event.*;
import java.security.GeneralSecurityException;

public class NuevoCredencial extends JDialog{
    private JPanel pnlTitle, pnlNewUser, pnlValues, pnlActions;
    private JLabel lblTitle, lblName, lblPassword, lblAlgorithm;
    private JButton btnAccept, btnCancel;
    private JTextField txtName;
    private JPasswordField txtPassword;
    private JComboBox cbAlgorithm;
    private DataTransfer data;

    public NuevoCredencial(DataTransfer data){
        this.data = data;
        setContentPane(pnlNewUser);
        setModal(true);
        getRootPane().setDefaultButton(btnAccept);

        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (GeneralSecurityException ex) {
                    ex.printStackTrace();
                }
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
        pnlNewUser.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setLocationRelativeTo(SwingUtilities.getRoot(null));
        pack();
        setVisible(true);
    }

    private void onOK() throws GeneralSecurityException {
        data.put("name",txtName.getText());
        data.put("password",txtPassword.getText());
        if (cbAlgorithm.getSelectedItem().toString().equals("bcrypt")) {
            data.put("algorithm", "1");
        } else {
            data.put("algorithm", "2");
        }
        data.put("cerrado",false);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        data.put("cerrado",true);
        dispose();
    }

    private void createUIComponents() {
        cbAlgorithm = new JComboBox();
        cbAlgorithm.addItem("bcrypt");
        cbAlgorithm.addItem("SHA3-512");
    }
}
