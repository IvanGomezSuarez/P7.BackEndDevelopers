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
@Table(name = "eca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eca.findAll", query = "SELECT e FROM Eca e")
    , @NamedQuery(name = "Eca.findByIdeca", query = "SELECT e FROM Eca e WHERE e.ideca = :ideca")
    , @NamedQuery(name = "Eca.findByCapBF", query = "SELECT e FROM Eca e WHERE e.capBF = :capBF")
    , @NamedQuery(name = "Eca.findByCapAF", query = "SELECT e FROM Eca e WHERE e.capAF = :capAF")
    , @NamedQuery(name = "Eca.findByPtc", query = "SELECT e FROM Eca e WHERE e.ptc = :ptc")
    , @NamedQuery(name = "Eca.findByTempDevanado", query = "SELECT e FROM Eca e WHERE e.tempDevanado = :tempDevanado")
    , @NamedQuery(name = "Eca.findByNATm", query = "SELECT e FROM Eca e WHERE e.nATm = :nATm")
    , @NamedQuery(name = "Eca.findByNATm1", query = "SELECT e FROM Eca e WHERE e.nATm1 = :nATm1")
    , @NamedQuery(name = "Eca.findByNATm2", query = "SELECT e FROM Eca e WHERE e.nATm2 = :nATm2")
    , @NamedQuery(name = "Eca.findByNATm3", query = "SELECT e FROM Eca e WHERE e.nATm3 = :nATm3")
    , @NamedQuery(name = "Eca.findByNATm4", query = "SELECT e FROM Eca e WHERE e.nATm4 = :nATm4")
    , @NamedQuery(name = "Eca.findByNATm5", query = "SELECT e FROM Eca e WHERE e.nATm5 = :nATm5")
    , @NamedQuery(name = "Eca.findByNATm6", query = "SELECT e FROM Eca e WHERE e.nATm6 = :nATm6")
    , @NamedQuery(name = "Eca.findByNATm7", query = "SELECT e FROM Eca e WHERE e.nATm7 = :nATm7")
    , @NamedQuery(name = "Eca.findByNATm8", query = "SELECT e FROM Eca e WHERE e.nATm8 = :nATm8")
    , @NamedQuery(name = "Eca.findByNATm9", query = "SELECT e FROM Eca e WHERE e.nATm9 = :nATm9")
    , @NamedQuery(name = "Eca.findByNATm10", query = "SELECT e FROM Eca e WHERE e.nATm10 = :nATm10")
    , @NamedQuery(name = "Eca.findByNATmay", query = "SELECT e FROM Eca e WHERE e.nATmay = :nATmay")
    , @NamedQuery(name = "Eca.findByNATmay1", query = "SELECT e FROM Eca e WHERE e.nATmay1 = :nATmay1")
    , @NamedQuery(name = "Eca.findByNATmay2", query = "SELECT e FROM Eca e WHERE e.nATmay2 = :nATmay2")
    , @NamedQuery(name = "Eca.findByNATmay3", query = "SELECT e FROM Eca e WHERE e.nATmay3 = :nATmay3")
    , @NamedQuery(name = "Eca.findByNATmay4", query = "SELECT e FROM Eca e WHERE e.nATmay4 = :nATmay4")
    , @NamedQuery(name = "Eca.findByNATmay5", query = "SELECT e FROM Eca e WHERE e.nATmay5 = :nATmay5")
    , @NamedQuery(name = "Eca.findByNATmay6", query = "SELECT e FROM Eca e WHERE e.nATmay6 = :nATmay6")
    , @NamedQuery(name = "Eca.findByNATmay7", query = "SELECT e FROM Eca e WHERE e.nATmay7 = :nATmay7")
    , @NamedQuery(name = "Eca.findByNATmay8", query = "SELECT e FROM Eca e WHERE e.nATmay8 = :nATmay8")
    , @NamedQuery(name = "Eca.findByNATmay9", query = "SELECT e FROM Eca e WHERE e.nATmay9 = :nATmay9")
    , @NamedQuery(name = "Eca.findByNATmay10", query = "SELECT e FROM Eca e WHERE e.nATmay10 = :nATmay10")
    , @NamedQuery(name = "Eca.findByDd500", query = "SELECT e FROM Eca e WHERE e.dd500 = :dd500")
    , @NamedQuery(name = "Eca.findByDd2500", query = "SELECT e FROM Eca e WHERE e.dd2500 = :dd2500")
    , @NamedQuery(name = "Eca.findByIp500", query = "SELECT e FROM Eca e WHERE e.ip500 = :ip500")
    , @NamedQuery(name = "Eca.findByIp2500", query = "SELECT e FROM Eca e WHERE e.ip2500 = :ip2500")
    , @NamedQuery(name = "Eca.findByDar500", query = "SELECT e FROM Eca e WHERE e.dar500 = :dar500")
    , @NamedQuery(name = "Eca.findByDar2500", query = "SELECT e FROM Eca e WHERE e.dar2500 = :dar2500")
    , @NamedQuery(name = "Eca.findByCt500", query = "SELECT e FROM Eca e WHERE e.ct500 = :ct500")
    , @NamedQuery(name = "Eca.findByCt2500", query = "SELECT e FROM Eca e WHERE e.ct2500 = :ct2500")
    , @NamedQuery(name = "Eca.findByRohmUV", query = "SELECT e FROM Eca e WHERE e.rohmUV = :rohmUV")
    , @NamedQuery(name = "Eca.findByRohmVW", query = "SELECT e FROM Eca e WHERE e.rohmVW = :rohmVW")
    , @NamedQuery(name = "Eca.findByRohmUW", query = "SELECT e FROM Eca e WHERE e.rohmUW = :rohmUW")
    , @NamedQuery(name = "Eca.findByCapMegger500", query = "SELECT e FROM Eca e WHERE e.capMegger500 = :capMegger500")
    , @NamedQuery(name = "Eca.findByCapMegger2500", query = "SELECT e FROM Eca e WHERE e.capMegger2500 = :capMegger2500")})
