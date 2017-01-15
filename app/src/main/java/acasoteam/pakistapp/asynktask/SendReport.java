package acasoteam.pakistapp.asynktask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import acasoteam.pakistapp.MapsActivity;

/**
 * Created by andre on 18/12/2016.
 */

public class SendReport extends AsyncTask<String, Void, Void> {


    BufferedReader reader = null;
    int res;

    Context context;

    public SendReport(Context activity) {
        this.context = activity;

    }

    @Override
    protected Void doInBackground(String... urls) {
        try {
            HttpURLConnection urlConnection = null;
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty( "charset", "utf-8");
            //urlConnection.setUseCaches( false );


            urlConnection.connect();

            Log.v("GetJson","dopo il connect");


            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                Log.v("GetJson","return null");
                res = 0;
            } else {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    Log.v("GetJson","buffer.length() == 0");
                    res = 0;
                } else {
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    Log.v("GetJson","buffer = "+buffer.toString());
                    res = Integer.parseInt(buffer.toString());
                }



                //forecastJsonStr = buffer.toString();


            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test debug", "eccez:" + e.getMessage());

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        // TODO: check this.exception
        // TODO: do something with the feed

        CharSequence text = "";

        if (res == 0){
            text = "Errore nell'invio del Report";
        } else {
            text = "Report inviato correttamente";
        }

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


    }
}
