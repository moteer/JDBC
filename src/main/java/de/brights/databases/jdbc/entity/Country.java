package de.brights.databases.jdbc.entity;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryName;
    private String continent;
    private String region;

    private List<City> cities = new ArrayList<>();

    public Country(String countryName, String continent, String region) {

        this.countryName = countryName;
        this.continent = continent;
        this.region = region;
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", cities=" + cities +
                '}';
    }
}
