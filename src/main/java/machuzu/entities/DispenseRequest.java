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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
public class DispenseRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOSAGE_ID")
    Long id;

    @JoinColumn(name="MEDICATIONREQUEST_ID", nullable=true)
    private Long medicationrequest;

    private String initialFill;
    private String initialQuantity;    
    private String initialDuration;
    private String dispenseInterval;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validityPeriodFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validityPeriodTo;
    private String numberOfRepeatsAllowed;
    private String quantity;
    private String expectedSupplyDuration;

    public DispenseRequest() {
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

    public String getInitialFill() {
        return initialFill;
    }

    public void setInitialFill(String initialFill) {
        this.initialFill = initialFill;
    }

    public String getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(String initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public void setInitialDuration(String initialDuration) {
        this.initialDuration = initialDuration;
    }
    
    public String getInitialDuration() {
        return initialDuration;
    }
    
    public void setDispenseInterval(String dispenseInterval) {
        this.dispenseInterval = dispenseInterval;
    }

    public String getDispenseInterval() {
        return dispenseInterval;
    }

    public void setValidityPeriodTo(Date validityPeriodTo) {
        this.validityPeriodTo = validityPeriodTo;
    }
    
    public Date getValidityPeriodTo() {
        return validityPeriodTo;
    }
    
    public void setValidityPeriodFrom(Date validityPeriodFrom) {
        this.validityPeriodFrom = validityPeriodFrom;
    }

    public Date getValidityPeriodFrom() {
        return validityPeriodFrom;
    }
    
    public void setNumberOfRepeatsAllowed(String numberOfRepeatsAllowed) {
        this.numberOfRepeatsAllowed = numberOfRepeatsAllowed;
    }

    public String getNumberOfRepeatsAllowed() {
        return numberOfRepeatsAllowed;
    }
    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
    
    public void setExpectedSupplyDuration(String expectedSupplyDuration) {
        this.expectedSupplyDuration = expectedSupplyDuration;
    }

    public String getExpectedSupplyDuration() {
        return expectedSupplyDuration;
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
        if (!(object instanceof DispenseRequest)) {
            return false;
        }
        DispenseRequest other = (DispenseRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.DispenseRequest[ id=" + id + " ]";
    }
}