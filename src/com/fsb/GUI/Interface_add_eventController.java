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
import java.sql.Statement;
import java.util.EventObject;
import java.util.ResourceBundle;

import com.fsb.utils.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static com.fsb.GUI.Planifier.*;


public class Interface_add_eventController implements Initializable {

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
    private TextArea TDescription;
    @FXML
    private DatePicker Tdate;
    @FXML
    private DatePicker Tdate1;
    @FXML
    private TextField Tname;
    @FXML
    private ImageView Timage;
    @FXML
    private TextField Timage_id;
    @FXML
    private Label user;
    @FXML
    private Label materiels;
    @FXML
    private Label car;

    int stock ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setText(nam);
        materiels.setText(mat1);
        car.setText(vahicule);


    }

    @FXML
    private void addEvent(ActionEvent event)  throws SQLException, IOException {


        Connection conn = DataSource.getInstance().getCnx();
        String req= "INSERT INTO event (name, description,date_debut,date_fin, image_id,creator,materiels,car) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement prs= conn.prepareStatement(req);
        prs.setString(1, Tname.getText());
        prs.setString(2, TDescription.getText());
        prs.setString(3, String.valueOf(Tdate.getValue()));
        prs.setString(4, String.valueOf(Tdate1.getValue()));
        prs.setString(5, Timage_id.getText());
        prs.setString(6, user.getText());
        prs.setString(7, materiels.getText());
        prs.setString(8, car.getText());


        if (Tname.getText().equals(""))
        {   terms.setText("username is empty!!");  }
        else if (TDescription.getText().equals(""))
        {   terms.setText("Description is empty!!");  }
       else if (Timage_id.getText().equals(""))
        {   terms.setText("image_id is empty!!");  }
else {
            prs.executeUpdate();

            System.out.println("event added !!");
        }

        stock = mat2 - 1 ;
        System.out.println(stock);

        String requete = "UPDATE materiels SET stock=? WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(requete);

        pst.setInt(1,stock );
        pst.setInt(2, id_mat);
        pst.executeUpdate();
        System.out.println("stock --");


        String requete1 = "UPDATE car SET availability=? WHERE id=?";
        PreparedStatement pst1 = conn.prepareStatement(requete1);
        pst1.setString(1,"non valable" );
        pst1.setInt(2, id_car);
        pst1.executeUpdate();
        System.out.println("car no more available ");


        String requete2 = "UPDATE user SET availability=? WHERE id=?";
        PreparedStatement pst2 = conn.prepareStatement(requete2);
        pst2.setString(1,"non valable" );
        pst2.setInt(2, id_car);
        pst1.executeUpdate();
        System.out.println("car no more available ");


    }
    @FXML
    private void events(ActionEvent event) throws IOException {

        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/com/fsb/GUI/interface_event.fxml")));
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void handleMouseEvenet(ActionEvent event) throws IOException {
        if (event.getSource() == btnClose){
            System.exit(0);
        }


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


    @FXML
    private void planifier(ActionEvent event) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource("/com/fsb/GUI/Planifier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }


    private void valvehicule(){

    }

}
