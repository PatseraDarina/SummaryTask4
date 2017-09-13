package ua.nure.patsera.periodicals.bean;

import com.sun.corba.se.spi.activation.BadServerDefinition;
import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Category extends BaseEntity<Integer> implements Serializable {
    private String name;

    public Category() {
    }

    public Category(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
