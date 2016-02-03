package com.example.rene.navigation_on_a_ski_slope;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class DisplayImage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.picture);
    }
    String path;

    /**
     * Shows appropriate arrow (image) depending on the entering angle when approaching junction.
     * @param angle
     */
    public void setImage(double angle){
        if(angle >= 0 && angle <= 60){
            path = "@mipmap-xhdpi/southeast";
        }else if(angle > 60 && angle < 120){
            path = "@mipmap-xhdpi/east";
        }else if(angle >= 120 && angle <= 180){
            path = "@mipmap-xhdpi/northeast";
        }else if(angle > 180 && angle < 240){
            path = "@mipmap-xhdpi/northwest";
        }else if(angle >= 240 && angle <= 300){
            path = "@mipmap-xhdpi/west";
        }else if(angle > 300 && angle <= 360){
            path = "@mipmap-xhdpi/southwest";
        }
        addImage(path);
    }
 ImageView imageView;
        private void addImage(String path){
        int imageResource = getResources().getIdentifier(path, null, getPackageName());
        imageView = (ImageView)findViewById(R.id.imageView2);
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);
    }

}
