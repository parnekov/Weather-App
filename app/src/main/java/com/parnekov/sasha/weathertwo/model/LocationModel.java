package com.parnekov.sasha.weathertwo.model;

/** Class for work with gps information*/
public class LocationModel {

    private double latitude;
    private double longitude;
    private int searchRequest;
    private String finalPlace;
    private boolean badRequest;

    public static LocationModel sLocationModel;

    public LocationModel() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(int searchRequest) {
        this.searchRequest = searchRequest;
    }

    public String getFinalPlace() {
        return finalPlace;
    }

    public void setFinalPlace(String finalPlace) {
        this.finalPlace = finalPlace;
    }

    public boolean isBadRequest() {
        return badRequest;
    }

    public void setBadRequest(boolean badRequest) {
        this.badRequest = badRequest;
    }


    // Singleton
    public static LocationModel getLocationModel() {
        if (sLocationModel == null) {
            sLocationModel = new LocationModel();
        }
        return sLocationModel;
    }


}
