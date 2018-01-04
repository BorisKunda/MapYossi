package com.happytrees.mapyossi;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddingLocFragment extends Fragment {

    EditText edtName,edtLatitude,edtLongitude;
    Button saveBtn;

    public AddingLocFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_adding_loc, container, false);

        edtName = (EditText)v.findViewById(R.id.editLocName);
        edtLatitude= (EditText)v.findViewById(R.id.editLocLatitude);
        edtLongitude = (EditText)v.findViewById(R.id.editLocLongitude);

        saveBtn = (Button)v.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.length() != 0 && edtLatitude.length() != 0  && edtLongitude.length() !=0) { //all fields are filled
                    //save Location
                    Location customLoc = new Location(edtName.getText().toString(),Double.parseDouble(edtLatitude.getText().toString()),Double.parseDouble(edtLongitude.getText().toString()));
                    customLoc.save();
                    getActivity().onBackPressed();//closes current fragment

                }else{
                    Toast.makeText(getActivity(),"please fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
