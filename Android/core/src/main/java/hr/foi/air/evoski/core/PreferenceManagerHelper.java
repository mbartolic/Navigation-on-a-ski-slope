package hr.foi.air.evoski.core;

import android.content.Context;

public class PreferenceManagerHelper {

    public static void setStartEndPoints(String startLong, String startLat, String endLong, String endLat, int algNumber, Context context)
    {
        android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString("start_long", startLong)
                .putString("start_lat", startLat)
                .putString("end_long", endLong)
                .putString("end_lat", endLat)
                .putInt("alg_number", algNumber)
                .commit();
    }

    public static String getStartLong(Context context)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getString("start_long", "");
    }
    public static String getStartLat(Context context)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getString("start_lat", "");
    }
    public static String getEndLong(Context context)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getString("end_long", "");
    }
    public static String getEndLat(Context context)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getString("end_lat", "");
    }
    public static int getAlgNumber(Context context)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getInt("alg_number", 1);
    }

}