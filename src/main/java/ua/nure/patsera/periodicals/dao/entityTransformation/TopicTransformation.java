package ua.nure.patsera.periodicals.dao.entityTransformation;

import ua.nure.patsera.periodicals.bean.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дарина on 07.09.2017.
 */
public class TopicTransformation implements ResultSetTransformation<Topic> {
    @Override
    public Topic getDBObject(ResultSet resultSet) throws SQLException {
        Topic topic = new Topic();
        while (resultSet.next()) {
            parseTopic(resultSet);
        }
        return topic;
    }

    @Override
    public List<Topic> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<Topic> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parseTopic(resultSet));
        }
        return list;
    }

    private Topic parseTopic(ResultSet resultSet) throws SQLException {
        Topic topic = new Topic();
        topic.setId(resultSet.getInt(1));
        topic.setName(resultSet.getString(2));
        return topic;
    }
}
