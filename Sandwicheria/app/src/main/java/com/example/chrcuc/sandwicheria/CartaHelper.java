package com.example.chrcuc.sandwicheria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 05/02/2018.
 */

public class CartaHelper extends SQLiteOpenHelper {
    private String DATA_CARTA =  "CREATE TABLE carta (id INTEGER primary key,nombre INTEGER,ingredientes TEXT," +
            "precio INTEGER not null,foto INTEGER not null)";

    public CartaHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATA_CARTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS carta");
        sqLiteDatabase.execSQL(DATA_CARTA);
    }
    public void fillCarta (SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Bacon y Huevo','Bacon/Huevo','1','3') ");
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Cangrejo','Cangrejo/Mayonesa','2','3') ");
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Campiñones','Champiñones/Queso','3','4') ");
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Peperoni','Peperoni/Queso','4','2') ");
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Pollo','Pollo/Queso','5','5') ");
        sqLiteDatabase.execSQL("INSERT INTO carta (nombre, ingredientes,fotito,precio) VALUES ('Esparagos con queso','Esparragos/Queso','6','3') ");
    }
}