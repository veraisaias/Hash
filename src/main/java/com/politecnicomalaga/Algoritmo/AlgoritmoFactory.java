package com.politecnicomalaga.Algoritmo;

import java.security.NoSuchAlgorithmException;

public class AlgoritmoFactory {
    public Algoritmo getAlgoritmo (int tipoAlgoritmo) throws NoSuchAlgorithmException {
        switch (tipoAlgoritmo){
            case 1: return new Bcrypt();
            case 2: return new SHA3_512();
            default: return new Bcrypt();
        }
    }
}
