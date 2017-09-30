package ua.nure.patsera.periodicals.service;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IPeriodicalsDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Дарина on 16.09.2017.
 */
public class PeriodicalService {
    private final TransactionManager transactionManager;
    private final IPeriodicalsDao periodicalDao;
    private final CategoryService categoryService;

    public PeriodicalService(TransactionManager transactionManager, IPeriodicalsDao periodicalDao, CategoryService categoryService) {
        this.transactionManager = transactionManager;
        this.periodicalDao = periodicalDao;
        this.categoryService = categoryService;
    }

    public void add(Periodical periodical) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection -> {
            periodicalDao.create(connection, periodical);
            return null;
        });
    }

    public void delete(int id) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>)connection -> {
            periodicalDao.delete(connection, id);
            return null;
        });
    }

    public void update(Periodical periodical) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection -> {
             periodicalDao.update(connection, periodical);
             return null;
        });
    }

    public Periodical getPeriodicById(int id) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        periodicalDao.read(connection, id));
    }

    public void addPeriodicals(PeriodicalDto periodicalDto) throws TransactionInterruptedException {
        add(getPeriodical(periodicalDto));
    }

    public void updatePeriodicals(PeriodicalDto periodicalDto) throws TransactionInterruptedException {
        update(getPeriodical(periodicalDto));
    }

    private Periodical getPeriodical(PeriodicalDto periodicalDto) {
        Periodical periodical = new Periodical();
        periodical.setIdCategory(categoryService.getCategory(periodicalDto.getCategory()).getId());
        periodical.setPhoto(periodicalDto.getPhoto());
        periodical.setPrice(periodicalDto.getPrice());
        periodical.setName(periodicalDto.getName());
        periodical.setId(periodicalDto.getId());
        return periodical;
    }

    public boolean contains(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                periodicalDao.getPeriodicalByName(connection, name)) != null;
    }

    public Optional<Periodical> getByName(String name) throws TransactionInterruptedException {
        return Optional.ofNullable(transactionManager.doTransaction(connection ->
                periodicalDao.getPeriodicalByName(connection, name)));
    }


    public List<PeriodicalDto> getAllPeriodicalDto() throws TransactionInterruptedException {
        return transactionManager.doTransaction(periodicalDao::getPeriodicalDto);
    }

    public List<PeriodicalDto> getPeriodicalDtoByCategory(String category) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
            periodicalDao.getPeriodicalByCategory(connection, category));
    }

    public List<PeriodicalDto> getPeriodicalDtoByReader(int id) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        periodicalDao.getPeriodicalByReaderId(connection, id));
    }

    public List<PeriodicalDto> sort(String orderBy, String sortType) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
        periodicalDao.getSortPeriodicals(connection, orderBy, sortType));
    }

    public List<PeriodicalDto> getPeriodicalDtoByName(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
            periodicalDao.getPeriodicalDtoByName(connection, name)
        );
    }
}
