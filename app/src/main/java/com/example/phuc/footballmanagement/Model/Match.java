package com.example.phuc.footballmanagement.Model;

import com.example.phuc.footballmanagement.DatabaseHelper;

import java.util.Date;

/**
 * Created by Phuc on 5/6/2016.
 */
public class Match {
    int id;
    int fieldId;
    int hostId;
    int status;
    int maxPlayer;
    int price;
    String start;
    String end;
    boolean isVerified;
    String dayCreate;
    String fieldName;
    public Match(){}

    public Match(int id, int fieldId, int hostId, int status, int maxPlayer, int price, String start, String end, boolean isVerified, String dayCreate) {
        this.id = id;
        this.fieldId = fieldId;
        this.hostId = hostId;
        this.status = status;
        this.maxPlayer = maxPlayer;
        this.price = price;
        this.start = start;
        this.end = end;
        this.isVerified = isVerified;
        this.dayCreate = dayCreate;
    }
    public Match( int fieldId, int hostId,int status, int maxPlayer, int price, String start, String end,boolean isVerified,String dayCreate) {
        this.fieldId = fieldId;
        this.hostId = hostId;
        this.maxPlayer = maxPlayer;
        this.price = price;
        this.start = start;
        this.end = end;
        this.status = status;
        this.isVerified = isVerified;
        this.dayCreate = dayCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(String dayCreate) {
        this.dayCreate = dayCreate;
    }

    public String toString(){
        return id + " - " + fieldId+" - "+start ;
    }
}
