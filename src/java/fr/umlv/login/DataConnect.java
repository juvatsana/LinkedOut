/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.login;

/**
 *
 * @author Vatsana
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DataConnect {
 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ProjetJEE","root", "root");
            return con;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println("Database.getConnection() Error -->"+ex+ ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}