package dao;

import table.ConferenceEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 30.03.2016.
 */
public interface ConferenceDao {
    public void addConference(ConferenceEntity conference) throws SQLException;
    public void deleteConference(ConferenceEntity conference) throws SQLException;
    public  ConferenceEntity getConference(int id) throws SQLException;
    public List<ConferenceEntity> getConferences() throws SQLException;
}
