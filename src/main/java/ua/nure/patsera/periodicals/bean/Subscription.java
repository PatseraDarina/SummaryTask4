package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Subscription extends BaseEntity<Integer> implements Serializable {
    private int idName;
    private int idPeriodicity;
    private int idType;

    public Subscription() {
    }

    public Subscription(int idName, int idPeriodicity, int idType) {

        this.idName = idName;
        this.idPeriodicity = idPeriodicity;
        this.idType = idType;
    }

    public int getIdName() {

        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public int getIdPeriodicity() {
        return idPeriodicity;
    }

    public void setIdPeriodicity(int idPeriodicity) {
        this.idPeriodicity = idPeriodicity;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
}
