package machuzu.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({  
 @NamedQuery(name="DoseAndRate.getDosageAndRate", 
    query="SELECT d FROM DoseAndRate d WHERE d.dosage = :dosage")
})
public class DoseAndRate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOSEANDRATE_ID")
    private Long id;

    // FIX: Changed from invalid @JoinColumn to standard @Column since it maps a plain Long ID field
    @Column(name="DOSAGE_ID", nullable=true)
    private Long dosage;

    private String dType;
    private int doseRangeLow;    
    private int doseRangeHigh;
    private int doseQuantity;
    private int rateRatioNumerator;    
    private int rateRatioDenominator;
    private int rateRangeLow;    
    private int rateRangeHigh;
    private int rateRangeQty;
    
    public DoseAndRate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getDosage() {
        return dosage;
    }

    public void setDosage(Long dosage) {
        this.dosage = dosage;
    }

    public String getDType() {
        return dType;
    }

    public void setDType(String dType) {
        this.dType = dType;
    }

    public int getDoseRangeLow() {
        return doseRangeLow;
    }

    public void setDoseRangeLow(int doseRangeLow) {
        this.doseRangeLow = doseRangeLow;
    }

    public void setDoseRangeHigh(int doseRangeHigh) {
        this.doseRangeHigh = doseRangeHigh;
    }
    
    public int getDoseRangeHigh() {
        return doseRangeHigh;
    }
    
    public void setDoseQuantity(int doseQuantity) {
        this.doseQuantity = doseQuantity;
    }

    public int getDoseQuantity() {
        return doseQuantity;
    }
    
    public void setRateRatioNumerator(int rateRatioNumerator) {
        this.rateRatioNumerator = rateRatioNumerator;
    }

    public int getRateRatioNumerator() {
        return rateRatioNumerator;
    }
    
    public void setRateRatioDenominator(int rateRatioDenominator) {
        this.rateRatioDenominator = rateRatioDenominator;
    }

    public int getRateRatioDenominator() {
        return rateRatioDenominator;
    }
    
    public void setRateRangeLow(int rateRangeLow) {
        this.rateRangeLow = rateRangeLow;
    }

    public int getRateRangeLow() {
        return rateRangeLow;
    }
    
    public void setRateRangeHigh(int rateRangeHigh) {
        this.rateRangeHigh = rateRangeHigh;
    }

    public int getRateRangeHigh() {
        return rateRangeHigh;
    }
    
    public void setRateRangeQty(int rateRangeQty) {
        this.rateRangeQty = rateRangeQty;
    }

    public int getRateRangeQty() {
        return rateRangeQty;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DoseAndRate)) {
            return false;
        }
        DoseAndRate other = (DoseAndRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.DoseAndRate[ id=" + id + " ]";
    }
}
