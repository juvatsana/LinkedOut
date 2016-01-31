/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.inscription;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "inscriptionBean")
@ApplicationScoped
public class InscriptionBean implements Serializable{
    static class Formation {
        private String degree;
        private String school;
        private int year;
        public Formation(String degree,String school,int year) {
            this.degree = degree;
            this.school = school;
            this.year = year;
        }
    }
    private static final long serialVersionUID = 1L;
    private ArrayList<Formation> formations = new ArrayList<>();
    private String user;
    private String pass;
    private String logUser;
    private String logPass;
}
