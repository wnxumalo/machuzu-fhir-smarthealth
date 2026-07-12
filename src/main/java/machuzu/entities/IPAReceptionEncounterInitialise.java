/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
public class IPAReceptionEncounterInitialise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IPA_ID")
    Long id;

    private String uuid;
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.EAGER)    
    private List<Identifier> identifiers = new ArrayList<>();   
    private boolean active;
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<HumanName> humanNames = new ArrayList<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<Contact> contact = new ArrayList<>();   

    private String gender;
    private Date birthDate;
    private boolean deceased;
    private String deceasedDateTime;

    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<ContactPoint> ipaTelecoms = new ArrayList<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private List<Address> ipaAddress = new ArrayList<>();

    @OneToOne(mappedBy="ipa",fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)    
    private Organisation organisation;

    private String maritalStatus;
    private boolean multipleBirth;
    private int multipleBirthQty;

    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Practitioner> practitioner = new ArrayList<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Encounter> encounters = new ArrayList<>();       
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Vitals> vitals = new ArrayList<>();       
        
    private String email;
    private String password;

    public IPAReceptionEncounterInitialise() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
    
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<HumanName> getHumanName() {
        return humanNames;
    }

    public void setHumanName(List<HumanName> humanNames) {
        this.humanNames = humanNames;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    public List<ContactPoint> getIPATelecoms() {
        return ipaTelecoms;
    }

    public void setIPATelecoms(List<ContactPoint> ipaTelecoms) {
        this.ipaTelecoms = ipaTelecoms;
    }
    
    public List<Address> getIPAAddress() {
        return ipaAddress;
    }

    public void setIPAAddress(List<Address> ipaAddress) {
        this.ipaAddress = ipaAddress;
    }
    
    public void setIdentifier(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
    
    public List<Identifier> getIdentifier() {
        return identifiers;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
    
    @XmlTransient
    public List<Contact> getContact() {
        return contact;
    }

    public void setDeceased(boolean deceased) {
        this.deceased = deceased;
    }
    
    public boolean getDeceased() {
        return deceased;
    }
    
    public void setDeceasedDateTime(String deceasedDateTime) {
        this.deceasedDateTime = deceasedDateTime;
    }
    
    public String getDeceasedDateTime() {
        return deceasedDateTime;
    }
        
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setMultipleBirth(boolean multipleBirth) {
        this.multipleBirth = multipleBirth;
    }
    
    public boolean getMultipleBirth() {
        return multipleBirth;
    }
    
    public void setMultipleBirthQty(int multipleBirthQty) {
        this.multipleBirthQty = multipleBirthQty;
    }
    
    public int getMultipleBirthQty() {
        return multipleBirthQty;
    }
    
    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
    
    public Organisation getOrganisation() {
        return organisation;
    }
    
    @XmlTransient
    public List<Practitioner> getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(List<Practitioner> practitioner) {
        this.practitioner = practitioner;
    }
    
    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }
    
    public List<Vitals> getVitals() {
        return vitals;
    }

    public void setVitals(List<Vitals> vitals) {
        this.vitals = vitals;
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
        return "machuzu.entities.IPA[ id=" + id + " ]";
    }
    
}