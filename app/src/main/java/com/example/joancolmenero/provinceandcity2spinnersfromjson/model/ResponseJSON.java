package com.example.joancolmenero.provinceandcity2spinnersfromjson.model;

import java.util.List;

public class ResponseJSON {
    private String province_name;
    private String province_id;
    private List<City> city = null;

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
}
