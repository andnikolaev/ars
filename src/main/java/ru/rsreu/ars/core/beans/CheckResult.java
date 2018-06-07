package ru.rsreu.ars.core.beans;

public class CheckResult {
    private String result;

    public CheckResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return result;
    }
}
