package ua.nure.patsera.periodicals.dao;

import ua.nure.patsera.periodicals.model.BaseEntity;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Provides an interface of CRUD operations
 * for interaction with the database.
 *
 * @author Daryna Patsera
 *
 * @param <T>   entity that represents a specific table
 *              from database. Will be used for CRUD operations.
 */
public interface GenericDao<T extends BaseEntity, PK>  {

    /**
     * Persist the {@code objectToCreate} into database
     *
     * @param objectToCreate    will be add into database
     *
     * @return id that will be generate for {@code objectToCreate}
     */
    PK create(Connection connection, T objectToCreate) throws SQLException;

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key.
     *
     * @param id  used for retrieving a specific data from database
     *            by it indicator.
     *
     * @return    object that consist of specific data that was requested
     *            from database.
     */
    T read(Connection connection, PK id) throws SQLException;

    /**
     * Save changes made to a persistent {@code objectToUpdate}.
     *
     * @param connection     uses for setting connection with database
     * @param objectToUpdate consists of new changed data that
     *                       will be stored into database
     */
    void update(Connection connection, T objectToUpdate) throws SQLException;

    /**
     * Remove an object from persistent storage in the database
     *
     * @param id key on which occurs deleting
     */
    void delete(Connection connection, PK id) throws SQLException;
}
