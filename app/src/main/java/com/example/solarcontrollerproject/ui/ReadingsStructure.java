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
}
