/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmotors;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "tecnicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tecnicos.findAll", query = "SELECT t FROM Tecnicos t")
    , @NamedQuery(name = "Tecnicos.findByIdtecnicos", query = "SELECT t FROM Tecnicos t WHERE t.idtecnicos = :idtecnicos")
    , @NamedQuery(name = "Tecnicos.findByFechaAlta", query = "SELECT t FROM Tecnicos t WHERE t.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Tecnicos.findByFechaBaja", query = "SELECT t FROM Tecnicos t WHERE t.fechaBaja = :fechaBaja")})
public class Tecnicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtecnicos")
    private Integer idtecnicos;
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @JoinColumn(name = "idtecnicos", referencedColumnName = "idpersona", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ejecutadoPor")
    private Collection<Ensayos> ensayosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tecnicoAsignado")
    private Collection<Emd> emdCollection;

    public Tecnicos() {
    }

    public Tecnicos(Integer idtecnicos) {
        this.idtecnicos = idtecnicos;
    }

    public Integer getIdtecnicos() {
        return idtecnicos;
    }

    public void setIdtecnicos(Integer idtecnicos) {
        this.idtecnicos = idtecnicos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @XmlTransient
    public Collection<Ensayos> getEnsayosCollection() {
        return ensayosCollection;
    }

    public void setEnsayosCollection(Collection<Ensayos> ensayosCollection) {
        this.ensayosCollection = ensayosCollection;
    }

    @XmlTransient
    public Collection<Emd> getEmdCollection() {
        return emdCollection;
    }

    public void setEmdCollection(Collection<Emd> emdCollection) {
        this.emdCollection = emdCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtecnicos != null ? idtecnicos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tecnicos)) {
            return false;
        }
        Tecnicos other = (Tecnicos) object;
        if ((this.idtecnicos == null && other.idtecnicos != null) || (this.idtecnicos != null && !this.idtecnicos.equals(other.idtecnicos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Tecnicos[ idtecnicos=" + idtecnicos + " ]";
    }
    
}
