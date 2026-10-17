package machuzu.entities.controller;

import machuzu.entities.ChargeItem;
import machuzu.entities.jpa.util.JsfUtil;
import machuzu.entities.jpa.util.JsfUtil.PersistAction;

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
import machuzu.entities.jpa.ChargeItemFacade;
import org.primefaces.PrimeFaces;

@Named("chargeItemController") 
@SessionScoped
public class ChargeItemController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ChargeItemFacade ejbFacade;
    private List<ChargeItem> items = null;
    private ChargeItem selected;

    public ChargeItemController() {
    }

    public ChargeItem getSelected() {
        return selected;
    }

    public void setSelected(ChargeItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ChargeItemFacade getFacade() {
        return ejbFacade;
    }

    public ChargeItem prepareCreate() {
        selected = new ChargeItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long account, Long encounter) {
        selected.setAccount(account);
        selected.setEncounter(encounter);
        persist(PersistAction.CREATE, "Charge Item Added Successfully " + encounter);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Charge Item Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Charge Item Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ChargeItem> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void generateInvoice() {
        
        try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('InvoiceCreateDialog').show();");

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
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

    public ChargeItem getChargeItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ChargeItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ChargeItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ChargeItem.class)
    public static class ChargeItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChargeItemController controller = (ChargeItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "chargeItemController");
            return controller.getChargeItem(getKey(value));
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
            if (object instanceof ChargeItem) {
                ChargeItem o = (ChargeItem) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ChargeItem.class.getName()});
                return null;
            }
        }

    }

}
