/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.login;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * ·
 *
 * @author Vatsana
 */

@SessionScoped
@ManagedBean
public class Login implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Connection connexion;
    private PreparedStatement ps;
    private Statement statement;
    private ResultSet resultSet;

    
    private String user = "";
    private String pass = "";
    
    public Login(){
        user = "";
        pass = "";
    }

    public String getpass(){
        return pass;
    }
    
    public String getuser(){
        return user;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public boolean validate() {
        System.out.println("VALIDATEEEE");
 
        try {
            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery("Select * from User");
            resultSet.next();
            System.out.println(resultSet.toString());
            String nameLog =  resultSet.getString(2);
            String passLog =  resultSet.getString(3);
            System.out.println("userLog = " + nameLog);
            System.out.println("passLog = " + passLog);
            System.out.println("user " +user);
            System.out.println("pass " +pass);
            if(nameLog.equals(user) && passLog.equals(pass)) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(connexion);
        }
        return false;
    }
    
    

}
