package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class District extends BaseEntity<Integer> implements Serializable {
    private String name;
    private int idCity;

    public District() {
    }

    public District(String name, int idCity) {

        this.name = name;
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
}
