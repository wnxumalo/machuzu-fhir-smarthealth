package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;


@Entity

public class DocumentReference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOCUMENTREFERENCE_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;

    private String drStatus;
    private String docStatus;
    private String drType;
    private String category;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date drDate;
    private String description;
    private String securityLabel;
    private String contentAttachment;
    private String contentFormat;

    public DocumentReference() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEncounter() {
        return encounter;
    }

    public void setEncounter(Long encounter) {
        this.encounter = encounter;
    }

    public String getDRStatus() {
        return drStatus;
    }

    public void setDRStatus(String drStatus) {
        this.drStatus = drStatus;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public void setDRType(String drType) {
        this.drType = drType;
    }
    
    public String getDRType() {
        return drType;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }

    public void setDRDate(Date drDate) {
        this.drDate = drDate;
    }
    
    public Date getDRDate() {
        return drDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setSecurityLabel(String securityLabel) {
        this.securityLabel = securityLabel;
    }
    
    public String getSecurityLabel() {
        return securityLabel;
    }
    
    public void setContentAttachment(String contentAttachment) {
        this.contentAttachment = contentAttachment;
    }
    
    public String getContentAttachment() {
        return contentAttachment;
    }
    
    public void setContentFormat(String contentFormat) {
        this.contentFormat = contentFormat;
    }
    
    public String getContentFormat() {
        return contentFormat;
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
        if (!(object instanceof DocumentReference)) {
            return false;
        }
        DocumentReference other = (DocumentReference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.DocumentReference[ id=" + id + " ]";
    }
    
}