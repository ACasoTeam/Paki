package acasoteam.pakistapp;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import acasoteam.pakistapp.asynktask.SendReport;
import acasoteam.pakistapp.database.DBHelper;

/**
 * Created by andre on 15/01/2017.
 */
public class ReportDao {

    public boolean sendReport (long loginId, LatLng latLng, Context context){

        String u = "http://acaso-pakistapp.rhcloud.com/UserOperation?action=sendReport&lat="+latLng.latitude+"&lon="+latLng.longitude+"&loginId="+loginId;
        try {
            new SendReport(context).execute(u);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}