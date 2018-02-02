package com.example.chrcuc.proyectofinal;

/**
 * Created by usuario on 30/01/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper {


    private Context mCtx = null;
    private SQLiteDatabase mDb = null;
    private DataBaseInternal dataBaseInternal = null;
    private static final String DATABASE_NAME = "dbsandwicheria";
    private static final int DATABASE_VERSION = 3;


    // tabla y campos

    private static final String DATABASE_TABLE_TODOLIST = "Clientes";
    public static final String SL_ID = "user";
    public static final String SL_ITEM = "password";


    // SQL de creación de la tabla

    private static final String DATABASE_CREATE_TODOLIST =
            "create table "+ DATABASE_TABLE_TODOLIST +" ("+SL_ID+" text not null, "+SL_ITEM+" text not null)";

    //constructor

    public DatabaseHelper(Context ctx) {
        this.mCtx = ctx ;
    }
    public static class DataBaseInternal extends SQLiteOpenHelper{
        public DataBaseInternal (Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);


        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE_TODOLIST);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }
    public DatabaseHelper open(){
        dataBaseInternal = new DataBaseInternal(mCtx);
        mDb = dataBaseInternal.getWritableDatabase();
        return this;
    }
    public void close(){
        dataBaseInternal.close();
    }
    public long insert( String user, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user",user);
        contentValues.put("password",password);

        return mDb.insert("Clientes",null,contentValues);

    }
    public Cursor getItems(){
        return mDb.query(DATABASE_TABLE_TODOLIST,new String[]{SL_ID,SL_ITEM},null,null,null,null,null);
    }


}