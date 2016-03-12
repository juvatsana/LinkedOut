/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.bean;

import fr.umlv.entity.Cv;
import fr.umlv.entity.Skill;
import fr.umlv.entity.User;
import fr.umlv.session.CvFacade;
import fr.umlv.session.UserFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.HashMap;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "cvBean")
@SessionScoped
public class CvBean {
    
  
    private int userId;
    
    @EJB
    private CvFacade cvf;
    @EJB
    private UserFacade userf;
    
    private Cv cv;
    private User user;
    private Collection<Cv> allCv;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Collection<Cv> getAllCv(){
        return allCv;
    }
 
    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
     public void init() {
        user = userf.find(new Integer(userId));
        cv = cvf.getCvId(new Integer(userId));
        allCv = cvf.getAllCv();
    }
     
    public String getTimeDifference(HashSet hashdate1,HashSet hashdate2) {
       Date date2 = (Date) hashdate2.iterator().next();
       Date date1 = (Date) hashdate1.iterator().next();
       long difference = date1.getTime() - date2.getTime();
      
       Calendar c = Calendar.getInstance(); 
       //Set time in milliseconds
       c.setTimeInMillis(difference);
       System.out.println(difference);
       System.out.println(date1);
       System.out.println(date2);
       
       int mYear = c.get(Calendar.YEAR)-1970;
       int mMonth = c.get(Calendar.MONTH); 
     
       return Integer.toString(mYear) + " years and " + Integer.toString(mMonth)+ " months";
       
    }
    
    public String getTimeDifferenceSinceNow(HashSet hashdate) {
       Date date = (Date) hashdate.iterator().next();
       long difference = System.currentTimeMillis()-date.getTime();
      
       Calendar c = Calendar.getInstance(); 
       //Set time in milliseconds
       c.setTimeInMillis(difference);
       System.out.println(difference);
       
       int mYear = c.get(Calendar.YEAR)-1970;
       int mMonth = c.get(Calendar.MONTH); 
     
       return Integer.toString(mYear) + " years and " + Integer.toString(mMonth)+ " months";
       
    }
    
    public String getFormatedDate(HashSet hashdate) {
       Date date = (Date) hashdate.iterator().next();
       SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
       return formater.format(date);
    }
    
    public HashMap<String, List<Skill>> getClassedSkill() {
        Collection<Skill> skills =  cv.getSkillCollection();    
        HashMap<String, List<Skill>> result = new HashMap<>();
        for (Skill s: skills) {
            List<Skill> list = result.get(s.getField());
            if (list == null) {
                list = new ArrayList<>();
                result.put(s.getField(), list);
            }
            list.add(s);
        }
        System.out.println(result);
        return result;
    }
    
    public HashMap<String, List<User>> getHashFriends() {
        Collection<User> friends =  user.getUserCollection();    
        HashMap<String, List<User>> result = new HashMap<>();
        int i = 0;
        List<User> lActive = new ArrayList<>();
        List<User> lReserve = new ArrayList<>();
        result.put("Active",lActive);
        result.put("Reserve",lReserve);
        for (User u: friends) {
            
            if (i<3) {
                List<User> list = result.get("Active");
                list.add(u);
            } else {
                List<User> list = result.get("Reserve");
                list.add(u);
            }
        }
        System.out.println(result);
        return result;
    }
    
    public User findUserById(int id){
        return userf.find(id);
    }
}
