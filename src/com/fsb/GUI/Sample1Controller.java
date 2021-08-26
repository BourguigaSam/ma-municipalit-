/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import com.fsb.models.Personne;
import com.fsb.services.ServicePersonne;
import com.fsb.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Sample1Controller implements Initializable {
    @FXML
    private Label cntrole;
    @FXML
    private Circle btnClose;
    @FXML

    private TextField tf_recherche;
    @FXML

    private TableView<Personne> userTable;
    @FXML

    private TableColumn<?, ?> username;
    @FXML

    private TableColumn<?, ?> name;
    @FXML

    private TableColumn<?, ?> email;
    @FXML

    private TableColumn<?, ?> phone;
    @FXML

    private TableColumn<?, ?> password;
    @FXML

    private TableColumn<?, ?> cin;
    @FXML

    private TableColumn<?, ?> roles;
    @FXML

    private TableColumn<?, ?> image;
    @FXML

    private TextField Tusername_canonical;
    @FXML

    private TextField Timage_id;
    @FXML

    private TextField Tusername ;
    @FXML

    private TextField Temail;
    @FXML

    private TextField Troles;
    @FXML

    private TextField Tpassword;
    Stage stage= new Stage();
    Scene scene;

    @FXML

    private Button btnSupprimer;
    @FXML

    private Button btnUpdate;

    private ObservableList<Personne> masterData = FXCollections.observableArrayList();
    ServicePersonne ServicePersonne = new ServicePersonne();
    @FXML
    private TextField phone1;
    @FXML
    private TextField Tcin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServicePersonne ser = new ServicePersonne();
        ArrayList<Personne> liste = (ArrayList<Personne>) ser.getAllUsers();
        ObservableList observableList = FXCollections.observableArrayList(liste);
            userTable.setItems(observableList);
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
        name.setCellValueFactory(new PropertyValueFactory<>("username_canonical"));

        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        image.setCellValueFactory(new PropertyValueFactory<>("image_id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));


        Connection conn = DataSource.getInstance().getCnx();
        String req1= "SELECT * FROM user WHERE email=?";
        PreparedStatement prs1= null;
        try {
            prs1 = conn.prepareStatement(req1);
            prs1.setString(1, email.getText());
            ResultSet rs = prs1.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        btnSupprimer .setOnMouseClicked(x -> {
        Personne p = new Personne();
        p = userTable.getSelectionModel().getSelectedItem();

        if (p== null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerte");
            alert.setHeaderText("Alerte");
            alert.setContentText("Il faut tout d'abord sélectionner un utilisateur");
            alert.show();
        } else {

            // get Selected Item
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer cet utilisateur?", ButtonType.YES, ButtonType.NO, null);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                //remove selected item from the table list
                ser.supprimerPersonne(p);
                //bonplanService.SupprimerCategorie(p);
                userTable.getItems().clear();

                userTable.getItems().addAll(ser.getAllUsers());
                ChargerPersonne();



            }
        }
    });

        userTable.setOnMouseClicked(e -> {
            Personne cat = new Personne();
            cat = (Personne) userTable.getSelectionModel().getSelectedItem();
            //idT.setText(String.valueOf(cat.getId()));
            Tusername.setText(cat.getUsername());
            Tusername_canonical.setText(cat.getUsername_canonical());
            Temail.setText(cat.getEmail());
            Tpassword.setText(cat.getPassword());
            Troles.setText(cat.getRoles());
            Timage_id.setText(cat.getImage_id());
            Tcin.setText(String.valueOf(cat.getCin()));
            phone1.setText(String.valueOf(cat.getPhone()));



        });

        btnUpdate.setOnAction(e -> {

            Personne ca = userTable.getSelectionModel().getSelectedItem();
            System.out.println("=======================================");
            System.out.println(ca);
            System.out.println("======================================");
            if (ca == null) {
               Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
               alert.setContentText("Il faut tout d'abord sélectionner un user");
               alert.show();
            } else {

               Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir modifier cet personne ", ButtonType.YES, ButtonType.NO, null);
               alert.showAndWait();
                Statement statement = null;
                ResultSet resultSet = null;

                if (Tusername.getText().equals("")){
                    cntrole.setText("please check username is empty!!");
                }
                else if (Tusername_canonical.getText().isEmpty()){
                    cntrole.setText("please check name is empty!!");
                }
                else if (Temail.getText().isEmpty()){
                    cntrole.setText("please check email is empty!!");
                }
                else if (Tpassword.getText().isEmpty()){
                    cntrole.setText("please check password is empty!!");
                }
                else if (Troles.getText().isEmpty()){
                    cntrole.setText("please check role is empty!!");
                }
                else if (Timage_id.getText().isEmpty()){
                    cntrole.setText("please check image_id is empty!!");
                }
                else if (Temail.getText().isEmpty()){
                    cntrole.setText("please check image_id is empty!!");
                }
                else if (Tcin.getText().isEmpty()){
                    cntrole.setText("please check cin is empty!!");
                }

else {
                ca.setUsername(Tusername.getText());
                ca.setUsername_canonical(Tusername_canonical.getText());
                ca.setEmail(Temail.getText());
                ca.setPassword(Tpassword.getText());
                ca.setRoles(Troles.getText());

                ca.setImage_id(Timage_id.getText());
                ca.setCin(Integer.parseInt(Tcin.getText()));
                    ca.setPhone(phone1.getText());

              //  if (alert.getResult() == ButtonType.YES) {

                    ServicePersonne.modifierPersonne(ca);
                    ChargerPersonne();

                }}
        //    }

        });

}






public void ChargerPersonne() {
        ServicePersonne ser = new ServicePersonne();
        ArrayList<Personne> liste = (ArrayList<Personne>) ser.getAllUsers();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        userTable.setItems(observableList);

    }




@FXML
    private void SearchByName(ActionEvent event) {
        ServicePersonne bs = new ServicePersonne();
        ArrayList AL = (ArrayList) bs.getAllUsers();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Personne> filtred_c = new FilteredList<>(OReservation, e -> true);
        tf_recherche.setOnKeyReleased(e -> {
            tf_recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_c.setPredicate((Predicate<? super Personne>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    //  String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((p.getUsername().contains(newValue)) ) {
                        return true;

                    }

                    return false;
                });
            });
        });
        userTable.setItems(filtred_c);
    }

@FXML
    private void addUser(ActionEvent event) throws SQLException, IOException, AWTException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/sample.fxml")));
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleMouseEvent(MouseEvent event){
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }



    @FXML
    private void users(ActionEvent event) throws IOException {

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/sample1.fxml")));
        stage.setScene(scene);
        stage.show();


    }
/*




    @FXML
    private void addUser(Stage primaryStage ,ActionEvent event) throws IOException {

    }





/*
    @FXML
    private void users(ActionEvent event) {




    }*/

    @FXML
    private void permission(ActionEvent event) {
    }

    @FXML
    private void project(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void cars(ActionEvent event) {
    }

    @FXML
    private void materiels(ActionEvent event) {
    }
}
