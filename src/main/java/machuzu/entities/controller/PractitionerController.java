package machuzu.entities.controller;

import machuzu.entities.Practitioner;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.PractitionerFacade;

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
import machuzu.entities.IPA;

@Named("practitionerController")
@SessionScoped
public class PractitionerController implements Serializable {

    @EJB
    private machuzu.entities.jpa.PractitionerFacade ejbFacade;
    private List<Practitioner> items = null;
    private Practitioner selected;

    public PractitionerController() {
    }

    public Practitioner getSelected() {
        return selected;
    }

    public void setSelected(Practitioner selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PractitionerFacade getFacade() {
        return ejbFacade;
    }

    public Practitioner prepareCreate() {
        selected = new Practitioner();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long ipa) {
        selected.setIPA(ipa);
        persist(PersistAction.CREATE, "Practitioner Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Practitioner Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Practitioner Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Practitioner> getItems() {
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

    public Practitioner getPractitioner(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Practitioner> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Practitioner> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Practitioner.class)
    public static class PractitionerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PractitionerController controller = (PractitionerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "practitionerController");
            return controller.getPractitioner(getKey(value));
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
            if (object instanceof Practitioner) {
                Practitioner o = (Practitioner) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Practitioner.class.getName()});
                return null;
            }
        }

    }

}
