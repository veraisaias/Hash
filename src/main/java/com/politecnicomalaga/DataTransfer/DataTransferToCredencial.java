package com.politecnicomalaga.DataTransfer;

import com.politecnicomalaga.Modelo.Credencial;

public class DataTransferToCredencial {
    public static Credencial transformar(String name, String hash, String salt, int algoritmo){
        return new Credencial(-1,name,hash,algoritmo,salt);
    }
}
