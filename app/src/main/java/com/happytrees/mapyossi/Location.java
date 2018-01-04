package com.happytrees.mapyossi;

import com.orm.SugarRecord;


public class Location extends SugarRecord {

    String name;
  //  int res;// -- > res is int variable associated with image Id -->  ADD AFTERWARDS
  //sugar ORM doesnt properly recognize google LatLng object,this why use latitude and longitude separately as double variables
    double latitude;
    double longitude;
    //latitude is written before longitude
    int id;

    //must have empty constructor
    public Location() {
    }

    //constructor 1
    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //constructor 2
    public Location(String name, double latitude, double longitude, int id) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }

}
