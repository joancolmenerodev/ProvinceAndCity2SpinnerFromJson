package com.example.joancolmenero.provinceandcity2spinnersfromjson.model;

public class City {

    private String city_id;
    private String city_name;
    private String province;

    public String getCityId() {
        return city_id;
    }

    public void setCityId(String cityId) {
        this.city_id = cityId;
    }

    public String getCityName() {
        return city_name;
    }

    public void setCityName(String cityName) {
        this.city_name = cityName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId='" + city_id + '\'' +
                ", cityName='" + city_name + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
