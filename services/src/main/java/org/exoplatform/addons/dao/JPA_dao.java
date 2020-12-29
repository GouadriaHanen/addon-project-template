package org.exoplatform.addons.dao;

import exo.rest.services.RestFavActService;
import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JPA_dao extends GenericDAOJPAImpl<FavoriteActivityEntity,Long>{

    private static final Log LOG = ExoLogger.getExoLogger(RestFavActService.class);
    public JPA_dao() {
    }

    //AddAct adds a new FavoriteActivityEntity
    public void AddAct(FavoriteActivityEntity act) {
        LOG.info("adding "+act.getActivityTitle());
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.persist(act);
        em.getTransaction().commit();
    }

    public List<FavoriteActivityEntity> FindAllActs() {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        LOG.info("EM propbreties :" +em.getProperties());
        Query q = em.createQuery("select f from FavoriteActivityEntity f  ");
        LOG.info("query :"+q.toString());
        List<FavoriteActivityEntity> l = q.getResultList();
        LOG.info("result list : "+q.getResultList());
        em.getTransaction().commit();
        return l;
    }

    public FavoriteActivityEntity FindActById(Long Id) {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        Query q = em.createQuery("select f from FavoriteActivityEntity f where f.ID= :idd");
        q.setParameter("idd", Id).getSingleResult();
        FavoriteActivityEntity res = (FavoriteActivityEntity) q.getSingleResult();
        em.getTransaction().commit();
        return res;
    }

    // @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        LOG.info("act ID="+act.getID());
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.merge(act);
        em.getTransaction().commit();
        FavoriteActivityEntity res = FindActById(act.getID());
        return res;
    }

    public void deleteAct (Long id) {
        FavoriteActivityEntity act = FindActById(id);
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.remove(act);
        em.getTransaction().commit();
    }
    public void deleteAll () {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.createQuery("delete from FavoriteActivityEntity ").executeUpdate();
        em.getTransaction().commit();
    }


}
