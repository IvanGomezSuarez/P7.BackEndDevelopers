/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmotors;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")
    , @NamedQuery(name = "Clientes.findByIdclientes", query = "SELECT c FROM Clientes c WHERE c.idclientes = :idclientes")
    , @NamedQuery(name = "Clientes.findByRSocial", query = "SELECT c FROM Clientes c WHERE c.rSocial = :rSocial")
    , @NamedQuery(name = "Clientes.findByVia", query = "SELECT c FROM Clientes c WHERE c.via = :via")
    , @NamedQuery(name = "Clientes.findByNumero", query = "SELECT c FROM Clientes c WHERE c.numero = :numero")
    , @NamedQuery(name = "Clientes.findByLocalidad", query = "SELECT c FROM Clientes c WHERE c.localidad = :localidad")
    , @NamedQuery(name = "Clientes.findByProvincia", query = "SELECT c FROM Clientes c WHERE c.provincia = :provincia")
    , @NamedQuery(name = "Clientes.findByCp", query = "SELECT c FROM Clientes c WHERE c.cp = :cp")})

public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idclientes")
    private Integer idclientes;
    @Basic(optional = false)
    @Column(name = "rSocial")
    private String rSocial;
    @Basic(optional = false)
    @Column(name = "via")
    private String via;
    @Basic(optional = false)
    @Column(name = " numero")
    private String numero;
    @Basic(optional = false)
    @Column(name = "localidad")
    private String localidad;
    @Basic(optional = false)
    @Column(name = "provincia")
    private String provincia;
    @Column(name = "cp")
    private String cp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Ensayos> ensayosCollection;
    @JoinColumn(name = "pContacto", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona pContacto;

    public Clientes() {
    }

    public Clientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public Clientes(Integer idclientes, String rSocial, String via, String numero, String localidad, String provincia) {
        this.idclientes = idclientes;
        this.rSocial = rSocial;
        this.via = via;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Integer getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(Integer idclientes) {
        this.idclientes = idclientes;
    }

    public String getRSocial() {
        return rSocial;
    }

    public void setRSocial(String rSocial) {
        this.rSocial = rSocial;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @XmlTransient
    public Collection<Ensayos> getEnsayosCollection() {
        return ensayosCollection;
    }

    public void setEnsayosCollection(Collection<Ensayos> ensayosCollection) {
        this.ensayosCollection = ensayosCollection;
    }

    public Persona getPContacto() {
        return pContacto;
    }

    public void setPContacto(Persona pContacto) {
        this.pContacto = pContacto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclientes != null ? idclientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idclientes == null && other.idclientes != null) || (this.idclientes != null && !this.idclientes.equals(other.idclientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Clientes[ idclientes=" + idclientes + " ]";
    }
    
}
