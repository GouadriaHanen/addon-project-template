package org.exoplatform.addons.dao;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class JPA_dao extends GenericDAOJPAImpl{

    public JPA_dao() {
        super();
    }

    //AddAct adds a new FavoriteActivityEntity
    public FavoriteActivityEntity AddAct(FavoriteActivityEntity act) {
         return (FavoriteActivityEntity) create(act);
    }

    public List<FavoriteActivityEntity> FindAllActs() {
        return  findAll();
    }

    public FavoriteActivityEntity FindActById(Long Id) {
        return (FavoriteActivityEntity) find(Id);
    }

   // @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        return (FavoriteActivityEntity) update(act);
    }

    public Object deleteAct (FavoriteActivityEntity act) {
      delete(act);
       return find(act.getID());
    }

}
