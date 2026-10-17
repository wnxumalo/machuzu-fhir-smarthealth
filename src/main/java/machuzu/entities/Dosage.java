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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@NamedQueries({  
 @NamedQuery(name="Dosage.fetchRecords", 
    query="SELECT a FROM Dosage a order by a.id desc") // Updated to explicit 'a.id'
})
public class Dosage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOSAGE_ID")
    private Long id;

    // FIX: Replaced invalid @JoinColumn with standard @Column since it maps a plain Long ID
    @Column(name="MEDICATION_ID", nullable=true)
    private Long medication;

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

    // FIX: Native EAGER fetching avoids the deprecated Hibernate LazyCollection annotation
    @OneToMany(mappedBy="dosage", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<DoseAndRate> doseAndRates = new ArrayList<>();

    private int maxDosePerPeriodNumerator;
    private int maxDosePerPeriodDenominator;
    private int maxDosePerLifetime;
    
    // FIX: Replaced invalid @JoinColumn with standard @Column since it maps a plain Long ID
    @Column(name="MEDICATIONADMINISTRATION_ID", nullable=true)
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
