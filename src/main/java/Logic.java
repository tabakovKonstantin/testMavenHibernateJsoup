import Parser.DateConference;
import Parser.FactoryParser;
import Parser.ParserConferenceRu;
import Parser.PlaceConference;
import dao.ConferenceDao;
import dao.ConferenceThemesDao;
import dao.ContactDao;
import dao.ThemeDao;
import daoImp.FormDaoImp;
import daoImp.ThemeDaoImp;
import general.Factory;
import table.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Константин on 01.04.2016.
 */
public class Logic {
    public void addAllThemes(ArrayList<String> urlAllConferences) {

        FactoryParser factoryParsery = FactoryParser.getInstanse();
        ParserConferenceRu parser = (ParserConferenceRu)factoryParsery.getParserConferenceRu();

        Factory factory = Factory.getInstance();
        ThemeDao themeDao = factory.getThemeDao();

        HashSet<String> uniqThemes = new HashSet<String>();
        int i  = 0;
        for (String urlConf : urlAllConferences) {

            try {
                parser.loadPage(urlConf);
                ArrayList<String> themesList = parser.getTheme();
                for(String theme : themesList) {
                    uniqThemes.add(theme);
                }
                System.out.println("Curent load page " + i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(String uniqTheme : uniqThemes) {
            ThemeEntity themeEntity = new ThemeEntity();
            themeEntity.setNameTheme(uniqTheme);
            try {
                themeDao.addTheme(themeEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void addConference(String urlConf) throws Exception {
        Factory factory = Factory.getInstance();

        ConferenceDao conferenceDao = factory.getConferenceDao();
        ConferenceThemesDao conferenceThemesDao = factory.getConferenceThemesDao();
        ContactDao contactDao = factory.getContactDao();
        FormDaoImp formDao = (FormDaoImp)factory.getFormDao();
        ThemeDaoImp themeDao = (ThemeDaoImp)factory.getThemeDao();


        FactoryParser factoryParsery = FactoryParser.getInstanse();
        ParserConferenceRu parser = (ParserConferenceRu)factoryParsery.getParserConferenceRu();

        parser.loadPage(urlConf);

        ConferenceEntity conferenceEntity = new ConferenceEntity();
        conferenceEntity.setConferenceTitle(parser.getTitle());

        ContactEntity contactEntity = new ContactEntity();
        PlaceConference placeConference = parser.getPlaceConference();
        contactEntity.setCity(placeConference.getCity());
        contactEntity.setCounry(placeConference.getCountry());
        contactEntity.setContact(parser.getContact());
        contactEntity.setEmail(parser.getEmail());
        contactDao.addContact(contactEntity);
        conferenceEntity.setContactEntity(contactEntity);

        DateConference dateConference = parser.getDate();
        conferenceEntity.setDateBegin(dateConference.getDateBegin());
        conferenceEntity.setDateEnd(dateConference.getDateEnd());
        conferenceEntity.setDateFilingEnd(dateConference.getDateFilingEnd());
        conferenceEntity.setDescription(parser.getDescription());

        String form = parser.getForm();
        if(form != null) {
            int idForm = formDao.getIdFormEq(form);
            FormEntity formEntity = formDao.getForm(idForm);
            conferenceEntity.setForm(formEntity);
        }

        conferenceEntity.setLanguage(parser.getLanguage());

        conferenceDao.addConference(conferenceEntity);

        for (String theme : parser.getTheme()) {
            if(theme != null) {
                int idTheme = themeDao.getIdFormEq(theme);
                ThemeEntity themeEntity = themeDao.geTheme(idTheme);
                ConferenceThemesEntity conferenceThemesEntity = new ConferenceThemesEntity();
                conferenceThemesEntity.setConferenceEntity(conferenceEntity);
                conferenceThemesEntity.setThemeEntity(themeEntity);

                conferenceThemesDao.addConferenceThemes(conferenceThemesEntity);
            }
        }
    }
}
