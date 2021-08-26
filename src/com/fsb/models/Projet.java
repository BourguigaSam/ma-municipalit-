package com.fsb.models;

import java.util.Date;

public class Projet {
    int id;
    String name;
    String Description ;
    int numbreOfParticipant;
    Date dateDebut;
    Date dateFin;



    public Projet(int id, String name, String description, int numbreOfParticipant, Date dateDebut, Date dateFin) {
        this.id = id;
        this.name = name;
        Description = description;
        this.numbreOfParticipant = numbreOfParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public Projet( String name, String description, int numbreOfParticipant, Date dateDebut, Date dateFin) {
        this.name = name;
        Description = description;
        this.numbreOfParticipant = numbreOfParticipant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getNumbreOfParticipant() {
        return numbreOfParticipant;
    }

    public void setNumbreOfParticipant(int numbreOfParticipant) {
        this.numbreOfParticipant = numbreOfParticipant;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", numbreOfParticipant=" + numbreOfParticipant +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }

}




