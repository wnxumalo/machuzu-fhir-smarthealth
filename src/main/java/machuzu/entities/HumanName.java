package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class HumanName implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HN_ID")
    private Long id;
    private String usedfor;
    private String text;
    private String family;
    private String given;
    private String nPrefix;
    private String sufix;
    private Date periodFrom;
    private Date periodTo;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;

    @JoinColumn(name="CONTACT_ID", nullable=true)
    private Long contact;

    public HumanName() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUse() {
        return usedfor;
    }

    public void setUse(String usedfor) {
        this.usedfor = usedfor;
    }    
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
    
    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }
    
    public String getPrefix() {
        return nPrefix;
    }

    public void setPrefix(String nPrefix) {
        this.nPrefix = nPrefix;
    }
    
    public String getSufix() {
        return sufix;
    }

    public void setSufix(String sufix) {
        this.sufix = sufix;
    }
    
    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }
    
    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }
    
    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
        this.ipa = ipa;
    }
    
    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
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
        if (!(object instanceof IPA)) {
            return false;
        }
        IPA other = (IPA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.HumanName[ id=" + id + " ]";
    }
    
}