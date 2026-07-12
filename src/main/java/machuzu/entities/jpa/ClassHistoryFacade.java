/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.ClassHistory;

/**
 *
 * @author Wandile
 */
@Stateless
public class ClassHistoryFacade extends AbstractFacade<ClassHistory> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassHistoryFacade() {
        super(ClassHistory.class);
    }
    
}
