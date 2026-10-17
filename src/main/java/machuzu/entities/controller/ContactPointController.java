package machuzu.entities.controller;

import machuzu.entities.ContactPoint;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.ContactPointFacade;

import java.io.Serializable;
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

@Named("contactPointController")
@SessionScoped
public class ContactPointController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ContactPointFacade ejbFacade;
    private List<ContactPoint> items = null;
    private ContactPoint selected;

    public ContactPointController() {
    }

    public ContactPoint getSelected() {
        return selected;
    }

    public void setSelected(ContactPoint selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContactPointFacade getFacade() {
        return ejbFacade;
    }

    public ContactPoint prepareCreate() {
        selected = new ContactPoint();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long ipa, Long contact) {
        selected.setIPA(ipa);
        selected.setContact(contact);
        persist(PersistAction.CREATE, "Contact Point Created Successfully");
        if (!JsfUtil.isValidationFailed()) { 
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Contact Point Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Contact Point Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContactPoint> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void refresh() {
        selected = null;
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

    public ContactPoint getContactPoint(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ContactPoint> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ContactPoint> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ContactPoint.class)
    public static class ContactPointControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactPointController controller = (ContactPointController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contactPointController");
            return controller.getContactPoint(getKey(value));
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
            if (object instanceof ContactPoint) {
                ContactPoint o = (ContactPoint) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ContactPoint.class.getName()});
                return null;
            }
        }

    }

}
