package com.example.rene.navigation_on_a_ski_slope;



import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;



public class Routs extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.routs_activity);

        Routes_object routs1 = new Routes_object("Sljeme",5100,1220,2600);
        Routes_object routs2 = new Routes_object("Ucka",250,250,255);
        Routes_object routs3 = new Routes_object("Garmisch-partenkirchen",250,250,255);
        Routes_object routs4 = new Routes_object("Routes4",300,250,855);
        Routes_object routs5 = new Routes_object("Routes5",150,250,455);
        Routes_object routs6 = new Routes_object("Routes5",150,250,455);
        Routes_object routs7 = new Routes_object("Routes5",150,250,455);
        Routes_object routs8 = new Routes_object("Routes5",150,250,455);
        Routes_object routs9 = new Routes_object("Routes5",150,250,455);
        Routes_object routs10 = new Routes_object("Routes5",150,250,455);

        Routes_object[] routesObjects = {routs1, routs2, routs3, routs4, routs5, routs6, routs7, routs8, routs9, routs10};
        ListAdapter listAdapter = new Routes_adapter(this, routesObjects);
        ListView listView = (ListView)findViewById(R.id.routListID);
        listView.setAdapter(listAdapter);



    }




}
