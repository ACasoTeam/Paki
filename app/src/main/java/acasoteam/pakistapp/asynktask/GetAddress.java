package acasoteam.pakistapp.asynktask;

/**
 * Created by andre on 04/12/2016.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Andrea22 on 15/06/2016.
 */
public class GetAddress extends AsyncTask<Void, Void, Integer>  {

    private ProgressDialog progressDialog;

    protected Integer doInBackground(Void... params) {
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=45.0773587,7.6805511&sensor=true");
            // making connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            // Reading data's from url
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String out="";
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                out+=output;
            }
            // Converting Json formatted string into JSON object
            JSONObject json = new JSONObject(out);
            JSONArray results=json.getJSONArray("results");
            JSONObject rec = results.getJSONObject(0);
            JSONArray address_components=rec.getJSONArray("address_components");
            for(int i=0;i<address_components.length();i++){
                JSONObject rec1 = address_components.getJSONObject(i);
                //trace(rec1.getString("long_name"));
                JSONArray types=rec1.getJSONArray("types");
                String comp=types.getString(0);

                if(comp.equals("locality")){
                    Log.v("json","city ————-"+rec1.getString("long_name"));
                }
                else if(comp.equals("country")){
                    Log.v("json","country ———-"+rec1.getString("long_name"));
                }
            }
            String formatted_address = rec.getString("formatted_address");
            Log.v("json","formatted_address ———-"+formatted_address);


            conn.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    protected void onPreExecute() {
        Log.d("test debug", "onPreExecute");

    }

    @Override
    protected void onPostExecute(Integer result) {

        // TODO: check this.exception
        // TODO: do something with the feed

    }

}
