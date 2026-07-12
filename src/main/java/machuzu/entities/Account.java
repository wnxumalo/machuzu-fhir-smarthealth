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
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    Long id;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa; //owner

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    @ManyToMany(mappedBy="account", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<ChargeItem> chargeItems = new ArrayList<>();

    private String aStatus;
    private String billingStatus;
    private String aType;    
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date servicePeriodFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date servicePeriodTo;
    private String owner; // medical aid
    private String description;
    private float balanceAggregate;
    private float balanceTerm;
    private float balanceEstimate;
    private float balanceAmount;
    private float balanceCalculatedAt;

    public Account() {
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

    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }
    
    public void setChargeItem(List<ChargeItem> chargeItem) {
        this.chargeItems = chargeItem;
    }
    
    public List<ChargeItem> getChargeItem() {
        return chargeItems;
    }

    public String getStatus() {
        return aStatus;
    }

    public void setStatus(String aStatus) {
        this.aStatus = aStatus;
    }
    
    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getType() {
        return aType;
    }

    public void setType(String aType) {
        this.aType = aType;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setServicePeriodFrom(Date servicePeriodFrom) {
        this.servicePeriodFrom = servicePeriodFrom;
    }

    public Date getServicePeriodFrom() {
        return servicePeriodFrom;
    }

    public void setServicePeriodTo(Date servicePeriodTo) {
        this.servicePeriodTo = servicePeriodTo;
    }

    public Date getServicePeriodTo() {
        return servicePeriodTo;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setBalanceAggregate(float balanceAggregate) {
        this.balanceAggregate = balanceAggregate;
    }
    
    public float getBalanceAggregate() {
        return balanceAggregate;
    }
    
    public void setBalanceTerm(float balanceTerm) {
        this.balanceTerm = balanceTerm;
    }
    
    public float getBalanceTerm() {
        return balanceTerm;
    }
    
    public void setBalanceEstimate(float balanceEstimate) {
        this.balanceEstimate = balanceEstimate;
    }
    
    public float getBalanceEstimate() {
        return balanceEstimate;
    }
    
    public void setBalanceAmount(float balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    
    public float getBalanceAmount() {
        return balanceAmount;
    }
    
    public void setBalanceCalculatedAt(float balanceCalculatedAt) {
        this.balanceCalculatedAt = balanceCalculatedAt;
    }
    
    public float getBalanceCalculatedAt() {
        return balanceCalculatedAt;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Account[ id=" + id + " ]";
    }
    
}