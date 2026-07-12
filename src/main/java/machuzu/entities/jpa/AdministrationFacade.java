/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Administration;
import machuzu.entities.IPA;
import machuzu.entities.controller.AbstractFacade;

/**
 *
 * @author Wandile
 */
@Stateless
public class AdministrationFacade extends AbstractFacade<Administration> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrationFacade() {
        super(Administration.class);
    }     
    
    public Administration login(String email, String password) {
        
        Administration admin = em.createNamedQuery("Administration.login", 
            Administration.class)
            .setParameter("email", email)
           .setParameter("password", password)
            .getSingleResult();

        return admin;
    }

    
}
