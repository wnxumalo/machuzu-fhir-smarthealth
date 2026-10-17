package machuzu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;


@Entity
public class CarePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CAREPLAN_ID")
    Long id;

    @JoinColumn(name="ENCOUNTER_ID", nullable=true)
    private Long encounter;
    
    private String status;
    private String intent;
    private String category;
    private String title;
    private String description;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private java.util.Date periodFrom;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private java.util.Date periodTo;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private java.util.Date created;
    private String note;

    @OneToMany(mappedBy="careplan", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CarePlanActivity> carePlanActivity = new ArrayList<>();

    public CarePlan() {
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

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }    

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }
    
    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }
    
    public Date getPeriodTo() {
        return periodTo;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
    
    public Date getCreated() {
        return created;
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
        if (!(object instanceof CarePlan)) {
            return false;
        }
        CarePlan other = (CarePlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.CarePlan[ id=" + id + " ]";
    }
    
}