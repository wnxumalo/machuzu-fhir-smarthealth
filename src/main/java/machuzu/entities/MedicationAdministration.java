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
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({  
 @NamedQuery(name="MedicationAdministration.fetchRecords", 
 query="SELECT a FROM MedicationAdministration a order by id desc")})
public class MedicationAdministration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEDICATIONADMINISTRATION_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String status;
    private String statusReason;
    private String category;
    private String medicationCodeableConept;
    private String medicationReference;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date effectiveFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private java.util.Date effectiveTo;
    private String reasonCode;
    private String note;

    @OneToOne
    private Dosage dosage;
    
    @OneToMany(mappedBy="medicationadministration", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
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

    public void setStatus(String statusReason) {
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
    
    @XmlTransient
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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