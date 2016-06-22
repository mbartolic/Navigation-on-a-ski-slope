package hr.foi.air.evoski.MainClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;
import hr.foi.air.evoski.R;
import hr.foi.air.evoski.core.PreferenceManagerHelper;

/**
 * Created by Marko1 on 13.6.2016..
 */
public class Options extends Activity{
    TextView sX, sY,dX,dY;
    Context context;
    RadioButton rbtnReal,rbtnMov,rbtnSmo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        context = getApplicationContext();
        sX = (TextView) findViewById(R.id.sourceX);
        sY = (TextView) findViewById(R.id.sourceY);
        dX = (TextView) findViewById(R.id.txtDestX);
        dY = (TextView) findViewById(R.id.txtDestY);

        sX.setText(PreferenceManagerHelper.getStartLong(context));
        sY.setText(PreferenceManagerHelper.getStartLat(context));
        dX.setText(PreferenceManagerHelper.getEndLong(context));
        dY.setText(PreferenceManagerHelper.getEndLat(context));

        rbtnReal = (RadioButton) findViewById(R.id.realButton);
        rbtnMov = (RadioButton) findViewById(R.id.movAvgButton);
        rbtnSmo = (RadioButton) findViewById(R.id.smoothButton);

        int idAlgNumb = PreferenceManagerHelper.getAlgNumber(context);

        rbtnReal.setChecked(idAlgNumb == 1);
        rbtnSmo.setChecked(idAlgNumb == 2);
        rbtnMov.setChecked(idAlgNumb == 3);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rbtnReal = (RadioButton) findViewById(R.id.realButton);
        rbtnMov = (RadioButton) findViewById(R.id.movAvgButton);
        rbtnSmo = (RadioButton) findViewById(R.id.smoothButton);
        int algID = rbtnSmo.isChecked() ? 2 : rbtnMov.isChecked() ? 3 : 1;

        String sorX,sorY,desX,desY;
        sorX = sX.getText().toString();
        sorY = sY.getText().toString();
        desX = dX.getText().toString();
        desY = dY.getText().toString();
        PreferenceManagerHelper.setStartEndPoints(sorX,sorY,desX,desY, algID, context);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("alg",algID);
        intent.putExtra("sX",sorX);
        intent.putExtra("sY",sorY);
        intent.putExtra("dX",desX);
        intent.putExtra("dY",desY);
        startActivity(intent);
        finish();
    }
}

