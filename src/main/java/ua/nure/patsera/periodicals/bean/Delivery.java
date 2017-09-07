package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Delivery extends BaseEntity<Integer> implements Serializable {
    private int idReader;
    private int idSubscription;
    private Date deliveryDate;

    public Delivery() {
    }

    public Delivery(int idReader, int idSubscription, Date deliveryDate) {

        this.idReader = idReader;
        this.idSubscription = idSubscription;
        this.deliveryDate = deliveryDate;
    }

    public int getIdReader() {

        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public int getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(int idSubscription) {
        this.idSubscription = idSubscription;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
