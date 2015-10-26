package com.example.rene.navigation_on_a_ski_slope;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Routes_adapter extends ArrayAdapter<Routes_object> {


    public Routes_adapter(Context context, Routes_object[] objects) {
        super(context, R.layout.routs_costum_list, objects);    //putting objects(routing_object) into list
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //creating view from custom item and list
        LayoutInflater inflater = LayoutInflater.from(getContext());        //mora da bude tu inače nevalja
        View customView = inflater.inflate(R.layout.routs_costum_list, parent, false); //

        Routes_object singleRoute = getItem(position);                                  //filling routes custom list
        TextView txtName = (TextView)customView.findViewById(R.id.txt_routesNameID);
        TextView txtLen = (TextView)customView.findViewById(R.id.txt_routesDistanceID);
        TextView txtDist = (TextView)customView.findViewById(R.id.txt_routesHeighID);
        TextView txtVert = (TextView)customView.findViewById(R.id.txt_vertDropID);

        txtName.setText(singleRoute.getRoutName());                                     //filling informative text under route name
        txtLen.setText("Leinght: " + singleRoute.getRoutLeinght() + "m");
        txtDist.setText("Height: " + singleRoute.getRoutHeight() + "m");
        txtVert.setText("Drop: " + singleRoute.getRoutVerticalDrop() + "m");
        return customView;
    }
}
