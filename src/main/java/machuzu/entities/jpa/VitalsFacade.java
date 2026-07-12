/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.Vitals;

/**
 *
 * @author Wandile
 */
@Stateless
public class VitalsFacade extends AbstractFacade<Vitals> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VitalsFacade() {
        super(Vitals.class);
    }

    public List<Vitals> getVitals(Long ipa) {

        List<Vitals> vitals = null;

        try {
            vitals = em.createNamedQuery("Vitals.getDashboard",
                    Vitals.class)
                    .setParameter("ipa", ipa)
                    .getResultList().subList(1, 5);

        } catch (Exception nre) {
            System.out.println("ERROR: " + nre);
            try {
                vitals = em.createNamedQuery("Vitals.getDashboard",
                        Vitals.class)
                        .setParameter("ipa", ipa)
                        .getResultList();
            } catch (Exception nre1) {            
                System.out.println("ERROR: " + nre1);
            }
        }

        return vitals;
    }

}
