package ua.nure.patsera.periodicals.service;

import org.apache.log4j.Logger;
import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dao.entityDaoInterface.IPeriodicalsDao;
import ua.nure.patsera.periodicals.dao.transaction.Operation;
import ua.nure.patsera.periodicals.dao.transaction.TransactionManager;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;
import ua.nure.patsera.periodicals.exceptions.TransactionInterruptedException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Дарина on 16.09.2017.
 */
public class PeriodicalService implements IService<Periodical> {
    private Logger LOGGER = Logger.getLogger(PeriodicalService.class);
    private final TransactionManager transactionManager;
    private final IPeriodicalsDao periodicalDao;
    private final CategoryService categoryService;

    public PeriodicalService(TransactionManager transactionManager, IPeriodicalsDao periodicalDao, CategoryService categoryService) {
        this.transactionManager = transactionManager;
        this.periodicalDao = periodicalDao;
        this.categoryService = categoryService;
    }

    @Override
    public void add(Periodical periodical) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection -> {
            periodicalDao.create(connection, periodical);
            return null;
        });
    }

    @Override
    public void delete(int id) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>)connection -> {
            periodicalDao.delete(connection, id);
            return null;
        });
    }

    @Override
    public void update(Periodical periodical) throws TransactionInterruptedException {
        transactionManager.doTransaction((Operation<Void>) connection -> {
             periodicalDao.update(connection, periodical);
             return null;
        });
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

    @Override
    public boolean contains(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                periodicalDao.getPeriodicalByName(connection, name)) != null;
    }

    public Optional<Periodical> getByName(String name) throws TransactionInterruptedException {
        return Optional.ofNullable(transactionManager.doTransaction(connection ->
                periodicalDao.getPeriodicalByName(connection, name)));
    }

    public List<Periodical> getAllPeriodical() {
        try {
            return transactionManager.doTransaction(periodicalDao::readAll);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PeriodicalDto> getAllPeriodicalDto() throws TransactionInterruptedException {
        return transactionManager.doTransaction(periodicalDao::getPeriodicalDto);
    }
}
