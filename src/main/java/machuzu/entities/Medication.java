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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({  
 @NamedQuery(name="Medication.fetchRecords", 
    query="SELECT a FROM Medication a order by a.id desc") // Updated to explicit a.id
})
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDICATION_ID")
    private Long id;

    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID
    @Column(name="MEDICATIONREQUEST_ID", nullable=true)
    private Long medicationrequest;

    private String mCode;
    private String mStatus;
    
    private String mForm;
    private String amount;
    private String batchLotNumber;
    
    @Temporal(TemporalType.DATE)
    private Date batchExpirationDate;
    
    @OneToMany(mappedBy="medication", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Dosage> dosages = new ArrayList<>();

    public Medication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getMedicationRequest() {
        return medicationrequest;
    }

    public void setMedicationRequest(Long medicationrequest) {
        this.medicationrequest = medicationrequest;
    }

    public String getMCode() {
        return mCode;
    }

    public void setMCode(String mCode) {
        this.mCode = mCode;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public void setForm(String mForm) {
        this.mForm = mForm;
    }
    
    public String getForm() {
        return mForm;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setBatchLotNumber(String batchLotNumber) {
        this.batchLotNumber = batchLotNumber;
    }
    
    public String getBatchLotNumber() {
        return batchLotNumber;
    }
    
    public void setBatchExpirationDate(Date batchExpirationDate) {
        this.batchExpirationDate = batchExpirationDate;
    }

    public Date getBatchExpirationDate() {
        return batchExpirationDate;
    }
    
    public void setDosages(List<Dosage> dosages) {
        this.dosages = dosages;                         
    }

    public List<Dosage> getDosages() {        
        return dosages;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
