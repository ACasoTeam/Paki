package acasoteam.pakistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import acasoteam.pakistapp.entity.Paki;

public class DBHelper extends SQLiteOpenHelper {

    static DBHelper dbhelper = null;

    private static final String DATABASE_NAME = "Pakistapp";
    private static final int DATABASE_VERSION = 1;


    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(PakiTable.SQL_CREATE_TABLE);
    }

    public void resetDatabase(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(PakiTable.SQL_DROP_TABLE);
        db.execSQL(PakiTable.SQL_CREATE_TABLE);
    }

    public static DBHelper getInstance(Context context){
        if (dbhelper == null){
            dbhelper = new DBHelper(context);
        }
        return dbhelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //controllo della versione

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PakiTable.SQL_DROP_TABLE);

        onCreate(db);
    }


    public void createDB(SQLiteDatabase db, JSONArray pakis) throws JSONException {

        db.beginTransaction();
        resetDatabase();

        /*

        JSONObject paki = new JSONObject(res);

        //  JSONObject paki = null;
        JSONArray pakis = paki.getJSONArray("pakis");
*/
        try {
            ContentValues values = new ContentValues();
            JSONObject paki;
            for (int i = 0; i < pakis.length(); i++) {


                paki = pakis.getJSONObject(i);
                //values = new ContentValues();
                values.put(PakiTable.COLUMN_IDPAKI, paki.getString("idPaki"));
                values.put(PakiTable.COLUMN_NAME, paki.getString("name"));
                values.put(PakiTable.COLUMN_ADDRESS, paki.getString("address"));
                values.put(PakiTable.COLUMN_LAT, paki.getString("lat"));
                values.put(PakiTable.COLUMN_LON, paki.getString("lon"));
                values.put(PakiTable.COLUMN_AVGRATE, paki.getString("avgRate"));
                values.put(PakiTable.COLUMN_NUMVOTE, paki.getString("numVote"));


                db.insert(PakiTable.TABLE_NAME, null, values);
                Log.v("db log", "Paki eseguito");
            }
            db.setTransactionSuccessful();

        } catch (Exception e) {
            //Error in between database transaction
        } finally {
            db.endTransaction();
        }
    }

    public List<Paki> selectPakis(SQLiteDatabase db) throws JSONException {

        db.beginTransaction();

        /*

        JSONObject paki = new JSONObject(res);

        //  JSONObject paki = null;
        JSONArray pakis = paki.getJSONArray("pakis");
*/      List<Paki> pakis = new ArrayList<>();
        try {

            Cursor c = db.rawQuery("SELECT * FROM "+PakiTable.TABLE_NAME, null);
            Log.v("dbhelper","size cursor:" + c.getCount() );
            if (c.moveToFirst()) {

                Paki paki;
                do {

                    paki = new Paki(c.getInt(c.getColumnIndex("idPaki")),
                            c.getString(c.getColumnIndex("name")),
                            c.getString(c.getColumnIndex("address")),
                            c.getDouble(c.getColumnIndex("lat")),
                            c.getDouble(c.getColumnIndex("lon")),
                            c.getDouble(c.getColumnIndex("avgRate")),
                            c.getInt(c.getColumnIndex("numVote")));

                    pakis.add(paki);
                    Log.v("dbhelper","aggiunto paki in pakis!");

                } while (c.moveToNext());



            }

            db.setTransactionSuccessful();




        } catch (Exception e) {
            //Error in between database transaction
            e.printStackTrace();
            return null;
        } finally {
            db.endTransaction();
        }


        return pakis;
    }



}
