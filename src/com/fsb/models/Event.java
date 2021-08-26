package com.fsb.models;

import java.util.Date;



public class Event {
    int id;
    String name;
    String description;
    Date date_debut;
    Date date_fin;
    String image_id;
    String creator;
    String materiels;
    String car;

    public Event(int id , String name, String description, Date date_debut, Date date_fin,String image_id,String creator, String materiels,String car){
        this.id=id;
        this.name=name;
        this.description=description;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
        this.image_id=image_id;
        this.creator=creator;
        this.materiels=materiels;
        this.car=car;


    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMateriels() {
        return materiels;
    }

    public void setMateriels(String materiels) {
        this.materiels = materiels;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Event(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", image_id='" + image_id + '\'' +
                ", creator='" + creator + '\'' +
                ", materiels='" + materiels + '\'' +
                ", car='" + car + '\'' +
                '}';
    }
}
