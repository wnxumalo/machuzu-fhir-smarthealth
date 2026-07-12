/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Timing;
import machuzu.entities.controller.AbstractFacade;

/**
 *
 * @author Wandile
 */
@Stateless
public class TimingFacade extends AbstractFacade<Timing> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TimingFacade() {
        super(Timing.class);
    }
    
}
