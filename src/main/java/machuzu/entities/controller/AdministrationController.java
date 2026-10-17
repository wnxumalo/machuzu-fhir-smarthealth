package machuzu.entities.controller;

import machuzu.entities.Administration;
import machuzu.entities.jpa.util.JsfUtil;
import machuzu.entities.jpa.util.JsfUtil.PersistAction;

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
import machuzu.entities.jpa.AdministrationFacade;

@Named("administrationController") 
@SessionScoped
public class AdministrationController implements Serializable {

    @EJB
    private machuzu.entities.jpa.AdministrationFacade ejbFacade;
    private List<Administration> items = null;
    private Administration selected;

    public AdministrationController() {
        prepareCreate();
    }

    public Administration getSelected() {
        return selected;
    }

    public void setSelected(Administration selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdministrationFacade getFacade() {
        return ejbFacade;
    }

    public Administration prepareCreate() {
        selected = new Administration();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdministrationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdministrationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdministrationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Administration> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public String prepareLogin() {
        selected = new Administration();

        initializeEmbeddableKey();
        return "/administration/AdminLogin";
    }    
    
    public String login(HttpSession session) {
        
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext context = FacesContext.getCurrentInstance();
        
        
        //session.removeAttribute("identifierController");


        this.selected = getFacade().login(this.selected.getEmail(), this.selected.getPassword());

        try {
            if (!selected.getStatus()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Not Successful", "This account is currently inactive."));

                return "/administration/Login";
            } else if ((selected != null) && (selected.getStatus()) && (selected.getEmpType().equals("Doctor"))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Staff Member Logged in successfully."));

                //Hibernate.initialize(selected.getOrganisation());

                return "/iPA/UserHome1";
            } else if ((selected != null) && (selected.getStatus()) && (selected.getEmpType().equals("Staff"))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Staff Member Logged in successfully."));

                //Hibernate.initialize(selected.getOrganisation());

                return "/iPA/StaffHome";
            } else if ((selected != null) && (selected.getStatus()) && (selected.getEmpType().equals("Administrator"))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Administrator Logged in successfully."));

                //Hibernate.initialize(selected.getOrganisation());

                return "/administration/AdminHome";
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "User Does Not Exist."));

                return "/administration/Login";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "User Does Not Exist."));

            return "/administration/Login";
        }
        
    }    
    
    public String Logout(HttpSession session) {
     
        Enumeration<String> attributes = session.getAttributeNames();
        
        try {
            while (attributes.hasMoreElements()) {
                System.out.println("LOGOUT: " + attributes.nextElement());
                session.removeAttribute(attributes.nextElement());

                attributes.asIterator().next();
            }

            //session.removeAttribute("administrationController");
        } catch (Exception e) {
            
        }    
        
        this.selected = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Log Off Successful", "Staff Member Logged off successfully."));
        
        return "/index";
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

    public Administration getAdministration(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Administration> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Administration> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }       

    @FacesConverter(forClass = Administration.class)
    public static class AdministrationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdministrationController controller = (AdministrationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "administrationController");
            return controller.getAdministration(getKey(value));
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
            if (object instanceof Administration) {
                Administration o = (Administration) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Administration.class.getName()});
                return null;
            }
        }

    }

}
