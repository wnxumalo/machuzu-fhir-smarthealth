package machuzu.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity

public class Organisation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORG_ID")
    private Long id;

    private String active;
    private String oType;
    private String oName;
    private String oAlias;

    @OneToOne
    @JoinColumn(name="IPA_ID", nullable=true)
    private IPA ipa;
    
    private String address;
    private String partOf;
    private String contact;
    private String endPoint;
        
    public Organisation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
    public String getOrgType() {
        return oType;
    }

    public void setOrgType(String oType) {
        this.oType = oType;
    }
    
    public String getName() {
        return oName;
    }

    public void setName(String oName) {
        this.oName = oName;
    }
    
    public String getAlias() {
        return oAlias;
    }

    public void setAlias(String oAlias) {
        this.oAlias = oAlias;
    }

    /*public void setTelecoms(List<ContactPoint> ipaTelecoms) {
        this.ipaTelecoms = ipaTelecoms;
    }

    public List<ContactPoint> getTelecoms() {
        return ipaTelecoms;
    }*/
        
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }
    
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public IPA getIPA() {
        return ipa;
    }

    public void setIPA(IPA ipa) {
        this.ipa = ipa;
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
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Organisation[ id=" + id + " ]";
    }
    
}