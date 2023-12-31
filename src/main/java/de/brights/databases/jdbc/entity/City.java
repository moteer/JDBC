package de.brights.databases.jdbc.entity;

public class City {
    private  int id;
    private  String name;
    private  String country;
    private  String district;
    private  String population;

    public City(int id, String name, String country, String district, String population) {

        this.id = id;
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}
