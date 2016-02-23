/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.umlv.session;

import fr.umlv.entity.Cv;
import java.util.List;
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
    
    public Cv getCvId(int idUser) {
        Query q = em.createQuery("SELECT c FROM Cv c WHERE c.idUser.idUser = :idUser");
        q.setParameter("idUser", idUser);
        System.out.println("--------------------------------\n\n\n");
        System.out.println(q.toString());
        System.out.println("\n--------------------------------\n\n\n");
        return (Cv) q.getSingleResult();
    }
}
