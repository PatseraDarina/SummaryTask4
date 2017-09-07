package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Street extends BaseEntity<Integer> implements Serializable {
    private String name;
    private int idDistrict;

    public Street() {

    }

    public Street(String name, int idDistrict) {
        this.name = name;
        this.idDistrict = idDistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }
}
