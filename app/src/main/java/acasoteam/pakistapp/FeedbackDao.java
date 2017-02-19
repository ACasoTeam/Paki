package acasoteam.pakistapp;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import acasoteam.pakistapp.asynktask.GetFeedback;
import acasoteam.pakistapp.asynktask.SendReport;

/**
 * Created by andre on 19/02/2017.
 */
public class FeedbackDao {

    public boolean getFeedback (int idpaki, Context context){

        String u = "https://acaso-pakistapp.rhcloud.com/PakiOperation?action=pakiFeedback&idpaki="+idpaki;
        try {
            new GetFeedback(context).execute(u);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }
}