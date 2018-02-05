package com.example.chrcuc.sandwicheria;

/**
 * Created by chrcuc on 2/02/18.
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
    private static final String DATABASE_NAME = "dbSandwicheria";
    private static final int DATABASE_VERSION = 3;
    // tabla y campos
    private static final String DATABASE_TABLE_CLIENTES = "Clientes";
    private static final String DATABASE_TABLE_PEDIDOS = "Pedidos";
    public static final String SL_ID_CLIENTES = "id";
    public static final String SL_USER= "user";
    public static final String SL_PASSWORD = "password";

    public static final String SL_NAME= "name";
    public static final String SL_CANTIDAD= "cantidad";
    public static final String SL_PRICE= "price";
    public static final String SL_ID_FOOD = "sandwich";
    public static final  String SL_EXTRAS = "Extras";


    // SQL de creación de la tabla
    private static final String DATABASE_CREATE_CLIENTES =
            "create table "+ DATABASE_TABLE_CLIENTES +" ("+SL_ID_CLIENTES+" integer primary key, "+SL_USER+" text not null, "+SL_PASSWORD+" text not null)";
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
            deleteTablesClientes(db);
            createTables(db);
        }
        private void createTables(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_CLIENTES);

        }

        private void deleteTablesClientes(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CLIENTES);
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
        return mDb.query(DATABASE_TABLE_CLIENTES, new String[] {SL_ID_CLIENTES, SL_USER, SL_PASSWORD}, null, null, null, null,null);
    }


    //crear elemento
    public long insertItem(String item, String place, String description, int importance){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_USER, item);
        initialValues.put(SL_PASSWORD, place);
        return mDb.insert(DATABASE_TABLE_CLIENTES, null, initialValues);
    }

    //borrar
    public int delete(int mLastRowSelected) {
        return mDb.delete(DATABASE_TABLE_CLIENTES, SL_ID_CLIENTES + "=?", new String[]{ Integer.toString(mLastRowSelected)});
    }

    //obtener elemento
    public Cursor getItem(int itemId){
        return mDb.rawQuery(" select "+ SL_USER+","+ SL_PASSWORD+" from " + DATABASE_TABLE_CLIENTES  + " where " + SL_ID_CLIENTES + "=?",new String[]{Integer.toString(itemId)});
    }

    //actualiza
    public int updateItem(int ident, String user, String password) {
        ContentValues cv = new ContentValues();
        cv.put(SL_USER, user);
        cv.put(SL_PASSWORD,password );

        return mDb.update(DATABASE_TABLE_CLIENTES, cv, SL_ID_CLIENTES + "=?", new String[]{Integer.toString(ident)});
    }


}