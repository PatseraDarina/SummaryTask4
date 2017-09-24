package ua.nure.patsera.periodicals.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Дарина on 03.09.2017.
 */
public interface IBaseEntity<PK> {

    PK getId();
    void setId(PK id);
}
