package com.example.chrcuc.sandwicheria;

/**
 * Created by chrcuc on 2/02/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper {
    private Context mCtx = null;
    private DataBaseHelperInternal mDbHelper = null;
    private SQLiteDatabase mDb = null;
    private static final String DATABASE_NAME = "DBSandwich";
    private static final int DATABASE_VERSION = 3;
    // tabla y campos
    private static final String DATABASE_TABLE_CLIENTES = "Clientes";
    private static final String DATABASE_TABLE_PEDIDOS = "Pedidos";
    public static final String SL_ID_CLIENTES = "id";
    public static final String SL_USER= "user";
    public static final String SL_PASSWORD = "password";

    public static final String SL_ENVIO= "envio";
    public static final String SL_CANTIDAD= "cantidad";
    public static final String SL_PRICE= "price";
    public static final String SL_ID_FOOD = "sandwich";
    public static final  String SL_EXTRAS = "Extras";


    // SQL de creación de la tabla
    public static final  String DATABASE_CREATE_PEDIDO = "CREATE TABLE pedidos (extras TEXT,sandwich TEXT," +
            "cantidad INTEGER not null,precio INTEGER not null,envio TEXT)";
    private static final String DATABASE_CREATE_CLIENTES =
            "create table "+ DATABASE_TABLE_CLIENTES +" ("+SL_ID_CLIENTES+" integer primary key, "+SL_USER+" text not null, "+SL_PASSWORD+" text not null)";
    //constructor
    public DataBaseHelper(Context ctx) {
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
            db.execSQL(DATABASE_CREATE_PEDIDO);

        }

        private void deleteTablesClientes(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_PEDIDO);
        }
    }

    public DataBaseHelper open()  {
        mDbHelper = new DataBaseHelperInternal(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //obtener todos los elementos
    public Cursor getItems() {
        return mDb.query(DATABASE_TABLE_PEDIDOS, new String[] {SL_EXTRAS, SL_ID_FOOD, SL_CANTIDAD,SL_PRICE,SL_ENVIO}, null, null, null, null,null);
    }


    //crear elemento
    public long insertItem(String extras, String food, int  cantidad, float importance,String envio){
        ContentValues initialValues = new ContentValues();
        initialValues.put(SL_EXTRAS, extras);
        initialValues.put(SL_ID_FOOD, food);
        initialValues.put(SL_CANTIDAD, cantidad);
        initialValues.put(SL_PRICE, importance);
        initialValues.put(SL_ENVIO, envio);

        return mDb.insert(DATABASE_TABLE_PEDIDOS, null, initialValues);
    }

    //borrar
    public int delete(int mLastRowSelected) {
        return mDb.delete(DATABASE_TABLE_PEDIDOS, SL_ID_FOOD + "=?", new String[]{ Integer.toString(mLastRowSelected)});
    }

    //actualiza
    public int updateItem(String extras, String food, int  cantidad, float importance,String envio) {
        ContentValues cv = new ContentValues();
        cv.put(SL_EXTRAS, extras);
        cv.put(SL_ID_FOOD, food);
        cv.put(SL_CANTIDAD, cantidad);
        cv.put(SL_PRICE, importance);
        cv.put(SL_ENVIO, envio);

        return mDb.update(DATABASE_TABLE_PEDIDOS, cv, SL_ID_FOOD + "=?", new String[]{Integer.toString(cantidad)});
    }


}