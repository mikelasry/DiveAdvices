package com.example.diveadvices.model;

import androidx.annotation.NonNull;

import java.util.HashMap;


public class User {
    private String email;
    private String name;

    public final String NAME = "name";
    public final String ID = "email";

    public User( String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public User(){}

    public HashMap<String, String> jsonify(){
        HashMap<String,String> map = new HashMap<>();
        map.put(NAME,this.name);
        map.put(ID,this.email);
        return map;
    }

    public User fromJson(HashMap<String,String> map){
        String name="Jhon Doe", email="nomail@user.com";
        if (map.containsKey(NAME)) name = map.get(NAME);
        if (map.containsKey(ID)) email = map.get(ID);
        return new User(name, email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
