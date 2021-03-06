/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.bean;

import fr.umlv.entity.Cv;
import fr.umlv.entity.Skill;
import fr.umlv.entity.User;
import fr.umlv.entity.Formation;
import fr.umlv.entity.Experience;
import fr.umlv.entity.Langage;
import fr.umlv.entity.Interest;

import fr.umlv.session.CvFacade;
import fr.umlv.session.SkillFacade;
import fr.umlv.session.UserFacade;
import fr.umlv.session.FormationFacade;
import fr.umlv.session.ExperienceFacade;
import fr.umlv.session.LangageFacade;
import fr.umlv.session.InterestFacade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mourougan
 */
@ManagedBean(name = "editBean")
@SessionScoped
public class EditBean {

    @EJB
    private CvFacade cvf;
    @EJB
    private SkillFacade sf;
    @EJB
    private UserFacade uf;
    @EJB
    private FormationFacade ff;
    @EJB
    private ExperienceFacade exf;
    @EJB
    private LangageFacade lf;
    @EJB
    private InterestFacade intf;

    //userId
    private int userId;

    //cv
    private Cv cv;
    private String title;

    //formations
    private Collection<Formation> formations;

    //experiences
    private Collection<Experience> experiences;
    
    //langage
    private Collection<Langage> langages;
    
    //interest
    private Collection<Interest> interests;
    
    //skills
    private HashMap<String, List<Skill>> organizedSkill = new HashMap<>();
    
    //friends
    private HashMap<String, List<User>> organizedFriends = new HashMap<>();

    //section personal
    private User u;
    private String username;
    private String surname;
    private String firstname;
    private String age;
    private String telephone;
    private String adresse;

    //section add skill
    private String skillField;
    private String skillName;
    private int skillLevel;
    private String skillPublic = "public";

    //section add formation
    private String formationSchool;
    private String formationDiploma;
    private String formationStart;
    private String formationEnd;
    private String formationPublic = "public";

    //section add experience
    private String experienceCompany;
    private String experienceContract;
    private String experienceOccupation;
    private String experienceDescription;
    private String experienceStart;
    private String experienceEnd;
    private String experiencePublic = "public";

    //section add langage
    private String langageName;
    private String langageLevel;
    private String langagePublic = "public";
    
    //section add interest
    private String interestName;
    private String interestDescription;
    private String interestPublic = "public";
    
    //roundValue
    private String roundValue;

    public String getRoundValue() {
        return roundValue;
    }

    public void setRoundValue(String roundValue) {
        this.roundValue = roundValue;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }
    
