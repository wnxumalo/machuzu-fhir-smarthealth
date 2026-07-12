package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Reaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REACTION_ID")
    Long id;
    
    @JoinColumn(name="ALLERGY_ID", nullable=true)
    private Long allergy;

    private String substance;
    private String manifestation;    
    private String description;
    private Date onset;
    private String severity;
    private String exposureRoute;    
    private String note;

    public Reaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getAllergy() {
        return allergy;
    }

    public void setAllergy(Long allergy) {
        this.allergy = allergy;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getManifestation() {
        return manifestation;
    }

    public void setManifestation(String manifestation) {
        this.manifestation = manifestation;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOnset(Date onset) {
        this.onset = onset;
    }
    
    public Date getOnset() {
        return onset;
    }
    
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public void setServicePeriodTo(String severity) {
        this.severity = severity;
    }

    public String getServicePeriodTo() {
        return severity;
    }
    
    public void setExposureRoute(String exposureRoute) {
        this.exposureRoute = exposureRoute;
    }
    
    public String getExposureRoute() {
        return exposureRoute;
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
        if (!(object instanceof Reaction)) {
            return false;
        }
        Reaction other = (Reaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Reaction[ id=" + id + " ]";
    }
    
}