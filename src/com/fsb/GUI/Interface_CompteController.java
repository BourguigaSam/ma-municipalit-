/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.fsb.models.Personne;
import com.fsb.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Interface_CompteController implements Initializable {
    Stage stage= new Stage();
    Scene scene;
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
    private Label terms;
    @FXML
    private TextField username;
    @FXML
    private TextField username_canonical;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField cin;
    @FXML
    private Label terms1;
    Connection cnx = DataSource.getInstance().getCnx();
    private Statement ste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Connection conn = DataSource.getInstance().getCnx();
        String req = "Select * from user where (username=? or email=?)  ";
        PreparedStatement prs = null;
        try {
            prs = conn.prepareStatement(req);
            prs.setString(1, Interface_connexionController.hh);
            prs.setString(2, Interface_connexionController.hh);
            ResultSet rs = prs.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                username.setText(rs.getString("username"));
                username_canonical.setText(rs.getString("username_canonical"));
                email.setText(rs.getString("email"));
                password.setText(rs.getString("password"));
                cin.setText(String.valueOf(Integer.parseInt(rs.getString("cin"))));
                terms.setText(String.valueOf(Integer.parseInt(rs.getString("id"))));
                terms1.setText((rs.getString("roles")));
                System.out.println(terms.getText());
                System.out.println(terms1.getText());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }







    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }




    @FXML
    private void update(ActionEvent event) throws SQLException, IOException {

            String requete = "UPDATE user SET username=?,username_canonical=?,email=?,password=?,cin=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1,username.getText() );
            pst.setString(2, username_canonical.getText());
            pst.setString(3, email.getText());

            pst.setString(4, password.getText());
            pst.setInt(5, Integer.parseInt(cin.getText()));
            pst.setInt(6, Integer.parseInt(terms.getText()));

            pst.executeUpdate();

            System.out.println(Integer.parseInt(terms.getText()));
            System.out.println(pst);

            System.out.println("Personne modifiée !");
            if (terms1.getText().equals("hr")){
                Node node =(Node)event.getSource();
                stage = (Stage)node.getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/hr_first_interface.fxml")));
                stage.setScene(scene);
                stage.show();
            }
       else if (terms1.getText().equals("admin")){
                Node node =(Node)event.getSource();
                stage = (Stage)node.getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/admin_first_interface.fxml")));
                stage.setScene(scene);
                stage.show();

        }if (terms1.getText().equals("ouvrier")){
            Node node =(Node)event.getSource();
            stage = (Stage)node.getScene().getWindow();
            stage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/ouvrier_first_interface.fxml")));
            stage.setScene(scene);
            stage.show();
        }
       else {
            System.out.println("something is wrong !!");
        }






    }
    
}
