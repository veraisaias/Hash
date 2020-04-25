package com.politecnicomalaga.Controlador;

import com.politecnicomalaga.Algoritmo.Algoritmo;
import com.politecnicomalaga.Algoritmo.GenerarAlgoritmo;
import com.politecnicomalaga.DAO.CredencialDAO;
import com.politecnicomalaga.DataTransfer.DataTransfer;
import com.politecnicomalaga.Modelo.Credencial;
import com.politecnicomalaga.Modelo.Credenciales;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorCredenciales {
    private CredencialDAO credencialDAO;
    private Credenciales listaCredenciales;

    public ControladorCredenciales() throws SQLException {
        credencialDAO = new CredencialDAO();
        listaCredenciales = new Credenciales();
    }

    public ArrayList<Credencial> getCredenciales(){
        ArrayList<Credencial> usersList =  new ArrayList<>();
        try {
            usersList = credencialDAO.getCredenciales();
            listaCredenciales.addCredenciales(usersList);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return usersList;
    }

    public Credencial addCredencial(Credencial credencial) throws SQLException{
        String name = credencial.getName();
        String hash = credencial.getHash();
        int algorithm = credencial.getAlgorithm();
        String salt = credencial.getSalt();
        int id = credencialDAO.crear(name, hash, algorithm, salt);
        System.out.println(id);
        Credencial credencial1 = new Credencial(id,name,hash,algorithm,salt);
        listaCredenciales.addCredencial(credencial1);
        return credencial1;
    }

    public Credencial getCredencialByID(int id) throws SQLException{
        return listaCredenciales.getCredencialByID(id);
    }

    public Credencial getCredencialByName(String name) throws SQLException{
        return listaCredenciales.getCredencialByName(name);
    }

    public Algoritmo getAlgorimoByID(int id) throws NoSuchAlgorithmException {
        return GenerarAlgoritmo.getAlgoritmo(id);
    }

    public DataTransfer getPasswordHashSalt (String password, int algortimoID) throws NoSuchAlgorithmException {
        DataTransfer temp = new DataTransfer();
        Algoritmo algoritmo = getAlgorimoByID(algortimoID);
        String hashedPassword = algoritmo.getPasswordHash(password);
        String salt = algoritmo.getSalt();
        temp.put("hash",hashedPassword);
        temp.put("salt",salt);
        return temp;
    }

    public boolean comprobarPassword(DataTransfer datos) throws NoSuchAlgorithmException {
        DataTransfer data = new DataTransfer();
        Credencial credencial = (Credencial) datos.get("credential");
        String password = (String) datos.get("password");
        String hash = credencial.getHash();
        String salt = credencial.getSalt();
        data.put("password",password);
        data.put("passwordHash",hash);
        data.put("salt",salt);
        Algoritmo algoritmo = GenerarAlgoritmo.getAlgoritmo(credencial.getAlgorithm());
        return algoritmo.checkPassword(data);
    }

    public boolean usuarioExiste(String name) throws SQLException {
        return getCredencialByName(name) != null;
    }
}
