package acasoteam.pakistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.List;

import acasoteam.pakistapp.database.DBHelper;
import acasoteam.pakistapp.entity.Paki;

/**
 * Created by andre on 15/01/2017.
 */
public class PakiDao {

    public Paki goToNearest(LatLng latLng, Context context) {

        DBHelper myHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = myHelper.getWritableDatabase();
        Paki best = null;
        double bestDist = Double.POSITIVE_INFINITY;

        try {
            List<Paki> pakis = myHelper.selectPakis(db);

            System.out.println("Num paki " + pakis.size());
            for(Paki p : pakis){

                double dist = distance(p.getLat(),p.getLon(), latLng.latitude,latLng.longitude,"K");
                System.out.println("Paki dist: " + p.getAddress() + " dist " + dist);

                if(dist<bestDist){
                    best = p;
                    bestDist =dist;

                }
            }

            System.out.println("Paki best: " + best.getAddress() + " dist " + bestDist);

            return best;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist*1000);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


}
