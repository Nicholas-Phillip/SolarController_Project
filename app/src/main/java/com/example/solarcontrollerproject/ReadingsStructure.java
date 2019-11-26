package com.example.solarcontrollerproject;

public class ReadingsStructure {
        //user info
        //private String name;
        //position
        private String longitude;
        private String latitude;
        private String elevation;
        private String  azimuth;
        private String timezone;
        //readings
        private double currentHarvest;
        private double averageHarvest;  //30 minute average
        private double totalHarvest;  //30 minute harvest
        private long timestamp;



    public ReadingsStructure(String longitude, String latitude, String elevation, String azimuth, String timezone) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.azimuth = azimuth;
        this.timezone = timezone;
    }

    public ReadingsStructure(){}

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public double getCurrentHarvest() {
        return currentHarvest;
    }

    public void setCurrentHarvest(double currentHarvest) {
        this.currentHarvest = currentHarvest;
    }

    public double getAverageHarvest() {
        return averageHarvest;
    }

    public void setAverageHarvest(double averageHarvest) {
        this.averageHarvest = averageHarvest;
    }

    public double getTotalHarvest() {
        return totalHarvest;
    }

    public void setTotalHarvest(double totalHarvest) {
        this.totalHarvest = totalHarvest;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ReadingsStructure(String longitude, String latitude, String elevation, String azimuth, String timezone, double currentHarvest, double averageHarvest, double totalHarvest, long timestamp) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
        this.azimuth = azimuth;
        this.timezone = timezone;
        this.currentHarvest = currentHarvest;
        this.averageHarvest = averageHarvest;
        this.totalHarvest = totalHarvest;
        this.timestamp = timestamp;
    }
}
