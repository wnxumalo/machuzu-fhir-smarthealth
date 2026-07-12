/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Medication;

/**
 *
 * @author Wandile
 */
@Stateless
public class MedicationFacade extends AbstractFacade<Medication> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicationFacade() {
        super(Medication.class);
    }
    
    public Long getMaxId() {

        Medication medication = null;
        
        try {
            medication = em.createNamedQuery("Medication.fetchRecords", 
                Medication.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return medication.getId();
    }
    
}
