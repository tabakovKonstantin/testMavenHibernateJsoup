package daoImp;

import dao.ThemeDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import table.FormEntity;
import table.ThemeEntity;
import util.HibernateSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Константин on 01.04.2016.
 */
public class ThemeDaoImp implements ThemeDao {

    @Override
    public void addTheme(ThemeEntity theme) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(theme);
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
    public void deleteTheme(ThemeEntity theme) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(theme);
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
    public ThemeEntity geTheme(int id) throws SQLException {
        ThemeEntity theme = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            theme = (ThemeEntity) session.get(ThemeEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return theme;
    }

    @Override
    public List<ThemeEntity> getThemes() throws SQLException {
        List<ThemeEntity> themeList = null;
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            themeList =  session.createCriteria(ThemeEntity.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return themeList;
    }

    public Integer getIdFormEq(String value) {
        if(value != null) {
            Integer id = null;
            Session session = null;
            try {
                session = HibernateSessionFactory.getSessionFactory().openSession();
                Criteria criteria = session.createCriteria(ThemeEntity.class)
                        .add(Restrictions.eq("nameTheme", value));
                List listResult = criteria.list();
                if(listResult.size() != 0) {
                    ThemeEntity theme = (ThemeEntity) criteria.list().get(0);
                    id = theme.getId();
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
