package com.politecnicomalaga.UI;

import com.politecnicomalaga.DataTransfer.DataTransfer;
import com.politecnicomalaga.Modelo.Credencial;

import javax.swing.*;
import java.awt.*;

public class RenderListaCredenciales extends JPanel implements ListCellRenderer<Credencial> {
    private JLabel lblUserName;

    public RenderListaCredenciales(){
        setOpaque(true);
        setLayout(new BorderLayout(10,10));
        lblUserName = new JLabel();
        add(lblUserName);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Credencial> list, Credencial value, int index, boolean isSelected, boolean cellHasFocus) {
        lblUserName.setOpaque(true);
        lblUserName.setText(value.getName());

        if (isSelected){
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }

        if (cellHasFocus){
            lblUserName.setBackground(new Color(51,185,255));
        } else {
            lblUserName.setBackground(Color.WHITE);
        }
        return this;
    }
}
