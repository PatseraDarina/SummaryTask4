package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class DeliveryTransformation implements ResultSetTransformation<Delivery> {
    @Override
    public Delivery getDBObject(ResultSet resultSet) throws SQLException {
        Delivery delivery = null;
        if (resultSet.next()) {
            delivery = parseDelivery(resultSet);
        }
        return delivery;
    }

    @Override
    public List<Delivery> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Delivery> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseDelivery(resultSet));
        }
        return list;
    }

    private  Delivery parseDelivery(ResultSet resultSet) throws SQLException {
        Delivery delivery = new Delivery();
        delivery.setId(resultSet.getInt(1));
        delivery.setIdReader(resultSet.getInt(2));
        delivery.setIdSubscription(resultSet.getInt(3));
        delivery.setDeliveryDate(resultSet.getDate(4));
        return delivery;
    }
}

