package machuzu.entities;

import java.io.Serializable;
import java.sql.Time;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


@Entity

public class Timing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TIMING_ID")
    Long id;

    @JoinColumn(name="DOSEANDRATE_ID", nullable=true)
    private Long doseAndRate;

    private String tRepeat;
    private String boundsDuration;    
    private String boundsRange;
    private String boundsPeriodFrom;
    private String boundsPeriodTo;    
    private int count;
    private int countMax;
    private float duration;
    private float durationMax;
    private String durationUnit;
    private int frequency;
    private int frequencyMax;
    private int periodLow;
    private int periodMax;
    private String periodUnit;
    private String dayOfWeek;
    private Time timeOfDay;
    private String rptWhen;
    private String rptOffset;
    private String tCode;

    public Timing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoseAndRate() {
        return doseAndRate;
    }

    public void setDoseAndRate(Long doseAndRate) {
        this.doseAndRate = doseAndRate;
    }

    public String getRepeat() {
        return tRepeat;
    }

    public void setRepeat(String tRepeat) {
        this.tRepeat = tRepeat;
    }

    public String getBoundsDuration() {
        return boundsDuration;
    }

    public void setBoundsDuration(String boundsDuration) {
        this.boundsDuration = boundsDuration;
    }

    public void setBoundsRange(String boundsRange) {
        this.boundsRange = boundsRange;
    }
    
    public String getBoundsRange() {
        return boundsRange;
    }
    
    public void setBoundsPeriodFrom(String boundsPeriodFrom) {
        this.boundsPeriodFrom = boundsPeriodFrom;
    }

    public String getBoundsPeriodFrom() {
        return boundsPeriodFrom;
    }
    
    public void setBatchExpirationDate(String boundsPeriodTo) {
        this.boundsPeriodTo = boundsPeriodTo;
    }

    public String getBatchExpirationDate() {
        return boundsPeriodTo;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCountMax(int countMax) {
        this.countMax = countMax;
    }
    
    public int getCountMax() {
        return countMax;
    }
    
    public void setDuration(float duration) {
        this.duration = duration;
    }
    
    public float getDuration() {
        return duration;
    }
    
    public void setDurationMax(float durationMax) {
        this.durationMax = durationMax;
    }
    
    public float getDurationMax() {
        return durationMax;
    }
    
    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }
    
    public String getDurationUnit() {
        return durationUnit;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public void setFrequencyMax(int frequencyMax) {
        this.frequencyMax = frequencyMax;
    }
    
    public int getFrequencyMax() {
        return frequencyMax;
    }
    
    public void setPeriodLow(int periodLow) {
        this.periodLow = periodLow;
    }
    
    public int getPeriodLow() {
        return periodLow;
    }
    
    public void setPeriodMax(int periodMax) {
        this.periodMax = periodMax;
    }
    
    public int getPeriodMax() {
        return periodMax;
    }
    
    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }
    
    public String getPeriodUnit() {
        return periodUnit;
    }
    
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }
    
    public void setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    
    public Time getTimeOfDay() {
        return timeOfDay;
    }
    
    public void setRPTWhen(String rptWhen) {
        this.rptWhen = rptWhen;
    }
    
    public String getRPTWhen() {
        return rptWhen;
    }
    
    public void setRPTOffset(String rptOffset) {
        this.rptOffset = rptOffset;
    }
    
    public String getRPTOffset() {
        return rptOffset;
    }
    
    public void setTCode(String tCode) {
        this.tCode = tCode;
    }
    
    public String getTCode() {
        return tCode;
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
        if (!(object instanceof Timing)) {
            return false;
        }
        Timing other = (Timing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Timing[ id=" + id + " ]";
    }
    
}