package machuzu.entities.controller;

import machuzu.entities.IPA;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.IPAFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javax.servlet.http.HttpSession;
import machuzu.entities.HumanName;
import machuzu.entities.Vitals;
import machuzu.entities.jpa.OrganisationFacade;
import org.primefaces.PrimeFaces;

@Named("iPAController")
@SessionScoped
public class IPAController implements Serializable {

    @EJB
    private machuzu.entities.jpa.IPAFacade ejbFacade;
    
    @EJB
    private machuzu.entities.jpa.OrganisationFacade orgFacade;
    
    private List<IPA> items = null;

    private IPA selected;

    private Date currDate;

    private List<HumanName> humanNames = new ArrayList<HumanName>();
    
    private String findPatient;

    public IPAController() {
    }

    public IPA getSelected() {
        return selected;
    }

    public void setSelected(IPA selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IPAFacade getFacade() {
        return ejbFacade;
    }
    
    private OrganisationFacade getOrgFacade() {
        return orgFacade;
    }
    
    public void searchPatient() {        
        
        try {
            
            selected = getFacade().searchPatient(findPatient);       
            
            //PrimeFaces current = PrimeFaces.current();
            //current.executeScript("PF('IPAViewDialog').show();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Found", "Profile Not Found."));
        }
    }
    
    public void searchPatientEmail(String email) {        
        
        try {
            
            selected = getFacade().searchPatient(email);       
            
            //PrimeFaces current = PrimeFaces.current();
            //current.executeScript("PF('IPAViewDialog').show();");

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Found", "Profile Not Found."));
        }
    }

    public void setFindPatient(String findPatient) {
        this.findPatient = findPatient;
    }
    
    public String getFindPatient() {
        return findPatient;
    }

    public IPA prepareCreate() {
        selected = new IPA();

        String uniqueID = UUID.randomUUID().toString();

        selected.setUUID(uniqueID);

        initializeEmbeddableKey();
        return selected;
    }
    
    public String prepareLogin() {
        selected = new IPA();

        initializeEmbeddableKey();
        return "/iPA/Login";
    }
    
    public String newUser(HttpSession session) {        
        session.setAttribute("iPA", null);
        selected = new IPA();
        
        return "/iPA/NewUser";
    }

    public void create() {
        persist(PersistAction.CREATE, "IPA Identity Saved Successfully");

        //PrimeFaces.current().executeScript("PF('IPACreateDialog').show()");
        //PrimeFaces.current().executeScript("HumanNameCreateDlg.show();");

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IPAUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IPADeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<IPA> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<HumanName> getHumanNames() {
        return humanNames;
    }

    public void setHumanNames(List<HumanName> humanNames) {
        this.humanNames = humanNames;
    }
    
    public void Logout(HttpSession session) {             
        this.selected = null;
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

    public IPA getIPA(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<IPA> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<IPA> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void refresh(Long id) {
        selected = getFacade().find(id);
    }

    public String login(HttpSession session) {
System.out.println("EEEEE: " + this.selected.getEmail() + ":" + this.selected.getPassword());
        this.selected = getFacade().checkIPAExists(this.selected.getEmail(), this.selected.getPassword());

        try {
            if ((selected != null) && (!selected.getActive())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Not Successful", "This account is currently inactive."));

                return "/iPA/Login";
            } else if ((selected != null) && (selected.getActive())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful", "Logged in successfully."));

                //Hibernate.initialize(selected.getOrganisation());

                return "/iPA/UserHome";
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "User Does Not Exist."));

                return "/iPA/Login";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "User Does Not Exist."));

            return "/iPA/Login";
        }
        
    }
    
    public Date getCurrentDate() {
        return this.currDate;
    }

    public void setCurrentDate() {
        this.currDate = new java.util.Date();
    }
    
    @FacesConverter(forClass = IPA.class)
    public static class IPAControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IPAController controller = (IPAController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "iPAController");
            return controller.getIPA(getKey(value));
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
            if (object instanceof IPA) {
                IPA o = (IPA) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), IPA.class.getName()});
                return null;
            }
        }

    }

}
