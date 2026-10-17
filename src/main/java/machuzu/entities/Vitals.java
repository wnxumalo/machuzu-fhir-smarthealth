package machuzu.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({  
 @NamedQuery(name="Vitals.getDashboard", 
    query="SELECT v FROM Vitals v WHERE v.ipa = :ipa ORDER BY v.encounter DESC")
})
public class Vitals implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bp;
    private String temp;
    private String pulse;
    private String resp;
    private String weight;
    private String height;
    private String spo2;
    private String bmi;
    private String bsa;

    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID field
    @Column(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID field
    @Column(name="IPA_ID", nullable=true)
    private Long ipa;
    
    public Vitals() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }
    
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    
    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
    
    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
    
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }
    
    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
    
    public String getBsa() {
        return bsa;
    }

    public void setBsa(String bsa) {
        this.bsa = bsa;
    }
    
    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }
    
    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
        this.ipa = ipa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vitals)) {
            return false;
        }
        Vitals other = (Vitals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Vitals[ id=" + id + " ]";
    }
}
