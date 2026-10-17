package machuzu.entities.controller;

import machuzu.entities.MedicationAdministration;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.MedicationAdministrationFacade;

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
import org.primefaces.PrimeFaces;


@Named("medicationAdministrationController")
@SessionScoped
public class MedicationAdministrationController implements Serializable {


    @EJB private machuzu.entities.jpa.MedicationAdministrationFacade ejbFacade;
    private List<MedicationAdministration> items = null;
    private MedicationAdministration selected;

    public MedicationAdministrationController() {
    }

    public MedicationAdministration getSelected() {
        return selected;
    }

    public void setSelected(MedicationAdministration selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MedicationAdministrationFacade getFacade() {
        return ejbFacade;
    }

    public MedicationAdministration prepareCreate() {
        selected = new MedicationAdministration();
        initializeEmbeddableKey();
        return selected;
    }

public void create(Long encounter) {
        selected.setEncounter(encounter);
        persist(PersistAction.CREATE, "Medication Administration Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('MedicationCreateDialog').show();");

            selected.setId(getFacade().getMaxId());

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Medication Administration Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Medication Administration Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MedicationAdministration> getItems() {
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

    public MedicationAdministration getMedicationAdministration(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MedicationAdministration> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MedicationAdministration> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=MedicationAdministration.class)
    public static class MedicationAdministrationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicationAdministrationController controller = (MedicationAdministrationController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicationAdministrationController");
            return controller.getMedicationAdministration(getKey(value));
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
            if (object instanceof MedicationAdministration) {
                MedicationAdministration o = (MedicationAdministration) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MedicationAdministration.class.getName()});
                return null;
            }
        }

    }

}
