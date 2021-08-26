package com.fsb.services;

import com.fsb.models.Projet;
import com.fsb.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceProjet {
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;



    public void addProjet(Projet projet){
        try {

            String req="INSERT INTO projet (name,description,numbreOfParticipant,dateDebut,dateFin) VALUES (?,?,?,?,?)";
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, projet.getName());
            pst.setString(2, projet.getDescription());
            pst.setInt(3,  projet.getNumbreOfParticipant());
            pst.setDate(4, (Date) projet.getDateDebut());
            pst.setDate(4, (Date) projet.getDateFin());

            pst.executeUpdate();
            System.out.println("Projet added !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


    public void deleteProjet(Projet projet){
        try {
            String requete = "DELETE FROM projet WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, projet.getId());
            pst.executeUpdate();
            System.out.println("Projet delete !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updateProjet(Projet projet){


        try {
            String requete = "UPDATE projet SET name=?,description=?,numbreOfParticipant=?,dateDebut=?,dateFin=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, projet.getName());
            pst.setString(2, projet.getDescription());
            pst.setInt(3,  projet.getNumbreOfParticipant());
            pst.setDate(4, (Date) projet.getDateDebut());
            pst.setDate(5, (Date) projet.getDateFin());
            pst.setInt(6,  projet.getId());


            pst.executeUpdate();

            System.out.println("Projet Updated !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }



    public List<Projet> getAllProjets(){
        String req = "SELECT * FROM projet ";
        List<Projet> projets = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                projets.add(new Projet(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("numbreOfParticipant"),rs.getDate("dateDebut"),rs.getDate("dateFin")));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return projets;
    }





}
