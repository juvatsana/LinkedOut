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
@Table(name = "Experience")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experience.findAll", query = "SELECT e FROM Experience e"),
    @NamedQuery(name = "Experience.findByIdExperience", query = "SELECT e FROM Experience e WHERE e.idExperience = :idExperience"),
    @NamedQuery(name = "Experience.findByContractType", query = "SELECT e FROM Experience e WHERE e.contractType = :contractType"),
    @NamedQuery(name = "Experience.findByEnterprise", query = "SELECT e FROM Experience e WHERE e.enterprise = :enterprise"),
    @NamedQuery(name = "Experience.findByDateStart", query = "SELECT e FROM Experience e WHERE e.dateStart = :dateStart"),
    @NamedQuery(name = "Experience.findByDateEnd", query = "SELECT e FROM Experience e WHERE e.dateEnd = :dateEnd"),
    @NamedQuery(name = "Experience.findByOccupation", query = "SELECT e FROM Experience e WHERE e.occupation = :occupation"),
    @NamedQuery(name = "Experience.findByDescription", query = "SELECT e FROM Experience e WHERE e.description = :description"),
    @NamedQuery(name = "Experience.findByVisibility", query = "SELECT e FROM Experience e WHERE e.visibility = :visibility")})
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_experience")
    private Integer idExperience;
    @Size(max = 25)
    @Column(name = "contract_type")
    private String contractType;
    @Size(max = 255)
    @Column(name = "enterprise")
    private String enterprise;
    @Column(name = "date_start")
    @Temporal(TemporalType.DATE)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.DATE)
    private Date dateEnd;
    @Size(max = 255)
    @Column(name = "occupation")
    private String occupation;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 7)
    @Column(name = "visibility")
    private String visibility;
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv")
    @ManyToOne(optional = false)
    private Cv idCv;

    public Experience() {
    }

    public Experience(String contractType, String enterprise, Date dateStart, Date dateEnd, String occupation, String description, String visibility, Cv idCv) {
        this.contractType = contractType;
        this.enterprise = enterprise;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.occupation = occupation;
        this.description = description;
        this.visibility = visibility;
        this.idCv = idCv;
    }

    public Experience(String contractType, String enterprise, Date dateStart, String occupation, String description, String visibility, Cv idCv) {
        this.contractType = contractType;
        this.enterprise = enterprise;
        this.dateStart = dateStart;
        this.occupation = occupation;
        this.description = description;
        this.visibility = visibility;
        this.idCv = idCv;
    }

    
    public Experience(Integer idExperience) {
        this.idExperience = idExperience;
    }

    public Integer getIdExperience() {
        return idExperience;
    }

    public void setIdExperience(Integer idExperience) {
        this.idExperience = idExperience;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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
        hash += (idExperience != null ? idExperience.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experience)) {
            return false;
        }
        Experience other = (Experience) object;
        if ((this.idExperience == null && other.idExperience != null) || (this.idExperience != null && !this.idExperience.equals(other.idExperience))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Experience[ idExperience=" + idExperience + " ]";
    }
    
}
