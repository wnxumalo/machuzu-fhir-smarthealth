package machuzu.entities.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import machuzu.entities.Diagnosis;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.DiagnosisFacade;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@Named("diagnosisController")
@SessionScoped
public class DiagnosisController implements Serializable {

    @EJB
    private machuzu.entities.jpa.DiagnosisFacade ejbFacade;
    private List<Diagnosis> items = null;
    private Diagnosis selected;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public DiagnosisController() {
    }

    public Diagnosis getSelected() {
        return selected;
    }

    public void setSelected(Diagnosis selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DiagnosisFacade getFacade() {
        return ejbFacade;
    }

    public Diagnosis prepareCreate() {
        selected = new Diagnosis();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE,"Diagnosis Created  Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Diagnosis Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Diagnosis Deleted Successfully");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Diagnosis> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public void moveICD10() throws FileNotFoundException, IOException, ParseException {
        
        FileReader fr = new FileReader("F:\\Wandile\\Wandile\\MaestroIT\\Health\\Project\\ICD-10_MIT_2021_Excel_16-March_2021-2.csv");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            
            String[] procedure = line.split(";");
            
            selected.setDiagID(procedure[0]);
            selected.setChapterNo(procedure[1]);
            selected.setChapterDesc(procedure[2]);  
            selected.setGroupCode(procedure[3]);  
            selected.setGroupDescription(procedure[4]);  
            selected.setICD3Code(procedure[5]);  
            selected.setICD3CodeDesc(procedure[6]);  
            selected.setICD10Code(procedure[7]);  
            selected.setWHOFULLDesc(procedure[8]);  
            selected.setValidICD10ClinicalUse(procedure[9]);  
            selected.setValidICD10Primary(procedure[10]);  
            selected.setAsterisk(procedure[11]);  
            selected.setDagger(procedure[12]);  
            selected.setSequelae(procedure[13]);  
            selected.setAgeRange(procedure[16]);  
            selected.setGender(procedure[17]);  
            selected.setStatusADM(procedure[20]);  
            try {
                selected.setStartDate(sdf.parse(procedure[17]));  
                selected.setEndDate(sdf.parse(procedure[18]));  
            } catch (Exception e) {}
            selected.setComment(procedure[19]);  

            create();
            
            line = br.readLine();
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

    public Diagnosis getDiagnosis(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Diagnosis> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Diagnosis> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Diagnosis.class)
    public static class DiagnosisControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DiagnosisController controller = (DiagnosisController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "diagnosisController");
            return controller.getDiagnosis(getKey(value));
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
            if (object instanceof Diagnosis) {
                Diagnosis o = (Diagnosis) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Diagnosis.class.getName()});
                return null;
            }
        }

    }

}
