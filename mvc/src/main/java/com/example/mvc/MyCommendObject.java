package com.example.mvc;

public class MyCommendObject {
    private String value1;
    private String value2;

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "MyCommendObject = {" + this.value1 + ", " + this.value2 + "}";
    }
}
