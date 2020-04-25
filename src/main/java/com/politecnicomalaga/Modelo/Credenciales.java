package com.politecnicomalaga.Modelo;

import java.util.ArrayList;
import java.util.TreeMap;

public class Credenciales {
    private TreeMap<Integer,Credencial> listaCredenciales;

    public Credenciales(){
        listaCredenciales = new TreeMap<>();
    }

    public ArrayList<Credencial> getListaCredenciales(){
        ArrayList<Credencial> credenciales = new ArrayList<>();
        for (Integer credencial : listaCredenciales.keySet()){
            credenciales.add(listaCredenciales.get(credencial));
        }
        return credenciales;
    }

    public void addCredenciales(ArrayList<Credencial> credenciales){
        for (Credencial credencial : credenciales){
            addCredencial(credencial);
        }
    }

    public void addCredencial(Credencial credencial){
        int key = credencial.getId();
        if (!listaCredenciales.containsKey(key)){
            listaCredenciales.put(key,credencial);
        } else {
            System.out.println("Key ya existe.");
        }
    }

    public Credencial getCredencialByID(int key){
        return listaCredenciales.get(key);
    }

    public Credencial getCredencialByName(String name){
        Credencial temp = null;
        for (Integer credencial : listaCredenciales.keySet()){
            if (listaCredenciales.get(credencial).getName().equals(name)){
                temp = listaCredenciales.get(credencial);
            }
        }
        return temp;
    }
}
