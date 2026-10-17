/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import machuzu.entities.Identifier;

/**
 *
 * @author Wandile
 */
@Stateless
public class IdentifierFacade extends AbstractFacade<Identifier> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdentifierFacade() {
        super(Identifier.class);
    }

    public Identifier searchPatient(String id) {
        
        Identifier identifier = em.createNamedQuery("Identifier.searchPatient", 
            Identifier.class)
            .setParameter("identifier", id)
            .getSingleResult();

        return identifier;
    }

}
