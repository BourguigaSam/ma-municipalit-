/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsb.GUI;
import java.sql.ResultSet;
import com.fsb.utils.DataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class SampleController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private TextField username;
    @FXML
    private TextField username_canonical;
    @FXML
    private TextField email;
    @FXML
    private Label terms;

    @FXML
    private TextField password;
    @FXML
    private TextField cin;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;

    @FXML
    private DatePicker date;

    @FXML
    private TextField image_id;

    Stage stage= new Stage();
    Scene scene;


    @FXML
    private ComboBox<String> sex1 ;


    @FXML
    private ComboBox<String> roleee ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sex1.getItems().add("homme");
        sex1.getItems().add("femme");

       roleee.getItems().add("admin");
       roleee.getItems().add("agent");
       roleee.getItems().add("hr");
       roleee.getItems().add("financier");



    }    

    @FXML
    private void handleMouseEvenet(MouseEvent event) {
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }

    @FXML
    private void add_personnel(ActionEvent event)  throws SQLException, IOException {


        Connection conn = DataSource.getInstance().getCnx();
        String req= "INSERT INTO user (username, username_canonical,email, password, roles,image_id,cin,availability,date,sex,address,phone) VALUES (?,?,?,?,?,'0',?,'valable',?,?,?,?)";
       String req1= "SELECT * FROM user WHERE email=?";
        PreparedStatement prs= conn.prepareStatement(req);
        PreparedStatement prs1= conn.prepareStatement(req1);
        prs1.setString(1, email.getText());
        ResultSet rs = prs1.executeQuery();
        prs.setString(1, username.getText());
        prs.setString(2, username_canonical.getText());
        prs.setString(3, email.getText());
        prs.setString(4, password.getText());
        prs.setString(5, roleee.getValue());
        prs.setInt(6, Integer.parseInt(cin.getText()));
        prs.setString(7, String.valueOf(date.getValue()));
        prs.setString(8, sex1.getValue());
        prs.setString(9, address.getText());
        prs.setString(10, phone.getText());





        if (username.getText().equals(""))
        {   terms.setText("username is empty!!");  }
        else if (username_canonical.getText().isEmpty()){
            terms.setText("Please insert your name!!");
        }
        else if (password.getText().isEmpty()){
            terms.setText("Please insert your Email!!");
        }
       // else if (role.getText().isEmpty()){
      //      terms.setText("Please insert your Role!!");
      //  }
        else if (email.getText().isEmpty()){
            terms.setText("Please insert your Email!!");
        }
        else if (validateEmaill() == false){
            terms.setText("Your Email Not Working!!");
        }
        else if (rs.next()){
            terms.setText("email deja existant!!");
        }
        else if (cin.getText().isEmpty()){
            terms.setText("Please insert your cin!!");
        }


else {
            prs.executeUpdate();

            Parent root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Sample1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();



            System.out.println("user added !!");




        }







    }



    @FXML
    private void precedent(ActionEvent event)  throws SQLException, IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/sample1.fxml")));
        stage.setScene(scene);
        stage.show();

    }



    public boolean validateEmaill(){
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }else{
            return false;
        }
    }
    
}
