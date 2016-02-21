/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.bean;

import fr.umlv.entity.Cv;
import fr.umlv.session.CvFacade;
import java.util.Calendar;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.concurrent.TimeUnit;


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
    
    public String getTimeDifference(HashSet hashdate1,HashSet hashdate2) {
       Date date2 = (Date) hashdate2.iterator().next();
       Date date1 = (Date) hashdate1.iterator().next();
       long difference = date2.getTime() - date1.getTime();
      
       Calendar c = Calendar.getInstance(); 
       //Set time in milliseconds
       c.setTimeInMillis(difference);
       System.out.println(difference);
       System.out.println(date1);
       System.out.println(date2);
       
       int mYear = c.get(Calendar.YEAR);
       int mMonth = c.get(Calendar.MONTH); 
     
       return Integer.toString(mYear) + " years and " + Integer.toString(mMonth)+ " months";
       
    }
}
