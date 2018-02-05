package com.example.chrcuc.sandwicheria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 05/02/2018.
 */

public class PedidoHelper  extends SQLiteOpenHelper {

    private static final  String DATABASE_CREATE_PEDIDO = "create table pedidos (id autoincrement primary key,extras INTEGER,sandwich TEXT," +
            "cantidad INTEGER not null,precio FLOAT not null,envio TEXT)";

    public PedidoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_PEDIDO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos");
                sqLiteDatabase.execSQL(DATABASE_CREATE_PEDIDO);
    }
}
