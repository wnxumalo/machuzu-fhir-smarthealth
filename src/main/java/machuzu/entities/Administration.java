package machuzu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
@NamedQueries({  
 @NamedQuery(name="Administration.login", 
    query="SELECT a FROM Administration a WHERE a.email = :email and a.password = :password")
})
public class Administration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADMINISTRATION_ID")
    Long id;

    private String employeeNo;
    private String idNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date employmentDate;    
    private String name;
    private String surname;    
    private String gender;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDay;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastDay;
    private String designation;
    private String contact;
    private String department;
    private String depSection;
    private String empLevel;
    private String empType;
    private boolean status;

    private String email;
    private String password;    
    
    public Administration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
    
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public Date getLastDay() {
        return lastDay;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setSection(String depSection) {
        this.depSection = depSection;
    }
    
    public String getSection() {
        return depSection;
    }
        
    public void setLevel(String empLevel) {
        this.empLevel = empLevel;
    }
    
    public String getLevel() {
        return empLevel;
    }
    
    public void setEmpType(String empType) {
        this.empType = empType;
    }
    
    public String getEmpType() {
        return empType;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public boolean getStatus() {
        return status;
    }
     
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
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
        if (!(object instanceof Administration)) {
            return false;
        }
        Administration other = (Administration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "machuzu.entities.Administration[ id=" + id + " ]";
    }
    
}