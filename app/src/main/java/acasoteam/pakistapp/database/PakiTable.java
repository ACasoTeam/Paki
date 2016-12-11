package acasoteam.pakistapp.database;

public class PakiTable {

    public static final String TABLE_NAME = "PAKI";
    public static final String COLUMN_IDPAKI = "idPaki";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";
    public static final String COLUMN_AVGRATE = "avgRate";
    public static final String COLUMN_NUMVOTE = "numVote";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_IDPAKI + " INTEGER PRIMARY KEY NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL, " +
                    COLUMN_LAT + " DOUBLE NOT NULL, " +
                    COLUMN_LON + " DOUBLE NOT NULL, " +
                    COLUMN_AVGRATE + " DOUBLE NOT NULL DEFAULT 0, " +
                    COLUMN_NUMVOTE + " INTEGER NOT NULL );";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
