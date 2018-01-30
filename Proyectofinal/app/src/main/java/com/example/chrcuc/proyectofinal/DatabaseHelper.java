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
    private DataBaseHelperInternal mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME = "Clientes";
    private static final int DATABASE_VERSION = 3;
    // tabla y campos
    private static final String DATABASE_TABLE_TODOLIST = "Clientes";
    public static final String SL_ID = "user";
    public static final String SL_ITEM = "password";


    // SQL de creación de la tabla
    private static final String DATABASE_CREATE_TODOLIST =
            "create table "+ DATABASE_TABLE_TODOLIST +" ("+SL_ID+" integer primary key, "+SL_ITEM+" text not null)";
    //constructor
    public DatabaseHelper(Context ctx) {
        this.mCtx = ctx;
    }
    //clase privada para control de la SQLite
    private static class DataBaseHelperInternal extends SQLiteOpenHelper {
        public DataBaseHelperInternal(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);		}

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTables(db);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            deleteTables(db);
            createTables(db);
        }
        private void createTables(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_TODOLIST);
        }

        private void deleteTables(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TODOLIST);
        }
    }

    public DatabaseHelper open()  {
        mDbHelper = new DataBaseHelperInternal(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //obtener todos los elementos
    public Cursor getItems() {
        return mDb.query(DATABASE_TABLE_TODOLIST, new String[] {SL_ID, SL_ITEM }, null, null, null, null,null);
    }


    //crear elemento
    public long insertItem(String password, String user){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_ITEM, password);
        initialValues.put(SL_ID,user);
        return mDb.insert(DATABASE_TABLE_TODOLIST, null, initialValues);
    }

    //borrar
    public int delete(int mLastRowSelected) {
        return mDb.delete(DATABASE_TABLE_TODOLIST, SL_ID + "=?", new String[]{ Integer.toString(mLastRowSelected)});
    }

    //obtener elemento
    public Cursor getItem(int itemId){
        return mDb.rawQuery(" select "+ SL_ITEM+","+ SL_ID + " from " + DATABASE_TABLE_TODOLIST  + " where " + SL_ID + "=?",new String[]{Integer.toString(itemId)});
    }

    //actualiza
    public int updateItem(int ident, String item, String place,
                          String description, int importance) {
        ContentValues cv = new ContentValues();
        cv.put(SL_ITEM, item);
        return mDb.update(DATABASE_TABLE_TODOLIST, cv, SL_ID + "=?", new String[]{Integer.toString(ident)});
    }


}