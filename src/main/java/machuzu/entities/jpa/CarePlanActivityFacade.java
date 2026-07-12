/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.CarePlanActivity;

/**
 *
 * @author Wandile
 */
@Stateless
public class CarePlanActivityFacade extends AbstractFacade<CarePlanActivity> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarePlanActivityFacade() {
        super(CarePlanActivity.class);
    }
    
}
