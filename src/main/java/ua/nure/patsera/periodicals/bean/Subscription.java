package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.dao.BaseEntity;

import java.io.Serializable;

public class Subscription extends BaseEntity<Integer> implements Serializable {
    private int idPeriodical;
    private int idReader;

    public Subscription() {
    }

    public Subscription(int idPeriodical, int idReader) {
        this.idPeriodical = idPeriodical;
        this.idReader = idReader;
    }

    public int getIdPeriodical() {

        return idPeriodical;
    }

    public void setIdPeriodical(int idPeriodical) {
        this.idPeriodical = idPeriodical;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }
}
