package machuzu.entities;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity

public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTACT_ID")
    private Long id;
    private String patientId;            
    private String relationship;

    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<HumanName> humanNames = new ArrayList<>();   
    
    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<ContactPoint> contactPoints = new ArrayList<>();   
    
    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();   
    
    private String gender;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;

    public Contact(Long id, String relationship, String gender) {

        this.id = id;
        this.relationship = relationship;
        this.gender = gender;

    }

    public Contact() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public List<HumanName> getHumanName() {
        return humanNames;
    }

    public void setHumanName(List<HumanName> humanNames) {
        this.humanNames = humanNames;
    }

    public List<ContactPoint> getIPATelecoms() {
        return contactPoints;
    }

    public void setIPATelecoms(List<ContactPoint> contactPoints) {
        this.contactPoints = contactPoints;
    }
    
    public List<Address> getContactAddress() {
        return addresses;
    }

    public void setContactAddress(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    public Long getIPA() {
        return ipa;
    }

    public void setIPA(Long ipa) {
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Contact[ id=" + id + " ]";
    }
    
}