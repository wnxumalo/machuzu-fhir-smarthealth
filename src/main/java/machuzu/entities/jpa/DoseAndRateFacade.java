/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import machuzu.entities.Dosage;
import machuzu.entities.DoseAndRate;
import machuzu.entities.controller.AbstractFacade;

/**
 *
 * @author Wandile
 */
@Stateless
public class DoseAndRateFacade extends AbstractFacade<DoseAndRate> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoseAndRateFacade() {
        super(DoseAndRate.class);
    }
    
    public List<DoseAndRate> getDosageAndRate(Long id) {

        List<DoseAndRate> doseAndRate = null;
        
        try {
            doseAndRate = em.createNamedQuery("DoseAndRate.getDosageAndRate", 
                DoseAndRate.class)
                .setParameter("dosage", id)           
            .getResultList();


        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return doseAndRate;
    }
    
}
