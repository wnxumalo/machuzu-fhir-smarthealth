package machuzu.entities.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import machuzu.entities.CPTCSMProcedures;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.CPTCSMProceduresFacade;

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

@Named("cPTCSMProceduresController") 
@SessionScoped
public class CPTCSMProceduresController implements Serializable {

    @EJB
    private machuzu.entities.jpa.CPTCSMProceduresFacade ejbFacade;
    private List<CPTCSMProcedures> items = null;
    private CPTCSMProcedures selected;

    public CPTCSMProceduresController() {
    }

    public CPTCSMProcedures getSelected() {
        return selected;
    }

    public void setSelected(CPTCSMProcedures selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CPTCSMProceduresFacade getFacade() {
        return ejbFacade;
    }

    public CPTCSMProcedures prepareCreate() {
        selected = new CPTCSMProcedures();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void moveProcedures() throws FileNotFoundException, IOException {
        
        FileReader fr = new FileReader("F:\\Wandile\\Wandile\\MaestroIT\\Health\\Project\\cpt-pcm-nhsn.csv");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            
            String[] procedure = line.split(";");
            
            selected.setCptCode(procedure[0]);
            selected.setProcedureCodeCategory(procedure[1]);
            selected.setProcedureCodeDescription(procedure[2]);  
            
            create();
            
            line = br.readLine();
        }        
        
    }


    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CPTCSMProceduresCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CPTCSMProceduresUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CPTCSMProceduresDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CPTCSMProcedures> getItems() {
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

    public CPTCSMProcedures getCPTCSMProcedures(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<CPTCSMProcedures> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CPTCSMProcedures> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CPTCSMProcedures.class)
    public static class CPTCSMProceduresControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CPTCSMProceduresController controller = (CPTCSMProceduresController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cPTCSMProceduresController");
            return controller.getCPTCSMProcedures(getKey(value));
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
            if (object instanceof CPTCSMProcedures) {
                CPTCSMProcedures o = (CPTCSMProcedures) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CPTCSMProcedures.class.getName()});
                return null;
            }
        }

    }

}
