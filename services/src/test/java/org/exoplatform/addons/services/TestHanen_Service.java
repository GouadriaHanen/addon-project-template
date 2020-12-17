package org.exoplatform.addons.services;

import org.junit.Assert;
import org.junit.Test;

public class TestHanen_Service {
    Hanen_Service service = new Hanen_Service();

    @Test
    public void testCall(){
     String msg = " Calling function call of Hanen_Service !";
     String outPut= service.call(msg);
     Assert.assertEquals(msg,outPut);
    }
}
