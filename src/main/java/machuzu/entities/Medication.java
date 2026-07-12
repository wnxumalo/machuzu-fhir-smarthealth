package machuzu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.HttpSession;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({  
 @NamedQuery(name="Medication.fetchRecords", 
 query="SELECT a FROM Medication a order by id desc")})
@XmlRootElement
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEDICATION_ID")
    Long id;

    @JoinColumn(name="MEDICATIONREQUEST_ID", nullable=true)
    private Long medicationrequest;

    private String mCode;
    private String mStatus;
    
    private String mForm;
    private String amount;
    private String batchLotNumber;
    private Date batchExpirationDate;
    
    @OneToMany(mappedBy="medication", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
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

    @XmlTransient
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