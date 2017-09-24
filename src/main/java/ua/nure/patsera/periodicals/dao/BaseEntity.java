package ua.nure.patsera.periodicals.dao;

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
