package acasoteam.pakistapp.asynktask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andre on 18/12/2016.
 */

public class GetFeedback extends AsyncTask<String, Void, Void> {

    private Exception exception;

    String res;


    BufferedReader reader = null;

    Context context;

    public GetFeedback(Context activity) {
        this.context = activity;

    }

    protected Void doInBackground(String... urls) {
        try {
            HttpURLConnection urlConnection = null;
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty( "charset", "utf-8");

            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                Log.v("GetJson","return null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                Log.v("GetJson","buffer.length() == 0");
                return null;
            }
            res = buffer.toString();

            //return  buffer.toString();


        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            Log.d("test debug", "eccez:" + e.getMessage());

            //return null;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        // TODO: check this.exception
        // TODO: do something with the feed

        CharSequence text = "";

        if (res.trim().equals("0") || res.trim().equals("-1")){
            text = "Si è verificato qualche problema";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            try {
                JSONArray feedbacks = new JSONArray(res);
                JSONObject feedback;
                for (int i = 0; i < feedbacks.length(); i++) {
                    feedback = feedbacks.getJSONObject(i);

                    //todo: inserire le informazioni sul div...
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }
}
