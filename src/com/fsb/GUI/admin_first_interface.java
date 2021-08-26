/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class admin_first_interface implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
private Label hello;
    Stage stage= new Stage();
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String a = Interface_connexionController.hh;
        String hallo = "hello admin "+ a;
    hello.setText(hallo);
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
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

    @FXML
    private void permission(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_permission.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void project(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/sample1.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_event.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cars(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Interface_car.fxml")));
        stage.setScene(scene);
        stage.show();
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
    private void logout(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_connexion.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML

        private void finance(ActionEvent event) throws IOException {
            Node node =(Node)event.getSource();
            stage = (Stage)node.getScene().getWindow();
            stage.close();
     //     scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_connexion.fxml")));
            stage.setScene(scene);
            stage.show();

    }


    
}
