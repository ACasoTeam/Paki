package acasoteam.pakistapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    public void createDB(SQLiteDatabase db, String res) throws JSONException {

        db.beginTransaction();
        resetDatabase();

        JSONObject paki = new JSONObject(res);
        ContentValues values = new ContentValues();
        //  JSONObject paki = null;
        JSONArray pakis = paki.getJSONArray("pakis");

        try {

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
}
