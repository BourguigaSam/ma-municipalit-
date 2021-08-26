/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.fsb.models.materiels;
import com.fsb.services.ServiceMateriels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Interface_materielsController implements Initializable {


    private ObservableList<materiels> masterData = FXCollections.observableArrayList();
    com.fsb.services.ServiceMateriels ServiceMateriels = new ServiceMateriels();
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
    private TextField tf_recherche;
    @FXML
    private TableView<materiels> eventTable;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> category;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tname1;
    @FXML
    private TextField Tname11;
    @FXML
    private DatePicker Tdate;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceMateriels ser = new ServiceMateriels();
        ArrayList<materiels> liste = (ArrayList<materiels>) ser.getAllMateriels();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);
     //  masterData.addAll(materiels);
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));





        btnSupprimer .setOnMouseClicked(x -> {
            materiels p = new materiels();
            p = (materiels) eventTable.getSelectionModel().getSelectedItem();

            if (p== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner un material");
                alert.show();
            } else {

                // get Selected Item
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer cet material?", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //remove selected item from the table list
                    ser.deleteMateriels(p);
                    eventTable.getItems().clear();
                    // to see       eventTable.getItems().addAll(ser.getAllEvents());
                    ChargerMaterial();



                }
            }
        });



        eventTable.setOnMouseClicked(e -> {
            materiels cat = new materiels();
            cat = (materiels) eventTable.getSelectionModel().getSelectedItem();
                Tname.setText(cat.getNom());
            Tname1.setText(cat.getCategory());

            //  Tdate.se(cat.getDate());
         //   Timage_id.setText(cat.getImage_id());



        });



        btnUpdate.setOnAction(e -> {

            materiels ca = (materiels) eventTable.getSelectionModel().getSelectedItem();
            System.out.println("=======================================");
            System.out.println(ca);
            System.out.println("======================================");
            if (ca == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner un material");
                alert.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir modifier cet material ", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();
                Statement statement = null;
                ResultSet resultSet = null;

                // //   ca.setId(Integer.parseInt(idT.getText()));
                ca.setNom(Tname.getText());
                ca.setCategory(Tname1.getText());

                if (Tname.getText().equals(""))
                {   terms.setText("name is empty!!");  }
                else if (Tname1.getText().equals(""))
                {   terms.setText("category is empty!!");  }
                //  if (alert.getResult() == ButtonType.YES) {
else {
                ServiceMateriels.updateMateriels(ca);
                ChargerMaterial();

            }}
            //    }

        });










    }

    public void ChargerMaterial() {
        ServiceMateriels ser = new ServiceMateriels();
        ArrayList<materiels> liste = (ArrayList<materiels>) ser.getAllMateriels();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);

    }


    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);}
    }

    @FXML
    private void materials(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_materiels.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DD(ActionEvent event)  throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_add_materiels.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SearchByName(ActionEvent event) {
        ServiceMateriels bs = new ServiceMateriels();
        ArrayList AL = (ArrayList) bs.getAllMateriels();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<materiels> filtred_c = new FilteredList<>(OReservation, e -> true);
        tf_recherche.setOnKeyReleased(e -> {
            tf_recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_c.setPredicate((Predicate<? super materiels>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    //  String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((p.getNom().contains(newValue)) ) {
                        return true;

                    }

                    return false;
                });
            });
        });
        eventTable.setItems(filtred_c);



    }

    @FXML
    private void permissionss(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_Permission.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/ouvrier_first_interface.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void user(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_compte.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    
}
