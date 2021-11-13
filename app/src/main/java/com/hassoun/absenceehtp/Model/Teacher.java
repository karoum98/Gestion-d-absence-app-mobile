package com.hassoun.absenceehtp.Model;

import java.util.SplittableRandom;

public class Teacher {
    private String name;
    private String password;
    private String imageURL;

    public Teacher() {
    }

    public Teacher(String name, String password, String imageURL) {
        this.name = name;
        this.password = password;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return imageURL;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
    }
}
