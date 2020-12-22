package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.TestCases;

import org.junit.Assert;
import org.junit.Test;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;


public class TestHanen_Service {

    Hanen_Service  service =new Hanen_Service();
    String msg = " Calling done!";

    public TestHanen_Service() {

    }

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
        for (TestCases testDatum : testData) {
            System.out.println(testDatum.getName());
            System.out.println(testDatum.getInput());
            System.out.println(testDatum.getExpectedOutput());
            String input = testDatum.getInput();
            String outPut= service.call(input);
            System.out.println("outPut " + " service  " + service);
            Assert.assertEquals(testDatum.getExpectedOutput(),outPut);
        }
    }
}
