package com.infs3605.scrumlabs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int id;
    private String email;
    private String token;
    private Boolean signedIn;

    public User(int id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.signedIn = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(Boolean signedIn) {
        this.signedIn = signedIn;
    }
}