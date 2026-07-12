/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import machuzu.entities.IPA;
import machuzu.entities.Organisation;

/**
 *
 * @author Wandile
 */
@Stateless
public class IPAFacade extends AbstractFacade<IPA> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IPAFacade() {
        super(IPA.class);
    }   

    public IPA checkIPAExists(String email, String password) {

        IPA ipa = null;
        
        try {
            ipa = em.createNamedQuery("IPA.loginUser", 
                IPA.class)
                .setParameter("email", email)
               .setParameter("password", password)
                .getSingleResult();

        } catch (Exception nre) {
        System.out.println("ERROR IPA EXISTS: "+nre);
        }

        return ipa;
    } 
    
    public String getOrganisation(Long ipaa) {

        EntityGraph graph = this.em.createEntityGraph(IPA.class);
        Subgraph itemGraph = graph.addSubgraph("ipa");

        Map hints = new HashMap();
        hints.put("javax.persistence.loadgraph", graph);

        Organisation org = this.em.find(Organisation.class, ipaa, hints);

        //System.out.println("IPAX: " + ipa.getEmail());
       // Organisation org = ipa.getOrganisation();
        System.out.println("ORGx: " + org.getName());

        return org.getName();
    }
    
    public IPA searchPatient(String email) {
        
        IPA ipa = null;
        try {
            ipa = em.createNamedQuery("IPA.searchPatient", 
                IPA.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch(Exception e) {}

        return ipa;
    }


}
