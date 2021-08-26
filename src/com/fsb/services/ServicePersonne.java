package com.fsb.services;

import com.fsb.models.Personne;
import com.fsb.utils.DataSource;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePersonne implements IService<Personne> {
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;

    @Override
    public void ajouterPersonne(Personne t) {
        try {
            String requete = "INSERT INTO user(username, username_canonical,email, password, roles,image_id,cin,availability,date,sex,address,phone ) VALUES (?,?,?,?,?,?,?,'valable',?,?,?,?)";
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getUsername_canonical());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setString(5, t.getRoles());
            pst.setString(6, t.getImage_id());
            pst.setInt(7, t.getCin());
            pst.setString(8, t.getAvailability());
            pst.setDate(8, (Date) t.getDate());
            pst.setString(8, t.getSex());
            pst.setString(8, t.getAddress());
            pst.setString(8, t.getPhone());




            pst.executeUpdate();
            System.out.println("Personne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            }

    }

    @Override
    public void supprimerPersonne(Personne t) {
        try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Personne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierPersonne(Personne t) {
        try {
            String requete = "UPDATE user SET username=?,username_canonical=?,email=?,password=?,roles=?,image_id=?,cin=?,phone=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, t.getUsername());
            pst.setString(2, t.getUsername_canonical());
            pst.setString(3, t.getEmail());

            pst.setString(4, t.getPassword());
            pst.setString(5, t.getRoles());
            pst.setString(6, t.getImage_id());
            pst.setInt(7, t.getCin());
            pst.setString(8, t.getPhone());
            pst.setInt(9, t.getId());


            pst.executeUpdate();

            System.out.println(t.getId());
            System.out.println(pst);
            System.out.println(t.toString());
            System.out.println("Personne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }

    @Override

    public List<Personne> getAllUsers() {
        String req = "SELECT * FROM user where roles NOT LIKE '%ROLE_SUPER_ADMIN%'";
        List<Personne> users = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                Personne p = new Personne(Integer.parseInt(rs.getString("id")),Integer.parseInt(rs.getString("cin")), rs.getString("username"),rs.getString("username_canonical"), rs.getString("email"),rs.getString("password"),rs.getString("roles"),  rs.getString("image_id"),rs.getString("availability"),rs.getDate("date"),rs.getString("sex"),rs.getString("address"),rs.getString("phone"));

                users.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public Personne getUserByuserName(String username) {
        try {
            String req = "select * from user where username=?";
            Personne u = null;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                u = new Personne(rs.getInt("cin"),rs.getString("username"), rs.getString("username_canonical"), rs.getString("email"),rs.getString("password"),rs.getString("roles"),rs.getString("image_id"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
