/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.fsb.models.permission;
import com.fsb.services.ServicePermission;
import com.fsb.utils.DataSource;
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



public class Interface_PermissionController implements Initializable {

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
    private Button userBtn11;
    @FXML
    private TextField tf_recherche;
    @FXML
    private TableView<permission> eventTable;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TextField Tname;
    @FXML
    private TextField Tname11;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnSupprimer;


    private ObservableList<permission> masterData = FXCollections.observableArrayList();
    com.fsb.services.ServicePermission ServicePermission = new ServicePermission();
    Stage stage= new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePermission ser = new ServicePermission();
        ArrayList<permission> liste = (ArrayList<permission>) ser.getAllPermission();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);
        //  masterData.addAll(users);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));






        btnSupprimer .setOnMouseClicked(x -> {
            permission p = new permission();
            p = (permission) eventTable.getSelectionModel().getSelectedItem();

            if (p== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner une permission");
                alert.show();
            } else {

                // get Selected Item
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer cet permission?", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    //remove selected item from the table list
                    ser.deletePermission(p);
                    eventTable.getItems().clear();
              //     eventTable.getItems().addAll(ser.getAllPermission());
                    ChargerPermission();



                }
            }
        });


        eventTable.setOnMouseClicked(e -> {
            permission cat = new permission();
            cat = (permission) eventTable.getSelectionModel().getSelectedItem();
            //idT.setText(String.valueOf(cat.getId()));
            Tname.setText(cat.getName());
            Tname11.setText(cat.getDescription());


        });



        btnUpdate.setOnAction(e -> {

            permission ca = (permission) eventTable.getSelectionModel().getSelectedItem();
            System.out.println("=======================================");
            System.out.println(ca);
            System.out.println("======================================");
            if (ca == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner une,permission");
                alert.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir modifier cette permission ", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();
                Statement statement = null;
                ResultSet resultSet = null;

                ca.setName(Tname.getText());
                ca.setDescription(Tname11.getText());

                if (Tname.getText().equals(""))
                {   terms.setText("name is empty!!");  }
                else if (Tname11.getText().equals(""))
                {   terms.setText("Description is empty!!");  }

                else {


                ServicePermission.updatePermission(ca);
                ChargerPermission();}

            }
            //    }

        });













    }

@FXML
    private void addP(ActionEvent event) throws SQLException {

        Connection conn = DataSource.getInstance().getCnx();
        String req= "INSERT INTO permission (name,description) VALUES (?,?)";
        PreparedStatement prs= conn.prepareStatement(req);
        prs.setString(1, Tname.getText());
        prs.setString(2, Tname11.getText());

    if (Tname.getText().equals(""))
    {   terms.setText("name is empty!!");  }
    else if (Tname11.getText().equals(""))
    {   terms.setText("Description is empty!!");  }

    else {


        prs.executeUpdate();

        System.out.println("Permission added !!");

    ChargerPermission();}


    }

    public void ChargerPermission() {
        ServicePermission ser = new ServicePermission();
        ArrayList<permission> liste = (ArrayList<permission>) ser.getAllPermission();
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
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_materiels.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DD(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_add_materiels.fxml")));
        stage.setScene(scene);
        stage.show();
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
    private void SearchByName(ActionEvent event) {
        ServicePermission bs = new ServicePermission();
        ArrayList AL = (ArrayList) bs.getAllPermission();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<permission> filtred_c = new FilteredList<>(OReservation, e -> true);
        tf_recherche.setOnKeyReleased(e -> {
            tf_recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_c.setPredicate((Predicate<? super permission>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    //  String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((p.getName().contains(newValue)) ) {
                        return true;

                    }

                    return false;
                });
            });
        });
        eventTable.setItems(filtred_c);




    }
    
}
