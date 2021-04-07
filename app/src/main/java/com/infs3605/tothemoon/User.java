package com.infs3605.tothemoon;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private String passwordHash;
    private Boolean signedIn;

    public User() {
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
        try {
            /*MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF_8"));

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < hash.length; i++){
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            if(sb.equals(this.passwordHash)) {
                return true;
            }*/

            if(this.passwordHash.equals(password)) {
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        try {
            /*MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF_8"));

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < hash.length; i++){
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            this.passwordHash = sb.toString();*/

            this.passwordHash = password;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean getSignedIn() {
        return signedIn;
    }

    public void setSignedIn(Boolean signedIn) {
        this.signedIn = signedIn;
    }
}