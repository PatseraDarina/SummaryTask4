package ua.nure.patsera.periodicals.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Дарина on 03.09.2017.
 */
public interface IBaseEntity<PK> {
    /**
     *
      * @return
     */
    PK getId();
    void setId(PK id);
}
