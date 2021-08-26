/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.fsb.Controller;
import com.fsb.models.Personne;
import com.fsb.services.LoginService;
import com.fsb.services.ServicePersonne;
import com.fsb.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Bourguiga
 */
public class Interface_connexionController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private Circle btnMin;
    @FXML
    private TextField email;
    @FXML
    private Label msg;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox remember;
    @FXML
    private Hyperlink forgot_pass;

    private final String path="src\\LoginData.ini";
    LoginService loginService = new LoginService();
    int x;
    Stage stage= new Stage();
    ServicePersonne ser = new ServicePersonne();
    Scene scene;
    public static String hh = "";
    public static String pp = "";


    // private BorderlessScene borderlessScene;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginService.readinifile(path,email,password,remember);

    }    

    @FXML
    private void handleMouseEvenet(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);
        }else if(event.getSource() == btnMin){

            System.out.println("ttttttttt");
        }
    }


    @FXML
    private void Connecter(ActionEvent event) throws SQLException, IOException{


        String s = email.getText();
        Interface_connexionController.hh = s;


        if (ser.getUserByuserName(email.getText()).getRoles().equals("hr")) {
            Connection conn = DataSource.getInstance().getCnx();
            String req = "Select * from user where (username=? or email=?) AND roles='hr' ";
            PreparedStatement prs = conn.prepareStatement(req);
            prs.setString(1, email.getText());
            prs.setString(2, email.getText());
            ResultSet rs = prs.executeQuery();

            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/hr_first_interface.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);


        }


        if (ser.getUserByuserName(email.getText()).getRoles().equals("admin"))

        {
            Connection conn = DataSource.getInstance().getCnx();
        String req = "Select * from user where (username=? or email=?)";
        PreparedStatement prs = conn.prepareStatement(req);
        prs.setString(1, email.getText());
        prs.setString(2, email.getText());
        ResultSet rs = prs.executeQuery();

        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/admin_first_interface.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

        if (ser.getUserByuserName(email.getText()).getRoles().equals("ouvrier")) {
            Connection conn = DataSource.getInstance().getCnx();
            String req = "Select * from user where (username=? or email=?) AND roles='ouvrier' ";
            PreparedStatement prs = conn.prepareStatement(req);
            prs.setString(1, email.getText());
            prs.setString(2, email.getText());
            ResultSet rs = prs.executeQuery();

            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/ouvrier_first_interface.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);


        }
}



    @FXML
    private void sendmail(ActionEvent event) throws SQLException, IOException, AWTException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
      // scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/sample1.fxml")));
        stage.setScene(scene);
        stage.show();
    }






}
