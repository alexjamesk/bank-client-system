package ru.itmo.model;

public class Credit extends Account {
    private double percent = 15;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}