/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import machuzu.entities.MedicationAdministration;

/**
 *
 * @author Wandile
 */
@Stateless
public class MedicationAdministrationFacade extends AbstractFacade<MedicationAdministration> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicationAdministrationFacade() {
        super(MedicationAdministration.class);
    }
    
    public Long getMaxId() {

        MedicationAdministration medicationAdministration = null;
        
        try {
            medicationAdministration = em.createNamedQuery("MedicationAdministration.fetchRecords", 
                MedicationAdministration.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return medicationAdministration.getId();
    }
    
}
