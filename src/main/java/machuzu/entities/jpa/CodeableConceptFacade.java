/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machuzu.entities.jpa;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
