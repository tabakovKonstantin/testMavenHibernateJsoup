package dao;

import table.ConferenceThemesEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public interface ConferenceThemesDao {
    public void addConferenceThemes(ConferenceThemesEntity conferenceThemes) throws SQLException;
    public void deleteConferenceThemes(ConferenceThemesEntity conferenceThemes) throws SQLException;
    public  ConferenceThemesEntity getConferenceThemes(int id) throws SQLException;
    public List<ConferenceThemesEntity> getAllConferenceThemes() throws SQLException;
}
