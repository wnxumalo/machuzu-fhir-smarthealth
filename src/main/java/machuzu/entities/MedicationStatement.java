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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({  
 @NamedQuery(name="MedicationStatement.fetchRecords", 
 query="SELECT a FROM MedicationStatement a order by id desc")})
@XmlRootElement
public class MedicationStatement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEDICATIONSTATEMENT_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String mStatus;
    private String statusReason;    
    private String category;        

    @OneToOne(fetch=FetchType.EAGER)
    private Medication medication;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectiveDate;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectivePeriodFrom;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date effectivePeriodTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAsserted;    
    private String reasonCode;        
    private String note;    

   /* @OneToMany(mappedBy="medicationstatement", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<Dosage> dosages = new ArrayList<>();*/

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

/*    public void setDosages(List<Dosage> dosages) {
        this.dosages = dosages;                         
    }

    public List<Dosage> getDosages() {        
        return dosages;
    }    */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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