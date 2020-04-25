package com.politecnicomalaga.UI;

import com.politecnicomalaga.DataTransfer.DataTransfer;
import com.politecnicomalaga.Modelo.Credencial;

import javax.swing.*;
import java.awt.*;

public class RenderListaResultados implements ListCellRenderer<DataTransfer>{
    private JPanel pnlValidationResult, pnlValues1, pnlValues2;
    private JLabel lblName1, lblPassword1, lblName2, lblPassword2, lblHash, lblResult, lblIcon;
    private ImageIcon iconGreen, iconRed;

    public RenderListaResultados() {
        iconGreen = new ImageIcon("./src/main/resources/check.png");
        iconRed = new ImageIcon("./src/main/resources/error.png");
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends DataTransfer> list, DataTransfer value, int index, boolean isSelected, boolean cellHasFocus) {
        Credencial credencial = (Credencial) value.get("credential");
        lblName2.setText(credencial.getName());
        lblPassword2.setText((String) value.get("password"));
        lblHash.setText(credencial.getHash());
        Boolean temp = (Boolean) value.get("checked");
        if (temp){
            lblIcon.setIcon(iconGreen);
        } else {
            lblIcon.setIcon(iconRed);
        }

        return pnlValidationResult;
    }
}
