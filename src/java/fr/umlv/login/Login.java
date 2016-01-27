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
        Connection con = null;
        PreparedStatement ps = null;
        Statement statement;
        ResultSet resultSet;

        try {
            
            System.out.println("user : " + user);
            con = DataConnect.getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("Select * from Users");
            resultSet.next();
            
            String name =  resultSet.getString(1).toString();
            System.out.println(name);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("login"));
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
    
    

}
