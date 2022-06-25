package pojoClass.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Location {


     private int   postcode;
     private String   country;
     private String   countryabbreviation;
     private List<Places> places;

    public int getPostcode() {
        return postcode;
    }
     @JsonProperty("post code")
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryabbreviation() {
        return countryabbreviation;
    }

    @JsonProperty("country abbreviation")
    public void setCountryabbreviation(String countryabbreviation) {
        this.countryabbreviation = countryabbreviation;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void setPlaces(List<Places> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Location{" +
                "postcode=" + postcode +
                ", country='" + country + '\'' +
                ", countryabbreviation='" + countryabbreviation + '\'' +
                ", places=" + places +
                '}';
    }
}