public class Eca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ideca")
    private Integer ideca;
    @Basic(optional = false)
    @Column(name = "capBF")
    private float capBF;
    @Basic(optional = false)
    @Column(name = "capAF")
    private float capAF;
    @Basic(optional = false)
    @Column(name = "ptc")
    private float ptc;
    @Basic(optional = false)
    @Column(name = "tempDevanado")
    private float tempDevanado;
    @Basic(optional = false)
    @Column(name = "15''nATm")
    private float nATm;
    @Basic(optional = false)
    @Column(name = "30''nATm")
    private float nATm1;
    @Basic(optional = false)
    @Column(name = "45''nATm")
    private float nATm2;
    @Basic(optional = false)
    @Column(name = "1nATm")
    private float nATm3;
    @Basic(optional = false)
    @Column(name = "2nATm")
    private float nATm4;
    @Basic(optional = false)
    @Column(name = "3nATm")
    private float nATm5;
    @Basic(optional = false)
    @Column(name = "5nATm")
    private float nATm6;
    @Basic(optional = false)
    @Column(name = "7nATm")
    private float nATm7;
    @Basic(optional = false)
    @Column(name = "9nATm")
    private float nATm8;
    @Basic(optional = false)
    @Column(name = "10nATm")
    private float nATm9;
    @Basic(optional = false)
    @Column(name = "15nATm")
    private float nATm10;
    @Basic(optional = false)
    @Column(name = "15''nATmay")
    private float nATmay;
    @Basic(optional = false)
    @Column(name = "30''nATmay")
    private float nATmay1;
    @Basic(optional = false)
    @Column(name = "45''nATmay")
    private float nATmay2;
    @Basic(optional = false)
    @Column(name = "1nATmay")
    private float nATmay3;
    @Basic(optional = false)
    @Column(name = "2nATmay")
    private float nATmay4;
    @Basic(optional = false)
    @Column(name = "3nATmay")
    private float nATmay5;
    @Basic(optional = false)
    @Column(name = "5nATmay")
    private float nATmay6;
    @Basic(optional = false)
    @Column(name = "7nATmay")
    private float nATmay7;
    @Basic(optional = false)
    @Column(name = "9nATmay")
    private float nATmay8;
    @Basic(optional = false)
    @Column(name = "10nATmay")
    private float nATmay9;
    @Basic(optional = false)
    @Column(name = "15nATmay")
    private float nATmay10;
    @Basic(optional = false)
    @Column(name = "DD500")
    private float dd500;
    @Basic(optional = false)
    @Column(name = "DD2500")
    private float dd2500;
    @Basic(optional = false)
    @Column(name = "IP500")
    private float ip500;
    @Basic(optional = false)
    @Column(name = "IP2500")
    private float ip2500;
    @Basic(optional = false)
    @Column(name = "DAR500")
    private float dar500;
    @Basic(optional = false)
    @Column(name = "DAR2500")
    private float dar2500;
    @Basic(optional = false)
    @Column(name = "CT500")
    private float ct500;
    @Basic(optional = false)
    @Column(name = "CT2500")
    private float ct2500;
    @Basic(optional = false)
    @Column(name = "RohmUV")
    private float rohmUV;
    @Basic(optional = false)
    @Column(name = "RohmVW")
    private float rohmVW;
    @Basic(optional = false)
    @Column(name = "RohmUW")
    private float rohmUW;
    @Basic(optional = false)
    @Column(name = "capMegger500")
    private float capMegger500;
    @Basic(optional = false)
    @Column(name = "capMegger2500")
    private float capMegger2500;
    @JoinColumn(name = "idensayo", referencedColumnName = "idensayos")
    @ManyToOne(optional = false)
    private Ensayos idensayo;

    public Eca() {
    }

    public Eca(Integer ideca) {
        this.ideca = ideca;
    }

    public Eca(Integer ideca, float capBF, float capAF, float ptc, float tempDevanado, float nATm, float nATm1, float nATm2, float nATm3, float nATm4, float nATm5, float nATm6, float nATm7, float nATm8, float nATm9, float nATm10, float nATmay, float nATmay1, float nATmay2, float nATmay3, float nATmay4, float nATmay5, float nATmay6, float nATmay7, float nATmay8, float nATmay9, float nATmay10, float dd500, float dd2500, float ip500, float ip2500, float dar500, float dar2500, float ct500, float ct2500, float rohmUV, float rohmVW, float rohmUW, float capMegger500, float capMegger2500) {
        this.ideca = ideca;
        this.capBF = capBF;
        this.capAF = capAF;
        this.ptc = ptc;
        this.tempDevanado = tempDevanado;
        this.nATm = nATm;
        this.nATm1 = nATm1;
        this.nATm2 = nATm2;
        this.nATm3 = nATm3;
        this.nATm4 = nATm4;
        this.nATm5 = nATm5;
        this.nATm6 = nATm6;
        this.nATm7 = nATm7;
        this.nATm8 = nATm8;
        this.nATm9 = nATm9;
        this.nATm10 = nATm10;
        this.nATmay = nATmay;
        this.nATmay1 = nATmay1;
        this.nATmay2 = nATmay2;
        this.nATmay3 = nATmay3;
        this.nATmay4 = nATmay4;
        this.nATmay5 = nATmay5;
        this.nATmay6 = nATmay6;
        this.nATmay7 = nATmay7;
        this.nATmay8 = nATmay8;
        this.nATmay9 = nATmay9;
        this.nATmay10 = nATmay10;
        this.dd500 = dd500;
        this.dd2500 = dd2500;
        this.ip500 = ip500;
        this.ip2500 = ip2500;
        this.dar500 = dar500;
        this.dar2500 = dar2500;
        this.ct500 = ct500;
        this.ct2500 = ct2500;
        this.rohmUV = rohmUV;
        this.rohmVW = rohmVW;
        this.rohmUW = rohmUW;
        this.capMegger500 = capMegger500;
        this.capMegger2500 = capMegger2500;
    }

    public Integer getIdeca() {
        return ideca;
    }

    public void setIdeca(Integer ideca) {
        this.ideca = ideca;
    }

    public float getCapBF() {
        return capBF;
    }

    public void setCapBF(float capBF) {
        this.capBF = capBF;
    }

    public float getCapAF() {
        return capAF;
    }

    public void setCapAF(float capAF) {
        this.capAF = capAF;
    }

    public float getPtc() {
        return ptc;
    }

    public void setPtc(float ptc) {
        this.ptc = ptc;
    }

    public float getTempDevanado() {
        return tempDevanado;
    }

    public void setTempDevanado(float tempDevanado) {
        this.tempDevanado = tempDevanado;
    }

    public float getNATm() {
        return nATm;
    }

    public void setNATm(float nATm) {
        this.nATm = nATm;
    }

    public float getNATm1() {
        return nATm1;
    }

    public void setNATm1(float nATm1) {
        this.nATm1 = nATm1;
    }

    public float getNATm2() {
        return nATm2;
    }

    public void setNATm2(float nATm2) {
        this.nATm2 = nATm2;
    }

    public float getNATm3() {
        return nATm3;
    }

    public void setNATm3(float nATm3) {
        this.nATm3 = nATm3;
    }

    public float getNATm4() {
        return nATm4;
    }

    public void setNATm4(float nATm4) {
        this.nATm4 = nATm4;
    }

    public float getNATm5() {
        return nATm5;
    }

    public void setNATm5(float nATm5) {
        this.nATm5 = nATm5;
    }

    public float getNATm6() {
        return nATm6;
    }

    public void setNATm6(float nATm6) {
        this.nATm6 = nATm6;
    }

    public float getNATm7() {
        return nATm7;
    }

    public void setNATm7(float nATm7) {
        this.nATm7 = nATm7;
    }

    public float getNATm8() {
        return nATm8;
    }

    public void setNATm8(float nATm8) {
        this.nATm8 = nATm8;
    }

    public float getNATm9() {
        return nATm9;
    }

    public void setNATm9(float nATm9) {
        this.nATm9 = nATm9;
    }

    public float getNATm10() {
        return nATm10;
    }

    public void setNATm10(float nATm10) {
        this.nATm10 = nATm10;
    }

    public float getNATmay() {
        return nATmay;
    }

    public void setNATmay(float nATmay) {
        this.nATmay = nATmay;
    }

    public float getNATmay1() {
        return nATmay1;
    }

    public void setNATmay1(float nATmay1) {
        this.nATmay1 = nATmay1;
    }

    public float getNATmay2() {
        return nATmay2;
    }

    public void setNATmay2(float nATmay2) {
        this.nATmay2 = nATmay2;
    }

    public float getNATmay3() {
        return nATmay3;
    }

    public void setNATmay3(float nATmay3) {
        this.nATmay3 = nATmay3;
    }

    public float getNATmay4() {
        return nATmay4;
    }

    public void setNATmay4(float nATmay4) {
        this.nATmay4 = nATmay4;
    }

    public float getNATmay5() {
        return nATmay5;
    }

    public void setNATmay5(float nATmay5) {
        this.nATmay5 = nATmay5;
    }

    public float getNATmay6() {
        return nATmay6;
    }

    public void setNATmay6(float nATmay6) {
        this.nATmay6 = nATmay6;
    }

    public float getNATmay7() {
        return nATmay7;
    }

    public void setNATmay7(float nATmay7) {
        this.nATmay7 = nATmay7;
    }

    public float getNATmay8() {
        return nATmay8;
    }

    public void setNATmay8(float nATmay8) {
        this.nATmay8 = nATmay8;
    }

    public float getNATmay9() {
        return nATmay9;
    }

    public void setNATmay9(float nATmay9) {
        this.nATmay9 = nATmay9;
    }

    public float getNATmay10() {
        return nATmay10;
    }

    public void setNATmay10(float nATmay10) {
        this.nATmay10 = nATmay10;
    }

    public float getDd500() {
        return dd500;
    }

    public void setDd500(float dd500) {
        this.dd500 = dd500;
    }

    public float getDd2500() {
        return dd2500;
    }

    public void setDd2500(float dd2500) {
        this.dd2500 = dd2500;
    }

    public float getIp500() {
        return ip500;
    }

    public void setIp500(float ip500) {
        this.ip500 = ip500;
    }

    public float getIp2500() {
        return ip2500;
    }

    public void setIp2500(float ip2500) {
        this.ip2500 = ip2500;
    }

    public float getDar500() {
        return dar500;
    }

    public void setDar500(float dar500) {
        this.dar500 = dar500;
    }

    public float getDar2500() {
        return dar2500;
    }

    public void setDar2500(float dar2500) {
        this.dar2500 = dar2500;
    }

    public float getCt500() {
        return ct500;
    }

    public void setCt500(float ct500) {
        this.ct500 = ct500;
    }

    public float getCt2500() {
        return ct2500;
    }

    public void setCt2500(float ct2500) {
        this.ct2500 = ct2500;
    }

    public float getRohmUV() {
        return rohmUV;
    }

    public void setRohmUV(float rohmUV) {
        this.rohmUV = rohmUV;
    }

    public float getRohmVW() {
        return rohmVW;
    }

    public void setRohmVW(float rohmVW) {
        this.rohmVW = rohmVW;
    }

    public float getRohmUW() {
        return rohmUW;
    }

    public void setRohmUW(float rohmUW) {
        this.rohmUW = rohmUW;
    }

    public float getCapMegger500() {
        return capMegger500;
    }

    public void setCapMegger500(float capMegger500) {
        this.capMegger500 = capMegger500;
    }

    public float getCapMegger2500() {
        return capMegger2500;
    }

    public void setCapMegger2500(float capMegger2500) {
        this.capMegger2500 = capMegger2500;
    }

    public Ensayos getIdensayo() {
        return idensayo;
    }

    public void setIdensayo(Ensayos idensayo) {
        this.idensayo = idensayo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideca != null ? ideca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eca)) {
            return false;
        }
        Eca other = (Eca) object;
        if ((this.ideca == null && other.ideca != null) || (this.ideca != null && !this.ideca.equals(other.ideca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testmotors.Eca[ ideca=" + ideca + " ]";
    }
    
}
