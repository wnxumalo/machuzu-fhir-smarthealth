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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

@Entity

public class Encounter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENCOUNTER_ID")
    Long id;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;

    @JoinColumn(name="IDENTIFIER_ID", nullable=true)
    private Long identifier;

    private String eStatus;
    
    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<StatusHistory> statusHistory = new ArrayList<>();

    private String eClass; //class
    
    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ClassHistory> classHistory = new ArrayList<>();
    
    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Allergy> allergies = new ArrayList<>();
    
    @OneToOne
    private CarePlan carePlan;

    private String type;
    private String serviceType;
    private String priority;
    
    private String eventId;
    private String eventTitle;
    private String eventDescription;            
    private Date periodFrom; //startdate
    private Date periodTo;//enddate
    
    private String eLength; //length
    private String reasonCode;
    
    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Illness> condition = new ArrayList<>(); //diagnosis

    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Procedures> procedure = new ArrayList<>(); 

    private String diagnosisUse; //use
    private int diagnosisRank; //rank

    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Account> account = new ArrayList<>();

    private String hospAdmitSource; //admitSource
    private String hospReAdmission; //reAdmission
    private String hospDietPreference; //dietPreference
    private String hospSpecialCourtesy; //specialCourtesy
    private String hospSpecialArrangement; //specialArrangement
    private String hospDischargeDisposition; //dischargeDisposition

    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<MedicationRequest> medicationRequest = new ArrayList<>();
    
    @OneToMany(mappedBy="encounter", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Vitals> vitals = new ArrayList<>();
    
    private String event;
    
    public Encounter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
        this.ipa = ipa;
    }

    public String getStatus() {
        return eStatus;
    }

    public void setStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getEClass() {
        return eClass;
    }

    public void setEClass(String eClass) {
        this.eClass = eClass;
    }
    
    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
    
    public Long getIdentifier() {
        return identifier;
    }

    public void setClassHistory(List<ClassHistory> classHistory) {
        this.classHistory = classHistory;
    }

    public List<ClassHistory> getClassHistory() {
        return classHistory;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    
    public String getServiceType() {
        return serviceType;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    
    public String getEventTitle() {
        return eventTitle;
    }
    
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    
    public String getEventDescription() {
        return eventDescription;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }
    
    public Date getPeriodFrom() {
        return periodFrom;
    }
    
    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Date getPeriodTo() {
        return periodTo;
    }
    
    public void setLength(String eLength) {
        this.eLength = eLength;
    }
    
    public String getLength() {
        return eLength;
    }
        
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public String getReasonCode() {
        return reasonCode;
    }
        
    public List<Illness> getConditions() {
        return condition;
    }

    public void setConditions(List<Illness> condition) {
        this.condition = condition;
    }
    
    public List<Procedures> getProcedures() {
        return procedure;
    }

    public void setProcedures(List<Procedures> procedure) {
        this.procedure = procedure;
    }

    public void setDiagnosisUse(String diagnosisUse) {
        this.diagnosisUse = diagnosisUse;
    }
    
    public String getDiagnosisUse() {
        return diagnosisUse;
    }
    
    public void setDiagnosisRank(int diagnosisRank) {
        this.diagnosisRank = diagnosisRank;
    }
    
    public int getDiagnosisRank() {
        return diagnosisRank;
    }
    
    public List<Account> getAccounts() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public void setAdmitSource(String hospAdmitSource) {
        this.hospAdmitSource = hospAdmitSource;
    }
    
    public String getAdmitSource() {
        return hospAdmitSource;
    }

    public void setHospReAdmission(String hospReAdmission) {
        this.hospReAdmission = hospReAdmission;
    }
    
    public String getHospReAdmission() {
        return hospReAdmission;
    }
    
    public void setDietPreference(String hospDietPreference) {
        this.hospDietPreference = hospDietPreference;
    }
    
    public String getDietPreference() {
        return hospDietPreference;
    }
    
    public void setSpecialCourtesy(String hospSpecialCourtesy) {
        this.hospSpecialCourtesy = hospSpecialCourtesy;
    }
    
    public String getSpecialCourtesy() {
        return hospSpecialCourtesy;
    }
    
    public void setSpecialArrangement(String hospSpecialArrangement) {
        this.hospSpecialArrangement = hospSpecialArrangement;
    }
    
    public String getSpecialArrangement() {
        return hospSpecialArrangement;
    }
    
    public void setDischargeDisposition(String hospDischargeDisposition) {
        this.hospDischargeDisposition = hospDischargeDisposition;
    }
    
    public String getDischargeDisposition() {
        return hospDischargeDisposition;
    }
    
    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
    
    public List<MedicationRequest> getMedicationRequest() {
        return medicationRequest;
    }

    public void setMedicationRequest(List<MedicationRequest> medicationRequest) {
        this.medicationRequest = medicationRequest;
    }
    
    public List<Vitals> getVitals() {
        return vitals;
    }

    public void setVitals(List<Vitals> vitals) {
        this.vitals = vitals;
    }
    
    public CarePlan getCarePlan() {
        return carePlan;
    }

    public void setCarePlan(CarePlan carePlan) {
        this.carePlan = carePlan;
    }
    
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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
        if (!(object instanceof Encounter)) {
            return false;
        }
        Encounter other = (Encounter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Encounter[ id=" + id + " ]";
    }
    
}