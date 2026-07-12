package machuzu.converters;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import machuzu.entities.Cities;
import machuzu.entities.jpa.CitiesFacade;

@SessionScoped
@Named
public class CitiesBean implements Serializable {

    @EJB
    private machuzu.entities.jpa.CitiesFacade ejbFacade;
 
    private Cities selectedCity;
    private List<Cities> cities;    
        
    public CitiesBean() {
    }

    private CitiesFacade getFacade() {
        return ejbFacade;
    }

    public Cities getCity(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public Cities getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(Cities selectedCity) {
        this.selectedCity = selectedCity;
    }

    public List<Cities> getCities() {
        return getFacade().findAll();
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }
    
    public Cities getCountry(java.lang.Long id) {
        return getFacade().find(id);
    }
    
    public Cities getCities(Integer id) {
        if (id == null){
            throw new IllegalArgumentException("no id provided");
        }
        for (Cities cities : cities){
            if (id.equals(cities.getId())){
                return cities;
            }
        }
        return null;
    }
    
    
}