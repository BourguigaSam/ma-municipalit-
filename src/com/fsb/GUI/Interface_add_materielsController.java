/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.fsb.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Interface_add_materielsController implements Initializable {

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
    private DatePicker date;
    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField stock ;


    Stage stage= new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleMouseEvent(MouseEvent event) {
    }

    @FXML
    private void materiels(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_materiels.fxml")));
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void add_materiels(ActionEvent event) throws IOException {

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_add_materiels.fxml")));
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void addMateriels(ActionEvent event) throws SQLException {

        Connection conn = DataSource.getInstance().getCnx();
        String req= "INSERT INTO materiels (nom,category,date,stock) VALUES (?,?,'2021-04-06',?)";
        PreparedStatement prs= conn.prepareStatement(req);
        prs.setString(1, name.getText());
        prs.setString(2, category.getText());
        prs.setString(3, stock.getText());
        // prs.setString(3, Tdate.getDate);

        if (name.getText().equals(""))
        {   terms.setText("name is empty!!");  }
        else if (category.getText().equals(""))
        {   terms.setText("category is empty!!");  }
else {
            prs.executeUpdate();

            System.out.println("Materiel added !!");
        }


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
