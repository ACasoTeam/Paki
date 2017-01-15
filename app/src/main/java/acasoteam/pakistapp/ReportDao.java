package acasoteam.pakistapp;

import com.google.android.gms.maps.model.LatLng;

import acasoteam.pakistapp.asynktask.SendReport;
import acasoteam.pakistapp.database.DBHelper;

/**
 * Created by andre on 15/01/2017.
 */
public class ReportDao {

    public boolean sendReport (LatLng latLng){

        String u = "http://acaso-pakistapp.rhcloud.com/PakiOperation?action=sendReport";
        try {
            new SendReport().execute(u);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}
