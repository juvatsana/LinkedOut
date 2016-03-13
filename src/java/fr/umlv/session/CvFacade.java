/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.session;

import fr.umlv.entity.Cv;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mourougan
 */
@Stateless
public class CvFacade extends AbstractFacade<Cv> {

    @PersistenceContext(unitName = "LinkedOutPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CvFacade() {
        super(Cv.class);
    }
    
    public void refresh(Cv cv) {
        em.refresh(cv);
    }
    public Cv getCvId(int idUser) {
        Query q = em.createQuery("SELECT c FROM Cv c WHERE c.idUser.idUser = :idUser");
        q.setParameter("idUser", idUser);
        Cv cv = (Cv)q.getSingleResult();
        em.refresh(cv);
        return cv ;
    }
    
    public Collection<Cv> getAllCv() {
        Query q = em.createQuery("SELECT c FROM Cv c");
        System.out.println("getAllCv--------------------------------\n\n\n");
        System.out.println(q.toString());
        System.out.println("\n--------------------------------\n\n\n");
        System.out.println(q.getResultList());
        return (Collection<Cv>) q.getResultList();
    }

}
