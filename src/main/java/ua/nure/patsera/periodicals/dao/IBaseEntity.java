package ua.nure.patsera.periodicals.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public interface IBaseEntity<PK> {

    PK getId();
    void setId(PK id);
}
