package general;

import dao.*;
import daoImp.*;

/**
 * Created by Константин on 30.03.2016.
 */
public class Factory {
    private static Factory instance = new Factory();
    private ConferenceDao conferenceDao;
    private FormDao formDao;
    private ThemeDao themeDao;
    private ContactDao contactDao;
    private ConferenceThemesDao conferenceThemesDao;

    private Factory() {}

    public static Factory getInstance() {
        return Factory.instance;
    }

    public ConferenceDao getConferenceDao() {
        if(conferenceDao == null) {
            conferenceDao = new ConferenceDaoImp();
        }
        return conferenceDao;
    }

    public FormDao getFormDao() {
        if(formDao == null) {
            formDao = new FormDaoImp();
        }
        return formDao;
    }

    public ThemeDao getThemeDao() {
        if(themeDao == null) {
            themeDao = new ThemeDaoImp();
        }
        return themeDao;
    }

    public ContactDao getContactDao() {
        if(contactDao == null) {
            contactDao = new ContactDaoImpl();
        }
        return contactDao;
    }

    public ConferenceThemesDao getConferenceThemesDao() {
        if(conferenceThemesDao == null) {
            conferenceThemesDao = new ConferenceThemesDaoImp();
        }
        return conferenceThemesDao;
    }
}
