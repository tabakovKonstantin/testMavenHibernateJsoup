package Parser;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Константин on 31.03.2016.
 */
public interface ParserWebPage {
    public String getTitle();
    public ArrayList<String> getTheme();
    public String getForm();
    public PlaceConference getPlaceConference();
    public String getLanguage();
    public String getDescription();
    public DateConference getDate() throws ParseException;
    public String getOrganizer();
    public String getContact();
    public String getEmail();
}
