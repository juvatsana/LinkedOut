/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.session;

import fr.umlv.entity.Langage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mourougan
 */
@Stateless
public class LangageFacade extends AbstractFacade<Langage> {

    @PersistenceContext(unitName = "LinkedOutPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LangageFacade() {
        super(Langage.class);
    }
    
}
