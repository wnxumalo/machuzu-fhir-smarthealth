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

public class Immunisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IMMUNISATION_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String iStatus;
    private String statusReason;
    private String vaccineCode;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date occurenceDate;
    private String occurenceString;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date recorded;
    private boolean primarySource;
    private String reportOrigin;
    private String lotNumber;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date expirationDate;
    private String site;
    private String route;
    private String doseQuantity;
    private String note;
    private String reasonCode;
    private boolean isSubpotent;
    private String subpotentReason;
    private String programEligibility;
    private String fundingSource;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date reactionDate;
    private String reactionDetail;
    private boolean reactionReported;

    public Immunisation() {
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

    public String getIStatus() {
        return iStatus;
    }

    public void setIStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }
    
    public String getVaccineCode() {
        return vaccineCode;
    }
    
    public void setOccurenceDate(Date occurenceDate) {
        this.occurenceDate = occurenceDate;
    }
    
    public Date getOccurenceDate() {
        return occurenceDate;
    }
    
    public void setOccurenceString(String occurenceString) {
        this.occurenceString = occurenceString;
    }
    
    public String getOccurenceString() {
        return occurenceString;
    }
    
    public void setRecorded(Date recorded) {
        this.recorded = recorded;
    }
    
    public Date getRecorded() {
        return recorded;
    }
  
    public void setPrimarySource(boolean primarySource) {
        this.primarySource = primarySource;
    }
    
    public boolean getPrimarySource() {
        return primarySource;
    }
    
    public void setReportOrigin(String reportOrigin) {
        this.reportOrigin = reportOrigin;
    }
    
    public String getReportOrigin() {
        return reportOrigin;
    }
    
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }
    
    public String getLotNumber() {
        return lotNumber;
    }
    
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    public String getSite() {
        return site;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }
    
    public String getRoute() {
        return route;
    }
    
    public void setDoseQuantity(String doseQuantity) {
        this.doseQuantity = doseQuantity;
    }
    
    public String getDoseQuantity() {
        return doseQuantity;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public String getReasonCode() {
        return reasonCode;
    }
    
    public void setIsSubpotent(boolean isSubpotent) {
        this.isSubpotent = isSubpotent;
    }
    
    public boolean getIsSubpotent() {
        return isSubpotent;
    }

    public void setSubpotentReason(String subpotentReason) {
        this.subpotentReason = subpotentReason;
    }
    
    public String getSubpotentReason() {
        return subpotentReason;
    }
    
    public void setProgramEligibility(String programEligibility) {
        this.programEligibility = programEligibility;
    }
    
    public String getProgramEligibility() {
        return programEligibility;
    }
    
    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }
    
    public String getFundingSource() {
        return fundingSource;
    }
    
    public void setReactionDate(Date reactionDate) {
        this.reactionDate = reactionDate;
    }
    
    public Date getReactionDate() {
        return reactionDate;
    }
    
    public void setReactionDetail(String reactionDetail) {
        this.reactionDetail = reactionDetail;
    }
    
    public String getReactionDetail() {
        return reactionDetail;
    }

    public void setReactionReported(boolean reactionReported) {
        this.reactionReported = reactionReported;
    }

    public boolean getReactionReported() {
        return reactionReported;
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
        if (!(object instanceof Immunisation)) {
            return false;
        }
        Immunisation other = (Immunisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Immunisation[ id=" + id + " ]";
    }
    
}