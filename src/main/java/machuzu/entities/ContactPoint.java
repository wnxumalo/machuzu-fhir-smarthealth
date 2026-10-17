package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


@Entity

public class ContactPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String patientId;            
    private String telecomSystem;
    private String telecomValue;
    private String telecomUse;
    private String telecomRank;
    private Date telecomPeriodFrom;
    private Date telecomPeriodTo;
    
    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;
    
    @JoinColumn(name="CONTACT_ID", nullable=true)
    private Long contact;

    public ContactPoint() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTelecomSystem() {
        return telecomSystem;
    }

    public void setTelecomSystem(String telecomSystem) {
        this.telecomSystem = telecomSystem;
    }
    
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public String getTelecomRank() {
        return telecomRank;
    }

    public void setTelecomRank(String telecomRank) {
        this.telecomRank = telecomRank;
    }
    
    public String getTelecomValue() {
        return telecomValue;
    }

    public void setTelecomValue(String telecomValue) {
        this.telecomValue = telecomValue;
    }
    
    public String getTelecomUse() {
        return telecomUse;
    }

    public void setTelecomUse(String telecomUse) {
        this.telecomUse = telecomUse;
    }
    
    public Date getTelecomPeriodFrom() {
        return telecomPeriodFrom;
    }

    public void setTelecomPeriodFrom(Date telecomPeriodFrom) {
        this.telecomPeriodFrom = telecomPeriodFrom;
    }

    public Date getTelecomPeriodTo() {
        return telecomPeriodTo;
    }

    public void setTelecomPeriodTo(Date telecomPeriodTo) {
        this.telecomPeriodTo = telecomPeriodTo;
    }

    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
        this.ipa = ipa;
    }
    
    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactPoint)) {
            return false;
        }
        ContactPoint other = (ContactPoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.ContactPoint[ id=" + id + " ]";
    }
    
}