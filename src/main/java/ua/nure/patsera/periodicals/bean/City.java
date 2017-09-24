package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class City extends BaseEntity<Integer> implements Serializable {
    private String name;

    public City() {

    }

    public City(final String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
