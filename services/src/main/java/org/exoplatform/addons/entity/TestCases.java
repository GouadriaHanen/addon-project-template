package org.exoplatform.addons.entity;

public class TestCases {
    private String name ;
    private  String input ;
    private  String ExpectedOutput ;

    public TestCases(String name, String input, String ExpectedOutput ) {
        this.name = name;
        this.input = input;
        this.ExpectedOutput  = ExpectedOutput ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput () {
        return ExpectedOutput ;
    }

    public void setOutput(String ExpectedOutput ) {
        this.ExpectedOutput = ExpectedOutput ;
    }
}
