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
@ManagedBean(name = "formationBean")
@RequestScoped
public class FormationBean implements Serializable {

    Connection connexion;
    Statement statement;
    ResultSet resultSet;
    private String diplome;
    private String school;
    private String year;

    /**
     * @return the diplome
     */
    public String getDiplome() {
        return diplome;
    }

    /**
     * @param diplome the diplome to set
     */
    public void setDiplome(String diplome) {
        this.diplome = diplome;
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

    public void saveFormation() {
        String request = "INSERT INTO `Formation`(`diplome`, `school`, `year`) VALUES ('" + diplome + "','" + school + "'," + year + ")";
        try {

            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery(request);
            resultSet.next();
            System.out.println("SAVE FORMATION : " + resultSet.toString());
        } catch (Exception e) {
            System.err.println("Error save Formation " + request);
        }
    }
}
