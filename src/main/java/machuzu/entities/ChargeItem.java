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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;


@Entity

public class ChargeItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHARGEITEM_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
      
    @JoinColumn(name="ACCOUNT_ID", nullable=true)
    private Long account;
    
    private String definitionUri;
    private String definitionCanonical;
    private String ciStatus;    
    
    private String ciCode;
    private Date ciPeriodFrom;
    private Date ciPeriodTo;
    
    private float quantity;
    private float bodySite;
    private float unitPriceComponent;
    private float totalPriceComponent;
    private String overridereason;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date entereddate;

    private String reason;
    private String ciService;
    private String ciProduct;
    private String note;
    
    public ChargeItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }

    public String getDefinitionUri() {
        return definitionUri;
    }

    public void setDefinitionUri(String definitionUri) {
        this.definitionUri = definitionUri;
    }
    
    public String getDefinitionCanonical() {
        return definitionCanonical;
    }

    public void setDefinitionCanonical(String definitionCanonical) {
        this.definitionCanonical = definitionCanonical;
    }

    public String getCIStatus() {
        return ciStatus;
    }

    public void setCIStatus(String ciStatus) {
        this.ciStatus = ciStatus;
    }
    
    public String getCICode() {
        return ciCode;
    }

    public void setCICode(String ciCode) {
        this.ciCode = ciCode;
    }
    
    public void setPeriodFrom(Date ciPeriodFrom) {
        this.ciPeriodFrom = ciPeriodFrom;
    }
    
    public Date getPeriodFrom() {
        return ciPeriodFrom;
    }
    
    public void setPeriodTo(Date ciPeriodTo) {
        this.ciPeriodTo = ciPeriodTo;
    }
    
    public Date getPeriodTo() {
        return ciPeriodTo;
    }
    
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    
    public float getQuantity() {
        return quantity;
    }
    
    public void setBodySite(float bodySite) {
        this.bodySite = bodySite;
    }
    
    public float getBodySite() {
        return bodySite;
    }
    
    public void setUnitPriceComponent(float unitPriceComponent) {
        this.unitPriceComponent = unitPriceComponent;
    }
    
    public float getUnitPriceComponent() {
        return unitPriceComponent;
    }
    
    public void setTotalPriceComponent(float totalPriceComponent) {
        this.totalPriceComponent = totalPriceComponent;
    }
    
    public float getTotalPriceComponent() {
        return totalPriceComponent;
    }
    
    public void setOverridereason(String overridereason) {
        this.overridereason = overridereason;
    }
    
    public String getOverridereason() {
        return overridereason;
    }
    
    public void setEntereddate(Date entereddate) {
        this.entereddate = entereddate;
    }
    
    public Date getEntereddate() {
        return entereddate;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public void setCIService(String ciService) {
        this.ciService = ciService;
    }

    public String getCIService() {
        return ciService;
    }

    public void setProduct(String ciProduct) {
        this.ciProduct = ciProduct;
    }
    
    public String getProduct() {
        return ciProduct;
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
        if (!(object instanceof ChargeItem)) {
            return false;
        }
        ChargeItem other = (ChargeItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.ChargeItem[ id=" + id + " ]";
    }
    
}