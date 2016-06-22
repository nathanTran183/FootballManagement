package com.example.phuc.footballmanagement.Model;

/**
 * Created by Phuc on 5/4/2016.
 */
public class District {
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    int id;
    String name;
    int cityId;

    public String toString(){
        return name;
    }
}
