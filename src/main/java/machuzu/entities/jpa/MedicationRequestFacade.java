/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import machuzu.entities.MedicationRequest;

/**
 *
 * @author Wandile
 */
@Stateless
public class MedicationRequestFacade extends AbstractFacade<MedicationRequest> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicationRequestFacade() {
        super(MedicationRequest.class);
    }

    public Long getMaxId() {

        MedicationRequest medicationRequest = null;
        
        try {
            medicationRequest = em.createNamedQuery("MedicationRequest.fetchRecords", 
                MedicationRequest.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return medicationRequest.getId();
    }
    
}
