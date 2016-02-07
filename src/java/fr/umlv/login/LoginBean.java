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
    
    private String error;
    private String user;
    private String pass;
    private String logUser;
    private String logPass;
    static int idUser;
    
    
    public LoginBean() {
        user = "";
        pass = "";
    }
    
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
                    resultSet = statement.executeQuery("SELECT * FROM `User` WHERE `username` = '"+user+"'");
                    resultSet.next();
                    idUser = resultSet.getInt(1);
                    logUser =  resultSet.getString(2);
                    logPass =  resultSet.getString(3);
                    }
                catch(Exception e){
                    
                }          
    }
    
    public boolean validate() {
        dbConnexion(user);
        boolean validatePass = pass.equals(logPass);
        boolean validateUser = user.equals(logUser);
        
        if(!validateUser) {
            this.error = "This user does not exist, sign up pls";
        } else if(validateUser && !validatePass){
            this.error = "Wrong password for user :"+user;
        } 
        return validateUser && validatePass;
    }
    
    public String go(){
        return "experience";
    }
}