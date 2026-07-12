package machuzu.entities.controller;

import machuzu.entities.Evidence;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.EvidenceFacade;

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

@Named("evidenceController")
@SessionScoped
public class EvidenceController implements Serializable {

    @EJB
    private machuzu.entities.jpa.EvidenceFacade ejbFacade;
    private List<Evidence> items = null;
    private Evidence selected;

    public EvidenceController() {
    }

    public Evidence getSelected() {
        return selected;
    }

    public void setSelected(Evidence selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EvidenceFacade getFacade() {
        return ejbFacade;
    }

    public Evidence prepareCreate() {
        selected = new Evidence();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long condition) {
        selected.setProblem(condition);
        persist(PersistAction.CREATE, "Evidence Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Evidence Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Evidence Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Evidence> getItems() {
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

    public Evidence getEvidence(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Evidence> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Evidence> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Evidence.class)
    public static class EvidenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EvidenceController controller = (EvidenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "evidenceController");
            return controller.getEvidence(getKey(value));
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
            if (object instanceof Evidence) {
                Evidence o = (Evidence) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Evidence.class.getName()});
                return null;
            }
        }

    }

}
