/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import machuzu.entities.CodeableConcept;

/**
 *
 * @author Wandile
 */
@Stateless
public class CodeableConceptFacade extends AbstractFacade<CodeableConcept> {

    @PersistenceContext(unitName = "IPA")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CodeableConceptFacade() {
        super(CodeableConcept.class);
    }
    
}
