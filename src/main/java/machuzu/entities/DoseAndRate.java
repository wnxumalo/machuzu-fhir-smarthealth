package machuzu.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({  
 @NamedQuery(name="DoseAndRate.getDosageAndRate", 
    query="SELECT d FROM DoseAndRate d WHERE d.dosage = :dosage")
})
@XmlRootElement
public class DoseAndRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOSEANDRATE_ID")
    Long id;

    @JoinColumn(name="DOSAGE_ID", nullable=true)
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
        // TODO: Warning - this method won't work in the case the id fields are not set
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