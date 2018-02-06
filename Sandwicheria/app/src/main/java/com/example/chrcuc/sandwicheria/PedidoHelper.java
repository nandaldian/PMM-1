package com.example.chrcuc.sandwicheria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chrcuc on 6/02/18.
 */

public class PedidoHelper extends SQLiteOpenHelper {

    private static final  String DATABASE_CREATE_PEDIDO = "create table pedidos (id autoincrement primary key,extras INTEGER,sandwich TEXT," +
            "cantidad INTEGER not null,precio INTEGER not null,envio TEXT)";

    public PedidoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
