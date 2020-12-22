package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

public class JPA_Services extends GenericDAOJPAImpl {
    EntityManager em= getEntityManager() ;
    public JPA_Services() {}
    public FavoriteActivityEntity AddAct(FavoriteActivityEntity act) {
         em.persist(act);
         return act;
    }

    public List<FavoriteActivityEntity> FindAllActs() {
      return    em.createQuery("SELECT * From FavoriteActivityEntity f ").getResultList() ;
    }
    public FavoriteActivityEntity FindActById(Long Id) {
      return (FavoriteActivityEntity) em.createQuery("SELECT * From FavoriteActivityEntity f where ID := id ").setParameter("id",Id).getSingleResult();
    }
    @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        em.merge(act);
        return FindActById(act.getID());
    }

    public void deleteAct (FavoriteActivityEntity act) {
        em.remove(act);
    }
}
