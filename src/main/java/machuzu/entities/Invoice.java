package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INVOICE_ID")
    Long id;

    @JoinColumn(name="ACCOUNT_ID", nullable=true)
    private Long account; 

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String iStatus;
    private String cancelledReason;
    private String iType;    
    
    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;

    private String recipient;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date iDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creation;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date billingPeriodFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date billingPeriodTo;
    
    private String liSequence;
    private String liPeriodFrom; // medical aid
    private String liPeriodTo;
    
    private float priceComponent;
    private float totalPriceComponent;
    private float totalNet;
    private float totalGross;
    private float paymentTerms;
    private float note;

    public Invoice() {
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

    public String getStatus() {
        return iStatus;
    }

    public void setStatus(String iStatus) {
        this.iStatus = iStatus;
    }
    
    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }

    public String getType() {
        return iType;
    }

    public void setType(String iType) {
        this.iType = iType;
    }
    
    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
        this.ipa = ipa;
    }
    
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    
    public void setDate(Date iDate) {
        this.iDate = iDate;
    }

    public Date getDate() {
        return iDate;
    }

    public void setCreationDate(Date creation) {
        this.creation = creation;
    }
    
    public Date getCreationDate() {
        return creation;
    }    
    
    public Date getBillingPeriodFrom() {
        return billingPeriodFrom;
    }

    public void setBillingPeriodFrom(Date billingPeriodFrom) {
        this.billingPeriodFrom = billingPeriodFrom;
    }
    
    public Date getBillingPeriodTo() {
        return billingPeriodTo;
    }

    public void setBillingPeriodTo(Date billingPeriodTo) {
        this.billingPeriodTo = billingPeriodTo;
    }
    
    public void setPeriodFrom(String liPeriodFrom) {
        this.liPeriodFrom = liPeriodFrom;
    }
    
    public String getPeriodFrom() {
        return liPeriodFrom;
    }
    
    public void setPeriodTo(String liPeriodTo) {
        this.liPeriodTo = liPeriodTo;
    }
    
    public String getPeriodTo() {
        return liPeriodTo;
    }
    
    public void setPriceComponent(float priceComponent) {
        this.priceComponent = priceComponent;
    }
    
    public float getPriceComponent() {
        return priceComponent;
    }
    
    public void setTotalPriceComponent(float totalPriceComponent) {
        this.totalPriceComponent = totalPriceComponent;
    }
    
    public float getTotalPriceComponent() {
        return totalPriceComponent;
    }
    
    public void setTotalNet(float totalNet) {
        this.totalNet = totalNet;
    }
    
    public float getTotalNet() {
        return totalNet;
    }
    
    public void setTotalGross(float totalGross) {
        this.totalGross = totalGross;
    }
    
    public float getTotalGross() {
        return totalGross;
    }
    
    public void setPaymentTerms(float paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    
    public float getPaymentTerms() {
        return paymentTerms;
    }
    
    public void setNote(float note) {
        this.note = note;
    }
    
    public float getNote() {
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Invoice[ id=" + id + " ]";
    }
    
}