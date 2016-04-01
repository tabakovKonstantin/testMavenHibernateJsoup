package Parser;

/**
 * Created by Константин on 31.03.2016.
 */
public class PlaceConference {
    private String country;
    private String city;

    public PlaceConference(String county, String city) {
        this.country = county;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
