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
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({  
 @NamedQuery(name="MedicationRequest.fetchRecords", 
 query="SELECT a FROM MedicationRequest a order by id desc")})
@XmlRootElement
public class MedicationRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEDICATIONREQUEST_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    @JoinColumn(name="MEDICATIONADMINISTRATION_ID", nullable=true)
    private Long medicationadministration;

    private String medStatus;
    private String statusReason;    
    private String intent;
    private String category;        
    private String priority;    
    private boolean doNotPerform;

    @OneToOne(fetch=FetchType.EAGER)
    private Medication medication;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date authoredOn;
    private String performerType;    
    private String reasonCode;        
    private String courseOfTherapyType;
    private String note;    
    
    @OneToMany(mappedBy="medicationrequest", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
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
    
    @XmlTransient
    public List<Medication> getMedications() {
        return medications;
    }
    
    public Long getMedicationDosage() {        
        return this.medications.get(0).getId();        
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Medication[ id=" + id + " ]";
    }

}