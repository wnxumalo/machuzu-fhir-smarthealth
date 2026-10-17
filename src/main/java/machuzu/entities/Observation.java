package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;


@Entity

public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OBSERVATION_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    @JoinColumn(name="DIAGNOSIS_ID", nullable=true)
    private Long diagnosis;

    private String oStatus;
    private String category;
    private String oCode;
    private String occurenceString;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date effectiveDate;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date issued;
    private String interpretation;
    private String note;
    private String bodySite;
    private String oMethod;

    public Observation() {
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
    
    public Long getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Long diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getOStatus() {
        return oStatus;
    }

    public void setOStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOCode(String oCode) {
        this.oCode = oCode;
    }
    
    public String getOCode() {
        return oCode;
    }
  
    public void setOccurenceString(String occurenceString) {
        this.occurenceString = occurenceString;
    }
    
    public String getOccurenceString() {
        return occurenceString;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    
    public void setIssued(Date issued) {
        this.issued = issued;
    }
    
    public Date getIssued() {
        return issued;
    }
    
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
    
    public String getInterpretation() {
        return interpretation;
    }
   
    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }
    
    public String getBodySite() {
        return bodySite;
    }
    
    public void setOMethod(String oMethod) {
        this.oMethod = oMethod;
    }
    
    public String getOMethod() {
        return oMethod;
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
        if (!(object instanceof Observation)) {
            return false;
        }
        Observation other = (Observation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Observation[ id=" + id + " ]";
    }
    
}