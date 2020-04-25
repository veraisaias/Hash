package com.politecnicomalaga.DataTransfer;

import java.util.TreeMap;

public class DataTransfer {
    private TreeMap<String,Object> data = new TreeMap<>();

    public void put(String key, Object value){
        data.put(key,value);
    }

    public Object get(String key){
        return data.get(key);
    }

    public String getValues(){
        return String.valueOf(data.keySet());
    }
}