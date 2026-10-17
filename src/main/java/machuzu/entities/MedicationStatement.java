package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({  
 @NamedQuery(name="MedicationStatement.fetchRecords", 
    query="SELECT a FROM MedicationStatement a order by a.id desc")
})
public class MedicationStatement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICATIONSTATEMENT_ID")
    private Long id;

    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID field
    @Column(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String mStatus;
    private String statusReason;    
    private String category;        

    @OneToOne(fetch=FetchType.EAGER)
    private Medication medication;

    @Temporal(TemporalType.DATE)
    private Date effectiveDate;    
    
    @Temporal(TemporalType.DATE)
    private Date effectivePeriodFrom;    
    
    @Temporal(TemporalType.DATE)
    private Date effectivePeriodTo;
    
    @Temporal(TemporalType.DATE)
    private Date dateAsserted;    
    
    private String reasonCode;        
    private String note;    

    public MedicationStatement() {
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

    public String getMStatus() {
        return mStatus;
    }

    public void setMStatus(String mStatus) {
        this.mStatus = mStatus;
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
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectivePeriodFrom(Date effectivePeriodFrom) {
        this.effectivePeriodFrom = effectivePeriodFrom;
    }

    public Date getEffectivePeriodFrom() {
        return effectivePeriodFrom;
    }
    
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Medication getMedication() {
        return medication;
    }
    
    public void setEffectivePeriodTo(Date effectivePeriodTo) {
        this.effectivePeriodTo = effectivePeriodTo;
    }
    
    public Date getEffectivePeriodTo() {
        return effectivePeriodTo;
    }
    
    public void setDateAsserted(Date dateAsserted) {
        this.dateAsserted = dateAsserted;
    }
    
    public Date getDateAsserted() {
        return dateAsserted;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public String getReasonCode() {
        return reasonCode;
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
        if (!(object instanceof MedicationStatement)) {
            return false;
        }
        MedicationStatement other = (MedicationStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.MedicationStatement[ id=" + id + " ]";
    }
}
