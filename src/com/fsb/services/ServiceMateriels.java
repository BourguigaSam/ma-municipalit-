package com.fsb.services;

import com.fsb.models.Event;
import com.fsb.models.materiels;
import com.fsb.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceMateriels {

    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;



    public void addMateriels(materiels mat){
        try {

            String req="INSERT INTO materiels (nom,category,date,stock) VALUES (?,?,?,?)";
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, mat.getNom());
            pst.setString(2, mat.getCategory());

            pst.setDate(3, (Date) mat.getDate());

            pst.setInt(4,mat.getStock());

            pst.executeUpdate();
            System.out.println("materiels ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }


    public void deleteMateriels(materiels mat){
        try {
            String requete = "DELETE FROM materiels WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, mat.getId());
            pst.executeUpdate();
            System.out.println("materiels supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }




    public void updateMateriels(materiels mat){


        try {
            String requete = "UPDATE materiels SET nom=?,category=?,date=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);


            pst.setString(1, mat.getNom());
            pst.setString(2, mat.getCategory());
            pst.setDate(3, (Date) mat.getDate());
            pst.setInt(4, mat.getId());
            pst.executeUpdate();

            System.out.println("materiels modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }



    public List<materiels> getAllMateriels(){
        String req = "SELECT * FROM materiels ";
        List<materiels> mat = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                mat.add(new materiels(rs.getInt("id"),rs.getString("nom"),rs.getString(3),rs.getDate(4),rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mat;
    }





}
