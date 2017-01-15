package acasoteam.pakistapp.asynktask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andre on 18/12/2016.
 */

public class SendReport extends AsyncTask<String, Void, String> {

    private Exception exception;



    BufferedReader reader = null;


    protected String doInBackground(String... urls) {
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
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");


            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                Log.v("GetJson","buffer.length() == 0");
                return null;
            }
            //forecastJsonStr = buffer.toString();

            Log.v("GetJson",buffer.toString());
            return  buffer.toString();





/*
            try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                Log.d("test debug", "postData:" + postData);
                Log.d("test debug", "postDataLength:" + postDataLength);
                Log.d("test debug", "urls[1]:" + urls[1]);
                wr.write( postData );
                wr.flush();
                wr.close();
            }
*/

            /*InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            Log.d("test debug", "data:" + data);

            String res="";
            while (data != -1) {
                char current = (char) data;
                res+=current;
                data = isw.read();
            }
            return res;*/

        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
            Log.d("test debug", "eccez:" + e.getMessage());

            return null;
        }
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
