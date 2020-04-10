package com.example.myapplication.bean;

import java.io.Serializable;

public class ModuleBean implements Serializable {
    private String title;
    private int image;
    private int id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public ModuleBean(String title, int image, int id) {
        this.title = title;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
