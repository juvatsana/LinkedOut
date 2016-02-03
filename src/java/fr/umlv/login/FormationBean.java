/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.login;

import fr.umlv.cv.Formation;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    private ArrayList<Formation> listFormations;

    public FormationBean() {
        listFormations = new ArrayList<>();
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

    public ArrayList<Formation> getList() {
        return listFormations;
    }

    public void saveFormation() { //TODO ajouter l'id de l'user
        String request = "INSERT INTO Formation(diplome, school, year,fk_user) VALUES ('" + diplome + "','" + school + "'," + year + "," + LoginBean.idUser + ")";
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
        String request = "SELECT * FROM Formation WHERE fk_user = " + LoginBean.idUser;

        try {
            connexion = DataConnect.getConnection();
            statement = connexion.createStatement();
            resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                listFormations.add(new Formation(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }

        } catch (Exception e) {
            System.err.println("Error get Formation " + request);
            throw new FacesException(e);
        }
    }

}
