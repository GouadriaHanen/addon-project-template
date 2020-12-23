package org.exoplatform.addons.dao;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class JPA_Services extends GenericDAOJPAImpl {
    EntityManager em= getEntityManager() ;
    public JPA_Services() {
       // EntityManagerFactory factory = Persistence.createEntityManagerFactory("plf_jpa");
       // this.em=factory.createEntityManager();
    }

    //AddAct adds a new FavoriteActivityEntity
    public FavoriteActivityEntity AddAct(FavoriteActivityEntity act) {
        em.getTransaction().begin();
        em.persist(act);
        em.getTransaction().commit();
         return act;
    }

    public List<FavoriteActivityEntity> FindAllActs() {
        em.getTransaction().begin();
       List <FavoriteActivityEntity> acts= em.createQuery("SELECT f From FavoriteActivityEntity f ").getResultList() ;
        em.getTransaction().commit();
        return acts;
    }

    public FavoriteActivityEntity FindActById(Long Id) {
        em.getTransaction().begin();
        FavoriteActivityEntity act= (FavoriteActivityEntity) em.createQuery("SELECT f From FavoriteActivityEntity f where f.ID = :id ").setParameter("id",Id).getSingleResult();
        em.getTransaction().commit();
      return act ;
    }

   // @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        em.getTransaction().begin();
        em.merge(act);
        em.getTransaction().commit();
        return FindActById(act.getID());
    }

    public void deleteAct (FavoriteActivityEntity act) {
        em.getTransaction().begin();
        em.remove(act);
        em.getTransaction().commit();
    }
}
