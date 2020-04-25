package com.politecnicomalaga;

import com.politecnicomalaga.Controlador.ControladorCredenciales;
import com.politecnicomalaga.UI.Principal;

import javax.swing.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        ControladorCredenciales controlador = new ControladorCredenciales();
        JFrame frame = new JFrame("User Register/Validator");
        frame.setContentPane(new Principal(controlador).getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
