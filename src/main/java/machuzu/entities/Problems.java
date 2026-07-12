package machuzu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
public class Problems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONDITION_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    private boolean clinicalStatus;
    private String verificationStatus;
    private String category;
    private String severity;    
    private String aCode;
    private String bodySite;
    private Date onsetDateTime;
    private int onsetAge;
    private Date onsetPeriodFrom;
    private Date onsetPeriodTo;
    private String onsetRange;
    private String onsetString;
    private Date abatementDateTime;
    private int abatementAge;
    private Date abatementPeriodFrom;
    private Date abatementPeriodTo;
    private String abatementRange;
    private String abatementString;    
    private Date recordedDate;
    private String note;

    @OneToMany(mappedBy="problems", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ConditionStage> stage = new ArrayList<>();
    
    @OneToMany(mappedBy="problems", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Evidence> evidence = new ArrayList<>();

    public Problems() {
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

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    public String getSeverity() {
        return severity;
    }
    
    public void setACode(String aCode) {
        this.aCode = aCode;
    }
    
    public String getACode() {
        return aCode;
    }
    
    public void setBodySite(String bodySite) {
        this.bodySite = bodySite;
    }
    
    public String getBodySite() {
        return bodySite;
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
    
    public void setAbatementDateTime(Date abatementDateTime) {
        this.abatementDateTime = abatementDateTime;
    }
    
    public Date getAbatementDateTime() {
        return abatementDateTime;
    }
    
    public void setAbatementAge(int abatementAge) {
        this.abatementAge = abatementAge;
    }
    
    public int getAbatementAge() {
        return onsetAge;
    }
    
    public void setAbatementPeriodFrom(Date abatementPeriodFrom) {
        this.abatementPeriodFrom = abatementPeriodFrom;
    }
    
    public Date getAbatementPeriodFrom() {
        return abatementPeriodFrom;
    }
    
    public void setAbatementPeriodTo(Date abatementPeriodTo) {
        this.abatementPeriodTo = abatementPeriodTo;
    }
    
    public Date getAbatementPeriodTo() {
        return abatementPeriodTo;
    }

    public void setAbatementRange(String abatementRange) {
        this.abatementRange = abatementRange;
    }
    
    public String getAbatementRange() {
        return abatementRange;
    }
    
    public void setAbatementString(String abatementString) {
        this.abatementString = abatementString;
    }
    
    public String getAbatementString() {
        return abatementString;
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
    
    public void setStage(List<ConditionStage> stage) {
        this.stage = stage;
    }

    @XmlTransient
    public List<ConditionStage> getStage() {
        return stage;
    }
    
    public void setEvidence(List<Evidence> evidence) {
        this.evidence = evidence;
    }

    @XmlTransient
    public List<Evidence> getEvidence() {
        return evidence;
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