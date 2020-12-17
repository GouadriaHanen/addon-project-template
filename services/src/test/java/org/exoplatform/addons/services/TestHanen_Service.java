package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.TestCases;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHanen_Service {
    Hanen_Service service = new Hanen_Service();
    String msg = " Calling function call of Hanen_Service !";
    String emptyString ;

    public List<TestCases> TestData () {
        TestCases validTestCaseA = new TestCases("valid",msg,msg);
        TestCases validTestCaseB = new TestCases("valid","","Sorry ! empty msg");
        List <TestCases> testData = new ArrayList<>() ;
        testData.add(validTestCaseA);
        testData.add(validTestCaseB);
        return testData ;
    }
    @Test
    public void testCall(){
        List <TestCases> testData  = TestData() ;
        for( int i=0 ; i<testData.size();i++){
            String outPut= service.call(testData.get(i).getInput());
            Assert.assertEquals(testData.get(i).getName(),testData.get(i).getExpectedOutput(),outPut);
        }
    }
}
