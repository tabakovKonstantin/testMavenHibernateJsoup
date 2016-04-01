package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Константин on 27.03.2016.
 */
public class ParserConferenceRu implements ParserWebPage{
    private Document bodyHTML;

    private int bodySize = 0;
    private int timeout = 0;
    private String mainUrl = "http://konferencii.ru";

        //    static String url = "http://konferencii.ru/info/2000";
        //    static String url = "http://konferencii.ru/info/116010";

        public void setProperty(int bodySize, int timeout, String mainUrl) {
            this.bodySize = bodySize;
            this.timeout = timeout;
            this.mainUrl = mainUrl;

        }
        public void loadPage(String url) throws IOException {
            bodyHTML = Jsoup.connect(url)
                    .maxBodySize(bodySize)
                    .timeout(timeout)
                    .post();
        }

        public ArrayList<String> getUrlAllConferences(String url) throws IOException {
            loadPage(url);
            ArrayList<String> urlAllConferences = new ArrayList<String>();
            Elements elements = bodyHTML.select("div.index_cat_tit").select("a");
            for (Element element : elements) {
                String urlConferences = element.attr("href");
                urlAllConferences.add(mainUrl.concat(urlConferences));
                System.out.println(urlConferences);
            }
            System.out.println("Count conferences " + elements.size());
            return urlAllConferences;
        }

        /*private void loadPage(String url) throws IOException {
            bodyHTML = loadPage(bodySize, timeout, url);
//                        System.out.println("name " + document.select("#inside_content > h1").text());
                        System.out.println("theme " + document.select("div.index_cat_link").text());
                        System.out.println("form " + document.select("#inside_content > div:eq(2) > p").get(1).text());
                        System.out.println("city " + document.select("#inside_content > div.left-col > p.city").text());
                        System.out.println("lang " + document.select("#inside_content > div:eq(2) > div.lang").text());
                        System.out.println("text " + document.select("#inside_content > div.left-col > p.p160").text());
                        System.out.println("data " + document.select("#main_top > p").text());
                        System.out.println("org " + document.select("#inside_content > div.left-col > p.lft").get(0).text());
                        System.out.println("contact " + document.select("#inside_content > div:eq(2) > p.lft").get(1).text());
                        System.out.println("email " + document.select("#inside_content > div.left-col > p").get(7).text());
        }*/

    @Override
    public String getTitle() {
        String title =  bodyHTML.select("#inside_content > h1").text();
        return title;
    }

    @Override
    public ArrayList<String> getTheme() {
        ArrayList<String> themeArrayList = new ArrayList<String>();
        String prefix = "Конференции | ";
        String result = bodyHTML.select("div.index_cat_link").text();
        if(result.startsWith(prefix)) {
            String[] splitStringResult = result.substring(prefix.length()).split(",");
            for (int i = 0; i < splitStringResult.length; i++) {
                themeArrayList.add(splitStringResult[i].trim());
            }
            return themeArrayList;
        }
        return null;
    }

    @Override
    public String getForm() {
        String prefix = "Форма участия: ";
        String result = bodyHTML.select("#inside_content > div:eq(2) > p").get(1).text();
        if(result.startsWith(prefix)) {
            return result.substring(prefix.length());
        }
        return null;
    }

    @Override
    public PlaceConference getPlaceConference() {
        String result = bodyHTML.select("#inside_content > div.left-col > p.city").text();
        String[] splitStringResult = result.split(",");
        if(splitStringResult.length == 2
                && !splitStringResult[0].isEmpty()) {

            String country = splitStringResult[0].trim();
            String[] splitStringResult1 = splitStringResult[1].trim().split("\\(");
            if( !splitStringResult1[0].isEmpty()) {
                String city = splitStringResult1[0].trim();
                return new PlaceConference(country, city);
            }
        }
        return null;
    }

    @Override
    public String getLanguage() {

        String prefix = "Язык информации: ";
        String result = bodyHTML.select("#inside_content > div:eq(2) > div.lang").text();
        if(result.startsWith(prefix)) {
            return result.substring(prefix.length());
        }
        return null;
    }

    @Override
    public String getDescription() {
        String result = bodyHTML.select("#inside_content > div.left-col > p.p160").text();
        return result;
    }

    private Date stringToDate(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        Date date = simpleDateFormat.parse(stringDate);
        return date;
    }

    @Override
    public DateConference getDate() throws ParseException {
        String result = bodyHTML.select("#main_top > p").text();
        String[] splitStringResult = result.split("—");
        if(splitStringResult.length == 2
                && !splitStringResult[0].isEmpty()) {

            String stringDateBegin = splitStringResult[0].trim();
            String[] splitStringResult1 = splitStringResult[1].split(",");
            if (splitStringResult1.length == 2
                    && !splitStringResult1[0].isEmpty()) {

                String stringDateEnd = splitStringResult1[0].trim();
                String[] splitStringResult2 = splitStringResult1[1].split(":");
                if (splitStringResult2.length == 2
                        && splitStringResult2[0].trim().equals("срок заявок")
                        && !splitStringResult2[1].isEmpty()) {

                    String stringDateFilingEnd = splitStringResult2[1].trim();
                    return new DateConference(stringToDate(stringDateBegin),
                            stringToDate(stringDateEnd),
                            stringToDate(stringDateFilingEnd));
                }
            }
        }

        return null;

    }

    @Override
    public String getOrganizer() {
        String prefix = "Организаторы: ";
        String result = bodyHTML.select("#inside_content > div.left-col > p.lft").get(0).text();
        if(result.startsWith(prefix)) {
            return result.substring(prefix.length());
        }
        return null;
    }

    @Override
    public String getContact() {
        String prefix = "Контактная информация: ";
        String result = bodyHTML.select("#inside_content > div:eq(2) > p.lft").get(1).text();
        if(result.startsWith(prefix)) {
            return result.substring(prefix.length());
        }
        return null;
    }

    @Override
    public String getEmail() {
        String prefix = "Эл. почта: ";
        String result = null;
        try {
           result = bodyHTML.select("#inside_content > div.left-col > p").get(7).text();
        } catch (Exception e) {
            return null;
        }

        if(result.startsWith(prefix)) {
            return result.substring(prefix.length());
        }
        return null;
    }
}
