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
@Table(name = "stepvoltage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stepvoltage.findAll", query = "SELECT s FROM Stepvoltage s")
    , @NamedQuery(name = "Stepvoltage.findByIdstepVoltage", query = "SELECT s FROM Stepvoltage s WHERE s.idstepVoltage = :idstepVoltage")
    , @NamedQuery(name = "Stepvoltage.findByTension", query = "SELECT s FROM Stepvoltage s WHERE s.tension = :tension")
    , @NamedQuery(name = "Stepvoltage.findByResistencia", query = "SELECT s FROM Stepvoltage s WHERE s.resistencia = :resistencia")
    , @NamedQuery(name = "Stepvoltage.findByDiferencia", query = "SELECT s FROM Stepvoltage s WHERE s.diferencia = :diferencia")})
public class Stepvoltage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idstepVoltage")
    private Integer idstepVoltage;
    @Column(name = "tension")
    private Integer tension;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "resistencia")
    private Float resistencia;
    @Column(name = "diferencia")
    private Float diferencia;
    @JoinColumn(name = "idEnsayo", referencedColumnName = "idensayos")
    @ManyToOne
    private Ensayos idEnsayo;

    public Stepvoltage() {
    }

    public Stepvoltage(Integer idstepVoltage) {
        this.idstepVoltage = idstepVoltage;
    }

    public Integer getIdstepVoltage() {
        return idstepVoltage;
    }

    public void setIdstepVoltage(Integer idstepVoltage) {
        this.idstepVoltage = idstepVoltage;
    }

    public Integer getTension() {
        return tension;
    }

    public void setTension(Integer tension) {
        this.tension = tension;
    }

    public Float getResistencia() {
        return resistencia;
    }

    public void setResistencia(Float resistencia) {
        this.resistencia = resistencia;
    }

    public Float getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Float diferencia) {
        this.diferencia = diferencia;
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
        hash += (idstepVoltage != null ? idstepVoltage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stepvoltage)) {
            return false;
        }
        Stepvoltage other = (Stepvoltage) object;
        if ((this.idstepVoltage == null && other.idstepVoltage != null) || (this.idstepVoltage != null && !this.idstepVoltage.equals(other.idstepVoltage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Stepvoltage[ idstepVoltage=" + idstepVoltage + " ]";
    }
    
}
