package machuzu.converters;

import java.io.Serializable;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import machuzu.entities.ObjectReference;
import machuzu.entities.jpa.ReferenceFacade;

@SessionScoped

public class ReferenceBean implements Serializable {

    @EJB
    private machuzu.entities.jpa.ReferenceFacade ejbFacade;
 
    private ObjectReference selectedReference;
    private List<ObjectReference> references;    

    public ReferenceBean() {
    }
    
    private ReferenceFacade getFacade() {
        return ejbFacade;
    }
    
    public ObjectReference getReference(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public ObjectReference getSelectedReference() {
        return selectedReference;
    }

    public void setSelectedReference(ObjectReference selectedReference) {
        this.selectedReference = selectedReference;
    }

    public List<ObjectReference> getReferences() {
        return getFacade().findAll();
    }

    public void setReferences(List<ObjectReference> references) {
        this.references = references;
    }
    
    public ObjectReference getReferences(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (ObjectReference refs : references){
            if (id.equals(refs.getId())){
                return refs;
            }
        }
        return null;
    }
    
    
}