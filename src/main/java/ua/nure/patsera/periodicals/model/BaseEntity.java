package ua.nure.patsera.periodicals.model;

import ua.nure.patsera.periodicals.exceptions.GenericDaoException;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by Daryna Patsera on 03.09.2017.
 */
public abstract  class BaseEntity <PK> implements IBaseEntity<PK> {
    protected PK id;

    @Override
    public PK getId() { return id; }

    @Override
    public void setId(PK id) { this.id = id; }

}
