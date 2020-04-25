package com.politecnicomalaga.DAO;

import com.politecnicomalaga.Modelo.Credencial;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CredencialDAO {
    private DBConnector conn;

    public CredencialDAO() throws SQLException {
        conn = new DBConnector();
    }

    public ArrayList<Credencial> getCredenciales() throws SQLException {
        ArrayList<Credencial> listaCredenciales = new ArrayList<>();
        ResultSet result = conn.query("SELECT * FROM Credential");
        while(result.next()){
            listaCredenciales.add(generarCredencial(result));
        }
        return listaCredenciales;
    }
    private Credencial generarCredencial(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String hash = result.getString("hash");
        int algorithm = result.getInt("algorithm");
        String salt = result.getString("salt");
        return new Credencial(id,name,hash,algorithm,salt);
    }

    public int crear(String name, String hash, int algorithm, String salt) throws SQLException {
        return conn.update("INSERT INTO Credential(name,hash,algorithm,salt) VALUES ('"+name+"','"+hash+"','"+algorithm+"','"+salt+"')");
    }

    public Credencial buscarPorID(int id) throws SQLException {
        Credencial user = null;
        ResultSet resultSet = conn.query("SELECT * FROM Credential WHERE id = '"+id+"'");
        while (resultSet.next()){
            user = generarCredencial(resultSet);
        }
        return user;
    }
}
