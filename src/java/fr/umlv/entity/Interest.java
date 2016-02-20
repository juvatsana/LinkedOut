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
@Table(name = "Interest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interest.findAll", query = "SELECT i FROM Interest i"),
    @NamedQuery(name = "Interest.findByIdInterest", query = "SELECT i FROM Interest i WHERE i.idInterest = :idInterest"),
    @NamedQuery(name = "Interest.findByName", query = "SELECT i FROM Interest i WHERE i.name = :name"),
    @NamedQuery(name = "Interest.findByDescription", query = "SELECT i FROM Interest i WHERE i.description = :description"),
    @NamedQuery(name = "Interest.findByPublic1", query = "SELECT i FROM Interest i WHERE i.public1 = :public1")})
public class Interest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_interest")
    private Integer idInterest;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "public")
    private Boolean public1;
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv")
    @ManyToOne(optional = false)
    private Cv idCv;

    public Interest() {
    }

    public Interest(Integer idInterest) {
        this.idInterest = idInterest;
    }

    public Integer getIdInterest() {
        return idInterest;
    }

    public void setIdInterest(Integer idInterest) {
        this.idInterest = idInterest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idInterest != null ? idInterest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interest)) {
            return false;
        }
        Interest other = (Interest) object;
        if ((this.idInterest == null && other.idInterest != null) || (this.idInterest != null && !this.idInterest.equals(other.idInterest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Interest[ idInterest=" + idInterest + " ]";
    }
    
}
