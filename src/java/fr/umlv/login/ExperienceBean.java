/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Vatsana
 */

@ManagedBean(name = "experienceBean")
@RequestScoped
public class ExperienceBean {
    private String dateBegin;
    private String dateEnd;
    private String entreprise;
    private String mission;
    private String place;

    /**
     * @return the dateBegin
     */
    public String getDateBegin() {
        return dateBegin;
    }

    /**
     * @param dateBegin the dateBegin to set
     */
    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * @return the dateEnd
     */
    public String getDateEnd() {
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * @return the entreprise
     */
    public String getEntreprise() {
        return entreprise;
    }

    /**
     * @param entreprise the entreprise to set
     */
    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    /**
     * @return the mission
     */
    public String getMission() {
        return mission;
    }

    /**
     * @param mission the mission to set
     */
    public void setMission(String mission) {
        this.mission = mission;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }
}
