package org.exoplatform.addons.dao;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.EntityManager;
import java.util.List;

public class JPA_dao extends GenericDAOJPAImpl<FavoriteActivityEntity,Long>{


    protected Class<FavoriteActivityEntity> modelClass;
    public JPA_dao() { }

     public String testing(){
        return getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().toString();
     }
    //AddAct adds a new FavoriteActivityEntity
    public boolean AddAct(FavoriteActivityEntity act) {
        EntityManager em= getEntityManager() ;
        em.persist(act);
        em.contains(act);
        return em.contains(act);
    }

    public List<FavoriteActivityEntity> FindAllActs() {

        return getEntityManager().createQuery("select f from FavoriteActivityEntity f").getResultList();
    }

    public FavoriteActivityEntity FindActById(Long Id) {
        return (FavoriteActivityEntity) getEntityManager().createQuery("select f from FavoriteActivityEntity f where f.ID="+Id).getSingleResult();
    }

    // @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        EntityManager em= getEntityManager() ;
        em.merge(act);
        return FindActById(act.getID());
    }

    public void deleteAct (Long id) {
        EntityManager em= getEntityManager() ;
        em.remove(FindActById(id));
    }

    public JSONObject ChangeToJSON (FavoriteActivityEntity act) throws JSONException {
        JSONObject data = new JSONObject(act.toString());
        return data ;
    }



}
