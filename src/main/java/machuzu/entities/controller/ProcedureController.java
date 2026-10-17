package machuzu.entities.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.ProcedureFacade;

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
import machuzu.entities.Procedures;

@Named("procedureController")
@SessionScoped
public class ProcedureController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ProcedureFacade ejbFacade;
    private List<Procedures> items = null;
    private Procedures selected;

    public ProcedureController() {
    }

    public Procedures getSelected() {
        return selected;
    }

    public void setSelected(Procedures selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProcedureFacade getFacade() {
        return ejbFacade;
    }

    public Procedures prepareCreate() {
        selected = new Procedures();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long encounter) {
        selected.setEncounter(encounter);
        persist(PersistAction.CREATE, "Procedure Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Procedure Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Procedure Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Procedures> getItems() {
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

    public Procedures getProcedure(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Procedures> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Procedures> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
        
    @FacesConverter(forClass = Procedures.class)
    public static class ProcedureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProcedureController controller = (ProcedureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "procedureController");
            return controller.getProcedure(getKey(value));
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
            if (object instanceof Procedures) {
                Procedures o = (Procedures) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Procedures.class.getName()});
                return null;
            }
        }

    }

}
