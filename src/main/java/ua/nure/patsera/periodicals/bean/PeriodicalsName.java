package ua.nure.patsera.periodicals.bean;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class PeriodicalsName extends BaseEntity<Integer> implements Serializable {
    private String name;
    private int idTopic;
    private int idCategory;

    public PeriodicalsName() {
    }

    public PeriodicalsName(String name, int idTopic, int idCategory) {

        this.name = name;
        this.idTopic = idTopic;
        this.idCategory = idCategory;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
