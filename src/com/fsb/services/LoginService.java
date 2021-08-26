package com.fsb.services;

import com.fsb.GUI.Interface_connexionController;
import com.fsb.models.Personne;
import com.fsb.utils.DataSource;
import java.io.File;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.ini4j.Wini;

public class LoginService {

    PreparedStatement pst;
    ResultSet rs;
    Connection cnx = DataSource.getInstance().getCnx();
    public Statement ste;
    public static Personne ConnectedUser = new Personne();

    public LoginService(){
        try {
            ste=cnx.createStatement();
        } catch (SQLException ex){
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public String LoginAction(Personne user) throws SQLException{
        String req= "Select * from user where username=? and password=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, user.getUsername());
        prs.setString(2, user.getPassword());
        rs = prs.executeQuery();
        ConnectedUser = (Personne)rs;
        System.out.println(ConnectedUser);
        return "good job u made it here";

    }

    public void createinFile(String path, String user, String pass ){
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            Wini wini = new Wini(new File(path));
            wini.put("Login data", "Username",user);
            wini.put("Login data", "Password",pass);
            wini.store();

        } catch (IOException ex) {
            Logger.getLogger(Interface_connexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }



    public void readinifile(String path, TextField userid, PasswordField passid, CheckBox remember){
        File file = new File(path);
        if(file.exists()){
            try {
                Wini wini = new Wini(new File(path));
                String username = wini.get("Login data","Username");
                String password = wini.get("Login data","Password");
                if ((username!=null && !username.equals(""))&&(password!=null && !password.equals("")) ){
                    userid.setText(username);
                    passid.setText(password);
                    //               remember.setSelected(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet info(String email) throws SQLException {

        String req= "Select username,password from user where email=? ";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, email);
        rs= prs.executeQuery();
        return rs;
    }


    public ResultSet userbyid(Integer id) throws SQLException {

        String req= "Select * from user where id=? ";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setInt(1, id);
        rs= prs.executeQuery();
        return rs;
    }

    public ResultSet user(String username , String password) throws SQLException {

        String req= "Select * from user where (username=? or email=?) and password=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, username);
        prs.setString(2, password);
        rs= prs.executeQuery();
        return rs;
    }


    public ResultSet check_email(String email) throws SQLException {

        String req= "Select * from user where email=?";
        PreparedStatement prs= cnx.prepareStatement(req);
        prs.setString(1, email);
        rs= prs.executeQuery();
        return rs;
    }





}




