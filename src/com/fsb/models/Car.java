package com.fsb.models;

public class Car {
    int id;
    String matricule ;
    String type ;
    String modele;
    String id_image ;
    String availability;


    public Car(){}


    public Car(int id,String matricule, String type, String modele, String id_image, String availability) {
        this.id=id;
        this.matricule = matricule;
        this.type = type;
        this.modele = modele;
        this.id_image = id_image;
        this.availability=availability;
    }

    public Car( String type, String modele, String id_image) {
        this.type = type;
        this.modele = modele;
        this.id_image = id_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    @Override
    public String toString() {
        return "Car{" +
                "matricule=" + matricule +
                ", type='" + type + '\'' +
                ", modele='" + modele + '\'' +
                ", id_image='" + id_image + '\'' +
                '}';
    }
}
