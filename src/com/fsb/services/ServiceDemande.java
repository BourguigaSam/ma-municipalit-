package com.fsb.services;


import com.fsb.models.Car;
import com.fsb.models.Demande;
import com.fsb.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDemande {
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;

    public void addDemande(Demande demande) {


        try {

            String req="INSERT INTO demande (user_nom,description,user_cin,user_date,user_phone,dateCreation,address,type) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, demande.getUser_nom());
            pst.setString(2, demande.getDescription());
            pst.setInt(3, demande.getUser_cin());
            pst.setDate(4, (Date) demande.getUser_date());
            pst.setString(5, demande.getUser_phone());
            pst.setDate(6, (Date) demande.getDateCreation());
            pst.setString(7, demande.getAddress());
            pst.setString(8, demande.getType());


            pst.executeUpdate();
            System.out.println("Demande added !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }
    public void deleteDemande(Demande demande){
        try {
            String requete = "DELETE FROM demande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,demande.getId());
            pst.executeUpdate();
            System.out.println("demande supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updateDemande(Demande demande){

        try {
            String requete = "UPDATE demande SET user_nom=?,description=?,user_cin=?,user_date=?,user_phone=?,dateCreation=?,address=?,type=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);


            pst.setString(1, demande.getUser_nom());
            pst.setString(2, demande.getDescription());
            pst.setInt(3, demande.getUser_cin());
            pst.setDate(4, (Date) demande.getUser_date());
            pst.setString(5, demande.getUser_phone());
            pst.setDate(6, (Date) demande.getDateCreation());
            pst.setString(7, demande.getAddress());
            pst.setString(8, demande.getType());

            pst.setInt(9, demande.getId());
            pst.executeUpdate();

            System.out.println("deamnde modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }

    public List<Demande> getAllDemandes(){
        String req = "SELECT * FROM demande ";
        List<Demande> demandes = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                demandes.add(new Demande(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(8)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return demandes;
    }





}
