package org.exoplatform.addons.services;

import org.exoplatform.addons.entity.TestCases;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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
        for (TestCases td : testData) {
            System.out.println("msg :"+ td.getName());
            System.out.println("msg :"+td.getInput());
            System.out.println("msg :"+td.getExpectedOutput());
            String outPut= service.call(td.getInput());
            Assert.assertEquals(td.getExpectedOutput(),outPut);
        }
    }
}
