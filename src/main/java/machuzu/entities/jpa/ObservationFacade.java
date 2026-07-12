/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Observation;

/**
 *
 * @author Wandile
 */
@Stateless
public class ObservationFacade extends AbstractFacade<Observation> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservationFacade() {
        super(Observation.class);
    }
    
}
