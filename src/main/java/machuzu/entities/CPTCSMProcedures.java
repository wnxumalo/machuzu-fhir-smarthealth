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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
public class CPTCSMProcedures implements Serializable {

    @Column(name = "CPTCSMPROCEDURES_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String procedureCodeCategory; //class
    private String cptCode; //period
    private String procedureCodeDescription; //period
    
    @OneToMany(mappedBy="cptcsmprocedures", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Procedures> procedures = new ArrayList<>();
    
    public CPTCSMProcedures() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProcedureCodeCategory() {
        return procedureCodeCategory;
    }

    public void setProcedureCodeCategory(String procedureCodeCategory) {
        this.procedureCodeCategory = procedureCodeCategory;
    }
    
    public String getCptCode() {
        return cptCode;
    }

    public void setCptCode(String cptCode) {
        this.cptCode = cptCode;
    }
    
    public String getProcedureCodeDescription() {
        return procedureCodeDescription;
    }

    public void setProcedureCodeDescription(String procedureCodeDescription) {
        this.procedureCodeDescription = procedureCodeDescription;
    }
    
    public void setProcedures(List<Procedures> procedures) {
        this.procedures = procedures;
    }

    @XmlTransient
    public List<Procedures> getProcedures() {
        return procedures;
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
        if (!(object instanceof CPTCSMProcedures)) {
            return false;
        }
        CPTCSMProcedures other = (CPTCSMProcedures) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.CPTCSMProcedures[ id=" + id + " ]";
    }
    
}