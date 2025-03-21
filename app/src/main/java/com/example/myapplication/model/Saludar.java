package com.example.myapplication.model;

public class Saludar {

    private String result;


    private Saludar(){

    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Saludar(String result) {
        this.result = result;
    }
}
