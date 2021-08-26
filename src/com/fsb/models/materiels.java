

package com.fsb.models;

import java.util.Date;

public class materiels {
    int id ;
    String nom;
    String category ;
    Date date ;
    int Stock;

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public materiels(int id, String nom, String category, Date date , int Stock ) {
        this.id = id;
        this.nom = nom;
        this.category = category;
        this.date = date;
        this.Stock=Stock;
    }

    public materiels() {
    }

    public materiels( String nom, String category,  Date date) {
        this.nom = nom;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "materiels{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                ", Stock=" + Stock +
                '}';
    }
}
