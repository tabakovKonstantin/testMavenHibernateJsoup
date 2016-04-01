package daoImp;

import dao.FormDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import table.FormEntity;
import util.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 30.03.2016.
 */
public class FormDaoImp implements FormDao {
    @Override
    public void addForm(FormEntity form) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(form);
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
    public void deleteForm(FormEntity form) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(form);
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
    public FormEntity getForm(int id) throws SQLException {
        FormEntity form = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            form = (FormEntity) session.get(FormEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return form;
    }

    @Override
    public List<FormEntity> getForms() throws SQLException {
        List<FormEntity> formList = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            formList =  session.createCriteria(FormEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return formList;
    }

    public Integer getIdFormEq(String value) {
        if(value != null) {
            Integer id = null;
            Session session = null;
            try {
                session = HibernateSessionFactory.getSessionFactory().openSession();
                Criteria criteria = session.createCriteria(FormEntity.class)
                        .add(Restrictions.eq("nameForm", value));
                List listResult = criteria.list();
                if(listResult.size() != 0) {
                    FormEntity form = (FormEntity) criteria.list().get(0);
                    id = form.getId();
                } else  throw new Exception(value + " не существуюет в таблице");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if ((session != null) && (session.isOpen())) {
                    session.close();
                }
            }
            return id;
        }
        return null;
    }

}
