package com.politecnicomalaga.Algoritmo;

import com.politecnicomalaga.DataTransfer.DataTransfer;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt implements Algoritmo{
    private String salt;

    public Bcrypt(){
    }

    @Override
    public String getPasswordHash(String password) {
        return BCrypt.hashpw(password, generateSalt());
    }

    @Override
    public String getSalt() {
        return salt;
    }

    @Override
    public boolean checkPassword(DataTransfer datos) {
        String password = (String) datos.get("password");
        String passwordHash = (String) datos.get("passwordHash");
        return BCrypt.checkpw(password,passwordHash);
    }

    public String generateSalt() {
        String temp = BCrypt.gensalt(12);
        setSalt(temp);
        return temp;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
