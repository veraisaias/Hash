package com.politecnicomalaga.Algoritmo;

import java.security.NoSuchAlgorithmException;

public class GenerarAlgoritmo {
    public static Algoritmo getAlgoritmo(int tipoAlgoritmo) throws NoSuchAlgorithmException {
        AlgoritmoFactory factory = new AlgoritmoFactory();
        return factory.getAlgoritmo(tipoAlgoritmo);
    }
}
