package ua.nure.patsera.periodicals.dao.entityDaoInterface;

import ua.nure.patsera.periodicals.bean.Periodical;
import ua.nure.patsera.periodicals.dto.PeriodicalDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public interface IPeriodicalsDao extends IBaseEntityDao<Periodical, Integer> {
    Periodical getPeriodicalByName(Connection connection, String name) throws SQLException;
    List<PeriodicalDto> getPeriodicalDto(Connection connection) throws SQLException;
    List<PeriodicalDto> getPeriodicalByCategory(Connection connection, String category) throws SQLException;
    List<PeriodicalDto> getPeriodicalByReaderId(Connection connection, int id) throws SQLException;
    List<PeriodicalDto> getSortPeriodicals(Connection connection, String orderBy, String sortType) throws SQLException;
    List<PeriodicalDto> getPeriodicalDtoByName(Connection connection, String name) throws SQLException;
}
