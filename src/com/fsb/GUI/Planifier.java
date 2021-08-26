/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fsb.models.Car;
import com.fsb.models.Personne;
import com.fsb.models.materiels;
import com.fsb.services.ServiceCar;
import com.fsb.services.ServiceMateriels;
import com.fsb.services.ServicePersonne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Planifier implements Initializable {

    public static String nam;
    public static String val;
    public static String val1;
    public static String vahicule;
    public static String mat1;
    public static int mat2;
    public static int id_mat;
    public static int id_car;
    public static int id_user;
    Stage stage= new Stage();
    Scene scene;
    @FXML
    private TableView<?> userTable;
    @FXML
    private TableColumn<?, ?> Username;
    @FXML
    private TableColumn<?, ?> UserAvailability;
    @FXML
    private TableView<?> CarTable;
    @FXML
    private TableColumn<?, ?> CarName;
    @FXML
    private TableColumn<?, ?> Caravailability;
    @FXML
    private TableView<?> materialTable;
    @FXML
    private TableColumn<?, ?> MaterialName;
    @FXML
    private TableColumn<?, ?> stockAvailability;
    @FXML
    private Circle btnClose;
    private ObservableList<Car> masterData = FXCollections.observableArrayList();
    com.fsb.services.ServiceCar ServiceCar = new ServiceCar();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePersonne ser = new ServicePersonne();
        ServiceCar ser1 = new ServiceCar();
        ServiceMateriels ser2 = new ServiceMateriels();

        ArrayList<Personne> liste = (ArrayList<Personne>) ser.getAllUsers();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        userTable.setItems(observableList);
        Username.setCellValueFactory(new PropertyValueFactory<>("username"));
        UserAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


        ArrayList<Car> liste1 = (ArrayList<Car>) ser1.getAllCars();
        ObservableList observableList1 = FXCollections.observableArrayList(liste1);
        CarTable.setItems(observableList1);
        CarName.setCellValueFactory(new PropertyValueFactory<>("matricule"));
       Caravailability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        ArrayList<materiels> liste2 = (ArrayList<materiels>) ser2.getAllMateriels();
        ObservableList observableList2 = FXCollections.observableArrayList(liste2);
        materialTable.setItems(observableList2);
        MaterialName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        stockAvailability.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }

    @FXML
    private void affecter(ActionEvent event) {

        Car ca = (Car) CarTable.getSelectionModel().getSelectedItem();
        System.out.println("=======================================");
      vahicule = ca.getMatricule();
      val = ca.getAvailability();
      id_car =ca.getId();
        System.out.println(vahicule);
        System.out.println(val);
        System.out.println("======================================");


        Personne user = (Personne) userTable.getSelectionModel().getSelectedItem();
        System.out.println("=======================================");
        nam = user.getUsername();
        val1=user.getAvailability();
        id_user = user.getId();
        System.out.println(nam);
        System.out.println(val1);
        System.out.println(id_user);

        System.out.println("======================================");



        materiels mat = (materiels) materialTable.getSelectionModel().getSelectedItem();
        System.out.println("=======================================");
       id_mat = mat.getId();
        mat1 = mat.getNom();
         mat2 = mat.getStock();
        System.out.println(mat1);
        System.out.println(mat2);
        System.out.println("======================================");

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_add_event.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void handleMouseEvenet(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);}

    }
    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_add_event.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }
}
