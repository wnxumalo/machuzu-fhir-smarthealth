package machuzu.entities.controller;

import machuzu.entities.Contact;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.ContactFacade;

import java.io.Serializable;
import java.util.ArrayList;
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
import machuzu.entities.Address;
import machuzu.entities.ContactPoint;
import machuzu.entities.HumanName;

@Named("contactController")
@SessionScoped
public class ContactController implements Serializable {

    @EJB
    private machuzu.entities.jpa.ContactFacade ejbFacade;
    private List<Contact> items = null;
    private List<Contact> viewItems = new ArrayList<Contact>();
    private Contact selected;

    public ContactController() {
    }
    
    public void checkSelected() {
        System.out.println(selected.getGender());
    }

    public Contact getSelected() {
        return selected;
    }

    public void setSelected(Contact selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContactFacade getFacade() {
        return ejbFacade;
    }

    public Contact prepareCreate() {
        selected = new Contact();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Long ipa) {
        selected.setIPA(ipa);
        persist(PersistAction.CREATE, "Contact Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
        selected = null;
    }

    public void update() {
        persist(PersistAction.UPDATE, "Contact Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Contact Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<Contact> getViewItems() {
        
       /* if (items == null) {
            items = getFacade().findAll();
        }*/

      /*  viewItems.clear();
        items.forEach(contact -> {
            if (contact.getRelationship().equals("C")) {
                viewItems.add(new Contact(contact.getId(), "Emergency Contact", contact.getGender()));
            } else if (contact.getRelationship().equals("E")) {
                viewItems.add(new Contact(contact.getId(), "Employer", contact.getGender()));
            } else if (contact.getRelationship().equals("I")) {
                viewItems.add(new Contact(contact.getId(), "Insurance Company", contact.getGender()));
            } else if (contact.getRelationship().equals("F")) {
                viewItems.add(new Contact(contact.getId(), "Federal Agency", contact.getGender()));
            } else if (contact.getRelationship().equals("N")) {
                viewItems.add(new Contact(contact.getId(), "Next-of-Kin", contact.getGender()));
            } else if (contact.getRelationship().equals("S")) {
                viewItems.add(new Contact(contact.getId(), "State Agency", contact.getGender()));
            } else if (contact.getRelationship().equals("U")) {
                viewItems.add(new Contact(contact.getId(), "Unknown", contact.getGender()));
            }
        });*/
        
        return items;
    }
    
    public void setViewItems(List<Contact> viewItems) {
        this.viewItems = viewItems;
    }

    public List<Contact> getItems() {
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

    public Contact getContact(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Contact> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contact> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = Contact.class)
    public static class ContactControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactController controller = (ContactController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contactController");
            return controller.getContact(getKey(value));
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
            if (object instanceof Contact) {
                Contact o = (Contact) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contact.class.getName()});
                return null;
            }
        }

    }

}
