package com.politecnicomalaga.Modelo;

public class Credencial {
    private int id, algorithm;
    private String name, hash, salt;

    public Credencial(int id, String name, String hash, int algorithm, String salt) {
        this.id = id;
        this.name = name;
        this.hash = hash;
        this.algorithm = algorithm;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Credencial{" +
                "id=" + id +
                ", algorithm=" + algorithm +
                ", name='" + name + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
