package machuzu.entities.controller;

import machuzu.entities.DoseAndRate;
import machuzu.entities.jpa.util.JsfUtil;
import machuzu.entities.jpa.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.DoseAndRateFacade;

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
import jakarta.servlet.http.HttpSession;
import machuzu.entities.Dosage;
import machuzu.entities.jpa.DosageFacade;
import org.primefaces.PrimeFaces;

@Named("doseAndRateController")
@SessionScoped
public class DoseAndRateController implements Serializable {

    @EJB
    private machuzu.entities.jpa.DoseAndRateFacade ejbFacade;
    
    @EJB
    private machuzu.entities.jpa.DosageFacade dosageFacade;
    
    private List<DoseAndRate> items = null;
    private DoseAndRate selected;
    private Dosage dosage;

    public DoseAndRateController() {
    }

    public DoseAndRate getSelected() {
        return selected;
    }

    public void setSelected(DoseAndRate selected) {
        this.selected = selected;
    }
    
    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DoseAndRateFacade getFacade() {
        return ejbFacade;
    }
    
    private DosageFacade getDosageFacade() {
        return dosageFacade;
    }

    public DoseAndRate prepareCreate() {
        selected = new DoseAndRate();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long dosage) {
        selected.setDosage(dosage);
        persist(PersistAction.CREATE, "Medication Added Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }

        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('TimingCreateDialog').show();");

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, "Medication Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Medication Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DoseAndRate> getItems() {
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

    public DoseAndRate getDoseAndRate(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DoseAndRate> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DoseAndRate> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DoseAndRate.class)
    public static class DoseAndRateControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DoseAndRateController controller = (DoseAndRateController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "doseAndRateController");
            return controller.getDoseAndRate(getKey(value));
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
            if (object instanceof DoseAndRate) {
                DoseAndRate o = (DoseAndRate) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DoseAndRate.class.getName()});
                return null;
            }
        }

    }

}
