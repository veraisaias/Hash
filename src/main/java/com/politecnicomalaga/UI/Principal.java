package com.politecnicomalaga.UI;

import com.politecnicomalaga.Controlador.ControladorCredenciales;
import com.politecnicomalaga.DataTransfer.DataTransfer;
import com.politecnicomalaga.DataTransfer.DataTransferToCredencial;
import com.politecnicomalaga.Modelo.Credencial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Principal {
    private JPanel pnlPrincipal, pnlTitle, pnlActions, pnlRegistry, pnlCredentialsList, pnlResult;
    private JLabel lblTitle;
    private JButton btnRegister, btnValidate;
    private JScrollPane jspCredentialsList, jspResult;
    private JList lstCredentials, lstRenderer;
    private JPanel pnlCentral;
    private DefaultListModel<Credencial> lstModelCredenciales;
    private DefaultListModel<DataTransfer> lstModelRegistro;
    private RenderListaCredenciales lstRenderCredenciales;
    private RenderListaResultados lstRenderResultados;

    public Principal(ControladorCredenciales controlador) {
        lstModelCredenciales = new DefaultListModel<>();
        lstRenderCredenciales = new RenderListaCredenciales();

        updateUserList(controlador.getCredenciales());

        lstCredentials.setModel(lstModelCredenciales);
        lstCredentials.setCellRenderer(lstRenderCredenciales);
        lstCredentials.setFixedCellWidth(pnlPrincipal.getWidth());
        lstCredentials.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Estiramos la celda a 1000 px de altura
                lstCredentials.setFixedCellHeight(1000);
                // Asignamos el valor -1 para que se recalcule la altura
                lstCredentials.setFixedCellHeight(-1);
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataTransfer data = new DataTransfer();
                NuevoCredencial nuevoCredencial = new NuevoCredencial(data);
                if (!(Boolean) data.get("cerrado")){
                    String password = (String) data.get("password");
                    int algoritmoID = Integer.parseInt((String) data.get("algorithm"));
                    String name = (String) data.get("name");
                    try {
                        if (!controlador.usuarioExiste(name)){
                            DataTransfer temp = controlador.getPasswordHashSalt(password,algoritmoID);
                            String hash = (String) temp.get("hash");
                            String salt = (String) temp.get("salt");
                            if (name != null){
                                lstModelCredenciales.addElement(controlador.addCredencial(DataTransferToCredencial.transformar(name,hash,salt,algoritmoID)));
                            }
                        } else {
                            System.out.println("Usuario ya existe.");
                        }

                    } catch (NoSuchAlgorithmException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataTransfer data = new DataTransfer();
                if (lstCredentials.isSelectionEmpty()) {
                    ComprobarCredencial comprobarCredencial = new ComprobarCredencial(data);
                    if (!(Boolean) data.get("cerrado")){
                        String writtenName = (String) data.get("writtenName");
                        try {
                            if (controlador.usuarioExiste(writtenName)){
                                data.put("credential",controlador.getCredencialByName(writtenName));
                                if (controlador.comprobarPassword(data)){
                                    data.put("checked",true);
                                } else {
                                    data.put("checked",false);
                                }
                                lstModelRegistro.addElement(data);
                            } else {
                                System.out.println("Nombre de usuario no coincide o no se encuentra registrado.");
                            }
                        } catch (SQLException | NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    int credencialID = lstModelCredenciales.getElementAt(lstCredentials.getSelectedIndex()).getId();
                    try {
                        Credencial credencial = controlador.getCredencialByID(credencialID);
                        data.put("name",credencial.getName());
                        ComprobarCredencial comprobarCredencial = new ComprobarCredencial(data);
                        if (!(Boolean) data.get("cerrado")){
                            String writtenName = (String) data.get("writtenName");
                            if (writtenName.equals(credencial.getName()) || controlador.usuarioExiste(writtenName)){
                                data.put("credential",controlador.getCredencialByName(writtenName));
                                if (controlador.comprobarPassword(data)){
                                    data.put("checked",true);
                                } else {
                                    data.put("checked",false);
                                }
                                lstModelRegistro.addElement(data);
                            } else {
                                System.out.println("Nombre de usuario no coincide o no se encuentra registrado.");
                            }
                        }
                    } catch (SQLException | NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public JPanel getPanel() {
        return pnlPrincipal;
    }

    private void updateUserList(ArrayList<Credencial> usersList){
        lstModelCredenciales.clear();
        for (Credencial uc : usersList){
            lstModelCredenciales.addElement(uc);
        }
    }

    private void createUIComponents() {
        lstRenderResultados = new RenderListaResultados();
        lstModelRegistro = new DefaultListModel<>();
        lstRenderer = new JList();
        lstRenderer.setModel(lstModelRegistro);
        lstRenderer.setCellRenderer(lstRenderResultados);
    }
}
