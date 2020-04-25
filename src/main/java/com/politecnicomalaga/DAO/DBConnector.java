package com.politecnicomalaga.DAO;

import com.politecnicomalaga.Lector.LectorENV;

import java.sql.*;

public class DBConnector {
    private Connection conn;

    public DBConnector() throws SQLException {
        LectorENV lector = new LectorENV();
        conn = DriverManager.getConnection(lector.getValueOf("url"), lector.getValueOf("user"), lector.getValueOf("password"));
    }

    public ResultSet query(String consulta) throws SQLException {
        return conn.prepareStatement(consulta).executeQuery();
    }

    public int update(String consulta) {
        int key  = -1;
        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY,
                    java.sql.ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(consulta,Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                key = rs.getInt(1);
            }
            rs.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return key;
    }
}
