package machuzu.entities.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import machuzu.entities.DocumentReference;
import machuzu.entities.controller.util.JsfUtil;
import machuzu.entities.controller.util.JsfUtil.PersistAction;
import machuzu.entities.jpa.DocumentReferenceFacade;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;

@Named("documentReferenceController")
@SessionScoped
public class DocumentReferenceController implements Serializable {

    @EJB
    private machuzu.entities.jpa.DocumentReferenceFacade ejbFacade;
    private List<DocumentReference> items = null;
    private DocumentReference selected;
    
    SimpleDateFormat stf1 = new SimpleDateFormat("hh:mm:ss");
    
    String newstr;
    int endIndex;
    HttpServletRequest request;
    String url;
    String uri;

    public DocumentReferenceController() {
    }
    
    public String listDocuments(boolean encounter) {
        
        if (encounter == false)              
            return "/documentReference/List";
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Cannot Proceed", "Select an Identifier First."));
            
            return null;
        }            
    }

    public DocumentReference getSelected() {
        return selected;
    }

    public void setSelected(DocumentReference selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DocumentReferenceFacade getFacade() {
        return ejbFacade;
    }

    public DocumentReference prepareCreate() {
        selected = new DocumentReference();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void uploadFile(String encounter) {
        selected.setEncounter(Long.parseLong(encounter));

         try {
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('DocumentReferenceUploadDialog').show();");

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }

    }

    public void handleFileUpload(FileUploadEvent event) throws IOException, SQLException {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        copyFile(event.getFile().getFileName(), event.getFile().getInputStream(), selected.getEncounter());

    }

    public void copyFile(String fileName, InputStream in, Long encounter) throws FileNotFoundException, IOException {

        
            String directoryName = new File("../docroot").getAbsolutePath() + "/ipa/";
            
            int endIndex = fileName.lastIndexOf(".");
            String fileType = "";
            
            for (int x=endIndex;x<fileName.length();x++) {
                fileType += fileName.charAt(x);
            }

            String fName = "";
            for (int x=0;x<fileName.length();x++) {
                if (fileName.charAt(x) == '.') {
                    x=fileName.length();                    
                } else {
                    fName += fileName.charAt(x);                    
                }
            }

            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            } 
            
            String fTime = stf1.format(new Date()).replace(":", "");            

            //Path fullPath = Paths.get(new File("/opt").getAbsolutePath() + "/wildfly/docroot/FHIR/" + fName + "_" + fileType);
            //Path fullPath = Paths.get(new File("/Wandile").getAbsolutePath() + "/Wandile/Java 14/wildfly-24.0.1.Final/wildfly-24.0.1.Final/docroot/MaestroIT/files/" + fName + "_" + fTime + fileType);
            //Path fullPath = Paths.get(new File("/Wandile").getAbsolutePath() + "/wildfly/docroot/FHIR/uploads/" + fName + "_" + fTime + fileType);                       

            /*try {
                Files.createFile(fullPath);
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }*/

            // write the inputStream to a FileOutputStream
            //OutputStream out = new FileOutputStream(new File("/opt").getAbsolutePath() + "/wildfly/docroot/FHIR/" + fName + "_" + fileType);
            OutputStream out = new FileOutputStream(new File("/Wandile").getAbsolutePath() + "/Wandile/Java 14/wildfly-24.0.1.Final/wildfly-24.0.1.Final/docroot/ipa/uploads/" + fName + fileType);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();                               
            
            try {
                create(selected.getEncounter(), fName, fileType);
            } catch(Exception e) {
                System.out.println("ERR: " + e);
            }        

       
    }

    public void create(Long encounter, String fName, String fType) {        
        //try {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            url = request.getRequestURL().toString();
            endIndex = url.lastIndexOf("faces");
            if (endIndex != -1) {
                newstr = url.substring(0, endIndex);
            }        
            
            selected.setEncounter(encounter);
            selected.setContentFormat(fType);
            selected.setContentAttachment(newstr + "uploadedfhir/uploads/" + fName + fType);                       
            

        //} catch(Exception e) {}
        persist(PersistAction.CREATE, "Document Reference Created Successfully");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        
    }

    public void update() {
        persist(PersistAction.UPDATE, "Document Reference Updated Successfully");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Document Reference Deleted");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DocumentReference> getItems() {
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

    public DocumentReference getDocumentReference(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<DocumentReference> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DocumentReference> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DocumentReference.class)
    public static class DocumentReferenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DocumentReferenceController controller = (DocumentReferenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "documentReferenceController");
            return controller.getDocumentReference(getKey(value));
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
            if (object instanceof DocumentReference) {
                DocumentReference o = (DocumentReference) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DocumentReference.class.getName()});
                return null;
            }
        }

    }

}
