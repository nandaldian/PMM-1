package com.example.chrcuc.accesodatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chrcuc on 16/01/18.
 */

public class ClientesSQLiteHelper extends SQLiteOpenHelper {
    String cadSQL = "CREATE TABLE Clientes (codigo INTEGER, nombre TEXT, telefono TEXT)";
    public ClientesSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory almacen,int version){
        super(context,nombre,almacen,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(cadSQL);
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int vAnterior,int vPosterior){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Clientes");

        sqLiteDatabase.execSQL(cadSQL);
    }
}
