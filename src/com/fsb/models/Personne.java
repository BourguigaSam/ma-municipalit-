package com.fsb.models;

import java.util.Date;

public class Personne {
private int id;
private int cin;
private String username;
private String username_canonical ;
private String email;
private String password ;
private String roles ;
private String image_id ;
private String availability;
private Date date;
private String sex;
private String address;
private String phone;




    public Personne(int id ,int cin , String username, String username_canonical, String email, String password, String roles, String image_id,String availability,Date date, String sex,String address,String phone ){
    this.id=id;
    this.cin=cin;
    this.username=username;
    this.username_canonical=username_canonical;
    this.email=email;
    this.password=password;
    this.roles=roles;
    this.image_id=image_id;
    this.availability=availability;
    this.date=date;
    this.sex=sex;
    this.address=address;
    this.phone=phone;
}
    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
    public Personne( int cin, String username, String username_canonical,String email, String password, String roles, String image_id) {
       this.cin=cin;
        this.username=username;
        this.username_canonical=username_canonical;
        this.email=email;
        this.password=password;
        this.roles=roles;
        this.image_id=image_id;

    }
    public Personne(){

    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getId(){
return id;
}
public void setId(){
this.id=id;
}

public String getUsername(){
    return username;
}
public void setUsername(String username){
    this.username=username;
}
public String getEmail(){
    return email;
}
public void setEmail(String email){
    this.email=email;
}
public String getPassword(){
    return password;
}
public void setPassword(String password){
    this.password=password;
}
public String getRoles(){return roles; }
public void setRoles(String roles){this.roles=roles;}
public String getUsername_canonical(){return username_canonical;}
public void setUsername_canonical(String username_canonical){this.username_canonical=username_canonical; }
public String getImage_id(){return image_id; }
public void setImage_id(String Image_id){this.image_id=image_id; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", cin=" + cin +
                ", username='" + username + '\'' +
                ", username_canonical='" + username_canonical + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", image_id='" + image_id + '\'' +
                ", availability='" + availability + '\'' +
                ", date=" + date +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
