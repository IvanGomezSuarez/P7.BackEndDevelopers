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
@Table(name = "emd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emd.findAll", query = "SELECT e FROM Emd e")
    , @NamedQuery(name = "Emd.findByIdemd", query = "SELECT e FROM Emd e WHERE e.idemd = :idemd")
    , @NamedQuery(name = "Emd.findByVersion", query = "SELECT e FROM Emd e WHERE e.version = :version")
    , @NamedQuery(name = "Emd.findByAutor", query = "SELECT e FROM Emd e WHERE e.autor = :autor")})
public class Emd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idemd")
    private Integer idemd;
    @Column(name = "version")
    private String version;
    @Column(name = "autor")
    private String autor;
    @JoinColumn(name = "tecnico_asignado", referencedColumnName = "idtecnicos")
    @ManyToOne(optional = false)
    private Tecnicos tecnicoAsignado;

    public Emd() {
    }

    public Emd(Integer idemd) {
        this.idemd = idemd;
    }

    public Integer getIdemd() {
        return idemd;
    }

    public void setIdemd(Integer idemd) {
        this.idemd = idemd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Tecnicos getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(Tecnicos tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemd != null ? idemd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emd)) {
            return false;
        }
        Emd other = (Emd) object;
        if ((this.idemd == null && other.idemd != null) || (this.idemd != null && !this.idemd.equals(other.idemd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Emd[ idemd=" + idemd + " ]";
    }
    
}
