package com.example.solarcontrollerproject.ui;

public class ReadingsStructure {

        //position
        private int longitude;
        private int latitude;
        private int elevation;
        private long  azimuth;
        private String timezone;
        //readings
        private double currentHarvest;
        private double averageHarvest;  //30 minute average
        private double totalHarvest;  //30 minute harvest
        private long timestamp;

    public ReadingsStructure(int longitude, int latitude, int elevation, long azimuth, String timezone, double currentHarvest, double averageHarvest, double totalHarvest, long timestamp) {
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

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public long getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(long azimuth) {
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
}