    public void initEditBean() {
        u = uf.getUserById(userId);
        cv = cvf.getCvId(userId);
        formations = cv.getFormationCollection();
        for(Formation f: formations) {
            System.out.println(f);
        }
        experiences = cv.getExperienceCollection();
        langages = cv.getLangageCollection();
        interests = cv.getInterestCollection();
        title = cv.getTitle();    
        getClassedSkill();
        getHashFriends();
     

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Collection<Formation> getFormations() {
        return formations;
    }

    public void setFormations(ArrayList<Formation> formations) {
        this.formations = formations;
    }

    public Collection<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(Collection<Experience> experiences) {
        this.experiences = experiences;
    }

    public HashMap<String, List<Skill>> getOrganizedSkill() {
        return organizedSkill;
    }
     
    public HashMap<String, List<User>> getOrganizedFriends() {
        return organizedFriends;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSkillField() {
        return skillField;
    }

    public void setSkillField(String skillField) {
        this.skillField = skillField;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    

    public void setSkillPublic(String skillPublic) {
        this.skillPublic = skillPublic;
    }

    public void initUserId(HashSet hashid) {
        userId = (Integer) hashid.iterator().next();
    }

    public String getFormationSchool() {
        return formationSchool;
    }

    public void setFormationSchool(String formationSchool) {
        this.formationSchool = formationSchool;
    }

    public String getFormationDiploma() {
        return formationDiploma;
    }

    public void setFormationDiploma(String formationDiploma) {
        this.formationDiploma = formationDiploma;
    }

    public String getFormationStart() {
        return formationStart;
    }

    public void setFormationStart(String formationStart) {
        this.formationStart = formationStart;
    }

    public String getFormationEnd() {
        return formationEnd;
    }

    public void setFormationEnd(String formationEnd) {
        this.formationEnd = formationEnd;
    }

    

    public void setFormationPublic(String formationPublic) {
        this.formationPublic = formationPublic;
    }

    public String getExperienceCompany() {
        return experienceCompany;
    }

    public void setExperienceCompany(String experienceCompany) {
        this.experienceCompany = experienceCompany;
    }

    public String getExperienceContract() {
        return experienceContract;
    }

    public void setExperienceContract(String experienceContract) {
        this.experienceContract = experienceContract;
    }

    public String getExperienceOccupation() {
        return experienceOccupation;
    }

    public void setExperienceOccupation(String experienceOccupation) {
        this.experienceOccupation = experienceOccupation;
    }

    public String getExperienceDescription() {
        return experienceDescription;
    }

    public void setExperienceDescription(String experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    public String getExperienceStart() {
        return experienceStart;
    }

    public void setExperienceStart(String experienceStart) {
        this.experienceStart = experienceStart;
    }

    public String getExperienceEnd() {
        return experienceEnd;
    }

    public void setExperienceEnd(String experienceEnd) {
        this.experienceEnd = experienceEnd;
    }

   

    public void setExperiencePublic(String experiencePublic) {
        this.experiencePublic = experiencePublic;
    }

    public Collection<Langage> getLangages() {
        return langages;
    }

    public void setLangages(Collection<Langage> langages) {
        this.langages = langages;
    }

    public Collection<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Collection<Interest> interests) {
        this.interests = interests;
    }

    public String getLangageName() {
        return langageName;
    }

    public void setLangageName(String langageName) {
        this.langageName = langageName;
    }

    public String getLangageLevel() {
        return langageLevel;
    }

    public void setLangageLevel(String langageLevel) {
        this.langageLevel = langageLevel;
    }

    public void setLangagePublic(String langagePublic) {
        this.langagePublic = langagePublic;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }


    public void setInterestPublic(String interestPublic) {
        this.interestPublic = interestPublic;
    }

    public String getInterestDescription() {
        return interestDescription;
    }

    public void setInterestDescription(String interestDescription) {
        this.interestDescription = interestDescription;
    }

    public String getSkillPublic() {
        return skillPublic;
    }

    public String getFormationPublic() {
        return formationPublic;
    }

    public String getExperiencePublic() {
        return experiencePublic;
    }

    public String getLangagePublic() {
        return langagePublic;
    }

    public String getInterestPublic() {
        return interestPublic;
    }

    
    public String addSkill() {
        System.out.println("ALLO");
        if (!skillField.equals("") && !skillName.equals("")) {

            Skill s = new Skill(skillName, skillField, skillLevel, skillPublic, cv);
            System.out.println(s);
            sf.create(s);
            //sf.insertSkill(s);
        }
        skillField = "";
        skillName = "";
        skillLevel = 0;
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#skill";
    }
    public String addLangage() {
        if (!langageName.equals("")) {
            Langage l = new Langage(langageName, langageLevel, langagePublic, cv);
            lf.create(l);
           
        }
        langageName = "";
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#langage";
    }

    public String addInterest() {
        if (!interestName.equals("")) {
            Interest i = new Interest(interestName, interestDescription, interestPublic, cv);
            intf.create(i);
           
        }
        interestName = "";
        interestDescription = "";
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#interest";
    }
    public String addFormation() {

        if (!formationDiploma.equals("") && !formationSchool.equals("") && !formationStart.equals("")) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (formationEnd.equals("")) {
                    Formation f = new Formation(formationDiploma, formationSchool, formatter.parse(formationStart), formationPublic, cv);
                    ff.create(f);
                } else {
                    Formation f = new Formation(formationDiploma, formationSchool, formatter.parse(formationStart), formatter.parse(formationEnd), formationPublic, cv);
                    ff.create(f);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        formationDiploma = "";
        formationSchool = "";
        formationStart = "";
        formationEnd = "";
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#formation";
    }

    public String addExperience() {
        
        if (!experienceCompany.equals("") && !experienceContract.equals("") && !experienceOccupation.equals("") &&!experienceStart.equals("")) {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (experienceEnd.equals("")) {
                    Experience e = new Experience(experienceContract, experienceCompany, formatter.parse(experienceStart), experienceOccupation,experienceDescription, experiencePublic, cv);
                    exf.create(e);
                } else {
                    Experience e = new Experience(experienceContract, experienceCompany, formatter.parse(experienceStart), formatter.parse(experienceEnd), experienceOccupation,experienceDescription, experiencePublic, cv);
                    exf.create(e);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        experienceContract = "";
        experienceCompany = "";
        experienceOccupation = "";
        experienceDescription = "";
        experienceStart = "";
        experienceEnd = "";
        
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#experience";
    }

    public String updateCv() {
        cvf.edit(cv);
      
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId);

    }
    public String updateProfile() {        
        uf.edit(u);
        return "onepageOwner.xhtml?userId=" + Integer.toString(userId) + "#profile";

    }

    public void updateFormation() {
        for (Formation f : formations) {
            ff.edit(f);
        }
        formations = cv.getFormationCollection();
        for(Formation f: formations) {
            System.out.println(f);
        }
    }

    public void updateExperience() {
        for (Experience e : experiences) {
            exf.edit(e);
        }
    }
    
    public void updateSkill() {
        Iterator it = organizedSkill.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
             List<Skill> l = (List<Skill>)pair.getValue();
             for (Skill s : l){
                 sf.edit(s);
             }
           
        }
    }
    
    public void updateLangage() {
        for (Langage l : langages) {
            lf.edit(l);
        }
    }
    
    public void updateInterest() {
        for (Interest i : interests) {
            intf.edit(i);
        }
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
        Collection<User> friends =  cv.getIdUser().getUserCollection();    
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
    public String deleteFormation(HashSet formationH) {
        int idFormation = (int)formationH.iterator().next();
        Formation form = ff.find(idFormation);
        ff.remove(form);
        return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#formation";
    }
    
    public String deleteExperience(HashSet experienceH) {
        int idExperience = (int)experienceH.iterator().next();
        Experience exp = exf.find(idExperience);
        exf.remove(exp);
        return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#experience";
    }
    
    public String deleteLangage(HashSet langageH) {
        int idLangage= (int)langageH.iterator().next();
        Langage l = lf.find(idLangage);
        lf.remove(l);
        return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#langage";
    }
    
    public String deleteInterest(HashSet interestH) {
        int idInterest= (int)interestH.iterator().next();
        Interest i = intf.find(idInterest);
        intf.remove(i);
        return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#interest";
    }
    
    
    
    public String deleteSkill(HashSet skillH) {
        int idSkill = (int)skillH.iterator().next();
        Skill s = sf.find(idSkill);
        sf.remove(s);
        return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#skill";
    }
    
    /*
    public String deleteSkill(HashSet fieldH, HashSet nameSkillH) {
         
        String nameField = (String) fieldH.iterator().next();
        List<Skill> skills = organizedSkill.get(nameField);
     
        String nameSkill = (String) nameSkillH.iterator().next();
        
        for(Skill s: skills) {
            if(s.getName().equals(nameSkill)) {
                sf.remove(s);
                return "onepageOwner.xhtml?userId="+Integer.toString(userId)+"#skill";
            }
        }
        return "onepageOwner.xhtml";
    }
    */
    
}
