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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "ensayos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ensayos.findAll", query = "SELECT e FROM Ensayos e")
    , @NamedQuery(name = "Ensayos.findByIdensayos", query = "SELECT e FROM Ensayos e WHERE e.idensayos = :idensayos")
    , @NamedQuery(name = "Ensayos.findByFechaInicio", query = "SELECT e FROM Ensayos e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Ensayos.findByFechaFin", query = "SELECT e FROM Ensayos e WHERE e.fechaFin = :fechaFin")})
public class Ensayos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idensayos")
    private Integer idensayos;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idensayo")
    private Collection<Eca> ecaCollection;
    @JoinColumn(name = "cliente", referencedColumnName = "idclientes")
    @ManyToOne(optional = false)
    private Clientes cliente;
    @JoinColumn(name = "ejecutadoPor", referencedColumnName = "idtecnicos")
    @ManyToOne(optional = false)
    private Tecnicos ejecutadoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnsayo")
    private Collection<Mca> mcaCollection;
    @OneToMany(mappedBy = "idEnsayo")
    private Collection<Stepvoltage> stepvoltageCollection;

    public Ensayos() {
    }

    public Ensayos(Integer idensayos) {
        this.idensayos = idensayos;
    }

    public Ensayos(Integer idensayos, Date fechaInicio, Date fechaFin) {
        this.idensayos = idensayos;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdensayos() {
        return idensayos;
    }

    public void setIdensayos(Integer idensayos) {
        this.idensayos = idensayos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public Collection<Eca> getEcaCollection() {
        return ecaCollection;
    }

    public void setEcaCollection(Collection<Eca> ecaCollection) {
        this.ecaCollection = ecaCollection;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Tecnicos getEjecutadoPor() {
        return ejecutadoPor;
    }

    public void setEjecutadoPor(Tecnicos ejecutadoPor) {
        this.ejecutadoPor = ejecutadoPor;
    }

    @XmlTransient
    public Collection<Mca> getMcaCollection() {
        return mcaCollection;
    }

    public void setMcaCollection(Collection<Mca> mcaCollection) {
        this.mcaCollection = mcaCollection;
    }

    @XmlTransient
    public Collection<Stepvoltage> getStepvoltageCollection() {
        return stepvoltageCollection;
    }

    public void setStepvoltageCollection(Collection<Stepvoltage> stepvoltageCollection) {
        this.stepvoltageCollection = stepvoltageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idensayos != null ? idensayos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ensayos)) {
            return false;
        }
        Ensayos other = (Ensayos) object;
        if ((this.idensayos == null && other.idensayos != null) || (this.idensayos != null && !this.idensayos.equals(other.idensayos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Ensayos[ idensayos=" + idensayos + " ]";
    }
    
}
