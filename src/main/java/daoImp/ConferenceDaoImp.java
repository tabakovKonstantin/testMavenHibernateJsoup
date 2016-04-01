package daoImp;

import dao.ConferenceDao;
import org.hibernate.Session;
import table.ConferenceEntity;
import util.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 30.03.2016.
 */
public class ConferenceDaoImp implements ConferenceDao {
    @Override
    public void addConference(ConferenceEntity conference) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(conference);
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
    public void deleteConference(ConferenceEntity conference) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(conference);
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
    public ConferenceEntity getConference(int id) throws SQLException {
        ConferenceEntity conference = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            conference = (ConferenceEntity) session.get(ConferenceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return conference;
    }

    @Override
    public List<ConferenceEntity> getConferences() throws SQLException {
        List<ConferenceEntity> conferenceList = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            conferenceList =  session.createCriteria(ConferenceEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return conferenceList;
    }
}
