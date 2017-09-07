package ua.nure.patsera.periodicals.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Дарина on 03.09.2017.
 */
public interface IBaseEntity<PK> {
    /**
     *
      * @return
     */
    String getCreateSql();
    String getReadSql();
    String getUpdateSql();
    String getDeleteSql();
    PK getId();
    void setId(PK id);
   /* void prepareCreateStatement(PreparedStatement stmt) throws SQLException;
    void prepareReadStatement(PreparedStatement stmt) throws SQLException;
    void prepareDeleteStatement(PreparedStatement stmt) throws SQLException;
    void setDataFromResultSet(ResultSet resultSet) throws SQLException;*/
}
