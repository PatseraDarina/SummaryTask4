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
    protected Properties props;
    protected final String READ_SQL;
    protected final String DELETE_SQL;
    protected final String CREATE_SQL;
    protected final String UPDATE_SQL;

    private static final String RESOURCES_DB_PROPERTIES = "db.properties";

    {
        props = new Properties();
        try(InputStream stream = this.getClass().getClassLoader().getResourceAsStream(RESOURCES_DB_PROPERTIES)) {
            props.load(stream);
            READ_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".read");
            DELETE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".delete");
            CREATE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".create");
            UPDATE_SQL = props.getProperty("sql." + this.getClass().getSimpleName().toLowerCase() + ".update");
        } catch (IOException e) {
            throw new GenericDaoException("Error loading SQL definitions", e);
        }
    }

    @Override
    public String getReadSql() { return READ_SQL; }

    @Override
    public String getCreateSql() { return CREATE_SQL; }

    @Override
    public String getDeleteSql() { return DELETE_SQL; }

    @Override
    public String getUpdateSql() { return UPDATE_SQL; }

    @Override
    public PK getId() { return id; }

    @Override
    public void setId(PK id) { this.id = id; }

    @Override
    public Properties getProperties() {
        return props;
    }
}
