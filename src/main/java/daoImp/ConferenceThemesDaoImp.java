package daoImp;

import dao.ConferenceThemesDao;
import org.hibernate.Session;
import table.ConferenceThemesEntity;
import util.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public class ConferenceThemesDaoImp implements ConferenceThemesDao {

    @Override
    public void addConferenceThemes(ConferenceThemesEntity conferenceThemes) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(conferenceThemes);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public void deleteConferenceThemes(ConferenceThemesEntity conferenceThemes) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(conferenceThemes);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public ConferenceThemesEntity getConferenceThemes(int id) throws SQLException {
        ConferenceThemesEntity conferenceThemes = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            conferenceThemes = (ConferenceThemesEntity) session.get(ConferenceThemesEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return conferenceThemes;
    }

    @Override
    public List<ConferenceThemesEntity> getAllConferenceThemes() throws SQLException {
        List<ConferenceThemesEntity> conferenceThemesList = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            conferenceThemesList =  session.createCriteria(ConferenceThemesEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return conferenceThemesList;
    }
}
