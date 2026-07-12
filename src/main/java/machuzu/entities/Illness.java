package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Illness implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONDITION_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String clinicalStatus;
    private String verificationStatus;
    
    private String category;
    private String severity;
    private String code;
    private String bodySite;
    private Date recordedDate;
    private String note;

    public Illness() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }

    public String getClinicalStatus() {
        return clinicalStatus;
    }

    public void setClinicalStatus(String clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public void setServiceType(String category) {
        this.category = category;
    }
    
    public String getServiceType() {
        return category;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }

    public String getBodySite() {
        return bodySite;
    }
    
    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }
    
    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String getNote() {
        return note;
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
        if (!(object instanceof Illness)) {
            return false;
        }
        Illness other = (Illness) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Illness[ id=" + id + " ]";
    }
    
}