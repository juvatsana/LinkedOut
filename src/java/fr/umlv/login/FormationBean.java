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
    private String diplome;
    private String school;
    private String year;

    public FormationBean() {
    }

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

    public void saveFormation() { //TODO ajouter l'id de l'user
        String request = "INSERT INTO Formation(diplome, school, year) VALUES ('" + diplome + "','" + school + "'," + year + ")";
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
    public void getFormation() {
        //Recupere la formation en base si existante
        System.out.println("GET FORMATION");
        String request = "SELECT * FROM Formation WHERE fk_user = " + LoginBean.idUser;
        System.out.println(request);

        try {
            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery(request);
            resultSet.next();

            int size = 0; //Recupere le nombre de resultat de la requete
            if (resultSet != null) {
                resultSet.beforeFirst();
                resultSet.last();
                size = resultSet.getRow();
            }

            if (size != 0) {
                this.idFormation = resultSet.getInt(1);
                this.diplome = resultSet.getString(2);
                this.school = resultSet.getString(3);
                this.year = resultSet.getString(4);

                System.out.println("GET IS OK");
            }

        } catch (Exception e) {
            System.err.println("Error get Formation " + request);
            throw new FacesException(e);
        }
    }
}
