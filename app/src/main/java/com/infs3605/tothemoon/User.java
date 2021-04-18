package com.infs3605.tothemoon;


/*
INFS3605 Capstone Project T1 2021
To the Moon
Caitlin O'Dowd z5183007
Sharon Cheung z5162825
Neil Matani z5162753
Aiden Mansley z5265120
Connor Williams z5189800
Timothy Baker z5162709
*/

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.UUID;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private Boolean signedIn;

    @Ignore
    private String salt, passwordHash;

    public User() {
        this.salt = UUID.randomUUID().toString();
    }

    /*public User(String email, String fName, String lName, String DOB, String token, Boolean signedIn) {
        this.email = email;
        this.firstName = fName;
        this.lastName = lName;
        this.DOB = DOB;
        this.token = token;
        this.signedIn = signedIn;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean checkPassword(String password) {
        final String givenHash = Hashing.sha512().hashString(password + this.salt, Charset.defaultCharset()).toString();

        if(this.passwordHash.equals(givenHash)) {
            return true;
        }

        return false;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = Hashing.sha512().hashString(password + this.salt, Charset.defaultCharset()).toString();
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Boolean getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(Boolean signedIn) {
        this.signedIn = signedIn;
    }
}