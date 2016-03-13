/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mourougan
 */
@Entity
@Table(name = "Formation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formation.findAll", query = "SELECT f FROM Formation f"),
    @NamedQuery(name = "Formation.findByIdFormation", query = "SELECT f FROM Formation f WHERE f.idFormation = :idFormation"),
    @NamedQuery(name = "Formation.findByDiploma", query = "SELECT f FROM Formation f WHERE f.diploma = :diploma"),
    @NamedQuery(name = "Formation.findBySchool", query = "SELECT f FROM Formation f WHERE f.school = :school"),
    @NamedQuery(name = "Formation.findByDateStart", query = "SELECT f FROM Formation f WHERE f.dateStart = :dateStart"),
    @NamedQuery(name = "Formation.findByDateEnd", query = "SELECT f FROM Formation f WHERE f.dateEnd = :dateEnd"),
    @NamedQuery(name = "Formation.findByPublic1", query = "SELECT f FROM Formation f WHERE f.public1 = :public1")})
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formation")
    private Integer idFormation;
    @Size(max = 25)
    @Column(name = "diploma")
    private String diploma;
    @Size(max = 25)
    @Column(name = "school")
    private String school;
    @Column(name = "date_start")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    @Column(name = "public")
    private Boolean public1;
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv")
    @ManyToOne(optional = false)
    private Cv idCv;

    public Formation() {
    }

    public Formation(String diploma, String school, Date dateStart, Date dateEnd, Boolean public1, Cv idCv) {
        this.diploma = diploma;
        this.school = school;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.public1 = public1;
        this.idCv = idCv;
    }

    public Formation(String diploma, String school, Date dateStart, Boolean public1, Cv idCv) {
        this.diploma = diploma;
        this.school = school;
        this.dateStart = dateStart;
        this.public1 = public1;
        this.idCv = idCv;
    }

    
    public Formation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public Integer getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
        hash += (idFormation != null ? idFormation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idFormation == null && other.idFormation != null) || (this.idFormation != null && !this.idFormation.equals(other.idFormation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Formation[ idFormation=" + idFormation + " ]";
    }
    
}
