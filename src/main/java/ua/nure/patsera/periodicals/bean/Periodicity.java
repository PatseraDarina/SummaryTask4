package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Periodicity extends BaseEntity<Integer> implements Serializable {
    private int idName;
    private int idType;
    private int minMonth;
    private int maxMonth;
    private double price;

    public Periodicity() {
    }

    public Periodicity(int idName, int idType, int minMonth, int maxMonth, double price) {

        this.idName = idName;
        this.idType = idType;
        this.minMonth = minMonth;
        this.maxMonth = maxMonth;
        this.price = price;
    }

    public int getIdName() {

        return idName;
    }

    public void setIdName(int idName) {
        this.idName = idName;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getMinMonth() {
        return minMonth;
    }

    public void setMinMonth(int minMonth) {
        this.minMonth = minMonth;
    }

    public int getMaxMonth() {
        return maxMonth;
    }

    public void setMaxMonth(int maxMonth) {
        this.maxMonth = maxMonth;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
