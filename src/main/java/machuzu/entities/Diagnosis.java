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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;

@Entity

public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DIAGNOSIS_ID")
    Long id;

    private String diagID;
    private String chapterNo;
    private String chapter_Desc;
    private String group_Code;
    private String group_Description;
    private String iCD_3Code;
    private String ICD_3Code_Desc;
    private String ICD10_Code;
    private String WHO_FULL_Desc;
    private String Valid_ICD10_ClinicalUse;
    private String Valid_ICD10_Primary;
    private String Asterisk;
    private String Dagger;
    private String Sequelae;
    private String Age_Range;
    private String Gender;
    private String Status_ADM;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date Start_Date;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date End_Date;
    private String Comment;

    
    @OneToMany(mappedBy="diagnosis", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Observation> observation = new ArrayList<>();
    
    public Diagnosis() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagID() {
        return diagID;
    }

    public void setDiagID(String diagID) {
        this.diagID = diagID;
    }

    public String getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(String chapterNo) {
        this.chapterNo = chapterNo;
    }

    public void setChapterDesc(String chapter_Desc) {
        this.chapter_Desc = chapter_Desc;
    }
    
    public String getChapterDesc() {
        return chapter_Desc;
    }

    public void setGroupCode(String group_Code) {
        this.group_Code = group_Code;
    }
    
    public String getGroupCode() {
        return group_Code;
    }
    
    public void setGroupDescription(String group_Description) {
        this.group_Description = group_Description;
    }
    
    public String getGroupDescription() {
        return group_Description;
    }
    
    public void setICD3Code(String iCD_3Code) {
        this.iCD_3Code = iCD_3Code;
    }
    
    public String getICD3Code() {
        return iCD_3Code;
    }
    
    public void setICD3CodeDesc(String ICD_3Code_Desc) {
        this.ICD_3Code_Desc = ICD_3Code_Desc;
    }
    
    public String getICD3CodeDesc() {
        return ICD_3Code_Desc;
    }
    
    public void setICD10Code(String ICD10_Code) {
        this.ICD10_Code = ICD10_Code;
    }
    
    public String getICD10Code() {
        return ICD10_Code;
    }
        
    public void setWHOFULLDesc(String WHO_FULL_Desc) {
        this.WHO_FULL_Desc = WHO_FULL_Desc;
    }
    
    public String getWHOFULLDesc() {
        return WHO_FULL_Desc;
    }
    
    public void setValidICD10ClinicalUse(String Valid_ICD10_ClinicalUse) {
        this.Valid_ICD10_ClinicalUse = Valid_ICD10_ClinicalUse;
    }
    
    public String getValidICD10ClinicalUse() {
        return Valid_ICD10_ClinicalUse;
    }
    
    public void setValidICD10Primary(String Valid_ICD10_Primary) {
        this.Valid_ICD10_Primary = Valid_ICD10_Primary;
    }
    
    public String getValidICD10Primary() {
        return Valid_ICD10_Primary;
    }
    
    public void setAsterisk(String Asterisk) {
        this.Asterisk = Asterisk;
    }
    
    public String getAsterisk() {
        return Asterisk;
    }
    
    public void setDagger(String Dagger) {
        this.Dagger = Dagger;
    }
    
    public String getDagger() {
        return Dagger;
    }
    
    public void setSequelae(String Sequelae) {
        this.Sequelae = Sequelae;
    }
    
    public String getSequelae() {
        return Sequelae;
    }
    
    public void setAgeRange(String Age_Range) {
        this.Age_Range = Age_Range;
    }
    
    public String getAgeRange() {
        return Age_Range;
    }
    
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    
    public String getGender() {
        return Gender;
    }
    
    public void setStatusADM(String Status_ADM) {
        this.Status_ADM = Status_ADM;
    }
    
    public String getStatusADM() {
        return Status_ADM;
    }
    
    public void setStartDate(Date Start_Date) {
        this.Start_Date = Start_Date;
    }
    
    public Date getStartDate() {
        return Start_Date;
    }
    
    public void setEndDate(Date End_Date) {
        this.End_Date = End_Date;
    }
    
    public Date getEndDate() {
        return End_Date;
    }
    
    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    
    public String getComment() {
        return Comment;
    }
    
    public void setObservation(List<Observation> observation) {
        this.observation = observation;
    }

    public List<Observation> getObservation() {
        return observation;
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
        if (!(object instanceof Diagnosis)) {
            return false;
        }
        Diagnosis other = (Diagnosis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Diagnosis[ id=" + id + " ]";
    }

}