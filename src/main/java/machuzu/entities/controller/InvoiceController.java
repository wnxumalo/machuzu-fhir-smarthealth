package machuzu.entities.controller;

import machuzu.entities.Invoice;
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
import machuzu.entities.jpa.InvoiceFacade;

@Named("invoiceController")
@SessionScoped
public class InvoiceController implements Serializable {

    @EJB
    private machuzu.entities.jpa.InvoiceFacade ejbFacade;
    private List<Invoice> items = null;
    private Invoice selected;

    public InvoiceController() {
    }

    public Invoice getSelected() {
        return selected;
    }

    public void setSelected(Invoice selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InvoiceFacade getFacade() {
        return ejbFacade;
    }

    public Invoice prepareCreate() {
        selected = new Invoice();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long account, Long encounter) {
        selected.setAccount(account);
        selected.setEncounter(encounter);                
        
        persist(PersistAction.CREATE, "Invoice Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Invoice Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Invoice Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Invoice> getItems() {
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

    public Invoice getInvoice(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Invoice> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Invoice> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Invoice.class)
    public static class InvoiceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InvoiceController controller = (InvoiceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "invoiceController");
            return controller.getInvoice(getKey(value));
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
            if (object instanceof Invoice) {
                Invoice o = (Invoice) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Invoice.class.getName()});
                return null;
            }
        }

    }

}
