package machuzu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity

public class Allergy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ALLERGY_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    private boolean clinicalStatus;
    private String verificationStatus;
    private String aType;
    private String category;
    private String criticality;
    private String aCode;
    private Date onsetDateTime;
    private int onsetAge;
    private Date onsetPeriodFrom;
    private Date onsetPeriodTo;
    private String onsetRange;
    private String onsetString;
    private Date recordedDate;
    private Date lastOccurrence;
    private String note;

    @OneToMany(mappedBy="allergy", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Reaction> reaction = new ArrayList<>();

    public Allergy() {
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

    public boolean getClinicalStatus() {
        return clinicalStatus;
    }

    public void setClinicalStatus(boolean clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public void setType(String aType) {
        this.aType = aType;
    }
    
    public String getType() {
        return aType;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }
    
    public String getCriticality() {
        return criticality;
    }
    
    public void setACode(String aCode) {
        this.aCode = aCode;
    }
    
    public String getACode() {
        return aCode;
    }
    
    public void setOnsetDateTime(Date onsetDateTime) {
        this.onsetDateTime = onsetDateTime;
    }
    
    public Date getOnsetDateTime() {
        return onsetDateTime;
    }
    
    public void setOnsetAge(int onsetAge) {
        this.onsetAge = onsetAge;
    }
    
    public int getOnsetAge() {
        return onsetAge;
    }
    
    public void setOnsetPeriodFrom(Date onsetPeriodFrom) {
        this.onsetPeriodFrom = onsetPeriodFrom;
    }
    
    public Date getOnsetPeriodFrom() {
        return onsetPeriodFrom;
    }
    
    public void setOnsetPeriodTo(Date onsetPeriodTo) {
        this.onsetPeriodTo = onsetPeriodTo;
    }
    
    public Date getOnsetPeriodTo() {
        return onsetPeriodTo;
    }

    public void setOnsetRange(String onsetRange) {
        this.onsetRange = onsetRange;
    }
    
    public String getOnsetRange() {
        return onsetRange;
    }
    
    public void setOnsetString(String onsetString) {
        this.onsetString = onsetString;
    }
    
    public String getOnsetString() {
        return onsetString;
    }
    
    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }
    
    public Date getRecordedDate() {
        return recordedDate;
    }
    
    public void setLastOccurrence(Date lastOccurrence) {
        this.lastOccurrence = lastOccurrence;
    }
    
    public Date getLastOccurrence() {
        return lastOccurrence;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setReaction(List<Reaction> reaction) {
        this.reaction = reaction;
    }

    public List<Reaction> getReaction() {
        return reaction;
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
        if (!(object instanceof Allergy)) {
            return false;
        }
        Allergy other = (Allergy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Allergy[ id=" + id + " ]";
    }
    
}