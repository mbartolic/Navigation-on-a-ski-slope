package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DisplayImage extends AppCompatActivity {
    public ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.picture);
        Double angle = null;
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if(bundle == null) {
                angle= 0.0;
            } else {
                angle=getIntent().getExtras().getDouble("EXTRA_ANGLE");
            }
        }
        imageview = (ImageView)findViewById(R.id.imageView2);
        setImage(angle);
    }

    public void setImage(double angle){
        if(angle >= 0 && angle <= 60){
            imageview.setImageResource(R.drawable.southeast);
        }else if(angle > 60 && angle < 120){
            imageview.setImageResource(R.drawable.east);
        }else if(angle >= 120 && angle <= 180){
            imageview.setImageResource(R.drawable.northeast);
        }else if(angle > 180 && angle < 240){
            imageview.setImageResource(R.drawable.northwest);
        }else if(angle >= 240 && angle <= 300){
            imageview.setImageResource(R.drawable.west);
        }else if(angle > 300 && angle <= 360){
            imageview.setImageResource(R.drawable.southwest);
        }

    }
}
