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
import jakarta.persistence.Temporal;

@Entity
public class CarePlanActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CAREPLANACTIVITY_ID")
    Long id;

    @JoinColumn(name="CAREPLAN_ID", nullable=true)
    private Long careplan;
    
    private String outcomeCodeableConcept;
    private String progress;
    private String detailKind;
    private String detailCode;
    private String detailReasonCode;
    private String status;
    private String statusReason;
    private boolean doNotPerform;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date scheduledPeriodFrom;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date scheduledPeriodTo;
    private String productCodeCodeableConcept;
    private String productReference;
    private String dailyAmount;
    private String quantity;
    private String description;

    @OneToMany(mappedBy="careplan", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CarePlanActivity> carePlanActivity = new ArrayList<>();

    public CarePlanActivity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCareplan() {
        return careplan;
    }

    public void setCareplan(Long careplan) {
        this.careplan = careplan;
    }

    public String getOutcomeCodeableConcept() {
        return outcomeCodeableConcept;
    }

    public void setOutcomeCodeableConcept(String outcomeCodeableConcept) {
        this.outcomeCodeableConcept = outcomeCodeableConcept;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setDetailKind(String detailKind) {
        this.detailKind = detailKind;
    }
    
    public String getDetailKind() {
        return detailKind;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }
    
    public String getDetailReasonCode() {
        return detailReasonCode;
    }

    public void setDetailReasonCode(String detailReasonCode) {
        this.detailReasonCode = detailReasonCode;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
    
    public boolean getDoNotPerform() {
        return doNotPerform;
    }

    public void setDetailCode(boolean doNotPerform) {
        this.doNotPerform = doNotPerform;
    }
    
    public Date getScheduledPeriodFrom() {
        return scheduledPeriodFrom;
    }

    public void setScheduledPeriodFrom(Date scheduledPeriodFrom) {
        this.scheduledPeriodFrom = scheduledPeriodFrom;
    }
        
    public Date getScheduledPeriodTo() {
        return scheduledPeriodTo;
    }

    public void setScheduledPeriodTo(Date scheduledPeriodTo) {
        this.scheduledPeriodTo = scheduledPeriodTo;
    }
    
    public String getProductCodeCodeableConcept() {
        return productCodeCodeableConcept;
    }

    public void setProductCodeCodeableConcept(String productCodeCodeableConcept) {
        this.productCodeCodeableConcept = productCodeCodeableConcept;
    }
    
    public String getProductReference() {
        return productReference;
    }

    public void setProductReference(String productReference) {
        this.productReference = productReference;
    }
    
    public String getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(String dailyAmount) {
        this.dailyAmount = dailyAmount;
    }
    
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<CarePlanActivity> getCarePlanActivity() {
        return carePlanActivity;
    }

    public void setCarePlanActivity(List<CarePlanActivity> carePlanActivity) {
        this.carePlanActivity = carePlanActivity;
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
        if (!(object instanceof CarePlanActivity)) {
            return false;
        }
        CarePlanActivity other = (CarePlanActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.CarePlanActivity[ id=" + id + " ]";
    }
    
}