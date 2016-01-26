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
    
    @ManagedProperty(value="#{user}")
    private String user = "test";
     @ManagedProperty(value="#{pass}")
    private String pass = "test";
    
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
        System.out.println("fr.umlv.login.Login.setPass()");
    }
    
    public void setUser(String user){
        this.user = user;
        System.out.println("fr.umlv.login.Login.setUser()");
    }
    
    public boolean validate() {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            
            System.out.println("user : " + user);
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from utilisateur where login = ? and mdp = ?");
            ps.setString(1, user);
            ps.setString(2, pass);

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
