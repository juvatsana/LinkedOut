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
import fr.umlv.session.SkillFacade;
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
    @EJB
    private SkillFacade skillf;
    
    private Cv cv;
    private User user;

    
    private HashMap<String, List<Skill>> organizedSkill = new HashMap<>();
    private HashMap<String, List<User>> organizedFriends = new HashMap<>();
    private List<String> listEncodedNameSkill = new ArrayList<>();

    //roundValue
    private String roundValue;

    public String getRoundValue() {
        return roundValue;
    }

    public void setRoundValue(String roundValue) {
        this.roundValue = roundValue;
    }
    
    public HashMap<String, List<Skill>> getOrganizedSkill() {
        return organizedSkill;
    }

    public HashMap<String, List<User>> getOrganizedFriends() {
        return organizedFriends;
    }

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
        user = userf.find(userId);
        cv = cvf.getCvId(userId);
        getClassedSkill();
        getHashFriends();
        allCv = cvf.getAllCv();
    }
     
    public String getTimeDifference(HashSet hashdate1,HashSet hashdate2) {
       Date date2 = (Date) hashdate2.iterator().next();
       Date date1 = (Date) hashdate1.iterator().next();
       long difference = date1.getTime() - date2.getTime();
      
       Calendar c = Calendar.getInstance(); 
       //Set time in milliseconds
       c.setTimeInMillis(difference);
    
       
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
       int mYear = c.get(Calendar.YEAR)-1970;
       int mMonth = c.get(Calendar.MONTH); 
     
       return Integer.toString(mYear) + " years and " + Integer.toString(mMonth)+ " months";
       
    }
    
    public String getFormatedDate(HashSet hashdate) {
       Date date = (Date) hashdate.iterator().next();
       SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
       return formater.format(date);
    }
    
    private void getClassedSkill() {
        Collection<Skill> skills =  cv.getSkillCollection();    
        organizedSkill = new HashMap<>();
        for (Skill s: skills) {
            List<Skill> list = organizedSkill.get(s.getField());
            if (list == null) {
                list = new ArrayList<>();
                organizedSkill.put(s.getField(), list);
            }
            list.add(s);
        }
        
    }
    
    private void getHashFriends() {
        Collection<User> friends =  user.getUserCollection();    
        organizedFriends = new HashMap<>();
        int i = 0;
        List<User> lActive = new ArrayList<>();
        List<User> lReserve = new ArrayList<>();
        organizedFriends.put("Active",lActive);
        organizedFriends.put("Reserve",lReserve);
        for (User u: friends) {
            if (i<3) {
                List<User> list = organizedFriends.get("Active");
                list.add(u);
            } else {
                List<User> list = organizedFriends.get("Reserve");
                list.add(u);
            }
        }

    }

    public User findUserById(int id){
        return userf.find(id);
    }

}
