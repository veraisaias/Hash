package com.politecnicomalaga.Lector;

import io.github.cdimascio.dotenv.Dotenv;

public class LectorENV {
    Dotenv dotenv;
    public LectorENV() {
        dotenv = Dotenv.configure().load();
    }

    public String getValueOf(String string){
        return dotenv.get(string);
    }
}
