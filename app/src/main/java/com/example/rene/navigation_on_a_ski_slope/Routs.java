package com.example.rene.navigation_on_a_ski_slope;



import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.db.Routes;


public class Routs extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.routs_activity);

     /*   Routes routs1 = new Routes(1,"Sljeme",5100,1220,2600);
        Routes routs2 = new Routes(2,"Ucka",250,250,255);
        Routes routs3 = new Routes(3,"Garmisch-partenkirchen",250,250,255);
        Routes routs4 = new Routes(4,"Routes4",300,250,855);
        Routes routs5 = new Routes(5,"Routes5",150,250,455);
        Routes routs6 = new Routes(6,"Routes5",150,250,455);
        Routes routs7 = new Routes(7,"Routes5",150,250,455);
        Routes routs8 = new Routes(8,"Routes5",150,250,455);
        Routes routs9 = new Routes(9,"Routes5",150,250,455);
        Routes routs10 = new Routes(10,"Routes5",150,250,455);

        Routes[] routesObjects = {routs1, routs2, routs3, routs4, routs5, routs6, routs7, routs8, routs9, routs10};
        ListAdapter listAdapter = new Routes_adapter(this, routesObjects);
        ListView listView = (ListView)findViewById(R.id.routListID);
        listView.setAdapter(listAdapter);
*/

    }




}
