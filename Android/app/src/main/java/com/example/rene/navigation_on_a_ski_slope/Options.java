package com.example.rene.navigation_on_a_ski_slope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Marko1 on 13.6.2016..
 */
public class Options extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RadioButton rbtnReal = (RadioButton) findViewById(R.id.realButton);
        RadioButton rbtnMov = (RadioButton) findViewById(R.id.movAvgButton);
        RadioButton rbtnSmo = (RadioButton) findViewById(R.id.smoothButton);
        TextView sX = (TextView) findViewById(R.id.sourceX);
        TextView sY = (TextView) findViewById(R.id.sourceY);
        TextView dX = (TextView) findViewById(R.id.destinationX);
        TextView dY = (TextView) findViewById(R.id.destinationY);
        String sorX,sorY,desX,desY;
        sorX = sX.getText().toString();
        sorY = sY.getText().toString();
        desX = dX.getText().toString();
        desY = dY.getText().toString();
        final Context context = getApplicationContext();
        int algID = 1;
        Intent intent = new Intent(context, MainActivity.class);    //creating new intent and replacing the old one
        if (rbtnSmo.isChecked()) {
            algID = 2;
        } else if (rbtnMov.isChecked()) {
            algID = 3;
        } else if (rbtnReal.isChecked()) {
            algID = 1;
        }
        intent.putExtra("alg",algID);
        intent.putExtra("sX",sorX);
        intent.putExtra("sY",sorY);
        intent.putExtra("dX",desX);
        intent.putExtra("dY",desY);
        startActivity(intent);
    }
}

