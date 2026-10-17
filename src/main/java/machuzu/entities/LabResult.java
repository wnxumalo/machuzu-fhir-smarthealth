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
public class LabResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LABRESULT_ID")
    Long id;

    @JoinColumn(name="LAB_ID", nullable=true)
    private Long lab;
    
    /*@JoinColumn(name="CAREPLAN_ID", nullable=true)
    private Long careplan;
    
    @JoinColumn(name="MEDICATIONREQUEST_ID", nullable=true)
    private Long medicationrequest;*/

    private String confirmTestDescription;
    private String labResult;
    private String code;
    private String category;
    private String reference;
    private String status;
    private String issued;
    private String conclusion;
    private String conclusionCode;

    /*@OneToMany(mappedBy="allergy", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Reaction> reaction = new ArrayList<>();*/
    
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date resultDate;   

    public LabResult() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getLab() {
        return lab;
    }

    public void setLab(Long lab) {
        this.lab = lab;
    }

    public String getConfirmTestDescription() {
        return confirmTestDescription;
    }

    public void setConfirmTestDescription(String confirmTestDescription) {
        this.confirmTestDescription = confirmTestDescription;
    }

    public String getLabResult() {
        return labResult;
    }

    public void setLabResult(String labResult) {
        this.labResult = labResult;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public String getReference() {
        return reference;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }
    
    public String getIssued() {
        return issued;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
    
    public String getConclusion() {
        return conclusion;
    }

    public void setConclusionCode(String conclusionCode) {
        this.conclusionCode = conclusionCode;
    }
    
    public String getConclusionCode() {
        return conclusionCode;
    }    
    
    public void setLabResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Date getLabResultDate() {        
        return resultDate;
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
        if (!(object instanceof LabResult)) {
            return false;
        }
        LabResult other = (LabResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.LabResult[ id=" + id + " ]";
    }
    
}