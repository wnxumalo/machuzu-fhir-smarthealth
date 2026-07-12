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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity

public class FinanceAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    Long id;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;
    
    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;    

    private String accountStatus;
    private String billingStatus;
    private String aType;
    private String accountName;
    private Date servicePeriodFrom;
    private Date servicePeriodTo;
    
    /*@OneToMany(mappedBy="allergy", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reaction> reaction = new ArrayList<>();*/

    public FinanceAccount() {
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public void setType(String aType) {
        this.aType = aType;
    }
    
    public String getType() {
        return aType;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    public String getAccountName() {
        return accountName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinanceAccount)) {
            return false;
        }
        FinanceAccount other = (FinanceAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.FinanceAccount[ id=" + id + " ]";
    }
    
}