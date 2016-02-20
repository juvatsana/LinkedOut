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
@Table(name = "Langage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Langage.findAll", query = "SELECT l FROM Langage l"),
    @NamedQuery(name = "Langage.findByIdLangage", query = "SELECT l FROM Langage l WHERE l.idLangage = :idLangage"),
    @NamedQuery(name = "Langage.findByName", query = "SELECT l FROM Langage l WHERE l.name = :name"),
    @NamedQuery(name = "Langage.findByLevel", query = "SELECT l FROM Langage l WHERE l.level = :level"),
    @NamedQuery(name = "Langage.findByPublic1", query = "SELECT l FROM Langage l WHERE l.public1 = :public1")})
public class Langage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_langage")
    private Integer idLangage;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 25)
    @Column(name = "level")
    private String level;
    @Column(name = "public")
    private Boolean public1;
    @JoinColumn(name = "id_cv", referencedColumnName = "id_cv")
    @ManyToOne(optional = false)
    private Cv idCv;

    public Langage() {
    }

    public Langage(Integer idLangage) {
        this.idLangage = idLangage;
    }

    public Integer getIdLangage() {
        return idLangage;
    }

    public void setIdLangage(Integer idLangage) {
        this.idLangage = idLangage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idLangage != null ? idLangage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Langage)) {
            return false;
        }
        Langage other = (Langage) object;
        if ((this.idLangage == null && other.idLangage != null) || (this.idLangage != null && !this.idLangage.equals(other.idLangage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.umlv.entity.Langage[ idLangage=" + idLangage + " ]";
    }
    
}
