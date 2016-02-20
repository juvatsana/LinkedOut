/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.session;

import fr.umlv.entity.Interest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mourougan
 */
@Stateless
public class InterestFacade extends AbstractFacade<Interest> {

    @PersistenceContext(unitName = "LinkedOutPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InterestFacade() {
        super(Interest.class);
    }
    
}
