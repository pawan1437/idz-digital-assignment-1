package com.android.registrationapp;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String dob;
    private String email;

    public User(String name, String dob, String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }
}

