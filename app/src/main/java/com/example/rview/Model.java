package com.example.rview;

public class Model {
    private String string;

    public Model(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
