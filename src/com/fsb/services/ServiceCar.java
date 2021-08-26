package com.fsb.services;

import com.fsb.models.Car;
import com.fsb.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceCar {



    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;


    public void addCar(Car car) {


        try {

            String req="INSERT INTO car (matricule,type,modele,image_id,availability) VALUES (?,?,?,?,'valable')";
            PreparedStatement pst =cnx.prepareStatement(req);

            pst.setString(1, car.getMatricule());
            pst.setString(2, car.getType());
            pst.setString(3, car.getModele());
            pst.setString(4, car.getId_image());
            pst.setString(5, car.getAvailability());


            pst.executeUpdate();
            System.out.println("Car added !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }



    }






    public void deleteCar(Car car){
        try {
            String requete = "DELETE FROM car WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, car.getId());
            pst.executeUpdate();
            System.out.println("Car supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }


    public void updateCar(Car car){

        try {
            String requete = "UPDATE car SET matricule=?,type=?,modele=?,image_id=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, car.getMatricule());
            pst.setString(2,  car.getType());

            pst.setString(3, car.getModele());
            pst.setString(4, car.getId_image());


            pst.setInt(5, car.getId());
            pst.executeUpdate();

            System.out.println("Car modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }



    public List<Car> getAllCars(){
        String req = "SELECT * FROM car ";
        List<Car> cars = new ArrayList<>();
        try {
            Statement ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()){
                cars.add(new Car(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePersonne.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cars;
    }






}
