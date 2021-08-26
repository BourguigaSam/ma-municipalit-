package com.fsb.services;

import com.fsb.models.Event;
import com.fsb.models.Personne;
import com.fsb.models.permission;
import com.fsb.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePermission {

    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;


    public void addPermission(permission permis){
        try {

            String req="INSERT INTO permission (name,description) VALUES (?,?)";
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, permis.getName());
            pst.setString(2, permis.getDescription());


            pst.executeUpdate();
            System.out.println("permission ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }



    public void deletePermission(permission permis){
        try {
            String requete = "DELETE FROM permission WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, permis.getId());
            pst.executeUpdate();
            System.out.println("permission supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updatePermission(permission permis){


        try {
            String requete = "UPDATE permission SET name=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, permis.getName());
            pst.setString(2, permis.getDescription());

            pst.setInt(3, permis.getId());
            pst.executeUpdate();

            System.out.println("permission modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }



    public List<permission> getAllPermission(){
        String req = "SELECT * FROM permission ";
        List<permission> permis = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                permis.add(new permission(rs.getInt(1),rs.getString(2),rs.getString(3)));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return permis;
    }


}
