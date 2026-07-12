/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.MedicationStatement;
import machuzu.entities.controller.AbstractFacade;

/**
 *
 * @author Wandile
 */
@Stateless
public class MedicationStatementFacade extends AbstractFacade<MedicationStatement> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicationStatementFacade() {
        super(MedicationStatement.class);
    }
    
    public Long getMaxId() {

        MedicationStatement medicationStatement = null;
        
        try {
            medicationStatement = em.createNamedQuery("MedicationStatement.fetchRecords", 
                MedicationStatement.class)
                .setMaxResults(1)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR: "+nre);
        }

        return medicationStatement.getId();
    }
    
    
}
