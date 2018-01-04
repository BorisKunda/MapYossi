package com.happytrees.mapyossi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentChanger {
/*
LIBRARIES I ADDED TO THIS PROJECT  :
compile 'com.github.satyan:sugar:1.5'
implementation 'com.google.android.gms:play-services:11.8.0'
implementation 'com.android.support:recyclerview-v7:27.0.2'
implementation 'com.android.support:cardview-v7:27.0.2'
 */

    LatLng customLatLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DELETE ALL
     /*  List<Location> allContacts = Location.listAll(Location.class);
        Location.deleteAll(Location.class); */



        //CREATE AND DISPLAY  WondersListFragment in containerLayout in MainActivity
        MyListFragment myListFragment = new MyListFragment();
        myListFragment.setRetainInstance(true);//KEEPS FRAGMENT SAME AFTER ROTATION CHANGE (portrait <-> landscape) --> FIND MORE ELEGANT WAY
        getFragmentManager().beginTransaction().replace(R.id.MainActLayout,myListFragment).commit();
    }

    @Override
    public void changeFragments(final Location chosenLocation) {
        customLatLng = new LatLng(chosenLocation.latitude, chosenLocation.longitude);
        Toast.makeText(MainActivity.this, chosenLocation.name, Toast.LENGTH_LONG).show();//shows Toast name of clicked item
        //CREATES MAP FRAGMENT ->  no need to create java,xml file for it .No need in creating GPS object and make Location Permission in manifest.Only key cause you using google API
        MapFragment mapFragment = new MapFragment();
        if (isTablet())//isTablet = true .Means device used xlarge xml layout of MainActivity,due to device large screen(tablet)
        {
            getFragmentManager().beginTransaction().addToBackStack("adding map").replace(R.id.DetailActLayout, mapFragment).commit(); //we add DetailsFragment only  to sub layout of xlarge MainActivity called DetailActLayout ,and only if its not null(its null if device detected normal size screen)
        } else {
            getFragmentManager().beginTransaction().addToBackStack("replacing").replace(R.id.MainActLayout, mapFragment).commit();//replaces current Fragment with mapFragment.
            //addingToBackStack in this example undoes fragment replacing.Otherwise pushing back button would exist from application(cause it would close activity and there no another activities)
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    //update location and zoom 0 is the most far
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(customLatLng, 17));//v --> is zoom
                }
            });
        }
    }

    public  boolean isTablet()//CHECKS IF DEVICE USES LARGE SCREEN
    {
        boolean isTab= false;
        LinearLayout detailsLayout= findViewById(R.id.DetailActLayout);
        if(detailsLayout != null)//if device chose xlarge xml layout of MainActivity then its sub layout  DetailActLayout wont be null
        {
            isTab=true;
        }

        return isTab;
    }
}


