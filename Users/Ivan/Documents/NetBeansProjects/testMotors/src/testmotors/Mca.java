/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmotors;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "mca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mca.findAll", query = "SELECT m FROM Mca m")
    , @NamedQuery(name = "Mca.findByIdmca", query = "SELECT m FROM Mca m WHERE m.idmca = :idmca")
    , @NamedQuery(name = "Mca.findByImpedUV", query = "SELECT m FROM Mca m WHERE m.impedUV = :impedUV")
    , @NamedQuery(name = "Mca.findByImpedVW", query = "SELECT m FROM Mca m WHERE m.impedVW = :impedVW")
    , @NamedQuery(name = "Mca.findByImpedUW", query = "SELECT m FROM Mca m WHERE m.impedUW = :impedUW")
    , @NamedQuery(name = "Mca.findByInducUV", query = "SELECT m FROM Mca m WHERE m.inducUV = :inducUV")
    , @NamedQuery(name = "Mca.findByInducVW", query = "SELECT m FROM Mca m WHERE m.inducVW = :inducVW")
    , @NamedQuery(name = "Mca.findByInducUW", query = "SELECT m FROM Mca m WHERE m.inducUW = :inducUW")
    , @NamedQuery(name = "Mca.findByIfUV", query = "SELECT m FROM Mca m WHERE m.ifUV = :ifUV")
    , @NamedQuery(name = "Mca.findByIfVW", query = "SELECT m FROM Mca m WHERE m.ifVW = :ifVW")
    , @NamedQuery(name = "Mca.findByIfUW", query = "SELECT m FROM Mca m WHERE m.ifUW = :ifUW")
    , @NamedQuery(name = "Mca.findByAngFasUV", query = "SELECT m FROM Mca m WHERE m.angFasUV = :angFasUV")
    , @NamedQuery(name = "Mca.findByAngFasVW", query = "SELECT m FROM Mca m WHERE m.angFasVW = :angFasVW")
    , @NamedQuery(name = "Mca.findByAngFasUW", query = "SELECT m FROM Mca m WHERE m.angFasUW = :angFasUW")})
public class Mca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmca")
    private Integer idmca;
    @Basic(optional = false)
    @Column(name = "impedUV")
    private float impedUV;
    @Basic(optional = false)
    @Column(name = "impedVW")
    private float impedVW;
    @Basic(optional = false)
    @Column(name = "impedUW")
    private float impedUW;
    @Basic(optional = false)
    @Column(name = "inducUV")
    private float inducUV;
    @Basic(optional = false)
    @Column(name = "inducVW")
    private float inducVW;
    @Basic(optional = false)
    @Column(name = "inducUW")
    private float inducUW;
    @Basic(optional = false)
    @Column(name = "ifUV")
    private int ifUV;
    @Basic(optional = false)
    @Column(name = "ifVW")
    private int ifVW;
    @Basic(optional = false)
    @Column(name = "ifUW")
    private int ifUW;
    @Basic(optional = false)
    @Column(name = "angFasUV")
    private int angFasUV;
    @Basic(optional = false)
    @Column(name = "angFasVW")
    private int angFasVW;
    @Basic(optional = false)
    @Column(name = "angFasUW")
    private int angFasUW;
    @JoinColumn(name = "idEnsayo", referencedColumnName = "idensayos")
    @ManyToOne(optional = false)
    private Ensayos idEnsayo;

    public Mca() {
    }

    public Mca(Integer idmca) {
        this.idmca = idmca;
    }

    public Mca(Integer idmca, float impedUV, float impedVW, float impedUW, float inducUV, float inducVW, float inducUW, int ifUV, int ifVW, int ifUW, int angFasUV, int angFasVW, int angFasUW) {
        this.idmca = idmca;
        this.impedUV = impedUV;
        this.impedVW = impedVW;
        this.impedUW = impedUW;
        this.inducUV = inducUV;
        this.inducVW = inducVW;
        this.inducUW = inducUW;
        this.ifUV = ifUV;
        this.ifVW = ifVW;
        this.ifUW = ifUW;
        this.angFasUV = angFasUV;
        this.angFasVW = angFasVW;
        this.angFasUW = angFasUW;
    }

    public Integer getIdmca() {
        return idmca;
    }

    public void setIdmca(Integer idmca) {
        this.idmca = idmca;
    }

    public float getImpedUV() {
        return impedUV;
    }

    public void setImpedUV(float impedUV) {
        this.impedUV = impedUV;
    }

    public float getImpedVW() {
        return impedVW;
    }

    public void setImpedVW(float impedVW) {
        this.impedVW = impedVW;
    }

    public float getImpedUW() {
        return impedUW;
    }

    public void setImpedUW(float impedUW) {
        this.impedUW = impedUW;
    }

    public float getInducUV() {
        return inducUV;
    }

    public void setInducUV(float inducUV) {
        this.inducUV = inducUV;
    }

    public float getInducVW() {
        return inducVW;
    }

    public void setInducVW(float inducVW) {
        this.inducVW = inducVW;
    }

    public float getInducUW() {
        return inducUW;
    }

    public void setInducUW(float inducUW) {
        this.inducUW = inducUW;
    }

    public int getIfUV() {
        return ifUV;
    }

    public void setIfUV(int ifUV) {
        this.ifUV = ifUV;
    }

    public int getIfVW() {
        return ifVW;
    }

    public void setIfVW(int ifVW) {
        this.ifVW = ifVW;
    }

    public int getIfUW() {
        return ifUW;
    }

    public void setIfUW(int ifUW) {
        this.ifUW = ifUW;
    }

    public int getAngFasUV() {
        return angFasUV;
    }

    public void setAngFasUV(int angFasUV) {
        this.angFasUV = angFasUV;
    }

    public int getAngFasVW() {
        return angFasVW;
    }

    public void setAngFasVW(int angFasVW) {
        this.angFasVW = angFasVW;
    }

    public int getAngFasUW() {
        return angFasUW;
    }

    public void setAngFasUW(int angFasUW) {
        this.angFasUW = angFasUW;
    }

    public Ensayos getIdEnsayo() {
        return idEnsayo;
    }

    public void setIdEnsayo(Ensayos idEnsayo) {
        this.idEnsayo = idEnsayo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmca != null ? idmca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mca)) {
            return false;
        }
        Mca other = (Mca) object;
        if ((this.idmca == null && other.idmca != null) || (this.idmca != null && !this.idmca.equals(other.idmca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Mca[ idmca=" + idmca + " ]";
    }
    
}
