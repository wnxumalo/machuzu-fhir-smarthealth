package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;


@Entity

public class Procedures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROCEDURE_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    @JoinColumn(name="CPTCSMPROCEDURES_ID", nullable=true)
    private Long cptcsmprocedures;

    private String procedureStatus;
    private String statusReason;    
    private String category;
    private String pCode;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date performedDateTime;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date performedPeriodFrom;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date performedPeriodTo;
    private String performedString;
    private String performedAge;
    private String performedRange;
    private String reasonCode;
    private String bodySite;
    private String outcome;
    private String complication;
    private String followUp;
    private String note;    


    public Procedures() {
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
    
    public Long getCptcsmprocedures() {
        return cptcsmprocedures;
    }

    public void setCptcsmprocedures(Long cptcsmprocedures) {
        this.cptcsmprocedures = cptcsmprocedures;
    }
    
    public String getProcedureStatus() {
        return procedureStatus;
    }

    public void setProcedureStatus(String procedureStatus) {
        this.procedureStatus = procedureStatus;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }

    public void setPCode(String pCode) {
        this.pCode = pCode;
    }

    public String getPCode() {
        return pCode;
    }
    
    public void setPerformedDateTime(Date performedDateTime) {
        this.performedDateTime = performedDateTime;
    }

    public Date getPerformedDateTime() {
        return performedDateTime;
    }

    public void setPerformedPeriodFrom(Date performedPeriodFrom) {
        this.performedPeriodFrom = performedPeriodFrom;
    }
    
    public Date getPerformedPeriodFrom() {
        return performedPeriodFrom;
    }
    
    public void setPerformedPeriodTo(Date performedPeriodTo) {
        this.performedPeriodTo = performedPeriodTo;
    }

    public Date getPerformedPeriodTo() {
        return performedPeriodTo;
    }
    
    public void setPerformedString(String performedString) {
        this.performedString = performedString;
    }
    
    public String getPerformedString() {
        return performedString;
    }
    
    public void setPerformedAge(String performedAge) {
        this.performedAge = performedAge;
    }
    
    public String getPerformedAge() {
        return performedAge;
    }
    
    public void setPerformedRange(String performedRange) {
        this.performedRange = performedRange;
    }
    
    public String getPerformedRange() {
        return performedRange;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public String getReasonCode() {
        return reasonCode;
    }
    
    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }
    
    public String getBodySite() {
        return bodySite;
    }
        
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
    
    public String getOutcome() {
        return outcome;
    }
        
    public String getComplication() {
        return complication;
    }
    
    public void setComplication(String complication) {
        this.complication = complication;
    }
    
    public String getFollowUp() {
        return followUp;
    }
    
    public void setFollowUp(String followUp) {
        this.followUp = followUp;
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
        if (!(object instanceof Procedures)) {
            return false;
        }
        Procedures other = (Procedures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Procedures[ id=" + id + " ]";
    }
    
}