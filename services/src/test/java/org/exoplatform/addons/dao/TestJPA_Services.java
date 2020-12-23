package org.exoplatform.addons.dao;

import org.exoplatform.addons.entity.FavoriteActivityEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;
import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;

public class TestJPA_Services {

    public TestJPA_Services() {}

    @Test
    public void TestAddAct() {
        JPA_dao service = new JPA_dao();
        Calendar c = Calendar.getInstance();
        FavoriteActivityEntity act = new FavoriteActivityEntity(1L,"act1",new ActivityEntity(), c);
        FavoriteActivityEntity outPut =service.AddAct(act);
        Assert.assertNotNull(outPut);
    }
}
