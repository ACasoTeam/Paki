package acasoteam.pakistapp.asynktask;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by andre on 18/12/2016.
 */

public class GetJson extends AsyncTask<String, Void, String> {

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




            /*if(RetrieveLoginTask.msCookieManager.getCookieStore().getCookies().size() > 0)
            {
                //While joining the Cookies, use ',' or ';' as needed. Most of the server are using ';'
                urlConnection.setRequestProperty("Cookie",
                        TextUtils.join(";", RetrieveLoginTask.msCookieManager.getCookieStore().getCookies()));
            }*/
            /*String urlParameters  = urls[0];
            byte[] postData       = urlParameters.getBytes( Charset.forName("UTF-8") );

            int    postDataLength = postData.length;
            urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));

            Log.v("GetJson","prima del connect");

*/

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
