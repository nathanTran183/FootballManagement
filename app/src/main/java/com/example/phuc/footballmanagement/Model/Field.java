package com.example.phuc.footballmanagement.Model;

/**
 * Created by Phuc on 5/6/2016.
 */
public class Field {
    int id;
    String name;
    int districtId;
    String address;
    float latitude;
    float longitude;
    String phone;

    public Field(){}

    public Field(int id, String name, int districtId, String address, float latitude, float longitude,String phone) {
        this.id = id;
        this.name = name;
        this.districtId = districtId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
    }
    public Field(String name, int districtId, String address, float latitude, float longitude,String phone) {
        this.name = name;
        this.districtId = districtId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
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

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString(){
        return name;
    };
}
