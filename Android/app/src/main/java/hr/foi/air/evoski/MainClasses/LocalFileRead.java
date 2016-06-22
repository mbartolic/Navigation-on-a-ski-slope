package hr.foi.air.evoski.MainClasses;
import android.app.Activity;
import android.os.Environment;

import hr.foi.air.evoski.core.MyTrackPoints;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marko1 on 13.6.2016..
 */
public class LocalFileRead extends Activity {

    public ArrayList<MyTrackPoints> readFile(){

        ArrayList<MyTrackPoints> myTrackPointses = new ArrayList<>();
        String response = null;
        String[] separated;
        try {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + "TrackRoute.txt";
            BufferedReader bufferedReader = null;
            StringBuffer output = new StringBuffer();
            bufferedReader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line + ";");
            }
                response = output.toString();
                separated = response.split(";");
                MyTrackPoints myTrP = new MyTrackPoints();
                int j = 0;
                for (int i = 0; i < separated.length; i++) {
                    if (i % 3 == 0) {
                        myTrP.y = Double.parseDouble(separated[i]);
                        j++;
                    } else if (i % 3 == 1) {
                        myTrP.x = Double.parseDouble(separated[i]);
                        j++;
                    } else if (i % 3 == 2) {
                        myTrP.turn = Integer.parseInt(separated[i]);
                        j++;
                    }
                    if (j == 3) {
                        j = 0;
                        myTrackPointses.add(myTrP);
                        myTrP = new MyTrackPoints();
                    }
                }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myTrackPointses;
    }

}
