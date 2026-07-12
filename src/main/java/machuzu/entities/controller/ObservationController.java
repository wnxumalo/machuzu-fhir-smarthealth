package machuzu.entities.controller;

import machuzu.entities.Observation;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.ObservationFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("observationController")
@SessionScoped
public class ObservationController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ObservationFacade ejbFacade;
    private List<Observation> items = null;
    private Observation selected;

    public ObservationController() {
    }

    public Observation getSelected() {
        return selected;
    }

    public void setSelected(Observation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ObservationFacade getFacade() {
        return ejbFacade;
    }

    public Observation prepareCreate() {
        selected = new Observation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long encounter) {
        selected.setEncounter(encounter);
        persist(PersistAction.CREATE, "Observation Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void createDiagnosis(Long encounter) {
        selected.setEncounter(encounter);
        selected.setCategory("Diagnosis");
        persist(PersistAction.CREATE, "Observation Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Observation Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Observation Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Observation> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public String listObservations(boolean encounter) {
        
        if (encounter == false) {
            return "/observation/List";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cannot Proceed", "Select an Identifier First."));

            return null;
        }
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

    public Observation getObservation(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Observation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Observation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Observation.class)
    public static class ObservationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObservationController controller = (ObservationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "observationController");
            return controller.getObservation(getKey(value));
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
            if (object instanceof Observation) {
                Observation o = (Observation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Observation.class.getName()});
                return null;
            }
        }

    }

}
