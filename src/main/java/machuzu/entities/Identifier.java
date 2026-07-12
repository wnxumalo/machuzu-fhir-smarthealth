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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Identifier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDENTIFIER_ID")
    private Long id;
    private String uses; //usual | official | temp | secondary | old (If known)
    private String iType;
    private String iSystem;
    private String description;
    private Date periodFrom;
    private Date periodTo;        

    @JoinColumn(name="REF_ID", nullable=true)
    private ObjectReference objectReference;

    @JoinColumn(name="IPA_ID", nullable=true)
    private Long ipa;
    
    @OneToMany(mappedBy="identifier", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Encounter> encounters = new ArrayList<>();

    public Identifier(Long id, String uses, String iType, String iSystem, String description, Date periodFrom, Date periodTo, ObjectReference objectReference) {
        this.id = id;
        this.uses = uses;
        this.iType = iType;
        this.iSystem = iSystem;
        this.description = description;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.objectReference = objectReference;

    }
    
    public Identifier() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUse() {
        return uses;
    }

    public void setUse(String uses) {
        this.uses = uses;
    }
    
    public String getIType() {
        return iType;
    }

    public void setIType(String iType) {
        this.iType = iType;
    }
    
    public String getSystem() {
        return iSystem;
    }

    public void setSystem(String iSystem) {
        this.iSystem = iSystem;
    }
    
    public String getValue() {
        return description;
    }

    public void setValue(String description) {
        this.description = description;
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

    public ObjectReference getObjectReference() {
        return objectReference;
    }

    public void setObjectReference(ObjectReference objectReference) {
        this.objectReference = objectReference;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
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
        return "machuzu.entities.Identifier[ id=" + id + " ]";
    }
    
}