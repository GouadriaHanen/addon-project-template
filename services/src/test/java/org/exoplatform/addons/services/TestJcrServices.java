package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.FavoriteAcitvity;
import org.junit.Assert;
import org.junit.Test;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJcrServices {
    JcrServices service = new JcrServices();
    int AddedActsCount ;
    public TestJcrServices() throws RepositoryException {}

    @Test
    public  void TestAddActivity() throws RepositoryException {
        FavoriteAcitvity act = new FavoriteAcitvity("FirstAct","/link", new Date().toString()) ;
        boolean outPut=service.AddActivity(act);
        Assert.assertTrue(outPut);
    }
  @Test
    public  void TestFindAll() throws RepositoryException {
      List<FavoriteAcitvity> acts = new ArrayList<>();
      acts = service.FindAll() ;
      Assert.assertEquals(AddedActsCount,acts.size());
    }

    @Test
    public  void TestARemoveActivity() throws RepositoryException {
        FavoriteAcitvity act = new FavoriteAcitvity("FirstAct","/link", new Date().toString()) ;
        boolean outPut=service.RemoveActivity(act);
        Assert.assertTrue(outPut);
    }


}
