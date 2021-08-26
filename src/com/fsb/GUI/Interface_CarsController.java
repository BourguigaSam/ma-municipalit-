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

import com.fsb.models.Car;
import com.fsb.services.ServiceCar;
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


public class Interface_CarsController implements Initializable {

    private ObservableList<Car> masterData = FXCollections.observableArrayList();
    com.fsb.services.ServiceCar ServiceCar = new ServiceCar();

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
    private Button userBtn11;
    @FXML
    private Button userBtn12;
    @FXML
    private TextField tf_recherche;
    @FXML
    private TableView<Car> eventTable;
    @FXML
    private TableColumn<?, ?> matricule;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TableColumn<?, ?> modele;
    @FXML
    private TableColumn<?, ?> id_image;
    @FXML
    private TextField Tmatricule;
    @FXML
    private TextField Ttype;
    @FXML
    private TextField Tmodele;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCar ser = new ServiceCar();
        ArrayList<Car> liste = (ArrayList<Car>) ser.getAllCars();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        id_image.setCellValueFactory(new PropertyValueFactory<>("id_image"));











        btnSupprimer .setOnMouseClicked(x -> {
            Car p = new Car();
            p = (Car) eventTable.getSelectionModel().getSelectedItem();

            if (p== null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner une voiture");
                alert.show();
            } else {

                // get Selected Item
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer cete voiture?", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    ser.deleteCar(p);
                    eventTable.getItems().clear();
                    ChargerCar();



                }
            }
        });





            eventTable.setOnMouseClicked(e -> {
                Car cat = new Car();
                cat = (Car) eventTable.getSelectionModel().getSelectedItem();
                Tmatricule.setText(cat.getMatricule());
                Ttype.setText(cat.getType());
                Tmodele.setText(cat.getModele());
            //    Tid_image.setText(cat.getId_image());

            });




        btnUpdate.setOnAction(e -> {

            Car ca = (Car) eventTable.getSelectionModel().getSelectedItem();
            System.out.println("=======================================");
            System.out.println(ca);
            System.out.println("======================================");
            if (ca == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alerte");
                alert.setHeaderText("Alerte");
                alert.setContentText("Il faut tout d'abord sélectionner une voiture ");
                alert.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir modifier cette voiture ", ButtonType.YES, ButtonType.NO, null);
                alert.showAndWait();
                Statement statement = null;
                ResultSet resultSet = null;

                // //   ca.setId(Integer.parseInt(idT.getText()));
                ca.setMatricule(Tmatricule.getText());
                ca.setType(Ttype.getText());
                ca.setModele(Tmodele.getText());
              //  ca.setId_image(.getText());

                if (Tmatricule.getText().equals(""))
                {   terms.setText("matricule is empty!!");  }
                else if (Ttype.getText().equals(""))
                {   terms.setText("type is empty!!");  }
                else if (Tmodele.getText().equals(""))
                {   terms.setText("model is empty!!");  }

                else {
                    ServiceCar.updateCar(ca);
                    ChargerCar();
                }
            }
            //    }

        });



    }



    public void ChargerCar() {
        ServiceCar ser = new ServiceCar();
        ArrayList<Car> liste = (ArrayList<Car>) ser.getAllCars();
        ObservableList observableList = FXCollections.observableArrayList(liste);
        eventTable.setItems(observableList);

    }


    @FXML
    private void addC(ActionEvent event) throws SQLException {

        Connection conn = DataSource.getInstance().getCnx();
        String req= "INSERT INTO car (matricule,type,modele,availability) VALUES (?,?,?,'valable')";
        PreparedStatement prs= conn.prepareStatement(req);
        prs.setString(1, Tmatricule.getText());
        prs.setString(2, Ttype.getText());
        prs.setString(3, Tmodele.getText());

        if (Tmatricule.getText().equals(""))
        {   terms.setText("matricule is empty!!");  }
        else if (Ttype.getText().equals(""))
        {   terms.setText("type is empty!!");  }
        else if (Tmodele.getText().equals(""))
        {   terms.setText("model is empty!!");  }

        else {


            prs.executeUpdate();

            System.out.println("Car added !!");

            ChargerCar();
        }

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
    private void DD1(ActionEvent event) {
    }

    @FXML
    private void Carss(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_car.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SearchByName(ActionEvent event) {
        ServiceCar bs = new ServiceCar();
        ArrayList AL = (ArrayList) bs.getAllCars();
        ObservableList OReservation = FXCollections.observableArrayList(AL);
        FilteredList<Car> filtred_c = new FilteredList<>(OReservation, e -> true);
        tf_recherche.setOnKeyReleased(e -> {
            tf_recherche.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filtred_c.setPredicate((Predicate<? super Car>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    //  String toLowerCaseNewValue = newValue.toLowerCase();
                    if ((p.getMatricule().contains(newValue)) ) {
                        return true;

                    }

                    return false;
                });
            });
        });
        eventTable.setItems(filtred_c);

    }
    
}
