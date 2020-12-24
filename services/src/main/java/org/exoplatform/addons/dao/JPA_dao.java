package org.exoplatform.addons.dao;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;

import javax.persistence.EntityManager;
import java.util.List;

public class JPA_dao extends GenericDAOJPAImpl<FavoriteActivityEntity,Long>{

    public JPA_dao() {
        super();
    }

    //AddAct adds a new FavoriteActivityEntity
    public void AddAct(FavoriteActivityEntity act) {
          create(act);
    }

    public List<FavoriteActivityEntity> FindAllActs() {
        List<FavoriteActivityEntity> l = findAll();
        return  l;
    }

    public FavoriteActivityEntity FindActById(Long Id) {
        return (FavoriteActivityEntity) find(Id);
    }

   // @ExoTransactional
    public FavoriteActivityEntity Update(FavoriteActivityEntity act) {
        return (FavoriteActivityEntity) update(act);
    }

    public Object deleteAct (Long Id) {
        delete(find(Id));
       return find(Id);
    }

}
