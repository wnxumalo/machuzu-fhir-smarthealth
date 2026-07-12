package machuzu.entities.controller;

import machuzu.entities.Vitals;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.VitalsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("vitalsController")
@SessionScoped
public class VitalsController implements Serializable {

    @EJB
    private machuzu.entities.jpa.VitalsFacade ejbFacade;
    private List<Vitals> items = null;
    private Vitals selected;

    public VitalsController() {
    }

    public Vitals getSelected() {
        return selected;
    }

    public void setSelected(Vitals selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VitalsFacade getFacade() {
        return ejbFacade;
    }

    public Vitals prepareCreate() {
        selected = new Vitals();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long encounter, Long ipa) {
        selected.setEncounter(encounter);
        selected.setIPA(ipa);
        persist(PersistAction.CREATE, "Vitals Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<Vitals> getDashboard(Long ipa) {
        return getFacade().getVitals(ipa);
    }

    public void update() {
        persist(PersistAction.UPDATE, "Vitals Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Vitals Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Vitals> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Vitals> getDashboardItems() {
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

    public Vitals getVitals(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Vitals> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Vitals> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Vitals.class)
    public static class VitalsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VitalsController controller = (VitalsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vitalsController");
            return controller.getVitals(getKey(value));
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
            if (object instanceof Vitals) {
                Vitals o = (Vitals) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Vitals.class.getName()});
                return null;
            }
        }

    }

}
