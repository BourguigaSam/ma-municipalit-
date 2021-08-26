package com.fsb.models;

import java.util.Date;

public class Demande {
    private int id;
    private String user_nom;
    private String description ;
    private int user_cin;
    private Date user_date;
    private String user_phone;
    private Date dateCreation;
    private String address;
    private String type;


    public Demande(String user_nom, String description, int user_cin, Date user_date, String user_phone, Date dateCreation,String address,String type) {
        this.user_nom = user_nom;
        this.description = description;
        this.user_cin = user_cin;
        this.user_date = user_date;
        this.user_phone = user_phone;
        this.dateCreation = dateCreation;
        this.address=address;
        this.type=type;
    }



    public Demande(int id , String user_nom, String description, int user_cin, Date user_date, String user_phone, Date dateCreation, String address, String type) {
        this.id=id;
        this.user_nom = user_nom;
        this.description = description;
        this.user_cin = user_cin;
        this.user_date = user_date;
        this.user_phone = user_phone;
        this.dateCreation = dateCreation;
        this.address=address;
        this.type=type;

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_nom() {
        return user_nom;
    }

    public void setUser_nom(String user_nom) {
        this.user_nom = user_nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_cin() {
        return user_cin;
    }

    public void setUser_cin(int user_cin) {
        this.user_cin = user_cin;
    }

    public Date getUser_date() {
        return user_date;
    }

    public void setUser_date(Date user_date) {
        this.user_date = user_date;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
