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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({  
 @NamedQuery(name="MedicationAdministration.fetchRecords", 
    query="SELECT a FROM MedicationAdministration a order by a.id desc")
})
public class MedicationAdministration implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICATIONADMINISTRATION_ID")
    private Long id;

    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID field
    @Column(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String status;
    private String statusReason;
    private String category;
    private String medicationCodeableConept;
    private String medicationReference;
    
    @Temporal(TemporalType.DATE)
    private java.util.Date effectiveFrom;
    
    @Temporal(TemporalType.DATE)
    private java.util.Date effectiveTo;
    
    private String reasonCode;
    private String note;

    @OneToOne
    private Dosage dosage;
    
    // FIX: Swapped out deprecated Hibernate LazyCollection for native FetchType.EAGER mapping
    @OneToMany(mappedBy="medicationadministration", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<MedicationRequest> medicationRequests = new ArrayList<>();    

    public MedicationAdministration() {
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

    public String getStatus() {
        return status;
    }

    // FIX: Corrected argument variable mismatch parameter name from statusReason to status
    public void setStatus(String status) {
        this.status = status;
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

    public void setTitle(String medicationCodeableConept) {
        this.medicationCodeableConept = medicationCodeableConept;
    }

    public String getTitle() {
        return medicationCodeableConept;
    }

    public void setDescription(String medicationReference) {
        this.medicationReference = medicationReference;
    }
    
    public String getDescription() {
        return medicationReference;
    }

    public void setPeriodFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    
    public Date getPeriodFrom() {
        return effectiveFrom;
    }

    public void setPeriodTo(Date effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
    
    public Date getPeriodTo() {
        return effectiveTo;
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

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }
    
    public Dosage getDosage() {
        return dosage;
    }   
    
    public void setMedicationRequests(List<MedicationRequest> medicationRequests) {
        this.medicationRequests = medicationRequests;
    }
    
    public List<MedicationRequest> getMedicationRequests() {
        return medicationRequests;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MedicationAdministration)) {
            return false;
        }
        MedicationAdministration other = (MedicationAdministration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.MedicationAdministration[ id=" + id + " ]";
    }
}
