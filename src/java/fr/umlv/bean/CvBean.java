/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.bean;

import fr.umlv.entity.Cv;
import fr.umlv.session.CvFacade;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "cvBean")
@ApplicationScoped
public class CvBean {
    
    @EJB
    private CvFacade cvf;
    private Cv cv;

    public void init(HashSet hashId) {
        cv = cvf.find(hashId.iterator().next());
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }
    
}
