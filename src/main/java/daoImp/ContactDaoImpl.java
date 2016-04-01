package daoImp;

import dao.ContactDao;
import org.hibernate.Session;
import table.ContactEntity;
import util.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public class ContactDaoImpl implements ContactDao {
    @Override
    public void addContact(ContactEntity contact) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(contact);
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
    public void deleteContact(ContactEntity contact) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(contact);
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
    public ContactEntity getContact(int id) throws SQLException {
        ContactEntity contact = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            contact = (ContactEntity) session.get(ContactEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return contact;    }

    @Override
    public List<ContactEntity> getContacts() throws SQLException {
        List<ContactEntity> contactList = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            contactList =  session.createCriteria(ContactEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return contactList;
    }
}
