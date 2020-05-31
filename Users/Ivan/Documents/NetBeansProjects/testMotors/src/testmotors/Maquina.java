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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "maquina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m")
    , @NamedQuery(name = "Maquina.findByIdmaquina", query = "SELECT m FROM Maquina m WHERE m.idmaquina = :idmaquina")
    , @NamedQuery(name = "Maquina.findByTipo", query = "SELECT m FROM Maquina m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Maquina.findByPotencia", query = "SELECT m FROM Maquina m WHERE m.potencia = :potencia")
    , @NamedQuery(name = "Maquina.findByMarca", query = "SELECT m FROM Maquina m WHERE m.marca = :marca")
    , @NamedQuery(name = "Maquina.findByModelo", query = "SELECT m FROM Maquina m WHERE m.modelo = :modelo")
    , @NamedQuery(name = "Maquina.findBySerial", query = "SELECT m FROM Maquina m WHERE m.serial = :serial")
    , @NamedQuery(name = "Maquina.findByTension", query = "SELECT m FROM Maquina m WHERE m.tension = :tension")
    , @NamedQuery(name = "Maquina.findByCorriente", query = "SELECT m FROM Maquina m WHERE m.corriente = :corriente")
    , @NamedQuery(name = "Maquina.findByTensionRotor", query = "SELECT m FROM Maquina m WHERE m.tensionRotor = :tensionRotor")
    , @NamedQuery(name = "Maquina.findByCorrienteRotor", query = "SELECT m FROM Maquina m WHERE m.corrienteRotor = :corrienteRotor")
    , @NamedQuery(name = "Maquina.findByRpm", query = "SELECT m FROM Maquina m WHERE m.rpm = :rpm")
    , @NamedQuery(name = "Maquina.findByCoseno", query = "SELECT m FROM Maquina m WHERE m.coseno = :coseno")
    , @NamedQuery(name = "Maquina.findByIp", query = "SELECT m FROM Maquina m WHERE m.ip = :ip")
    , @NamedQuery(name = "Maquina.findByOrientacion", query = "SELECT m FROM Maquina m WHERE m.orientacion = :orientacion")})
public class Maquina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmaquina")
    private Integer idmaquina;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "potencia")
    private Float potencia;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "serial")
    private String serial;
    @Column(name = "tension")
    private Integer tension;
    @Column(name = "corriente")
    private Float corriente;
    @Column(name = "tensionRotor")
    private Integer tensionRotor;
    @Column(name = "corrienteRotor")
    private Float corrienteRotor;
    @Column(name = "rpm")
    private Float rpm;
    @Column(name = "coseno")
    private Float coseno;
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "orientacion")
    private Character orientacion;

    public Maquina() {
    }

    public Maquina(Integer idmaquina) {
        this.idmaquina = idmaquina;
    }

    public Maquina(Integer idmaquina, String tipo, Character orientacion) {
        this.idmaquina = idmaquina;
        this.tipo = tipo;
        this.orientacion = orientacion;
    }

    public Integer getIdmaquina() {
        return idmaquina;
    }

    public void setIdmaquina(Integer idmaquina) {
        this.idmaquina = idmaquina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getPotencia() {
        return potencia;
    }

    public void setPotencia(Float potencia) {
        this.potencia = potencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getTension() {
        return tension;
    }

    public void setTension(Integer tension) {
        this.tension = tension;
    }

    public Float getCorriente() {
        return corriente;
    }

    public void setCorriente(Float corriente) {
        this.corriente = corriente;
    }

    public Integer getTensionRotor() {
        return tensionRotor;
    }

    public void setTensionRotor(Integer tensionRotor) {
        this.tensionRotor = tensionRotor;
    }

    public Float getCorrienteRotor() {
        return corrienteRotor;
    }

    public void setCorrienteRotor(Float corrienteRotor) {
        this.corrienteRotor = corrienteRotor;
    }

    public Float getRpm() {
        return rpm;
    }

    public void setRpm(Float rpm) {
        this.rpm = rpm;
    }

    public Float getCoseno() {
        return coseno;
    }

    public void setCoseno(Float coseno) {
        this.coseno = coseno;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Character getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Character orientacion) {
        this.orientacion = orientacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaquina != null ? idmaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquina)) {
            return false;
        }
        Maquina other = (Maquina) object;
        if ((this.idmaquina == null && other.idmaquina != null) || (this.idmaquina != null && !this.idmaquina.equals(other.idmaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Maquina[ idmaquina=" + idmaquina + " ]";
    }
    
}
