package machuzu.entities.controller;

import machuzu.entities.Dosage;
import machuzu.entities.jpa.util.JsfUtil;
import machuzu.entities.jpa.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.DosageFacade;

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
import machuzu.entities.DoseAndRate;
import machuzu.entities.jpa.DoseAndRateFacade;
import org.primefaces.PrimeFaces;

@Named("dosageController")
@SessionScoped
public class DosageController implements Serializable {

    @EJB
    private machuzu.entities.jpa.DosageFacade ejbFacade;
    
    @EJB
    private machuzu.entities.jpa.DoseAndRateFacade ejbDoseAndRateFacade;
    
    private List<Dosage> items = null;
    private Dosage selected;
    private List<DoseAndRate> selectedDoseAndRate;

    public DosageController() {
    }

    public Dosage getSelected() {
        return selected;
    }

    public void setSelected(Dosage selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DosageFacade getFacade() {
        return ejbFacade;
    }
    
    private DoseAndRateFacade getDoseAndRateFacade() {
        return ejbDoseAndRateFacade;
    }

    public Dosage prepareCreate() {
        selected = new Dosage();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long medication) {   
        selected.setMedication(medication);
        persist(PersistAction.CREATE, "Dosage Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('DoseAndRateCreateDialog').show();");     
            
            selected.setId(getFacade().getMaxId());

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        
    }

    public void update() {
        persist(PersistAction.UPDATE, "Dosage Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Dosage Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Dosage> getItems() {
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

    public Dosage getDosage(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Dosage> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Dosage> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<DoseAndRate> getDosagesRates(List<Dosage> dosages) {
        try {
        System.out.println("DOSAGERate000: " + dosages.get(0).getId());
        selectedDoseAndRate =  getDoseAndRateFacade().getDosageAndRate(dosages.get(0).getId());
        System.out.println("DOSAGERate111: " + selectedDoseAndRate.get(0).getDoseQuantity());
        } catch (Exception e) {
            System.out.println("DISERR: " + e);
        }
        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('MedicationRequestViewDialog').show();");     
            
           // selected.setId(getFacade().getMaxId());

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        
        
        return selectedDoseAndRate;
        //selected = (DoseAndRate) session.getAttribute("dosageList");
       // System.out.println("DOSAGERate: " + selected.getId());
        //selected = getFacade().find(dosage.g);
    }

        
    @FacesConverter(forClass = Dosage.class)
    public static class DosageControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DosageController controller = (DosageController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dosageController");
            return controller.getDosage(getKey(value));
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
            if (object instanceof Dosage) {
                Dosage o = (Dosage) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Dosage.class.getName()});
                return null;
            }
        }

    }

}
