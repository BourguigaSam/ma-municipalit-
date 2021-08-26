/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.fsb.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class DemandeController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private TextField name;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker naissance;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label terms;
    @FXML
    private Label dateCreation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().add("authorisation");
        type.getItems().add("doléance");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        dateCreation.setText(String.valueOf(date));
    }


    @FXML
    private void valider(ActionEvent event) throws SQLException {
        Connection cnx = DataSource.getInstance().getCnx();

        String req = "INSERT INTO demande (user_nom,description,user_cin,user_date,user_phone,dateCreation,address,type) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pst = cnx.prepareStatement(req);

        pst.setString(1, name.getText());
        pst.setString(2, description.getText());
        pst.setInt(3, Integer.parseInt(cin.getText()));
        pst.setString(4, String.valueOf(naissance.getValue()));
        pst.setString(5, phone.getText());
        pst.setString(6, dateCreation.getText());
        pst.setString(7, address.getText());
        pst.setString(8, type.getValue());


        pst.executeUpdate();
        System.out.println("Demande added !");

    }


    @FXML
    private void annuler(ActionEvent event) throws SQLException, IOException {


        Parent root;

        root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/hr_first_interface.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    private void handleMouseEvent(ActionEvent event) throws IOException {
        if (event.getSource() == btnClose){
            System.exit(0);
        }


    }
}
