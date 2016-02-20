/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.session;

import fr.umlv.entity.Experience;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mourougan
 */
@Stateless
public class ExperienceFacade extends AbstractFacade<Experience> {

    @PersistenceContext(unitName = "LinkedOutPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienceFacade() {
        super(Experience.class);
    }
    
}
