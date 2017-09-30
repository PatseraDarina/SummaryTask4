package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

public class PeriodicityType extends BaseEntity<Integer> implements Serializable {
    private String name;

    public PeriodicityType() {
    }

    public PeriodicityType(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
