package com.example.uzair.keepyournotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Uzair on 6/11/2017.
 */
public class Mydatabase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "notes.db";
    public static final String TABLE_NAME = "myTable";
    public static final String COL_N_TITLE = "notesTitle";
    public static final String COL_N_DATE = "notesDate";
    public static final String COL_N_ID = "id";
    public static final String COL_N_NOTE = "notesBody";

    private static Context context;

   // private SQLiteDatabase  db;


    public static Mydatabase getInstance()
    {
        return new Mydatabase(context,null,null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Query = "create table "+TABLE_NAME+ " ( "+ COL_N_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COL_N_TITLE + " TEXT ,"+ COL_N_DATE +" TEXT ,"+ COL_N_NOTE +" TEXT );";

        Log.d("mymessage","   "+Query);

        db.execSQL(Query);

        Log.d("mymessage","this is on create");

    }

    public Mydatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, 1);

        this.context = context;
        Log.d("mymessage","this is constructor");
      //  db = getWritableDatabase();

    }

    public void addNotes(NotesModel notesModel)
    {
        ContentValues myValues = new ContentValues();
        SQLiteDatabase  db3 = getWritableDatabase();
        myValues.put(COL_N_TITLE,notesModel.getName());
        myValues.put(COL_N_DATE,notesModel.getDate());
        myValues.put(COL_N_NOTE,notesModel.getBody());

        db3.insert(TABLE_NAME,null,myValues);
        db3.close();
        Log.d("mymessage","this is add product");


    }
    public void updateNotes(NotesModel notesModel)
    {
        ContentValues myValues = new ContentValues();
        SQLiteDatabase  db3 = getWritableDatabase();
        myValues.put(COL_N_TITLE,notesModel.getName());
        myValues.put(COL_N_DATE,notesModel.getDate());
        myValues.put(COL_N_NOTE,notesModel.getBody());

        db3.update(TABLE_NAME,myValues,COL_N_ID,new String[] {notesModel.getId()} );
        db3.close();
        Log.d("mymessage","this is add product");


    }
    public void deleteProduct (String Title)
    {
       SQLiteDatabase db2 = getWritableDatabase();
        db2.execSQL("DELETE FROM "+ TABLE_NAME+" WHERE "+COL_N_TITLE+ "=\""+Title+"\";");
        Log.d("mymessage","this is delete product");

        db2.close();
    }

    public ArrayList<NotesModel> getAllValues()
    {
       SQLiteDatabase db1 = getWritableDatabase();
        Cursor cur = db1.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE 1",null);
        cur.moveToFirst();

        ArrayList<NotesModel> arrayList = new ArrayList<>();
        while (!cur.isAfterLast())
        {
            NotesModel notesModel = new NotesModel();
            if (cur.getString(cur.getColumnIndex(COL_N_TITLE))!=null){

                notesModel.setName(cur.getString(cur.getColumnIndex(COL_N_TITLE)));
                notesModel.setDate(cur.getString(cur.getColumnIndex(COL_N_DATE)));
                notesModel.setBody(cur.getString(cur.getColumnIndex(COL_N_NOTE)));
                notesModel.setId(cur.getString(cur.getColumnIndex(COL_N_ID)));

            }
            arrayList.add(notesModel);
            cur.moveToNext();
        }
        Log.d("mymessage","this is Getting all values");

        return arrayList;
    }

    public NotesModel getValue(String id)
    {
        SQLiteDatabase db1 = getWritableDatabase();
        Cursor cur = db1.rawQuery(String.format("SELECT * FROM %s WHERE %s = ?", TABLE_NAME, COL_N_ID),new String []{id});
        cur.moveToFirst();

        NotesModel notesModel = new NotesModel();
        if (cur.getString(cur.getColumnIndex(COL_N_TITLE))!=null){

            notesModel.setName(cur.getString(cur.getColumnIndex(COL_N_TITLE)));
            notesModel.setDate(cur.getString(cur.getColumnIndex(COL_N_DATE)));
            notesModel.setBody(cur.getString(cur.getColumnIndex(COL_N_NOTE)));
            notesModel.setId(cur.getString(cur.getColumnIndex(COL_N_ID)));

        }


        Log.d("mymessage","this is Getting all values");

        return notesModel;
    }
}
