package machuzu.entities.controller;

import java.io.IOException;
import machuzu.entities.Identifier;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.IdentifierFacade;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import machuzu.entities.IPA;
import machuzu.entities.ObjectReference;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

@Named("identifierController")
@SessionScoped
public class IdentifierController implements Serializable {

    @EJB
    private machuzu.entities.jpa.IdentifierFacade ejbFacade;
    private List<Identifier> items = null;
    private Identifier selected;

    private List<ObjectReference> references = null;

    public IdentifierController() {
    }

    public Identifier getSelected() {
        return selected;
    }

    public void setSelected(Identifier selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IdentifierFacade getFacade() {
        return ejbFacade;
    }

    public Identifier prepareCreate() {
        selected = new Identifier();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void refresh() {
        selected = null;
    }

    public void create(IPA ipa,HttpSession session) {        
        selected.setIPA(ipa.getId());
        persist(PersistAction.CREATE, "Identifier Created Successfully");        
        selected = null;

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IdentifierUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IdentifierDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Identifier> getItems() {
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

    public Identifier getIdentifier(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Identifier> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Identifier> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void onRowSelect(SelectEvent event) throws IOException {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Not Successful", "Selected"));
    }

    @FacesConverter(forClass = Identifier.class)
    public static class IdentifierControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdentifierController controller = (IdentifierController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "identifierController");
            return controller.getIdentifier(getKey(value));
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
            if (object instanceof Identifier) {
                Identifier o = (Identifier) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Identifier.class.getName()});
                return null;
            }
        }

    }

}
