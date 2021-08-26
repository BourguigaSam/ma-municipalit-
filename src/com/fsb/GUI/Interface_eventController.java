/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.fsb.models.Event;
import com.fsb.services.ServiceEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Interface_eventController implements Initializable {
    private ObservableList<Event> masterData = FXCollections.observableArrayList();
    com.fsb.services.ServiceEvent ServiceEvent = new ServiceEvent();
    Stage stage= new Stage();
    Scene scene;
    @FXML
private Label terms;
    @FXML

    private Circle btnClose;
    @FXML
    private HBox events;
    @FXML
    private Button userBtn;
    @FXML
    private Button userBtn1;
    @FXML
    private TableView<?> eventTable;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> Description;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> Date1;
    @FXML
    private TableColumn<?, ?> Image_id;
    @FXML
    private TextField Tname;
    @FXML
    private TextField TDescription;
    @FXML
    private DatePicker Tdate1;
    @FXML
    private DatePicker Tdate2;

    @FXML
    private TextField Timage_id;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnUpdate;
    @FXML
    private ImageView Timage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceEvent ser = new ServiceEvent();
        ArrayList<Event> liste = (ArrayList<Event>) ser.getAllEvents();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);
        //  masterData.addAll(users);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        Date1.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        Image_id.setCellValueFactory(new PropertyValueFactory<>("image_id"));



        btnSupprimer .setOnMouseClicked(x -> {
            Event p = new Event();
            p = (Event) eventTable.getSelectionModel().getSelectedItem();

            if (p== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner un event");
                alert.show();
            } else {

                // get Selected Item
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer cet event?", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //remove selected item from the table list
                    ser.deleteEvent(p);
                    eventTable.getItems().clear();
                    // to see       eventTable.getItems().addAll(ser.getAllEvents());
                    ChargerEvent();



                }
            }
        });



        eventTable.setOnMouseClicked(e -> {
            Event cat = new Event();
            cat = (Event) eventTable.getSelectionModel().getSelectedItem();
            //idT.setText(String.valueOf(cat.getId()));
            Tname.setText(cat.getName());
            TDescription.setText(cat.getDescription());
          //  Tdate.setPromptText(String.valueOf(cat.getDate_debut()));
         //   Tdate1.setPromptText(String.valueOf(cat.getDate_debut()));
            Timage_id.setText(cat.getImage_id());



        });




        btnUpdate.setOnAction(e -> {

            Event ca = (Event) eventTable.getSelectionModel().getSelectedItem();
            System.out.println("=======================================");
            System.out.println(ca);
            System.out.println("======================================");
          //  if (ca == null) {
            //    Alert alert = new Alert(Alert.AlertType.WARNING);
            //    alert.setTitle("Alerte");
           //     alert.setHeaderText("Alerte");
           //     alert.setContentText("Il faut tout d'abord sélectionner un event");
           //     alert.show();
          //  } else {

              //  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir modifier cet event ", ButtonType.YES, ButtonType.NO, null);
              //  alert.showAndWait();
              //  Statement statement = null;
              //  ResultSet resultSet = null;

                // //   ca.setId(Integer.parseInt(idT.getText()));

                //date_debut
                LocalDate localDateDebut = Tdate1.getValue();
                Instant instant = Instant.from(localDateDebut.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date date = java.util.Date.from(instant);
                java.sql.Date dtdebut = new java.sql.Date(date.getTime());


                //date_fin
                LocalDate localDateFin = Tdate2.getValue();
                Instant instant1 = Instant.from(localDateFin.atStartOfDay(ZoneId.systemDefault()));
                java.util.Date date1 = java.util.Date.from(instant1);
                java.sql.Date dtfin = new java.sql.Date(date1.getTime());


                ca.setName(Tname.getText());
                ca.setDescription(TDescription.getText());
                ca.setDescription(TDescription.getText());
                ca.setDate_debut(dtdebut);
                ca.setDate_fin(dtfin);
                ca.setImage_id(Timage_id.getText());

                //  if (alert.getResult() == ButtonType.YES) {


                if (Tname.getText().equals(""))
                {   terms.setText("username is empty!!");  }
                else if (TDescription.getText().equals(""))
                {   terms.setText("Description is empty!!");  }
                else if (Timage_id.getText().equals(""))
                {   terms.setText("image_id is empty!!");  }
                else {

                    System.out.println(ca);
                ServiceEvent.updateEvent(ca);
                ChargerEvent();
                }
        //    }
            //    }

        });




    }
    public void ChargerEvent() {
        ServiceEvent ser = new ServiceEvent();
        ArrayList<Event> liste = (ArrayList<Event>) ser.getAllEvents();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);

    }

    @FXML
    private void handleMouseEvenet(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);
        }

    }  @FXML
    private void SearchByName() {
        
    }
    @FXML
    private void Events(ActionEvent event) throws IOException {

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_event.fxml")));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    private void DD(ActionEvent event) throws IOException {

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_add_event.fxml")));
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    private void home(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/hr_first_interface.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
}
