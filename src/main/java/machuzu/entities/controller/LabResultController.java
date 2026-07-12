package machuzu.entities.controller;

import machuzu.entities.LabResult;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.LabResultFacade;

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
import machuzu.entities.Lab;
import machuzu.entities.jpa.LabFacade;

@Named("labResultController")
@SessionScoped
public class LabResultController implements Serializable {

    @EJB
    private machuzu.entities.jpa.LabResultFacade ejbFacade;
    
    @EJB
    private machuzu.entities.jpa.LabFacade labFacade;
    
    private List<LabResult> items = null;
    private LabResult selected;

    public LabResultController() {
    }

    public LabResult getSelected() {
        return selected;
    }

    public void setSelected(LabResult selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LabResultFacade getFacade() {
        return ejbFacade;
    }
    
    private LabFacade getLabFacade() {
        return labFacade;
    }

    public LabResult prepareCreate() {
        selected = new LabResult();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long lab) {
        selected.setLab(lab);
        Lab mainlab = getLabFacade().find(lab);
        mainlab.setResults(true);
        getLabFacade().edit(mainlab);
        persist(PersistAction.CREATE, "Lab Result Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Lab Result Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Lab Result Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LabResult> getItems() {
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

    public LabResult getLabResult(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LabResult> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LabResult> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = LabResult.class)
    public static class LabResultControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LabResultController controller = (LabResultController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "labResultController");
            return controller.getLabResult(getKey(value));
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
            if (object instanceof LabResult) {
                LabResult o = (LabResult) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LabResult.class.getName()});
                return null;
            }
        }

    }

}
