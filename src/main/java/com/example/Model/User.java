package com.example.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    private int userid;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String role;

    @NotNull
    private String password;

    public User(){

    }

    public User(String name, String email, String password,String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString(){
        return userid+" "+name+" "+" "+role+" "+password;
    }
}

