/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Dosage;
import machuzu.entities.controller.AbstractFacade;

/**
 *
 * @author Wandile
 */
@Stateless
public class DosageFacade extends AbstractFacade<Dosage> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DosageFacade() {
        super(Dosage.class);
    }

    public Long getMaxId() {

        Dosage dosage = null;
        
        try {
            dosage = em.createNamedQuery("Dosage.fetchRecords", 
                Dosage.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return dosage.getId();
    }
    
    public Long getDosage() {

        Dosage dosage = null;
        
        try {
            dosage = em.createNamedQuery("Dosage.fetchRecords", 
                Dosage.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return dosage.getId();
    }
    
}
