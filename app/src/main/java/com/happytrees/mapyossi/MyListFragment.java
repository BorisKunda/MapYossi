package com.happytrees.mapyossi;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment {



    public MyListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_list, container, false);



        Button addBtn = (Button) v.findViewById(R.id.AddBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ALERT DIALOG
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Adding Options");//dialog title
                builder.setMessage("choose an option");//dialog message

                builder.setPositiveButton("current location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //here will be opened adding fragment with current lattide ,longtidue of current location and save to db
                    }
                });

                builder.setNegativeButton("custom location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Here will be custom location
                        AddingLocFragment addingLocFragment = new AddingLocFragment();
                        getFragmentManager().beginTransaction().addToBackStack("custom location").replace(R.id.MainActLayout,addingLocFragment).commit();

                    }
                });
                builder.show();//don't forget otherwise dialog wont show


            }
        });




        //ARRAY LIST OF LOCATIONS TAKEN FROM DB CREATED BY SUGAR ORM

        //ADD THREE RANDOM LOCATIONS TO DATABASE


       /* Location loc1 = new Location("Christ the Redeemer",-22.951911,-43.2126759);
        loc1.save();
        Location loc2 = new Location("Coliseum", 41.8902142,12.4900422);
        loc2.save();
        Location loc3 = new Location("Taj Mahal",27.1750199,78.0399665);
        loc3.save();*/


        List<Location> allLocations = Location.listAll(Location.class);

        RecyclerView recyclerView = v.findViewById(R.id.locationRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());//layout manager defines look of RecyclerView -- > grid,list
        recyclerView.setLayoutManager(layoutManager);

        //adapter
        LocationAdapter locationAdapter = new LocationAdapter(allLocations, getActivity());

        recyclerView.setAdapter(locationAdapter);


        return v;
    }


}


