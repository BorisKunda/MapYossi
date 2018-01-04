package com.happytrees.mapyossi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 12/24/2017.
 */

public class LocationAdapter  extends RecyclerView.Adapter<LocationAdapter.MyViewHolder>{

    List<Location> allLocations;
    Context context;

    public LocationAdapter(List<Location> allLocations, Context context) {
        this.allLocations = allLocations;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewFromXML = LayoutInflater.from(context).inflate(R.layout.single_item,null);//"single_item" is xml template of single item in Recycler View
        MyViewHolder myViewHolder = new MyViewHolder(viewFromXML);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder  myViewHolder, int position) {
        Location currentLocation = allLocations.get(position);
        myViewHolder.bindMyWonderData(currentLocation);
    }

    @Override
    public int getItemCount() {
        return allLocations.size();
    }


    //INNER CLASS
    public class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;//DEFINE VIEW VARIABLE

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;
        }

        //CUSTOM METHOD WE NEED TO DEFINE
        public void bindMyWonderData(final Location  currentLocation) {
            TextView itemText = itemView.findViewById(R.id.single_text_id);
            itemText.setText(currentLocation.name);

            //MAKE RECYCLER VIEW CLICKABLE
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//CHANGES FRAGMENTS WHEN ITEM IS CLICKED
                    FragmentChanger fragmentChanger = (FragmentChanger)context;
                    fragmentChanger.changeFragments(currentLocation);
                }
            });
        }

        }
}
