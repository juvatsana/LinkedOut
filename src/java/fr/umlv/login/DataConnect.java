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
 
public class DataConnect {
 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("after driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://sqletud.univ-mlv.fr/mperouma_db","mperouma", "azerty");
            //Connexion en local ok
            System.out.println("connexion seems ok");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"+ ex.getMessage());
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