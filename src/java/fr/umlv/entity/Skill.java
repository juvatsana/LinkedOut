/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mourougan
 */
@Entity
@Table(name = "Skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s"),
    @NamedQuery(name = "Skill.findByIdSkill", query = "SELECT s FROM Skill s WHERE s.idSkill = :idSkill"),
    @NamedQuery(name = "Skill.findByName", query = "SELECT s FROM Skill s WHERE s.name = :name"),
    @NamedQuery(name = "Skill.findByField", query = "SELECT s FROM Skill s WHERE s.field = :field"),
    @NamedQuery(name = "Skill.findByLevel", query = "SELECT s FROM Skill s WHERE s.level = :level"),
    @NamedQuery(name = "Skill.findByPublic1", query = "SELECT s FROM Skill s WHERE s.public1 = :public1")})
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_skill")
    private Integer idSkill;
    @Size(max = 25)
    @Column(name = "name")
    private String name;
    @Size(max = 25)
    @Column(name = "field")
    private String field;
    @Size(max = 25)
    @Column(name = "level")
    private String level;
    @Column(name = "public")
    private Boolean public1;
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv")
    @ManyToOne(optional = false)
    private Cv idCv;

    public Skill() {
    }

    public Skill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public Integer getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Integer idSkill) {
        this.idSkill = idSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getPublic1() {
        return public1;
    }

    public void setPublic1(Boolean public1) {
        this.public1 = public1;
    }

    public Cv getIdCv() {
        return idCv;
    }

    public void setIdCv(Cv idCv) {
        this.idCv = idCv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSkill != null ? idSkill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.idSkill == null && other.idSkill != null) || (this.idSkill != null && !this.idSkill.equals(other.idSkill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Skill[ idSkill=" + idSkill + " ]";
    }
    
}
