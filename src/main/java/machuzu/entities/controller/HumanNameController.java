package machuzu.entities.controller;

import machuzu.entities.HumanName;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.HumanNameFacade;

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

@Named("humanNameController")
@SessionScoped
public class HumanNameController implements Serializable {

    @EJB
    private machuzu.entities.jpa.HumanNameFacade ejbFacade;
    private List<HumanName> items = null;
    private HumanName selected;

    public HumanNameController() {
    }

    public HumanName getSelected() {
        return selected;
    }

    public void setSelected(HumanName selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HumanNameFacade getFacade() {
        return ejbFacade;
    }

    public HumanName prepareCreate() {
        selected = new HumanName();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long ipa) {
        selected.setIPA(ipa);
//        selected.setContact(contact);
        persist(PersistAction.CREATE, "Name Created Succesfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Name Updated Succesfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Name Deleted Succesfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<HumanName> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void refresh() {
        selected = null;
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

    public HumanName getHumanName(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<HumanName> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HumanName> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = HumanName.class)
    public static class HumanNameControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HumanNameController controller = (HumanNameController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "humanNameController");
            return controller.getHumanName(getKey(value));
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
            if (object instanceof HumanName) {
                HumanName o = (HumanName) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HumanName.class.getName()});
                return null;
            }
        }

    }

}
