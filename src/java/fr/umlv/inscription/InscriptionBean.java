/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.inscription;

import fr.umlv.database.DataConnect;
import fr.umlv.login.LoginBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.FacesException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "inscriptionBean")
@ApplicationScoped
public class InscriptionBean implements Serializable{

    Connection connexion;
    Statement statement;
    ResultSet resultSet;
    private String username;
    private String pass;
    private String firstname;
    private String surname;
    private int age;
    private int tel;
    private String adress;
    
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    
    public boolean saveUser() { //TODO ajouter l'id de l'user
        String request = "INSERT INTO `User`(`username`, `password`, `firstname`, `surname`, `age`, `telephone`, `adresse`) VALUES ('"+username+"','"+ pass+"','"+firstname+"','"+surname+"','"+age+"','"+tel+"','"+ adress+"');";
        try {
            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            statement.execute(request);
        } catch (Exception e) {
            System.err.println("Error save Formation " + request+"\n"+e);
            if(e.getMessage().toLowerCase().contains("Duplicate entry".toLowerCase())){
                error = "L'utilisateur "+username+" existe déjà";
            }
            return false;
        }
        return true;
        
    }
}
