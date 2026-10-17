package machuzu.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQueries({  
 @NamedQuery(name="IPA.loginUser", 
    query="SELECT a FROM IPA a WHERE a.email = :email and a.password = :password"),
 @NamedQuery(name="IPA.searchPatient", 
    query="SELECT i FROM IPA i WHERE i.email = :email")
})
public class IPA implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Matches modern WildFly/MySQL strategies
    @Column(name = "IPA_ID")
    private Long id;

    private String uuid;
    
    // Using Set instead of List avoids MultipleBagFetchExceptions when using EAGER fetching
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)    
    private Set<Identifier> identifiers = new HashSet<>();   
    
    private boolean active;
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<HumanName> humanNames = new HashSet<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Contact> contact = new HashSet<>();   

    private String gender;
    
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    private boolean deceased;
    private String deceasedDateTime;

    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<ContactPoint> ipaTelecoms = new HashSet<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Address> ipaAddress = new HashSet<>();

    @OneToOne(mappedBy="ipa", fetch=FetchType.EAGER)
    private Organisation organisation;

    private String maritalStatus;
    private boolean multipleBirth;
    private int multipleBirthQty;

    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Practitioner> practitioner = new HashSet<>();   
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Encounter> encounters = new HashSet<>();       
    
    @OneToMany(mappedBy="ipa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<Vitals> vitals = new HashSet<>();       
        
    private String email;
    private String password;

    public IPA() {
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

    public Set<HumanName> getHumanName() {
        return humanNames;
    }

    public void setHumanName(Set<HumanName> humanNames) {
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
    
    public Set<ContactPoint> getIPATelecoms() {
        return ipaTelecoms;
    }

    public void setIPATelecoms(Set<ContactPoint> ipaTelecoms) {
        this.ipaTelecoms = ipaTelecoms;
    }
    
    public Set<Address> getIPAAddress() {
        return ipaAddress;
    }

    public void setIPAAddress(Set<Address> ipaAddress) {
        this.ipaAddress = ipaAddress;
    }
    
    public void setIdentifier(Set<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
    
    public Set<Identifier> getIdentifier() {
        return identifiers;
    }

    public void setContact(Set<Contact> contact) {
        this.contact = contact;
    }
    
    public Set<Contact> getContact() {
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
    
    public Set<Practitioner> getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Set<Practitioner> practitioner) {
        this.practitioner = practitioner;
    }
    
    public Set<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(Set<Encounter> encounters) {
        this.encounters = encounters;
    }
    
    public Set<Vitals> getVitals() {
        return vitals;
    }

    public void setVitals(Set<Vitals> vitals) {
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
