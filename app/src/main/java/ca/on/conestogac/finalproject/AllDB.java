package ca.on.conestogac.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yumil on 2017-12-28.
 */

public class AllDB {
    public static final String DB_NAME = "allDB.db";
    public static final int DB_VERSION = 1;
    public static final String NOTE_TABLE = "note";
    public static final String NOTE_LINE = "line";
    public static final int NOTE_LINE_COL = 0;
    public static final String CREATE_NOTE_TABLE =
            "CREATE TABLE " + NOTE_TABLE + " (" +
                    NOTE_LINE + " TEXT NOT NULL);";
    public static final String DROP_NOTE_TABLE =
            "DROP TABLE IF EXISTS " + NOTE_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_NOTE_TABLE);
            db.execSQL("INSERT INTO note VALUES ('Hello')");
            db.execSQL("INSERT INTO note VALUES ('LOL')");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("Note", "Upgrading db from version " +
                    oldVersion + "to " + newVersion);
            db.execSQL(AllDB.DROP_NOTE_TABLE);
            onCreate(db);
        }
    }
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    //constructor
    public AllDB(Context context){
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }
    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }
    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }
    private void closeDB(){
        if (db != null)
            db.close();
    }
    public ArrayList<Note> getNotes(){
        this.openReadableDB();
        Cursor cursor = db.query(NOTE_TABLE, null, null, null, null, null, null);
        ArrayList<Note> notes = new ArrayList<Note>();
        while (cursor.moveToNext()){
            notes.add(getNoteFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return notes;
    }
    private static Note getNoteFromCursor(Cursor cursor){
        if (cursor == null || cursor.getCount() == 0){
            return null;
        } else {
            try {
                Note note = new Note(
                        cursor.getString(NOTE_LINE_COL));
                return note;
            } catch (Exception e) {
                return null;
            }
        }
    }
    public long insertNote(Note note){
        ContentValues cv = new ContentValues();
        cv.put(NOTE_LINE, note.getLine());
        this.openWriteableDB();
        long rowID = db.insert(NOTE_TABLE, null, cv);
        this.closeDB();
        return rowID;
    }
    public int deleteAllNotes(){
        this.openWriteableDB();
        int rowCount = db.delete(NOTE_TABLE, null, null);
        this.closeDB();
        return rowCount;
    }
}
