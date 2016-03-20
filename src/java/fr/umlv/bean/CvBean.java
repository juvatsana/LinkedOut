/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.bean;

import fr.umlv.entity.Cv;
import fr.umlv.entity.Interest;
import fr.umlv.entity.Langage;
import fr.umlv.entity.Skill;
import fr.umlv.entity.User;
import fr.umlv.login.LoginBean;
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
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "cvBean")
@SessionScoped
public class CvBean {
    
    @ManagedProperty(value="#{loginBean}")
    private LoginBean loginb;

    private int userId;
    
    @EJB
    private CvFacade cvf;
    @EJB
    private UserFacade userf;
    @EJB
    private SkillFacade skillf;
    
    private Cv cv;
    private User user;
    private User userlogin;
    
    
    private String username;
    private String lastname;
    private String email;
    private String password;
    private int age;
    private String telephone;
    private String adresse;
    
    //langage
    private Collection<Langage> langages;
    
    //interest
    private Collection<Interest> interests;
    
    private HashMap<String, List<Skill>> organizedSkill = new HashMap<>();
    private HashMap<String, List<User>> organizedFriends = new HashMap<>();
    private List<String> listEncodedNameSkill = new ArrayList<>();

    //roundValue
    private String roundValue;
    
    private boolean enabled;
    
    public String toggle() {
        if(enabled == true) {
            //enlever des following
            System.out.println("************************");
            System.out.println(userlogin.getUserCollection());
            userlogin.getUserCollection().remove(user);
            userf.edit(userlogin);
            System.out.println(userlogin.getUserCollection());
            System.out.println("************************");
        } else {
            //ajouter des following
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            System.out.println(userlogin.getUserCollection());
            userlogin.getUserCollection().add(user);
            userf.edit(userlogin);
            System.out.println(userlogin.getUserCollection());
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        }
        enabled = !enabled;
        
        return "onepage.xhtml?userId="+Integer.toString(userId)+"&faces-redirect=true";
    }
    
    public void change(String value) {
        System.out.print("banane");
        System.out.println(value);
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
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
    
    public Collection<Cv> getAllCv() {
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
    
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public boolean addUser()
    {
        try {
            User newU  = new User(email,password,username,lastname,age,telephone,adresse);
            System.out.println("------------------");
            System.out.println(newU);
            System.out.println("------------------");
            userf.create(newU);
            user = userf.getIdByUsername(email);
            userId = user.getIdUser();
            loginb.setError("sign up sucessfull, pls connect");
            System.out.println("true ----------------");
            cvf.create(new Cv(user));
            return true;
        } catch(Exception e){
            System.out.println("------------------");
            System.out.println(e);
            System.out.println("------------------");
            return false;
        }
    }
    
    
    
    public void init() {
        user = userf.find(userId);
        cv = cvf.getCvId(userId);
        getClassedSkill();
        getHashFriends();
        langages = cv.getLangageCollection();
        interests = cv.getInterestCollection();      
        allCv = cvf.getAllCv();
        
        userlogin = userf.find(loginb.getIdUser());
        Collection<User> friends = userlogin.getUserCollection();   
        enabled = false;
        for(User u: friends) {
            if(u.getIdUser().equals(user.getIdUser())){
                enabled = true;
            }
        }
       
    }
    
    public String getTimeDifference(HashSet hashdate1, HashSet hashdate2) {
        Date date2 = (Date) hashdate2.iterator().next();
        Date date1 = (Date) hashdate1.iterator().next();
        long difference = date1.getTime() - date2.getTime();
        
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(difference);
        
        int mYear = c.get(Calendar.YEAR) - 1970;
        int mMonth = c.get(Calendar.MONTH);        
        
        return Integer.toString(mYear) + " years and " + Integer.toString(mMonth) + " months";
        
    }
    
    public String getTimeDifferenceSinceNow(HashSet hashdate) {
        Date date = (Date) hashdate.iterator().next();
        long difference = System.currentTimeMillis() - date.getTime();
        
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(difference);
        int mYear = c.get(Calendar.YEAR) - 1970;
        int mMonth = c.get(Calendar.MONTH);        
        
        return Integer.toString(mYear) + " years and " + Integer.toString(mMonth) + " months";
        
    }
    
    public String getFormatedDate(HashSet hashdate) {
        Date date = (Date) hashdate.iterator().next();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        return formater.format(date);
    }
    
    private void getClassedSkill() {
        Collection<Skill> skills = cv.getSkillCollection();        
        organizedSkill = new HashMap<>();
        for (Skill s : skills) {
            List<Skill> list = organizedSkill.get(s.getField());
            if (list == null) {
                list = new ArrayList<>();
                organizedSkill.put(s.getField(), list);
            }
            list.add(s);
        }
        
    }

    public Collection<Langage> getLangages() {
        return langages;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }
    
    private void getHashFriends() {
        Collection<User> friends = user.getUserCollection();        
        organizedFriends = new HashMap<>();
        int i = 0;
        List<User> lActive = new ArrayList<>();
        List<User> lReserve = new ArrayList<>();
        organizedFriends.put("Active", lActive);
        organizedFriends.put("Reserve", lReserve);
        for (User u : friends) {
            if (i < 2) {
                List<User> list = organizedFriends.get("Active");
                list.add(u);
            } else {
                List<User> list = organizedFriends.get("Reserve");
                list.add(u);
            }
        }
        
    }

    public void setLoginb(LoginBean loginb) {
        this.loginb = loginb;
    }


    public User findUserById(HashSet hashID) {
       int id = (int) hashID.iterator().next();
       return userf.find(id);
    }
    
}
