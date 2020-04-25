package com.politecnicomalaga.Algoritmo;

import com.politecnicomalaga.DataTransfer.DataTransfer;

import java.security.NoSuchAlgorithmException;

public interface Algoritmo {
    public String getPasswordHash (String password) throws NoSuchAlgorithmException;
    public String getSalt();
    public boolean checkPassword(DataTransfer datos) throws NoSuchAlgorithmException;
}