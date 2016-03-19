/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mourougan
 */
@Entity
@Table(name = "CV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cv.findAll", query = "SELECT c FROM Cv c"),
    @NamedQuery(name = "Cv.findByIdCv", query = "SELECT c FROM Cv c WHERE c.idCv = :idCv"),
    @NamedQuery(name = "Cv.findByTitle", query = "SELECT c FROM Cv c WHERE c.title = :title")})
public class Cv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cv")
    private Integer idCv;
    @Size(max = 100)
    @Column(name = "title")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCv")
    private Collection<Interest> interestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCv")
    private Collection<Experience> experienceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCv")
    private Collection<Skill> skillCollection;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCv")
    private Collection<Formation> formationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCv")
    private Collection<Langage> langageCollection;

    public Cv() {
    }

    public Cv(Integer idCv) {
        this.idCv = idCv;
    }

    public Integer getIdCv() {
        return idCv;
    }

    public void setIdCv(Integer idCv) {
        this.idCv = idCv;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Collection<Interest> getInterestCollection() {
        return interestCollection;
    }

    public void setInterestCollection(Collection<Interest> interestCollection) {
        this.interestCollection = interestCollection;
    }

    @XmlTransient
    public Collection<Experience> getExperienceCollection() {
        return experienceCollection;
    }

    public void setExperienceCollection(Collection<Experience> experienceCollection) {
        this.experienceCollection = experienceCollection;
    }

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @XmlTransient
    public Collection<Formation> getFormationCollection() {
        return formationCollection;
    }

    public void setFormationCollection(Collection<Formation> formationCollection) {
        this.formationCollection = formationCollection;
    }

    @XmlTransient
    public Collection<Langage> getLangageCollection() {
        return langageCollection;
    }

    public void setLangageCollection(Collection<Langage> langageCollection) {
        this.langageCollection = langageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCv != null ? idCv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cv)) {
            return false;
        }
        Cv other = (Cv) object;
        if ((this.idCv == null && other.idCv != null) || (this.idCv != null && !this.idCv.equals(other.idCv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Cv[ idCv=" + idCv + " ]";
    }
    
}
