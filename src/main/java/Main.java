import Parser.DateConference;
import Parser.FactoryParser;
import Parser.ParserConferenceRu;
import Parser.PlaceConference;
import dao.*;
import daoImp.ConferenceThemesDaoImp;
import daoImp.ContactDaoImpl;
import daoImp.FormDaoImp;
import daoImp.ThemeDaoImp;
import general.Factory;
import table.*;
import util.HibernateSessionFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Константин on 29.03.2016.
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        Logic logic = new Logic();

        Factory factory = Factory.getInstance();

        ConferenceDao conferenceDao = factory.getConferenceDao();
        ConferenceThemesDao conferenceThemesDao = factory.getConferenceThemesDao();
        ContactDao contactDao = factory.getContactDao();
        FormDaoImp formDao = (FormDaoImp)factory.getFormDao();
        ThemeDaoImp themeDao = (ThemeDaoImp)factory.getThemeDao();


        FactoryParser factoryParsery = FactoryParser.getInstanse();
        ParserConferenceRu parser = (ParserConferenceRu)factoryParsery.getParserConferenceRu();

        String url = "http://konferencii.ru/search?advance%5Bkeyword%5D=&advance%5BsearchOr%5D=1&advance%5BstartDate%5D=&advance_startDate=&advance%5BendDate%5D=&advance_endDate=&advance%5BlastRequestDate1%5D=&advance_lastRequestDate1=&advance%5BlastRequestDate2%5D=&advance_lastRequestDate2=&advance%5BcountryId%5D=&advance%5BcityId%5D=&advance%5BeventId%5D=1&advance%5BtopicId%5D%5B%5D=22&advance%5BparticalId%5D=&advance%5BorderBy%5D=startDate&advance%5Blimit%5D=1353";
        ArrayList<String> urlAllConferences = null;
        try {
            urlAllConferences = parser.getUrlAllConferences(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        int size = urlAllConferences.size();
        ArrayList<String> badUrl = new ArrayList<String>();
        Long timeStart = System.currentTimeMillis();
        for (String urlConf : urlAllConferences) {

            try {
                logic.addConference(urlConf);
                count++;
                System.out.println("Обработан элемент номер: "+ count + " останолось " + (size - count));
            } catch (Exception e) {
                System.out.println(urlConf);
                badUrl.add(urlConf);
                e.printStackTrace();
            }
        }
        Long timeEnd = System.currentTimeMillis();
        System.out.println(count + " элементов обработано за: " + (timeEnd - timeStart) + "(ms)");
        System.out.println("Не ужадлсь обработать следующие страницы: ");
        for (String s : badUrl) {
            System.out.println(s);
        }
        HibernateSessionFactory.shutdown();
    }
}
