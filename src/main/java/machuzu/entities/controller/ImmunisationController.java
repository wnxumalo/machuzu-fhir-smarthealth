package machuzu.entities.controller;

import machuzu.entities.Immunisation;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.ImmunisationFacade;

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

@Named("immunisationController")
@SessionScoped
public class ImmunisationController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ImmunisationFacade ejbFacade;
    private List<Immunisation> items = null;
    private Immunisation selected;

    public ImmunisationController() {
    }
    
    public String listImmunisations(boolean encounter) {

        if (encounter == false) {
            return "/immunisation/List";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cannot Proceed", "Select an Identifier First."));

            return null;
        }
    }

    public Immunisation getSelected() {
        return selected;
    }

    public void setSelected(Immunisation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImmunisationFacade getFacade() {
        return ejbFacade;
    }

    public Immunisation prepareCreate() {
        selected = new Immunisation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long encounter) {
        selected.setEncounter(encounter);
        persist(PersistAction.CREATE, "Immunisation Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Immunisation Updated  Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "ImmunisationDeleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Immunisation> getItems() {
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

    public Immunisation getImmunisation(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Immunisation> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Immunisation> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Immunisation.class)
    public static class ImmunisationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImmunisationController controller = (ImmunisationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "immunisationController");
            return controller.getImmunisation(getKey(value));
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
            if (object instanceof Immunisation) {
                Immunisation o = (Immunisation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Immunisation.class.getName()});
                return null;
            }
        }

    }

}
