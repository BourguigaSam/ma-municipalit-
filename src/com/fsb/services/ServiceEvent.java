package com.fsb.services;

import com.fsb.models.Event;
import com.fsb.models.Personne;
import com.fsb.utils.DataSource;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceEvent {

    Connection cnx = DataSource.getInstance().getCnx();


    public void addEvent(Event event){
        try {

        String req="INSERT INTO event (name,description,date_debut,date_fin,image_id) VALUES (?,?,?,?,?)";
        PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, event.getName());
            pst.setString(2, event.getDescription());
            pst.setObject(3, event.getDate_debut());
            pst.setObject(4, event.getDate_fin());
            pst.setString(5,  event.getImage_id());

            pst.executeUpdate();
            System.out.println("Event ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }


    public void deleteEvent(Event event){
        try {
            String requete = "DELETE FROM event WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, event.getId());
            pst.executeUpdate();
            System.out.println("Event supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }




    public void updateEvent(Event event){


        try {
            String requete = "UPDATE event SET name=?,description=?,date_debut=?,date_fin=?,image_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, event.getName());
            pst.setString(2, event.getDescription());
            pst.setDate(3, Date.valueOf(String.valueOf((Date) event.getDate_debut())));
            pst.setDate(4, Date.valueOf(String.valueOf((Date) event.getDate_fin())));
            pst.setString(5,  event.getImage_id());

            pst.setInt(6, event.getId());
            pst.executeUpdate();

            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }

    public List<Event> getAllEvents(){
        String req = "SELECT * FROM event ";
        List<Event> event = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
            event.add(new Event(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getDate("date_debut"),rs.getDate("date_fin"),rs.getString("image_id"),rs.getString("creator"),rs.getString("materiels"),rs.getString("car")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return event;
    }

}




