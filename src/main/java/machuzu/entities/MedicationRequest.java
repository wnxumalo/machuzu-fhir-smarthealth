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
 @NamedQuery(name="MedicationRequest.fetchRecords", 
    query="SELECT a FROM MedicationRequest a order by a.id desc")
})
public class MedicationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICATIONREQUEST_ID")
    private Long id;

    // FIX: Swapped invalid @JoinColumn out for proper standard primitive @Column mappings
    @Column(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    @Column(name="MEDICATIONADMINISTRATION_ID", nullable=true)
    private Long medicationadministration;

    private String medStatus;
    private String statusReason;    
    private String intent;
    private String category;        
    private String priority;    
    private boolean doNotPerform;

    @OneToOne(fetch=FetchType.EAGER)
    private Medication medication;

    @Temporal(TemporalType.DATE)
    private Date authoredOn;
    private String performerType;    
    private String reasonCode;        
    private String courseOfTherapyType;
    private String note;    
    
    // FIX: Removed unsupported Hibernate LazyCollection annotation for clean standard JPA fetch elements
    @OneToMany(mappedBy="medicationrequest", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Medication> medications = new ArrayList<>();
    
    @OneToOne(fetch=FetchType.LAZY)
    private DispenseRequest dispenseRequest;    

    public MedicationRequest() {
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
    
    public Long getMedicationAdministration() {
        return medicationadministration;
    }

    public void setMedicationAdministration(Long medicationadministration) {
        this.medicationadministration = medicationadministration;
    }

    public String getMedStatus() {
        return medStatus;
    }

    public void setMedStatus(String medStatus) {
        this.medStatus = medStatus;
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

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getIntent() {
        return intent;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setDoNotPerform(boolean doNotPerform) {
        this.doNotPerform = doNotPerform;
    }

    public boolean getDoNotPerform() {
        return doNotPerform;
    }
    
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Medication getMedication() {
        return medication;
    }
    
    public void setAuthoredOn(Date authoredOn) {
        this.authoredOn = authoredOn;
    }
    
    public Date getAuthoredOn() {
        return authoredOn;
    }
    
    public void setPerformType(String performerType) {
        this.performerType = performerType;
    }
    
    public String getPerformType() {
        return performerType;
    }
    
    public void setCourseOfTherapyType(String courseOfTherapyType) {
        this.courseOfTherapyType = courseOfTherapyType;
    }
    
    public String getCourseOfTherapyType() {
        return courseOfTherapyType;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public String getReasonCode() {
        return reasonCode;
    }
    
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
    
    public List<Medication> getMedications() {
        return medications;
    }
    
    public Long getMedicationDosage() {        
        return (this.medications != null && !this.medications.isEmpty()) ? this.medications.get(0).getId() : null;        
    }

    public void setDispenseRequest(DispenseRequest dispenseRequest) {
        this.dispenseRequest = dispenseRequest;
    }

    public DispenseRequest getDispenseRequest() {
        return dispenseRequest;
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
        // FIX: Corrected class type matching from Medication to MedicationRequest
        if (!(object instanceof MedicationRequest)) {
            return false;
        }
        MedicationRequest other = (MedicationRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // FIX: Corrected class target output reference
        return "machuzu.entities.MedicationRequest[ id=" + id + " ]";
    }
}
