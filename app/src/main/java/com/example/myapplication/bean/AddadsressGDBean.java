package com.example.myapplication.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AddadsressGDBean {
    @Id(autoincrement = true)
    Long id;
    @Unique
    String name;
    String img;
    String address;
    String beizhu;
    String bizhongid;
    @Generated(hash = 1159367606)
    public AddadsressGDBean(Long id, String name, String img, String address,
            String beizhu, String bizhongid) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.address = address;
        this.beizhu = beizhu;
        this.bizhongid = bizhongid;
    }
    @Generated(hash = 1115468013)
    public AddadsressGDBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBeizhu() {
        return this.beizhu;
    }
    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
    public String getBizhongid() {
        return this.bizhongid;
    }
    public void setBizhongid(String bizhongid) {
        this.bizhongid = bizhongid;
    }
}
