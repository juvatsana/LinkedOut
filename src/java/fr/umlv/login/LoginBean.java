/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Vatsana
 */

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    Connection connexion;
    Statement statement;
    ResultSet resultSet;

    private String user;
    private String pass;
    private String logUser;
    private String logPass;

  
    public LoginBean() {
        user = "";
        pass = "";
    }
    public String getPass(){
        return pass;
    }
    
    public String getUser(){
        return user;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public void dbConnexion(String user){
                try {
                    connexion = DataConnect.getConnection();
                    statement = connexion.createStatement();
                    resultSet = statement.executeQuery("Select * from User");
                    resultSet.next();
                    System.out.println(resultSet.toString());
                    logUser =  resultSet.getString(2);
                    logPass =  resultSet.getString(3);
                    }
                catch(Exception e){
                    
                }          
    }
    
    public boolean validate() {
        dbConnexion(user);
        return logPass.equals(pass) && logUser.equals(user);
    }
    
    public String go(){
        return "experience";
    }
}