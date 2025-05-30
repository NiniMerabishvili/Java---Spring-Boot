package com.postgresql.NetWorkers.entities;


import com.postgresql.NetWorkers.enums.UserType;

public class LoginRequest {
    private String email;
    private String password;

    // Constructors, getters, and setters

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

}

