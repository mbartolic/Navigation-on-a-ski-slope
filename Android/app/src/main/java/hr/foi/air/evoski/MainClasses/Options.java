package hr.foi.air.evoski.MainClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import hr.foi.air.evoski.R;
import hr.foi.air.evoski.core.PreferenceManagerHelper;

/**
 * Created by Marko1 on 13.6.2016..
 */
public class Options extends Activity{
    TextView sX, sY,dX,dY, chosenAlg;
    Context context;
    Button rbtnReal,rbtnMov,rbtnSmo;

    int idAlgNumb = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        context = getApplicationContext();
        sX = (TextView) findViewById(R.id.sourceX);
        sY = (TextView) findViewById(R.id.sourceY);
        dX = (TextView) findViewById(R.id.destinationX);
        dY = (TextView) findViewById(R.id.destinationY);
        chosenAlg = (TextView)findViewById(R.id.chosenAlg);

        sX.setText(PreferenceManagerHelper.getStartLong(context));
        sY.setText(PreferenceManagerHelper.getStartLat(context));
        dX.setText(PreferenceManagerHelper.getEndLong(context));
        dY.setText(PreferenceManagerHelper.getEndLat(context));

        rbtnReal = (Button) findViewById(R.id.realButton);
        rbtnMov = (Button) findViewById(R.id.movAvgButton);
        rbtnSmo = (Button) findViewById(R.id.smoothButton);
        idAlgNumb = PreferenceManagerHelper.getAlgNumber(context);

        if(idAlgNumb == 1){
            chosenAlg.setText("Chosen algorithm is real points");
        }else if(idAlgNumb == 2){
            chosenAlg.setText("Chosen algorithm is smooth algorithm");
        }else{
            chosenAlg.setText("Chosen algorithm is move avg");
        }

        addListenerToButton();

    }

    public void addListenerToButton(){
        context = getApplicationContext();
        idAlgNumb = PreferenceManagerHelper.getAlgNumber(context);
        chosenAlg = (TextView)findViewById(R.id.chosenAlg);

        rbtnReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idAlgNumb = 1;
                chosenAlg.setText("Chosen algorithm is real points");
            }
        });
        rbtnSmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idAlgNumb = 2;
                chosenAlg.setText("Chosen algorithm is smooth algorithm");
            }
        });
        rbtnMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idAlgNumb = 3;
                chosenAlg.setText("Chosen algorithm is move avg");
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rbtnReal = (Button) findViewById(R.id.realButton);
        rbtnMov = (Button) findViewById(R.id.movAvgButton);
        rbtnSmo = (Button) findViewById(R.id.smoothButton);

        String sorX,sorY,desX,desY;
        sorX = sX.getText().toString();
        sorY = sY.getText().toString();
        desX = dX.getText().toString();
        desY = dY.getText().toString();
        PreferenceManagerHelper.setStartEndPoints(sorX,sorY,desX,desY, idAlgNumb, context);

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

