package machuzu.entities.controller;

import machuzu.entities.ObjectReference;
import machuzu.entities.jpa.util.JsfUtil;
import machuzu.entities.jpa.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;
import machuzu.converters.ReferenceBean;
import machuzu.entities.jpa.ReferenceFacade;

@Named("referenceController")
@SessionScoped
public class ReferenceController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ReferenceFacade ejbFacade;
    private List<ObjectReference> items;
    private ObjectReference selected;

    public ReferenceController() {
        
    }

    public ObjectReference getSelected() {
        return selected;
    }

    public void setSelected(ObjectReference selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReferenceFacade getFacade() {
        return ejbFacade;
    }
    
    public List<ObjectReference> getReferences() {
        items = new ArrayList<ObjectReference>();
        items = getFacade().findAll();

        return items;
    }
    
    public void setReferences(List<ObjectReference> references) {
        this.items = references;
    }

    public ObjectReference getReferences(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (ObjectReference refs : items){
            if (id.equals(refs.getId())){
                return refs;
            }
        }
        return null;
    }


    public ObjectReference prepareCreate() {
        selected = new ObjectReference();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long ipa) {
        selected.setIPA(ipa);
        persist(PersistAction.CREATE, "Reference Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Reference Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Reference Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ObjectReference> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ObjectReference getReference(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public List<ObjectReference> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ObjectReference> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ObjectReference.class)
    public static class ReferenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReferenceController controller = (ReferenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "referenceController");
            return controller.getReference(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ObjectReference) {
                ObjectReference o = (ObjectReference) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjectReference.class.getName()});
                return null;
            }
        }

    }

}
