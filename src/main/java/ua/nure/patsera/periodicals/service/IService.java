package ua.nure.patsera.periodicals.service;

import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;
import ua.nure.patsera.periodicals.model.BaseEntity;

/**
 * Created by Дарина on 12.09.2017.
 */
public interface IService<T extends BaseEntity> {
    void add(T entity) throws TransactionInterruptedException;
    void update(T entity) throws TransactionInterruptedException;
    void delete(int id) throws TransactionInterruptedException;
    boolean contains(String name) throws TransactionInterruptedException;
}
