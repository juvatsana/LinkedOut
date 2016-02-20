/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.temp;


import fr.umlv.database.DataConnect;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Vatsana
 */
@ManagedBean(name = "formationBean")
@RequestScoped
public class FormationBean implements Serializable {

    Connection connexion;
    Statement statement;
    ResultSet resultSet;
    private int idFormation;
    private String diploma;
    private String school;
    private String year;
    
    public FormationBean() {
      
    }

    /**
     * @return the diploma
     */
    public String getDiploma() {
        return diploma;
    }

    /**
     * @param diploma the diploma to set
     */
    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    /**
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }


    public void saveFormation(String idUser) { //TODO ajouter l'id de l'user
        String request = "INSERT INTO Formation(diploma, school, year,fk_user) VALUES ('" + diploma + "','" + school + "'," + year + "," + idUser + ")";
        try {

            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();

            statement.execute(request);
        } catch (Exception e) {
            System.err.println("Error save Formation " + request);
            throw new FacesException(e);
        }
    }

    /**
     *
     */
    public void getFormation(String idUser) {
        String request = "SELECT * FROM Formation WHERE fk_user = " + idUser;

        try {
            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery(request);
                       
            while (resultSet.next()) {
                /*
                if(!mapFormations.containsKey(resultSet.getInt(1))) {
                    mapFormations.put(resultSet.getInt(1), new Formation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
                }
                */
            }

        } catch (Exception e) {
            System.err.println("Error get Formation " + request);
            throw new FacesException(e);
        }
    }

}
