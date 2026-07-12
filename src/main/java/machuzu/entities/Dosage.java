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
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@NamedQueries({  
 @NamedQuery(name="Dosage.fetchRecords", 
 query="SELECT a FROM Dosage a order by id desc")})
@XmlRootElement
public class Dosage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOSAGE_ID")
    Long id;

    @JoinColumn(name="MEDICATION_ID", nullable=true)
    private Long medication;

    //@JoinColumn(name="MEDICATIONSTATEMENT_ID", nullable=true)
    //private Long medicationstatement;

    private String dSequence;
    private String text;
    
    private String additionalInstruction;
    private String patientInstruction;

    @OneToOne(fetch=FetchType.EAGER)
    private Timing timing;

    private boolean asNeeded;
    private String site;
    private String route;
    private String method;

    @OneToMany(mappedBy="dosage", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DoseAndRate> doseAndRates = new ArrayList<>();

    private int maxDosePerPeriodNumerator;
    private int maxDosePerPeriodDenominator;
    private int maxDosePerLifetime;
    
    @JoinColumn(name="MEDICATIONADMINISTRATION_ID", nullable=true)
    private Long medicationAdministration;

    
    public Dosage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getMedication() {
        return medication;
    }

    public void setMedication(Long medication) {
        this.medication = medication;
    }
    
/*    public Long getMedicationStatement() {
        return medicationstatement;
    }

    public void setMedicationStatement(Long medicationstatement) {
        this.medicationstatement = medicationstatement;
    }*/

    public String getSequence() {
        return dSequence;
    }

    public void setSequence(String dSequence) {
        this.dSequence = dSequence;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAdditionalInstruction(String additionalInstruction) {
        this.additionalInstruction = additionalInstruction;
    }
    
    public String getAdditionalInstruction() {
        return additionalInstruction;
    }
    
    public void setPatientInstruction(String patientInstruction) {
        this.patientInstruction = patientInstruction;
    }

    public String getPatientInstruction() {
        return patientInstruction;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
    }
    
    public Timing getTiming() {
        return timing;
    }
    
    public void setAsNeeded(boolean asNeeded) {
        this.asNeeded = asNeeded;
    }

    public boolean getAsNeeded() {
        return asNeeded;
    }
    
    public void setSite(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }
    
    public void setRoute(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
    
    public void setDoseAndRates(List<DoseAndRate> doseAndRates) {
        this.doseAndRates = doseAndRates;
    }

    @XmlTransient
    public List<DoseAndRate> getDoseAndRates() {
        return doseAndRates;
    }
    
    public void setMaxDosePerPeriodNumerator(int maxDosePerPeriodNumerator) {
        this.maxDosePerPeriodNumerator = maxDosePerPeriodNumerator;
    }

    public int getMaxDosePerPeriodNumerator() {
        return maxDosePerPeriodNumerator;
    }
    
    public void setMaxDosePerPeriodDenominator(int maxDosePerPeriodDenominator) {
        this.maxDosePerPeriodDenominator = maxDosePerPeriodDenominator;
    }

    public int getMaxDosePerPeriodDenominator() {
        return maxDosePerPeriodDenominator;
    }
    
    public void setMaxDosePerLifetime(int maxDosePerLifetime) {
        this.maxDosePerLifetime = maxDosePerLifetime;
    }

    public int getMaxDosePerLifetime() {
        return maxDosePerLifetime;
    }
    
    public Long getMedicationAdministration() {
        return medicationAdministration;
    }

    public void setMedicationAdministration(Long medicationAdministration) {
        this.medicationAdministration = medicationAdministration;
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
        if (!(object instanceof Dosage)) {
            return false;
        }
        Dosage other = (Dosage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Dosage[ id=" + id + " ]";
    }
    
}