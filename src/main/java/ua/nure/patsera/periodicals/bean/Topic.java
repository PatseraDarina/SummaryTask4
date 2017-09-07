package ua.nure.patsera.periodicals.bean;

import org.apache.log4j.xml.SAXErrorHandler;
import ua.nure.patsera.periodicals.model.BaseEntity;

import java.io.Serializable;

/**
 * Created by Дарина on 05.09.2017.
 */
public class Topic extends BaseEntity<Integer> implements Serializable {
    private String name;

    public Topic() {
    }

    public Topic(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
